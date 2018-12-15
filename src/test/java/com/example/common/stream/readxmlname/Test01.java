package com.example.common.stream.readxmlname;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

public class Test01 {
    String path = "D:\\svn_work_new\\client\\tools\\MessageGenerator\\msg";
    @Test
    public void test01() {
        ReadXmlName readXmlName = new ReadXmlName();
        readXmlName.start(path);
    }
}
