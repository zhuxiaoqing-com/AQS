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
     *
     * jvm参数
     * -Dcom.sun.management.jmxremote
     *  启用 JMX 远程管理功能，允许外部客户端（如 JConsole、VisualVM）连接
     *  在 Java 6+ 版本中，默认已开启本地 JMX 访问，但‌远程访问必须显式启用此参数‌
     *  若省略，仅能通过本地连接管理 JVM
     *
     * -Dcom.sun.management.jmxremote.port=8011
     *  指定 JMX 服务的 RMI 注册端口（如 8011），客户端需通过此端口连接
     *         service:jmx:rmi://localhost:1111/jndi/rmi://localhost:8999/jmxrmi 和这里的8999要一样;
     *         8999（jndi/rmi 部分）‌：这是 RMI 注册端口，对应 JMX 服务的注册表端口，用于客户端查找 JMX 服务地址。
     * 必须通过 -Dcom.sun.management.jmxremote.port=PORT 显式设置‌（例如设为 8999），否则注册服务无法启动。
     * 1111（rmi:// 部分）‌：这是 RMI 传输端口‌，用于实际数据传输（如方法调用、属性读写），默认随机生成
     * ，但可通过 -Dcom.sun.management.jmxremote.rmi.port=PORT 显式固定。
     * 若未指定传输端口（如未设 jmxremote.rmi.port），客户端仍能通过注册端口获取真实传输端口，但需防火墙开放随机端口范围
     *
     *
     * -Dcom.sun.management.jmxremote.ssl=false
     *  禁用 SSL 加密通信，降低连接复杂度13。
     * ‌是否可省略‌：
     * 生产环境建议启用 SSL（设为 true）以保证安全性48。
     * 测试环境可禁用（设为 false），但‌若省略则默认启用 SSL‌，可能导致连接失败
     *
     * -Dcom.sun.management.jmxremote.authenticate=false
     * 关闭身份验证，允许匿名访问 JMX 服务13。
     * ‌是否可省略‌：
     * 生产环境‌必须启用认证‌（设为 true并配置密码文件）以防止未授权访问28。
     * 若省略且未显式设为 false，默认启用认证但未配置密码文件会抛出异常
     */
    public static void main(String[] args) throws IOException, MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer server = register();

        try {
            //这个步骤很重要，注册一个端口，绑定url后用于客户端通过rmi方式连接JMXConnectorServer
            LocateRegistry.createRegistry(8999);
            //URL路径的结尾可以随意指定，但如果需要用Jconsole来进行连接，则必须使用jmxrmi
            /**
             * （1）service:jmx:rmi://localhost:0/jndi/rmi://localhost:1099/jmxrmi
             * 　　蓝色部分可以省略掉
             * 　　（2）service:jmx: 这个是JMX URL的标准前缀，所有的JMX URL都必须以该字符串开头，否则会抛MalformedURLException
             * 　　（3）rmi: 这个是jmx connector server的传输协议，在这个url中是使用rmi来进行传输的
             * 　　（4）localhost:0 这个是jmx connector server的IP和端口，也就是真正提供服务的host和端口，可以忽略，那么会在运行期间随意绑定一个端口提供服务
             * 　　（5）jndi/rmi://localhost:1099/jmxrmi 这个是jmx connector server的路径，具体含义取决于前面的传输协议。
             *          比如该URL中这串字符串就代表着该jmx connector server的stub是使用 jndi api 绑定在 rmi://localhost:1099/jmxrmi 这个地址
             */
            JMXServiceURL url = new JMXServiceURL
                    ("service:jmx:rmi://localhost:1111/jndi/rmi://localhost:8999/jmxrmi");
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
            System.out.println("begin rmi start");
            jcs.start();
            System.out.println("rmi start");

            while (true) {
                Thread.sleep(5000);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  Thread.sleep(60 * 60 * 1000);
    }

    private static MBeanServer register() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        /**
         * IWEB:type=Reload,name=ReloadBean
         * IWEB   表示 MBean 的‌域（domain）‌，通常用于区分不同模块或应用的 MBean
         * type=Reload
         *      定义 MBean 的‌类型, 客户端可通过 type 属性筛选同类 MBean（如查询所有 type=Reload 的实例）
         *name=ReloadBean
         *      指定 MBean 的‌实例名称‌，用于唯一标识同一类型下的不同实例
         *      若需区分多个重载服务，可追加其他属性（如 name=ReloadBean1、name=ReloadBean2
         *
         *
         *
         */
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        // 域名：name=MBean名称
        ObjectName userName = new ObjectName("IWEB:type=Reload,name=ReloadBean");
        server.registerMBean(new Reload(), userName);
        return server;
    }
}
