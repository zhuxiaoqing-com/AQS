package com.mdb;

public interface MdbInterface {

    /*public void onFlushOver(Map<Object, TRecord> records,
                            Consumer<Map<Object, TRecord>> handler, Consumer<Map<Object, String>> callback);*/

    /**
     * 所有的线程都执行该任务;
     */
    public void allExecute(Runnable runnable);

    /**
     * 某个线程执行任务;
     */
    public void execute(String threadName, Object key, Runnable runnable);

    /**
     * 当某个业务线程由多个线程组成时候获取改key具体在哪个线程执行;比如role线程由6个业务线程组成;
     * 执行的时候可以获取某个线程
     * 要有这个方法的话，感觉其实应该弄个方法判断下 是否属于该线程的判断;
     */
    public boolean checkThreadNameAndKey(String threadName, Object key);

    public boolean checkThreadName(String threadName);

    /**
     * 单独检测key是否能在该线程执行; checkThreadName和checkKey合并起来就是checkThreadNameAndKey;
     */
    public boolean checkKeyBelongCurrThread(Object key);
}
