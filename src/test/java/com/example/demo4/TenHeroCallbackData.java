package com.example.demo4;

/**
 *
 * @author yaoyiyao
 */
public class TenHeroCallbackData {
    /** 应用 ID */
    private String appId;
    /** 订单号 */
    private String orderNo;
    /** 游戏订单号 */
    private String productOrderNo;
    /** 订单金额(元) */
    private float amount;
    /** 订单成交金额(元) */
    private float dealAmount;
    /** 商品ID */
    private String orderGoodsId;
    /** 支付方式(1,支付宝;7,微信;8,苹果) */
    private int payType;
    /** 用户UID */
    private int uid;
    /** 渠道ID(非必传) */
    private String channelCode;
    /** 订单主题(非必传) */
    private String orderSubject;
    /** 支付时间 */
    private int payTime;
    /** 透传参数 */
    private String extrasParams;
    /** 签名串 */
    private String sign;

    /**
     * 是否有非法参数;
     *
     * @return true: 有非法参数
     */
    public boolean invalid() {
        return appId == null || orderNo == null || productOrderNo == null || amount <= 0 || dealAmount <= 0 ||
                orderGoodsId == null || uid == 0 || payTime == 0 || extrasParams == null || sign == null;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductOrderNo() {
        return productOrderNo;
    }

    public void setProductOrderNo(String productOrderNo) {
        this.productOrderNo = productOrderNo;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(float dealAmount) {
        this.dealAmount = dealAmount;
    }

    public String getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(String orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public void setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
    }

    public int getPayTime() {
        return payTime;
    }

    public void setPayTime(int payTime) {
        this.payTime = payTime;
    }

    public String getExtrasParams() {
        return extrasParams;
    }

    public void setExtrasParams(String extrasParams) {
        this.extrasParams = extrasParams;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "TenHeroCallbackData{" +
                "appId='" + appId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", productOrderNo='" + productOrderNo + '\'' +
                ", amount=" + amount +
                ", dealAmount=" + dealAmount +
                ", orderGoodsId='" + orderGoodsId + '\'' +
                ", payType=" + payType +
                ", uid=" + uid +
                ", channelCode='" + channelCode + '\'' +
                ", orderSubject='" + orderSubject + '\'' +
                ", payTime=" + payTime +
                ", extrasParams='" + extrasParams + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
