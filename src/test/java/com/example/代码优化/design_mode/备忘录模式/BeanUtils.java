package com.example.代码优化.design_mode.备忘录模式;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class BeanUtils {
    public static HashMap<String, Object> backupProp(Object bean) {
        try {
            // 获取bean描述
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor des : descriptors) {
                String name = des.getName();
                Method getter = des.getReadMethod();
                Object invoke = getter.invoke(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
