package com.example.common.stream.xmlupdate;

import com.example.common.stream.readxmlname.ReadXmlName;
import org.junit.Test;

public class Test01 {
    String path = "D:\\svn_work_new\\client\\tools\\MessageGenerator\\msg";
    @Test
    public void test01() {
        XMLUpdate xmlUpdate = new XMLUpdate();
        xmlUpdate.start(path);
    }
}
