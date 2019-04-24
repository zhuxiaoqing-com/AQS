package com.example.design_mode.访问者模式.inf;

public class Role1 {
    public void accept(AbsActor actor){
        actor.act(this);
    }
}
