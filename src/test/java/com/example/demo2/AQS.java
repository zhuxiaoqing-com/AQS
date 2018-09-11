package com.example.demo2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;

public class AQS {
    static final class Node {
        static final Node SHARED = new Node();
        static final Node EXCLUSIVE = null;
        static final int SIGNAL = -1;
        static final int CANCELLED = 1;
        static final int CONDITION = -2;
        static final int PROPAGATE = -3;
        volatile int waitStatus;
        volatile Node nextWaiter;
        volatile Node prev;
        volatile Node next;
        volatile Thread thread;

        Node(){};
        Node(Thread thread, Node mode){
            this.thread = thread;
            nextWaiter = mode;
        }

        Node(Thread thread, int waitStatus){
            this.thread = thread;
            this.waitStatus = waitStatus;
        }

        final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null)
                throw new NullPointerException();
            else
                return p;
        }

        final boolean isShared() {
            return nextWaiter == SHARED;
        }
    }

    private volatile Node head;
    private volatile Node tail;
    private volatile int state;


///////////////////通用的代码//////////////////////
///////////////////通用的代码//////////////////////
///////////////////通用的代码//////////////////////

    protected final int getState() {
        return state;
    }

    public void setHead(Node node) {
        head = node;
        node.prev = null;
        node.thread = null;
    }
    static void selfInterrupt() {
        Thread.currentThread().interrupt();
    }

    protected boolean isHeldExclusively() {
        throw new UnsupportedOperationException();
    }




    ////////////////////Exclusive//////////////////////////
    ////////////////////Exclusive//////////////////////////
    ////////////////////Exclusive//////////////////////////

    public boolean tryAcquire(int arg) {
        return arg == 1 ? true : false;
    }


    public final void acquire(int arg) {
        if(!tryAcquire(arg) || acquireQueue(addWater(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }

    public Node addWater(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        Node t = this.tail;
        if(t != null) {
            node.prev = t;
            if(compareAndSetTail(t, node))
                t.next = node;
        }
        enq(node);
        return node;
    }

    public Node enq(Node node) {
        for(;;) {
           Node t = this.tail;
           if(t == null) {
               if(compareAndSetHead(new Node()))
                   tail = head;
           } else {
               node.prev = t;
               if(compareAndSetTail(t, node)){
                   t.prev = node;
                   return t;
               }
           }
        }
    }

    public boolean acquireQueue(Node node, int arg) {
        boolean failed = true;
        try{
            boolean interrupted = false;
            for(;;) {
                Node p = node.predecessor();
                if(p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null;
                    failed = false;
                    return interrupted;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                        parkAndCheckInterrupt())
                    interrupted = true;
                }
        } finally {
            if (failed)
                cancelAcquire(node);
        }

    }

    public boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        int waitStatus = pred.waitStatus;
        if (waitStatus == Node.SIGNAL)
            return true;
        if (waitStatus > 0) {
            do{
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
            compareAndSetWaitStatus(pred, waitStatus, Node.SIGNAL);
        }
        return false;
    }

    public boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        return Thread.interrupted();
    }

    /**
     * 去除无用节点
     * @param node
     */
    public void cancelAcquire(Node node) {
        // 应该是一定存在的，因为不是无用状态，不会被去除
        // 能抛出异常一定是在 park 以后，也就说入队，并且刚醒来；
        // 因为是刚醒来，所以一定不是 head
        if (node == null)
            return;
        node.thread = null;

        Node pred = node.prev;
        while (pred.waitStatus > 0)
           node.prev = pred = pred.prev;

        Node predNext = pred.prev;
        node.waitStatus = Node.CANCELLED;

        if(node == tail && compareAndSetTail(node, pred)) {
            /**
             * 如果这里失败说明,condition signal 那里检测到了失败节点。然后将其新加入的 node 唤醒;
             * 并且到了 shouldParkAfterFailedAcquire 那里将 取消节点都给去除了，并且 prev.next = node;
             * 所以这里的 cas 有可能失败，不能强制赋值。强制赋值，可能会把上面的节点顶掉
             * signal
             * if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
             *         // 因为当前线程入队了
             * LockSupport.unpark(node.thread);
              */
            compareAndSetNext(pred, predNext, null);

            /**
             * 1、pred == head; 因为 node 是 cancelled 节点，如果唤醒的话，是不会唤醒 cancelled 节点的。会从 tail 开始遍历
             *      找到一个最后一个不是 cancelled 的节点。消耗的资源太大了，所以就唤醒 node.next 线程让其在 shouldParkAfterFailedAcquire 的
             *      时候去除 node 无效节点;
             *
             * 2、状态不是 status 也就是说基本就是变成了 cancelled (没有想到其他的情况)，也是因为正常唤醒将会唤醒 node 节点，也就说是无效节点。
             *      也就会出现 1 描述的情况，所以也可以在这个方法里面 直接唤醒 node.next; 来跳过 cancelled 节点
             *
             * 3、 pred.thread == null 因为 pred 判断过了不是 head 节点，那么他就是 无效节点了;或者在 pred != head 判断的期间，pred 成为了 head
             *      节点也是有可能的，其实 还是 1 的情况;
             *
             */
        } else {
            int ws;
            if(pred != head &&
                    ((ws = pred.waitStatus) == Node.SIGNAL ||
                            ((ws <= 0) && compareAndSetWaitStatus(pred, ws, Node.SIGNAL))) &&
                    pred.thread != null) {
                    Node next = node.next;
                    if(next != null && next.waitStatus <= 0)
                        compareAndSetNext(pred, predNext, next);
            } else {
                unparkSuccessor(node);
            }
        }
        // 可能是会到导致 GC 回收 快一点吧。可达性算法，已经是不可达了呀，为什么还要 node.next = node;
        node.next = node;
    }

    /**
     * 失败的节点并不需要被唤醒，因为它们本身就没有沉睡;如果唤醒了他们，可能会导致 一些意外发生;
     * @param node
     */
    public void unparkSuccessor(Node node) {
        int waitStatus = node.waitStatus;
        // 因为要释放 node.next 了。next 不再是 锁定的了；所以要将 head.waitStatus 变为 0;
        // 如果不是 cancelled 状态的话
        if(waitStatus < 0)
            compareAndSetWaitStatus(node, waitStatus, 0);

        Node s = node.next;
        // s == null 代表有可能是 tail 节点
        if(s == null || s.waitStatus > 0) {
            // 如果是 s.waitStatus 的话，就将 s == null; 这样就不会进入 LockSupport
            s = null;
            // 如果从 head 开始往下面判断的话，h.next 可能为 null; 但是其实 tail 已经有了，导致遍历不到的情况
            // 其实主要是将 tail 固定住，也就说和 cas 一样，固定住 tail 以后，这个就是单线程的了;
            // 也就是说后面的新加的 node 不管了，因为新加的 node 在 确定 tail 的那一刻起，已经算是完成了这个方法以后的 node 了;
            // 还有调用了这个方法的时候，doRelease 方法也已经调用过了，也就是说 锁已经释放了;
            // 如果没有就说明这一时刻可以唤醒的线程

            /**
             * 还有一种就是  在取消节点的时候 node.next = node; 如果你从 head 开始便利。后面碰见 cancelled 节点的话，
             *  因为 cancelled 状态的 节点的 next = node 本身; 如果你往下面找 遇到了 这种情况你就找不下去了，因为 next 指向了本身 就死循环了
             *      或者 找不下去了;
             *  如果你是 prvd 的话，就不用担心了，因为 pred 用于指向前面;  在 aqs 里面 pred 是安全的; 永远不会断;
             *
             *  如果 node 取消了 那么 node.next = node; 如果是 pred 会只想不是 cancelled 的 pred ;
             *
             *  这是由于 cancelAcquire 方法 决定的;
             *      节点取消后 pred 因为有 head 作为 保证所以如果 前面都是 cancelled 最终会指向 head;
             *      而 next 最终都会指向 node.next = next; 这样的话就会造成错误;
             *
             *      如果你从 head.next 开始遍历，可能会出现 node.next = node 无限循环
             *      但是你从 tail.prev 开始遍历，就不会出现问题;
             */
            for(Node t = tail; t != null && t != node; t = t.prev) {
                if(t.waitStatus <= 0)
                    s = t;
            }
        }

        if(s == null) {
            LockSupport.unpark(s.thread);
        }
    }


    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }

    public boolean tryRelease(int arg) {
        return arg == 1 ? true : false;
    }
    //////////////////////////////共享///////////////////////////////

    public int tryAcquireShare(int arg) {
        return arg;
    }


    public final void acquireShare(int arg) {
        if(tryAcquireShare(arg) < 0)
            doAcquireShared(arg);
    }

    private void doAcquireShared(int arg) {
        final Node node = addWater(Node.SHARED);
        boolean failed = true;
        try{
            boolean interrupted = false;
            for(;;) {
                Node pred = node.predecessor();
                if(pred == head) {
                    int r = tryAcquireShare(arg);
                    if(r >= 0) {
                        setHeadPropagete(node, r);
                        pred.next = null;
                        failed = false;
                        if (interrupted)
                            selfInterrupt();
                            // 为什么要将中断状态改回来不知道; AcquireQueue 都不用改回来的 明明
                            // 哦 AcquireQueue 是将转态返回，在外面改，都是一样的这个在里面改;
                            // 因为这个没有返回值，为什么没有，不知道
                            // Thread.currentThread().interrupt();
                        return;
                    }
                }
                if(shouldParkAfterFailedAcquire(node, pred) && parkAndCheckInterrupt())
                    interrupted = true;
            }
        }finally {
            if(failed)
                cancelAcquire(node);
        }
    }

    public void setHeadPropagete(Node node, int arg) {
        Node h = head;
        /**
         * head = node;
         * node.prev = null;
         * node.thread = null;
         */
        setHead(node);

        // 应该是调用之前判断一下 h 是不是为 null; 不是的话 就调用 h.waitStatus < 0;
        // 如果 不对就再取一次，在不对就放弃
        if(arg > 0 || h == null || h.waitStatus < 0
                || (h = head) == null || h.waitStatus < 0) {
            Node s = node.next;
            // 为什么 next = null 也进来; 因为可能下一秒就又有 node 进来了
            // 由 doReleaseShared 来判断 队列中是否有子节点
            // 最终解释权由 doReleaseShared() 归属
            if(s == null || s.isShared())
                doReleaseShared();
        }
    }


    public void  doReleaseShared() {
        /**
         * 1、判断 head 是否为 Null 和 队列中是否还有节点
         * 2、不为 null 就进行 h.waitStatus = SIGNAL 就 解锁
         *  然后将 h.waitStatus 变为 0;
         * 3、h.waitStatus = 0; 就将其变为 Propagate
         */
        for(;;) {
           Node h = head;
           if(h != null && h != tail) {
               /**
                * 为什么要这么判断
                *   因为 Semaphore 信号量的原因。doRelease() 运行一次就会释放一个信号量;
                *   而如果释放的时候，头不是 signal 就不会释放信号量，而是直接不进入 if;
                *   所以你需要将 head 从 0 设置给 Node.PROPAGATE, 然后交给 setHeadPropagate() 来判断;
                *   为了让 Semaphore 先 doRelease 运行以后，不会造成死循环
                *
                *
                *   为什么不设置为 Node.SIGNAL  要设置成 Node.PROPAGATE
                *   因为 下面这个方法 设置成功后就会结束，也没有尝试去唤醒后续节点; Node.SIGNAL 需要有后续节点是阻塞状态的
                *
                *   什么情况下 h.waitStatus == 0
                *   head 已经运行过 unparkSuccessor(h)，或 head 后面没有可以唤醒的节点;
                *   然后你将其替换成 signal 以后，新的节点也刚好在 shouldParkAfterFailedAcquire 正好一看 signal 就 park 了
                *   如果你是 Propagate 就会将其替换为 signal 然后在尝试获取锁一次;
                *
                *
                *
                * 回过头来回答你的问题：不可以将头结点设置为 SIGNAL。
                *
                * 此场景很难模拟出现，也就是在唤醒的时候，阻塞队列的最后一个节点成为了头结点，我们知道，最后一个节点的状态通常为 0，
                * 但是突然出现了一个新的节点，这个节点跑到 shouldParkAfterFailedAcquire 这个方法了。
                *
                * 如果按照你的设想设置为 SIGNAL 了，那么新的节点进到 shouldParkAfterFailedAcquire 以后，会返回 true，
                * 然后会进到 parkAndCheckInterrupt 挂起，而头节点的这个线程也已经从共享锁退出，那么已经没有线程会负责唤醒这个新节点了。
                *
                * 如果是设置为 PROPAGATE，那么新的节点在 shouldParkAfterFailedAcquire 回来的时候是 false，也就不会挂起，
                * 而会进到下一个 for 循环，接下来就简单了。
                */
               if(h.waitStatus == Node.SIGNAL) {
                   if(compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                       continue;
                   unparkSuccessor(h);
               }
               if(h.waitStatus == 0) {
                   if(compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                       continue;
                   /**
                    * 为什么不在这里唤醒
                    * 因为只有 head 为 SIGNAL 才可以唤醒线程
                    */
               }
           }
           if(h == head)
               break;
        }
    }

    public void unparkSuccessor2(Node node) {
        int ws = node.waitStatus;
        // 如果不是 cancelled 和默认节点，就将节点变为默认节点
        // 因为 cancelled 是无效的，不属于 fifo 队列了。所以就不修改 cancelled 了，修改了 可能 node 不是头结点，会导致 node 不能出列;
        // 因为 0 是指默认节点，本来就要修改为 0 就不修改了;
        if(ws < 0)
            compareAndSetWaitStatus(node, ws, 0);

        Node s = node.next;
        if(s == null || s.waitStatus > 0) {
            // 如果 n 是取消节点的话, 就将其设为 null 因为取消节点不能被唤醒，它已经不属于这个队列了
            s = null;
            // 循环寻找不是取消节点的节点;
            /**
             * cancelAcquire 做了什么
             * 如果取消的节点是尾节点，将其替换
             * 如果取消的节点不是尾节点，将其 Node 置为空，node.next = node; node.prev
             * 为什么要从后面往前面找，因为 cancelAcquire  会将 node.next = node;
             *  如果你碰上取消节点了，就会无限循环;
             *  但是其实是没有必要的 node.next = node; 不明白为什么
             */
            for(Node n = tail; n != null && n != node; n = n.prev) {
                if(n.waitStatus <= 0)
                    s = n;
            }
        }

        if(s != null)
            LockSupport.unpark(s.thread);


    }







/////////////////ConditionObject/////////////////////
/////////////////ConditionObject/////////////////////
/////////////////ConditionObject/////////////////////



    ////ConditionObject
public class ConditionObject implements Condition {
        private Node firstWaiter;
        private Node lastWaiter;

        /** Mode meaning to reinterrupt on exit from wait */
        private static final int REINTERRUPT =  1;
        /** Mode meaning to throw InterruptedException on exit from wait */
        private static final int THROW_IE    = -1;

        public ConditionObject(){}

        /**
         * 添加节点,添加之前是查看是否有不是 CONDITION 的节点
         * @return
         */
        public Node addConditionWaiter() {
            Node t = lastWaiter;
            if(t != null && t.waitStatus != Node.CONDITION) {
                unlinkCancelledWaiter();
                // 处理过以后再次获取
                t = lastWaiter;
            }
            Node node = new Node(Thread.currentThread(), Node.CONDITION);
            if(t == null)
                firstWaiter = node;
            else
                t.nextWaiter = node;
            lastWaiter = node;
            return node;

        }

        /**
         * 遍历 ConditionQueue 断开取消的 Waiter 节点
         * @return
         */
        public void unlinkCancelledWaiter() {
            Node t = firstWaiter;
            Node trail = null;
            if(t != null) {
                Node next = t.nextWaiter;
                if(t.waitStatus != Node.CONDITION) {
                    if(trail == null)
                        firstWaiter = next;
                    else
                        trail.nextWaiter = next;
                    if(next == null)
                        lastWaiter = trail;
                } else
                    trail = t;
                t = next;
            }
        }

        public int fullyRelease(Node node) {
            boolean failed = true;
            try{
                int state = getState();
                if(release(state)) {
                    failed = false;
                    return state;
                } else
                    throw new IllegalMonitorStateException();

            }finally {
                if(failed)
                    node.waitStatus = Node.CANCELLED;

            }

        }

        public boolean isOnSyncQueue(Node node) {
            if(node.waitStatus == Node.CONDITION || node.prev == null)
                return false;
            if(node.next != null)
                return true;
            return findNodeFromTail(node);

        }

        public boolean findNodeFromTail(Node node) {
            Node t = tail;
            for (;;) {
                if(t == node)
                    return true;
                if(t == null)
                    return false;
                t = t.prev;
            }
        }

        public int checkInterruptWhileWaiting(Node node) {
            /**
             * 如果醒来以后发现线程是中断状态的,那么就说明在 await 以后才被中断的
             * 因为入队列之前,都需要先判断是否是中断状态
             * 目前所有的只要是响应 interrupt 的方法都是在进来之前先判断一次，入队以后从 park 醒来以后再判断一次
             * 不响应 interrupt 的方法就是将其 interrupt 的状态保存起来
             * interrupt 虽然不会让 park 抛出异常，却会让其立即醒来;而且不会重置 interrupt 的状态
             * 如果在 park 之前就 interrupt 的话, park 也会立即醒来
             *
              */
            return Thread.interrupted()?
                    (transferAfterCancelledWait(node) ? THROW_IE : REINTERRUPT):
            0;
        }

        public boolean transferAfterCancelledWait(Node node) {
            if(compareAndSetWaitStatus(node, Node.CONDITION, 0)){
                enq(node);
                /**
                 * 在 signal 前面中断, 是不是可以使用 interrupt 来代替 signal 呢?
                 * 也说明了 await 是天生就响应 interrupt 的;
                  */
                return true;
            }
            /**
             * 如果上面的 cas 失败的话，因为 进来的 node 不可能为 cancelled 因为,成为 cancelled 的节点都抛出了异常;
             * 不可能走到这里,或者说不可能在 condition 里面被 park
             * 就和 不可能在 aqs 被唤醒失败的节点; 因为会进行过滤的;
             *
             * 所以来到了这里只有一个可能就是正在被 signal 释放，只需要稍微等待一下就好了;
             */
            while(!isOnSyncQueue(node)) {
                Thread.yield();
            }
            // 在 signal 之后被中断的就返回 false;
            return false;
        }

        public void reportInterruptAfterWait(int interruptMode) throws InterruptedException {
            if(interruptMode == THROW_IE)
                throw new InterruptedException();
            if(interruptMode == REINTERRUPT)
                selfInterrupt();
        }



        @Override
        public void await() throws InterruptedException {
            /**
             * 1、入队列
             * 2、解锁
             * 3、while 是否已经入队列，入队列就结束，没有就继续 park
             *     while 的时候，检查是否中断 中断也 break
             * 4、尝试获取重复锁
             * 5、查看 signal 是否成功，失败就要去除失败节点
             * 6、根据异常，选择抛出异常还是
             * REINTERRUPT 在 signal 之后被中断。因为在 signal 之后才被中断,就和condition 没有关系。不会抛出异常
             * THROW_IE 在 signal 之前被中断。所以需要抛出异常
             */
            if(Thread.interrupted())
                throw new InterruptedException();
            Node node = addConditionWaiter();
            int saveStatus = fullyRelease(node);
            int interruptMode = 0;
            while (!isOnSyncQueue(node)) {
                LockSupport.park(this);
                if((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                    break;
            }
            /**
             * 可能在 checkInterruptWhileWaiting 那里是 0; 但是经过 acquireQueue 里面的不断 park
             * unpark 途中被人变成了 interrupt 了, 因为 acquireQueue 会将当前的获取锁的线程的的中断状态
             * 返回，如果为 true 就将其 interruptMode = REINTERRUPT;
             */
            if(acquireQueue(node, saveStatus) && interruptMode != THROW_IE)
                interruptMode = REINTERRUPT;
            /**
             * 如果在 signal 之前就被人 interrupt 了,在 checkInterruptWhileWaiting 里面唤醒的线程;
             * 那么 node 就是这样 node.nextWaiter != null ,这里就需要你手动去除 Cancelled 的节点，
             * 当然这里主要是去除 Node.SIGNAL 的节点; 因为 入队以后会将 waitStatus 变为 0;
             */
            if(node.nextWaiter != null)
                unlinkCancelledWaiter();
            if(interruptMode != 0)
                reportInterruptAfterWait(interruptMode);
        }

        public void await2() throws InterruptedException {
            Node node = addConditionWaiter();
            int saveStatus = fullyRelease(node);
            int interruptMode = 0;
            while(!isOnSyncQueue(node)) {
                LockSupport.park(this);
                if((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                    break;
            }
            // 1、请求独占锁
            if(acquireQueue(node, saveStatus) && interruptMode != THROW_IE)
                interruptMode = REINTERRUPT;
            /**
              * 2、看是否是正常状态的入 阻塞队列 如果是在 checkInterruptWhileWaiting 进入的 阻塞队列是不会将
             *  node.nextWaiter == null 的;但是 node 的状态为 0 了;而 unlinkCancelledWaiter 只要不是 CONDTION 的节点
             *  都会被去除，所以在这里将 node 从 ConditionQueue 里面去除;
              */
            if(node.nextWaiter != null)
                unlinkCancelledWaiter();
            if(interruptMode != 0)
                reportInterruptAfterWait(interruptMode);
        }

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {
            // 调用 signal 方法的线程必须持有当前的独占锁
            if(!isHeldExclusively())
                throw new IllegalMonitorStateException();
            Node first = firstWaiter;
            if (first != null) {
                doSignal(first);
            }
        }

        @Override
        public void signalAll() {

        }

        public void doSignal(Node node) {
           return;
        }
    }
















/////////////////unsafe/////////////////////
/////////////////unsafe/////////////////////
/////////////////unsafe/////////////////////

    // unsafe
    private static final Unsafe unsafe = initUnsafe();
    private static long stateOffset;
    private static long headOffset;
    private static long tailOffset;
    private static long waitStatusOffset;
    private static long nextOffset;


    static {
        try {
            stateOffset = unsafe.objectFieldOffset(AQS.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset(AQS.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(AQS.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset(AQS.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset(AQS.class.getDeclaredField("next"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static Unsafe initUnsafe() {
        Field theUnsafeInstance = null;
        try {
            theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeInstance.setAccessible(true);
            // return (Unsafe) theUnsafeInstance.get(null);是等价的
            return (Unsafe) theUnsafeInstance.get(Unsafe.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    /**
     * CAS tail field. Used only by enq.
     */
    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    /**
     * CAS waitStatus field of a node.
     */
    private static final boolean compareAndSetWaitStatus(Node node,
                                                         int expect,
                                                         int update) {
        return unsafe.compareAndSwapInt(node, waitStatusOffset,
                expect, update);
    }

    /**
     * CAS next field of a node.
     */
    private static final boolean compareAndSetNext(Node node,
                                                   Node expect,
                                                   Node update) {
        return unsafe.compareAndSwapObject(node, nextOffset, expect, update);
    }

}




















