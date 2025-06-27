package com.coin.bnb;

import com.coin.bnb.query.BNBQuery;
import com.coin.bnb.random.BNBWalletGenerator;
import com.example.demo4.TestUtil;
import com.mysql.cj.util.TestUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class Test01 {

    @Test
    public void test01() throws Exception {
        TestUtil.testTime(()->BNBWalletGenerator.createBscAddress(), 100,"");
    }
}
