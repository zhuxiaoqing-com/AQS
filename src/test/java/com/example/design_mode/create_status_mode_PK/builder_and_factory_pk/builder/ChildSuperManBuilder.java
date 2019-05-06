package com.example.design_mode.create_status_mode_PK.builder_and_factory_pk.builder;


public class ChildSuperManBuilder extends Builder {
    @Override
    public SuperMan getSuperMan() {
        super.setBody("强壮的躯体");
        super.setSpecialTalent("刀枪不入");
        super.setSpecialSymbol("胸前带小S标记");
        return super.superMan;
    }
}
