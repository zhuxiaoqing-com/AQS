package com.coin.bnb.random;

import com.google.common.collect.ImmutableList;
import org.bitcoinj.crypto.*;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import sun.security.provider.SecureRandom;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BNBWalletGenerator {

    public static void main(String[] args) throws Exception {
        Map<String, String> bscAddress = new BNBWalletGenerator().createBscAddress();
        System.out.println(bscAddress);
    }
    /**
     * path路径
     */
    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

    /**
     * 创建地址(离线)
     */

    public static Map<String, String> createBscAddress() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[DeterministicSeed.DEFAULT_SEED_ENTROPY_BITS / 8];
        secureRandom.engineNextBytes(entropy);
        //生成12位助记词
        List<String> str = MnemonicCode.INSTANCE.toMnemonic(entropy);
        /*str = Arrays.asList(
                "blade",
                "today",
                "duck",
                "useful",
                "snap",
                "quit",
                "dove",
                "school",
                "twelve",
                "clock",
                "execute",
                "lounge"
        );*/
        //使用助记词生成钱包种子
        byte[] seed = MnemonicCode.toSeed(str, "");
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        DeterministicKey deterministicKey = deterministicHierarchy
                .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
        byte[] bytes = deterministicKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        //通过公钥生成钱包地址
        String address = Keys.getAddress(keyPair.getPublicKey());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("address", "0x" + address);
        stringMap.put("privateKey", "0x" + keyPair.getPrivateKey().toString(16));
        stringMap.put("publicKey", keyPair.getPublicKey().toString(16));
        stringMap.put("mnemonic", str.toString());
        return stringMap;
    }
}
