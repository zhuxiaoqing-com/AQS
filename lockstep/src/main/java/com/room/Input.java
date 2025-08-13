package com.room;

import com.pb.C2S_InputMsg;

public class Input {
    Long playerId = 0L;
    C2S_InputMsg c2SInputMsg;

    public Input(Long playerId, C2S_InputMsg c2SInputMsg) {
        this.playerId = playerId;
        this.c2SInputMsg = c2SInputMsg;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }


}
