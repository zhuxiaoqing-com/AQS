package com.youxi.genexcel.com;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AModel {
    public List<Integer> id;

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public void parseId(String value) {
        id = Arrays.stream(value.split(";")).map(Integer::parseInt).collect(Collectors.toList());
    }

}
