package com.example.demo1;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.demo1.hah.Role;
import com.example.demo1.hah.UserResult;
import com.example.demo1.hah.UserResult2;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {

    @Test
    public void contextLoads() {

        String repStr = "{\"errorCode\":0,\"message\":{\"errorCode\":0,\"lastLoginOutTime\":1533209142442,\"level\":11," +
                "\"loginDays\":2,\"name\":\"内向的罗夫斯\",\"reCharge\":0,\"rid\":381949133783432,\"vipLevel\":0}}";
        String repStr2 = "{\"errorCode\":0,\"message\":\"zhangsan\"}";

        Gson gson = new Gson();
        UserResult2 fromJson = gson.fromJson(repStr2, UserResult2.class);
        System.out.println(fromJson);
       /* UserResult2 tUserResult22 = JSONObject.parseObject(repStr2, UserResult2.class);
        System.out.println(tUserResult22);*/

        UserResult<Role> tUserResult = JSONObject.parseObject(repStr, new TypeReference<UserResult<Role>>() {});
        System.out.println(tUserResult.getErrorCode());
        UserResult<Role> tUserResult2 = JSONObject.parseObject(repStr, UserResult.class);
        if (tUserResult != null) {
            System.out.println(tUserResult.getMessage() + "," + tUserResult.getErrorCode() + "," + tUserResult.getMessage().toString());
        }
        if (tUserResult2 != null) {
            System.out.println("2::::"+tUserResult.getMessage() + "," + tUserResult.getErrorCode() + "," + tUserResult.getMessage().toString());
        }
    }


}
