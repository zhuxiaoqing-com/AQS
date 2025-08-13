package com.room;

import com.pb.InputData;
import com.pb.PBEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameManager {
    private Map<Integer, Frame> frameMap = new HashMap<Integer, Frame>();
    private int currentFrame = 0;

    public void tick(Room room) {
        currentFrame++;
        onTick(room);
    }


    /**
     * 这里根据不同的游戏类型做不不同的操作; 按小飞机来说,因为客户端的实现,一帧只能收到一个输入;多余的只能抛弃
     * 像有些要搓客户端连招的格斗游戏,就应该客户端先预判,有个确定帧的世界,和逻辑帧的世界,服务器如果在一帧内收到两个Input,就将其都保存起来,一起输出给客户端;
     * <p>
     * 具体看逻辑,这里实现就只用最后一个
     */
    public Frame pushInput(InputData input) {
        Frame frame = frameMap.get(currentFrame);
        if (frame == null) {
            frame = new Frame(currentFrame);
            frameMap.put(currentFrame, frame);
        }
        List<InputData> inputs = frame.getInputMap().get(input.getId());
        if (inputs == null) {
            inputs = new ArrayList<InputData>();
            frame.getInputMap().put(input.getId(), inputs);
        }
        // 有数据了 就删除用最后一个
        if (inputs.size() > 1) {
            inputs.remove(0);
            return frame;
        }
        inputs.add(input);

        return frame;
    }


    public Frame getFrame(int currentFrame) {
        return frameMap.get(currentFrame);
    }

    public List<Frame> getFrames(int startIndex, int endIndex) {
        List<Frame> frames = new ArrayList<>();
        for (int i = startIndex; i <= endIndex; i++) {
            Frame frame = frameMap.get(i);
            if (frame == null) {
                continue;
            }
            frames.add(frame);
        }
        return frames;
    }


    public int getCurrentFrame() {
        return currentFrame;
    }


    private void onTick(Room room) {
        int frame = currentFrame - 1;
        int pre = frame - 1;
        Frame preData = frameMap.get(pre);
        if (preData == null) {
            return;
        }

        Frame frameData = frameMap.get(frame);
        if (frameData == null) {
            frameData = new Frame(frame);
            frameMap.put(frame, frameData);
        }

        for (Player player : room.playerMap.values()) {
            List<InputData> inputDataList = frameData.getInputMap().get(player.getPlayerId());
            // 本帧有输入
            if (inputDataList != null && !inputDataList.isEmpty()) {
                continue;
            }
            // 玩家在线继承上一帧， 玩家离线直接空输入
            if (!player.isOnline()) {
                continue;
            }
            // 上一帧也没输入
            List<InputData> preInputDataList = preData.getInputMap().get(player.getPlayerId());
            if (preInputDataList == null || preInputDataList.isEmpty()) {
                continue;
            }

            // 上一帧的输入
            InputData preInputData = preInputDataList.get(preInputDataList.size() - 1);

            InputData currInput = preInputData.toBuilder()
                    .setEvent(PBEvent.newBuilder().build()) //事件不继承
                    .build();
            if (inputDataList == null) {
                inputDataList = new ArrayList<>();
                frameData.getInputMap().put(player.getPlayerId(), inputDataList);
            }
            inputDataList.add(currInput);
        }

    }
}
