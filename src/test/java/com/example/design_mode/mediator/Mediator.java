package com.example.design_mode.mediator;

public class Mediator extends AbstractMediator {
    /**
     * 中介者最重要的方法叫做事件方法，处理多个对象之间的关系
     *
     * @param str     str
     * @param objects objects
     */
    @Override
    public void execute(String str, Object... objects) {
        switch (str) {
            case "purcjase.buy":
                // 采购电脑
                ;
            case "sale.sell":
                ;
            case "sale.offsell":
                ;
            case "sale.clear":
        }
    }

    /**
     * 采购电脑
     *
     * @param number number
     */
    private void buyComputer(int number) {
        int saleStatus = super.sale.getSaleStatus();
        if (saleStatus > 80) {// 销售情况良好
            System.out.println("采购IBM电脑：" + number + " 台");
        } else {// 销售情况不好
            int buyNumber = number / 2;
            System.out.println("采购IBM电脑：" + buyNumber + "台");
        }
    }

    /**
     * 销售电脑
     *
     * @param number number
     */
    private void sellComputer(int number) {
        if (super.stock.getStockNumber() < number) {
            super.purchase.buyIBMComputer(number);
        }
        super.stock.decrease(number);
    }

    /**
     * 折价销售电脑
     */
    private void offSell() {
        System.out.println("折价教授IBM电脑 " + stock.getStockNumber() + "台");
    }

    // 清仓处理
    private void clearStock() {
        // 要求清仓销售
        super.sale.offSale();
        // 要求采购人员不要采购
        super.purchase.refuseBuyIBM();
    }
}
