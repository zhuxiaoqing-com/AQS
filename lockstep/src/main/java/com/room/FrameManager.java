package com.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameManager {
    private Map<Integer, Frame> frameMap = new HashMap<Integer, Frame>();
    private int currentFrame = 0;

    public void tick() {
        currentFrame++;
    }

    public void pushInput(Input input) {
        Frame frame = frameMap.get(currentFrame);
        if(frame == null) {
            frame = new Frame(currentFrame);
            frameMap.put(currentFrame, frame);
        }
        List<Input> inputs = frame.getInputMap().get(input.getPlayerId());
        if(inputs == null) {
            inputs = new ArrayList<Input>();
            frame.getInputMap().put(input.getPlayerId(), inputs);
        }
        // 一帧传过来的操作过多 抛弃掉
        if (inputs.size() > 10) {
            return;
        }
        inputs.add(input);
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


}
