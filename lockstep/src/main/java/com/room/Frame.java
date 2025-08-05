package com.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frame {
    int frameId;
    Map<Long, List<Input>> inputMap = new HashMap<Long, List<Input>>();

    public Frame(int frameId) {
        this.frameId = frameId;
    }

    public int getFrameId() {
        return frameId;
    }

    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    public Map<Long, List<Input>> getInputMap() {
        return inputMap;
    }

    public void setInputMap(Map<Long, List<Input>> inputMap) {
        this.inputMap = inputMap;
    }
}
