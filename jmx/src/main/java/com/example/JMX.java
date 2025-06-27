package com.example;

import com.example.entity.Reload;

import javax.management.*;
import javax.management.monitor.Monitor;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class JMX {
    /**
     * https://zhuanlan.zhihu.com/p/166530442
     * https://www.cnblogs.com/myseries/p/11778166.html
     * @param args
     * @throws IOException
     * @throws MalformedObjectNameException
     */
    public static void main(String[] args) throws IOException, MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName userName = new ObjectName("IWEB:type=Reload,name=ReloadBean");
        server.registerMBean(new Reload(), userName);

        try {
            //这个步骤很重要，注册一个端口，绑定url后用于客户端通过rmi方式连接JMXConnectorServer
            LocateRegistry.createRegistry(8999);
            //URL路径的结尾可以随意指定，但如果需要用Jconsole来进行连接，则必须使用jmxrmi
            JMXServiceURL url = new JMXServiceURL
                    ("service:jmx:rmi://localhost:1111/jndi/rmi://localhost:8999/jmxrmi");
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
            System.out.println("begin rmi start");
            jcs.start();
            System.out.println("rmi start");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(60 * 60 * 1000);
    }
}
