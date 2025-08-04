package com.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Frame {
    int frameId;
    List<Input> input = new ArrayList<Input>();

    public Frame(int frameId) {
        this.frameId = frameId;
    }

    public int getFrameId() {
        return frameId;
    }

    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    public List<Input> getInput() {
        return input;
    }

    public void setInput(List<Input> input) {
        this.input = input;
    }
}
