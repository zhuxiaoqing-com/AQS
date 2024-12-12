package com.coin.bnb;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BNBQuery {
   static Web3j web3j = web3j();
    public static Web3j web3j() {
        //return Web3j.build(new HttpService("wss://stream.binance.com:9443/ws/bnbbtc"));
        //return Web3j.build(new HttpService(""));
        return null;
    }
    /**
     * <b>功能描述：</b>查询BNB余额<br>
     * <b>修订记录：</b><br>
     */
    public static BigDecimal queryBNBBalance(String address) throws Exception {
        a();
        web3j =  Web3j.build(new HttpService(""));
        DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(web3j.ethBlockNumber().send().getBlockNumber());
        EthGetBalance balance = web3j.ethGetBalance(address, defaultBlockParameter).send();
        BigDecimal bigDecimal = new BigDecimal(balance.getBalance());
        BigInteger pow = BigInteger.valueOf(10L).pow(18);
        return bigDecimal.divide(new BigDecimal(pow), 4, RoundingMode.HALF_UP);
    }

    public static void a() {
       Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/YOURKEY"));
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                //Connected
                System.out.println(clientVersion.getWeb3ClientVersion());
            }
            else {
                System.out.println("error");
                //Show Error
            }
        }
        catch (Exception e) {
            //Show Error
            e.printStackTrace();
        }
    }

    /**
     * <b>功能描述：</b>查询BEP20余额<br>
     * <b>修订记录：</b><br>
     * <li>20240309&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;创建方法</li><br>
     */
    /*public BigDecimal queryBEP2EBBalance(String contractAddr, String address) throws Exception {
        try {
            BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
            TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, address);
            StaticGasProvider staticGasProvider = new StaticGasProvider(gasPrice, BigInteger.valueOf(4700000));
            BEP2E bep2e = BEP2E.load(contractAddr, web3j, transactionManager, staticGasProvider);
            BigInteger balance = bep2e.balanceOf(address).send();
            BigDecimal bigDecimal = new BigDecimal(balance);
            BigInteger pow = BigInteger.valueOf(10L).pow(18);
            return bigDecimal.divide(new BigDecimal(pow), 4, RoundingMode.HALF_UP);
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }*/
}
