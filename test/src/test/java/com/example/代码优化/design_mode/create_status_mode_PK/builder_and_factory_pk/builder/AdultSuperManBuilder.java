package com.example.代码优化.design_mode.create_status_mode_PK.builder_and_factory_pk.builder;

public class AdultSuperManBuilder extends Builder {
    @Override
    public SuperMan getSuperMan() {
        super.setBody("强壮的躯体");
        super.setSpecialTalent("会飞行");
        super.setSpecialSymbol("胸前带S标记");
        return super.superMan;
    }
}
