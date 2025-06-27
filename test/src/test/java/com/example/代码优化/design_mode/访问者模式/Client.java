package com.example.代码优化.design_mode.访问者模式;

import com.example.代码优化.design_mode.访问者模式.impl.KungFuRole;
import com.example.代码优化.design_mode.访问者模式.inf.AbsActor;

public class Client {
    public static void main(String[] args) {
        AbsActor absActor = new AbsActor();
        KungFuRole kungFuRole = new KungFuRole();
        kungFuRole.accept(absActor);
    }
}
