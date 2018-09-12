package com.example.demo1;

import org.junit.Test;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Test2 {
    volatile String firstNode = "1";
    volatile String endNode = "1";
    @Test
    public void fun1() {
        long s = 3 << 28 | 2;
        System.out.println(Long.toBinaryString(s));
    }
}
/**
 * private void unlinkCancelledWaiters() {
 *      // 遍历整个队列进行去除状态为取消的节点
 *      Node t = firstWaiter;
 *      // 用来保存最后一个不是取消的节点
 *      Node trail = null;
 *      while(t != null) {
 *          Node next = t.nextWaiter;
 *          if(t.waitStatus != Node.CONDITION) {
 *              if(trail == null)
 *                  firstWaiter = next;
 *              else
 *                  trail.nextWaiter = next;
 *              if(next == null)
 *                  lastWaiter = trail;
 *          } else
 *            trail = t;
 *          t = next;
 *      }
 * }
 */

/**
 * private void unlinkCancelledWaiters() {
 *      Node t = firstWaiter;
 *      Node trail = null;
 *      while(t != null) {
 *          Node next = t.nextWaiter;
 *          if(t.waitStatus != Node.CONDITION) {
 *              if(trail == null)
 *                  firstWaiter = next;
 *              else
 *                  trail.nextWaiter = next;
 *              if(next == null)
 *                  lastWaiter = trail;
 *          } else
 *              trail = t;
 *          t = next;
 *      }
 * }
 */

/**
 * private void unlinkCancelledWaiters() {
 *     Node t = firstWaiter;
 *     Node trail = null;
 *     firstWaiter = null;
 *     while(t!=null) {
 *         if(t.waitStatus != Node.CONDITION) {
 *             do{
 *                  Node t = t.next;
 *                  if(t == null) {
 *                      lastWaiter = trail;
 *                      break;
 *                  }
 *             } while(t.waitStatus != Node.CONDITION);
 *               if(t != null)
 *                   trail = t;
 *              if(firstWaiter == null) {
 *                  firstWaiter = trail;
 *              }
 *
 *         }
 *         if()
 *         t = trail;
 *     }
 * }
 */

/**
 * 改编版
 * private void unlinkCancelledWaiters() {
 *     Node t = firstWaiter;
 *     Node trail = null;
 *     //  t 是临时变量
 *     while(t != null) {
 *         Node next = t.nextWaiter;
 *         if(t.waitStatus == Node.CONDITION) {
 *             if(trail == null) {
 *                  firstWaiter = t;
 *                  trail = t;
 *             } else
 *                  trail = trail.nextWaiter = t;
 *             if(next == null)
 *                  lastWaiter = trail;
 *         }
 *         t = next;
 *     }
 * }
 */

/**
 * // 先记住 next;如果不行就换,如果可以就不做修改;
 * private void unlinkCancelledWaiters() {
 *      Node t = firstWaiter;
 *      Node trail = null;
 *      while(t != null) {
 *          Node next = t.nextWaiter;
 *          if(t.waitStatus != Node.CONDITION) {
 *              // 将 nextWaiter 置为 null 让其可以回收
 *              t.nextWaiter = null;
 *              if(trail == null)
 *                  // 在多线程下面排除掉一个就可以实时共享，上面的不可以
 *                  firstWaiter = next;
 *              else
 *                  trail.nextWaiter = next;
 *              if(next == null)
 *                  lastWaiter = trail;
 *          } else
 *              trail = t;
 *          t = next;
 *      }
 * }
 */


/**
 * private void unlinkCancelledWaiters() {
 *      Node t = firstWaiter;
 *      Node trail = null;
 *      while(t != null) {
 *          Node next= t.nextWaiter;
 *          if(t.waitStatus == Node.CONDITION){
 *              if(trail == null)
 *                  firstWaiter = next;
 *              else
 *                  trail.nextWaiter = next;
 *              if(next == null)
 *                  lastWaiter = trail;
 *          }else
 *              trail = t;
 *          t = next;
 *      }
 * }
 */




























