package com.example.javase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DecimalToBit {
    private final static DecimalToBit instance = new DecimalToBit();

    public static DecimalToBit getInstance() {
        return instance;
    }


    public void toBinary(double number) {
        String numberStr = String.valueOf(number);
        String[] split = numberStr.split("\\.");

        int integrity = 0;
        int decimals = 0;
        int bit = 0;

        integrity = Integer.parseInt(split[0]);
        if (split.length > 1) {
            decimals = Integer.parseInt(split[1]);
            bit = (int) Math.pow(10, split[1].length());
        }
        List<Integer> integersBit = integrityToBinary(integrity);
        List<Integer> decimalsBit = decimalsToBinary(decimals, bit);
        String integersStr = integersBit.stream().map(String::valueOf).collect(Collectors.joining());
        integersStr = integersStr.isEmpty() ? "0" : integersStr;
        String decimalStr = decimalsBit.stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(String.join(".", integersStr, decimalStr));

    }

    public List<Integer> integrityToBinary(int integrity) {
        ArrayList<Integer> list = new ArrayList<>(32);
        while (integrity != 0) {
            list.add(integrity % 2);
            integrity = integrity / 2;
        }
        Collections.reverse(list);
        return list;
    }

    public List<Integer> decimalsToBinary(int decimals, int bit) {
        ArrayList<Integer> list = new ArrayList<>(100);
        while (decimals != 0) {
            decimals = decimals * 2;
            if (decimals - bit >= 0) {
                list.add(1);
                decimals = decimals - bit;
            } else {
                list.add(0);
            }
            if (list.size() >= 100) {
                break;
            }
        }
        return list;
    }

}
