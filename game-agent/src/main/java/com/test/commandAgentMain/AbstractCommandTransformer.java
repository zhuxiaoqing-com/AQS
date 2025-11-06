package com.test.commandAgentMain;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * @author zhuxiaoqing
 * @Description: AbstractCommandTransformer
 * @Date 2025/10/30 15:36
 **/

public class AbstractCommandTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {


        if (className == null) {
            return null;
        }


        // 转换 className 为标准 Java 类名
        String dotClassName = className.replace("/", ".");


        try {
            ClassPool pool = ClassPool.getDefault();
            pool.appendClassPath(new LoaderClassPath(loader));

            // 例：操作接口 IQueueDriverCommand
            if ("com.sh.concurrent.IQueueDriverCommand".equals(dotClassName)) {
                System.out.println("[Agent] Modifying " + className);
                CtClass ctInterface = pool.get(dotClassName);

                try {
                    ctInterface.getDeclaredField("threadLocal");
                } catch (NotFoundException e) {
                    // 创建静态 ThreadLocal 字段并初始化
                    CtField field = new CtField(pool.get("java.lang.ThreadLocal"), "threadLocal", ctInterface);
                    field.setModifiers(Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL);
                    ctInterface.addField(field, "new java.lang.ThreadLocal()"); // 不用 lambda
                }

                return ctInterface.toBytecode();
            }


            if ("com.sh.concurrent.QueueDriver".equals(dotClassName)) {

                System.out.println("[Agent] Modifying " + className);

                CtClass ctClass = pool.get(dotClassName);

                CtMethod run = ctClass.getDeclaredMethod("addCommand",
                        new CtClass[]{pool.get("com.sh.concurrent.IQueueDriverCommand")}); // 参数类型要一致


                run.insertBefore("{$1.setQueueId(0);}");
                run.instrument(new ExprEditor(){
                    @Override
                    public void edit(MethodCall m) throws CannotCompileException {
                        if(m.getMethodName().equals("setCommandQueue")) {

                            System.out.println("[Agent] MethodCall " + m.getMethodName());
                            m.replace("{ " +
                                    "  $_ = $proceed($$); " + // 调用原方法
                                    "   $0.setQueueId((int)this.queueId);"+
                                    "}"
                                    );
                        }
                    }
                });

                // run.insertBefore("{$1.setQueueId(this.queueId);}");
                return ctClass.toBytecode();
            }


            // 例：操作类 AbstractCommand，给 run() 方法加 ThreadLocal 设置
            if ("com.sh.concurrent.AbstractCommand".equals(dotClassName)
                    || "com.sh.server.MessageHandler".equals(dotClassName)
            ) {

                System.out.println("[Agent] Modifying " + className);

                CtClass ctClass = pool.get(dotClassName);

                // 给 run 方法加前置逻辑
                CtMethod run = ctClass.getDeclaredMethod("run");


                run.insertBefore(
                        "{ " +
                                "try {" +
                                "  java.lang.reflect.Field f = Class.forName(\"com.sh.concurrent.IQueueDriverCommand\").getDeclaredField(\"threadLocal\");" +
                                "  java.lang.ThreadLocal tl = (java.lang.ThreadLocal) f.get(null);" +
                                "  tl.set(Integer.valueOf(queueId));" +
                                "} catch (Exception e) { e.printStackTrace(); }" +
                                " }"
                );
                return ctClass.toBytecode();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }

}

