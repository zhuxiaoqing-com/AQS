package com.example;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.net.MalformedURLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class JMXClient {
    //java -cp GS_DIR/lib/jmxc.jar jmxc controlRole JMXPASSWD 127.0.0.1 JMX_PORT reload
    public static void main(String[] args) throws Exception {
        // service:jmx:rmi://localhost:1111/jndi/rmi://localhost:8999/jmxrmi
        String jmxUrl = "service:jmx:rmi:///jndi/rmi://localhost:8999/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(jmxUrl);
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        // 调用MBean方法（示例：查询线程数）
        ObjectName threadMBean = new ObjectName("IWEB:type=Reload,name=ReloadBean");
        Object reloadRet = mbsc.invoke(threadMBean, "reload", null, null);
        //Object reloadRet = (int) mbsc.getAttribute(threadMBean, "reload");
        System.out.println("reload 结果: " + reloadRet);

        jmxc.close();
    }
}