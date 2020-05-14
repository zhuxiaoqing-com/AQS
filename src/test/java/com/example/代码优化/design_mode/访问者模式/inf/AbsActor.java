package com.example.代码优化.design_mode.访问者模式.inf;

import com.example.demo1.hah.Role;
import com.example.代码优化.design_mode.访问者模式.impl.KungFuRole;

public class AbsActor {
    public void act(Role1 role1) {
        System.out.println("role1");
    }

    public void act(KungFuRole kungFuRole) {
        System.out.println("kungFuRole");
    }
}
