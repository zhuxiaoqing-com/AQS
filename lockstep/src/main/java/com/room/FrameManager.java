package com.room;

import java.util.HashMap;
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
        frame.getInput()
    }
}
