package com.example.design_mode.mediator.module;

import com.example.design_mode.mediator.AbstractMediator;

public class Purchase extends AbstractColleague {

    public Purchase(AbstractMediator _mediator) {
        super(_mediator);
    }

    /**
     * 采购IBM电脑
     *
     * @param number number
     */
    public void buyIBMComputer(int number) {
        super.mediator.execute("purchase.buy", number);
    }

    /**
     * 不在采购IBM电脑
     */
    public void refuseBuyIBM() {
        System.out.println("不在采购IBM电脑");
    }
}
