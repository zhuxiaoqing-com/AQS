package com.example.javase.interObj;

public class EmpTest {
    private Integer id;
    private Integer empLevel;
    private String mapingOrderLevel;
    private String empNo;
    private Integer orderNumLimit;

    // 私有化构造函数
    private EmpTest(Builder builder) {
        setId(builder.id);
        setEmpLevel(builder.empLevel);
        setMapingOrderLevel(builder.mapingOrderLevel);
        setEmpNo(builder.empNo);
        setOrderNumLimit(builder.orderNumLimit);
    }

    // 创建 Builder
    public static Builder newBuilder() {
        return new Builder();
    }

   public static final class Builder {
       private Integer id;
       private Integer empLevel;
       private String mapingOrderLevel;
       private String empNo;
       private Integer orderNumLimit;

       public Builder() {
       }

       public Builder id(Integer val) {
           id = val;
           return this;
       }

       public Builder empLevel(Integer val) {
           empLevel = val;
           return this;
       }

       public Builder mapingOrderLevel(String val) {
           mapingOrderLevel = val;
           return this;
       }

       public Builder empNo(String val) {
           empNo = val;
           return this;
       }

       public Builder orderNumLimit(Integer val) {
           orderNumLimit = val;
           return this;
       }

       public EmpTest build() {
           return new EmpTest(this);
       }
   }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpLevel() {
        return empLevel;
    }

    public void setEmpLevel(Integer empLevel) {
        this.empLevel = empLevel;
    }

    public String getMapingOrderLevel() {
        return mapingOrderLevel;
    }

    public void setMapingOrderLevel(String mapingOrderLevel) {
        this.mapingOrderLevel = mapingOrderLevel;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Integer getOrderNumLimit() {
        return orderNumLimit;
    }

    public void setOrderNumLimit(Integer orderNumLimit) {
        this.orderNumLimit = orderNumLimit;
    }
}
