package com.room;

import com.pb.InputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frame {
    int frameId;
    Map<Long, List<InputData>> inputMap = new HashMap<>();

    public Frame(int frameId) {
        this.frameId = frameId;
    }

    public int getFrameId() {
        return frameId;
    }

    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    public Map<Long, List<InputData>> getInputMap() {
        return inputMap;
    }

    public void setInputMap(Map<Long, List<InputData>> inputMap) {
        this.inputMap = inputMap;
    }
}
