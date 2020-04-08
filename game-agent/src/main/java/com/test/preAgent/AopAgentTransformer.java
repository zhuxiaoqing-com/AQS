package com.test.preAgent;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 10:17
 * @Description:
 */
public class AopAgentTransformer implements ClassFileTransformer {
    /**
     * 有两种 ClassFileTransformer ，根据 canRetransform决定是哪一种。
     * 在向Instrumentation#addTransformer添加转换器的时候，会指定canRetransform，默认为false。
     * 决定retransformation是否可用。
     * <p>
     * 一旦一个 transformer 被注册到 instrumentation 中 ，每当一个类被定义（ClassLoader.defineClass）
     * 或被重新定义（Instrumentation.redefineClasses）时，它都会被调用。
     * <p>
     * 如果 retransformation 可用，那么一个类被retransformation（Instrumentation.retransformClasses）时，transformer也会被调用。
     * <p>
     * 存在多个transformers时，每个transformer会进行链式调用。
     * <p>
     * 多个transformers调用顺序：
     * <p>
     * Retransformation不可用的
     * Retransformation不可用的native 的transformation
     * Retransformation可用的
     * Retransformation可用的native 的transformation
     * 发生retransformations的时候，Retransformation不可用的transformers不会被调用。
     * 同一种transformers按照注册顺序执行。
     * native的transformers通过ClassFileLoadHook提供。
     * <p>
     * 如果一个transformer不想改变任何代码，那么返回null。否则，应该创建一个新的byte[]，不能修改classfileBuffer。
     * <p>
     * 一个transformer抛出异常，后续的transformer依然会执行，抛异常和返回Null效果相同
     * ————————————————
     * 版权声明：本文为CSDN博主「ljz2016」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/ljz2016/java/article/details/83309599
     * <p>
     * 在要代理的项目的 main 方法运行前运行
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        System.out.println("Transforming " + className);
        ClassPool pool = null;
        CtClass cl = null;
        if (!className.startsWith("com/agentTest")) {
            return transformed;
        }
        try {
            pool = ClassPool.getDefault();

            cl = pool.makeClass(new java.io.ByteArrayInputStream(
                    classfileBuffer));

//            CtMethod aop_method = pool.get("com.jdktest.instrument.AopMethods").
//                    getDeclaredMethod("aopMethod");
//            System.out.println(aop_method.getLongName());

            if (cl.isInterface() == false) {
                CtMethod[] methods = cl.getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    if (!methods[i].isEmpty()) {
                        AOPInsertMethod(methods[i]);
                    }
                }
                transformed = cl.toBytecode();
            }
        } catch (Exception e) {
            System.err.println("Could not instrument  " + className
                    + ",  exception : " + e.getMessage());
        } finally {
            if (cl != null) {
                cl.detach();
            }
        }
        return transformed;
    }

    private void AOPInsertMethod(CtMethod method) throws NotFoundException, CannotCompileException {
        //situation 1:添加监控时间
        method.instrument(new ExprEditor() {
            public void edit(MethodCall m) throws CannotCompileException {
                CtClass enclosingClass = m.getEnclosingClass();
                m.replace("{ long stime = System.currentTimeMillis(); " +
                        "$_ = $proceed($$);" +
                        "System.out.println(\""
                        + enclosingClass.getName() + "." + method.getMethodInfo().getName()
                        + " cost:\" + (System.currentTimeMillis() - stime) + \" ms\");}");

            }
        });
        //situation 2:在方法体前后语句
//      method.insertBefore("System.out.println(\"enter method\");");
//      method.insertAfter("System.out.println(\"leave method\");");
    }
}
