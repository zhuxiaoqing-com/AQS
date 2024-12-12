package com.coin.bnb;

import com.coin.bnb.query.BNBQuery;
import com.coin.bnb.random.BNBWalletGenerator;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class Test01 {

    @Test
    public void test01() throws Exception {
        Map<String, String> bscAddress = BNBWalletGenerator.createBscAddress();
        String address = bscAddress.get("address");
        BigDecimal bigDecimal = BNBQuery.queryBNBBalance(address);
        System.out.println(bigDecimal);
    }
}
