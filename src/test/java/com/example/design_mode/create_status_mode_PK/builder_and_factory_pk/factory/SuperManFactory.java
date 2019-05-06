package com.example.design_mode.create_status_mode_PK.builder_and_factory_pk.factory;

/**
 * 工厂模式关注的是一个产品整体，生产出的产品应该具有相似的功能的架构
 *
 * 也就是说只管 new 不管内部组装
 */
public class SuperManFactory {
    public static ISuperMan createSUperMan(String type) {
        // 根据输入参数产生不同的超人
        if (type.equalsIgnoreCase("adult")) {
            // 生产成人超人
            return new AdultSuperMan();
        } else if (type.equalsIgnoreCase("child")) {
            // 生产未成年超人
            return new ChildSuperMan();
        } else {
            return null;
        }
    }
}
