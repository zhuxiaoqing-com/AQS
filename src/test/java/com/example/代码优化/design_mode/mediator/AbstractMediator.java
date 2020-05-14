package com.example.代码优化.design_mode.mediator;

import com.example.代码优化.design_mode.mediator.module.Purchase;
import com.example.代码优化.design_mode.mediator.module.Sale;
import com.example.代码优化.design_mode.mediator.module.Stock;

public abstract class AbstractMediator {
    protected Purchase purchase;
    protected Sale sale;
    protected Stock stock;

    public AbstractMediator() {
        this.purchase = new Purchase(this);
        this.sale = new Sale(this);
        this.stock = new Stock(this);
    }

    /**
     * 中介者最重要的方法叫做事件方法，处理多个对象之间的关系
     *
     * @param str     str
     * @param objects objects
     */
    public abstract void execute(String str, Object... objects);
}

