package com.example.design_mode.mediator.module;

import com.example.design_mode.mediator.AbstractMediator;

import java.util.Random;

public class Sale extends AbstractColleague {
    public Sale(AbstractMediator _mediator) {
        super(_mediator);
    }

    /**
     * 销售IBM电脑
     *
     * @param number number
     */
    public void sellIBMComputer(int number) {
        super.mediator.execute("sale.sell", number);
        System.out.println("销售IBM电脑 " + number + " 台");
    }

    /**
     * 反馈销售情况，0~100之间变化，0代表根本没人买，100代表非常畅销，出一个卖一个
     *
     * @return
     */
    public int getSaleStatus() {
        Random random = new Random(System.currentTimeMillis());
        int saleStatus = random.nextInt(100);
        System.out.println("IBM电脑的销售情况为：" + saleStatus);
        return saleStatus;
    }

    /**
     * 折价处理
     */
    public void offSale() {
        super.mediator.execute("sale.offsell");
    }

}
