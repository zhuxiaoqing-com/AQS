package com.example;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

public class HotAgent {

    public static void agentmain(String args, Instrumentation inst) throws Exception {
        System.out.println("[Agent] attached");

        AgentArgs config = AgentArgs.parse(args);
        byte[] newBytes = Files.readAllBytes(config.classFilePath);

        // 1. 先查这个类是否已经加载，注意不要 Class.forName，否则会主动把它加载出来
        Class<?> loadedClass = findLoadedClass(inst, config.className);

        if (loadedClass != null) {
            // 已加载：直接 redefine
            if (!inst.isRedefineClassesSupported()) {
                throw new UnsupportedOperationException("Current JVM does not support redefineClasses");
            }

            if (!inst.isModifiableClass(loadedClass)) {
                throw new UnsupportedOperationException("Class is not modifiable: " + config.className);
            }

            inst.redefineClasses(new ClassDefinition(loadedClass, newBytes));
            System.out.println("[Agent] redefine done for already loaded class: " + config.className);
            return;
        }

        // 2. 未加载：注册 transformer，等它未来首次加载时替换
      /*  ClassFileTransformer transformer = new ClassFileTransformer() {
            @Override
            public byte[] transform(
                    ClassLoader loader,
                    String className,
                    Class<?> classBeingRedefined,
                    ProtectionDomain protectionDomain,
                    byte[] classfileBuffer) {

                String dotName = className.replace('/', '.');
                if (dotName.equals(config.className)) {
                    System.out.println("[Agent] transform on first load: " + dotName);
                    return newBytes;
                }
                return null;
            }
        };

        inst.addTransformer(transformer, false);*/

        registerOneShotTransformer(inst, config.className, newBytes);
        System.out.println("[Agent] class not loaded yet, transformer registered for: " + config.className);
    }

    private static Class<?> findLoadedClass(Instrumentation inst, String className) {
        for (Class<?> c : inst.getAllLoadedClasses()) {
            if (c.getName().equals(className)) {
                return c;
            }
        }
        return null;
    }

    private static class AgentArgs {
        final String className;
        final Path classFilePath;

        private AgentArgs(String className, Path classFilePath) {
            this.className = className;
            this.classFilePath = classFilePath;
        }

        static AgentArgs parse(String args) {
            if (args == null || !args.contains("=")) {
                throw new IllegalArgumentException(
                        "args format must be: full.class.Name=/path/to/YourClass.class");
            }

            int idx = args.indexOf('=');
            String className = args.substring(0, idx).trim();
            String path = args.substring(idx + 1).trim();

            if (className.isEmpty() || path.isEmpty()) {
                throw new IllegalArgumentException(
                        "args format must be: full.class.Name=/path/to/YourClass.class");
            }

            return new AgentArgs(className, Paths.get(path));
        }
    }

    static void registerOneShotTransformer(
            final Instrumentation inst,
            final String targetClassName,
            final byte[] newBytes) {

        final AtomicBoolean done = new AtomicBoolean(false);
        final ClassFileTransformer[] holder = new ClassFileTransformer[1];

        holder[0] = new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader,
                                    String className,
                                    Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain,
                                    byte[] classfileBuffer) {

                if (className == null) {
                    return null;
                }

                String dotName = className.replace('/', '.');
                if (!targetClassName.equals(dotName)) {
                    return null;
                }

                // 保证只处理一次
                if (!done.compareAndSet(false, true)) {
                    return null;
                }

                System.out.println("[Agent] transform on first load: " + dotName);

                // 先移除自己
                try {
                    boolean removed = inst.removeTransformer(holder[0]);
                    System.out.println("[Agent] transformer removed: " + removed);
                } catch (Throwable e) {
                    e.printStackTrace();
                }

                // 返回替换后的字节码
                return newBytes;
            }
        };

        inst.addTransformer(holder[0], false);
    }
}
