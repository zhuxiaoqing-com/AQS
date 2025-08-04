package com.room;

public class Input {
    Long playerId = 0L;
    int event;

    public Input(Long playerId, int event) {
        this.playerId = playerId;
        this.event = event;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }
}
