package com.example.networkProtocol.kcp;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/17 16:48
 * @Description:
 */

import java.util.ArrayList;

/**
 * https://blog.csdn.net/iteye_19871/article/details/81575044
 *
 * 净负荷是相对开销而言的。因为互联网是分层结构，数据自上向下转换时，每层都会加上一定的开销(overhead)，
 * 而原来的数据就被称为净荷(payload)。对于 IP 来说，一个 IP 包由头和数据组成，头即是开销，
 * 而后面的数据即是 IP 包的净荷。当然 IP 的净荷实际上也包括上层（如 TCP、UDP 等）的开销，
 * 而整个 IP 包又是其下层（如以太网 MAC）的净荷。
 */
public abstract class KCP {
    public final int IKCP_RTO_NDL = 30;   // no delay min rto 没有延迟的最小rto
    public final int IKCP_RTO_MIN = 100;  // normal min rto 正常模式的最小rto
    public final int IKCP_RTO_DEF = 200; // 默认 rto
    public final int IKCP_RTO_MAX = 60000;// 最大 rto
    public final int IKCP_CMD_PUSH = 81;  // cmd: push data
    public final int IKCP_CMD_ACK = 82;   // cmd: ack
    public final int IKCP_CMD_WASK = 83;  // cmd: window probe (ask) 窗口检测 TCP的坚持定时器
    public final int IKCP_CMD_WINS = 84;  // cmd: window size (tell) 窗口大小更新
    public final int IKCP_ASK_SEND = 1;   // need to send IKCP_CMD_WASK 需要发送 窗口检测
    public final int IKCP_ASK_TELL = 2;   // need to send IKCP_CMD_WINS 需要发送 窗口大小更新
    public final int IKCP_WND_SND = 32; //wnd:  windows send 发送队列滑动窗口最大值
    public final int IKCP_WND_RCV = 32;  // wnd: windows receive 接收窗口大小
    public final int IKCP_MTU_DEF = 1400; //  segment: 报文默认大小 [mtu 网络最小传输单元]
    public final int IKCP_ACK_FAST = 3; // null: 快速重传？
    public final int IKCP_INTERVAL = 100; // flush: 控制刷新时间间隔
    public final int IKCP_OVERHEAD = 24; // 开销 应该是是指 kcp 的头部的大小
    public final int IKCP_DEADLINK = 10; // 连接存活定时吗？
    public final int IKCP_THRESH_INIT = 2; // ssthresh: 慢热启动 初始窗口大小
    public final int IKCP_THRESH_MIN = 2; // ssthresh: 慢热启动 最小窗口大小
    public final int IKCP_PROBE_INIT = 7000;   // probe: 请求询问远端窗口大小的初始时间  7 secs to probe window size
    public final int IKCP_PROBE_LIMIT = 120000;  // probe: 请求询问远端窗口大小的最大时间  up to 120 secs to probe

    protected abstract void output(byte[] buffer, int size); // 需具体实现


    // encode 8 bits unsigned int
    public static void ikcp_encode8u(byte[] p, int offset, byte c) {
        p[0 + offset] = c;
    }

    // decode 8 bits unsigned int
    public static byte ikcp_decode8u(byte[] p, int offset) {
        return p[0 + offset];
    }

    /* encode 16 bits unsigned int (msb) */
    public static void ikcp_encode16u(byte[] p, int offset, int w) {
        p[offset + 0] = (byte) (w >> 8);
        p[offset + 1] = (byte) (w >> 0);
    }

    /* decode 16 bits unsigned int (msb) */
    public static int ikcp_decode16u(byte[] p, int offset) {
        int ret = (p[offset + 0] & 0xFF) << 8
                | (p[offset + 1] & 0xFF);
        return ret;
    }

    /* encode 32 bits unsigned int (msb) */
    public static void ikcp_encode32u(byte[] p, int offset, long l) {
        p[offset + 0] = (byte) (l >> 24);
        p[offset + 1] = (byte) (l >> 16);
        p[offset + 2] = (byte) (l >> 8);
        p[offset + 3] = (byte) (l >> 0);
    }

    /* decode 32 bits unsigned int (msb) */
    public static long ikcp_decode32u(byte[] p, int offset) {
        long ret = (p[offset + 0] & 0xFFL) << 24
                | (p[offset + 1] & 0xFFL) << 16
                | (p[offset + 2] & 0xFFL) << 8
                | p[offset + 3] & 0xFFL;
        return ret;
    }


    public static void slice(ArrayList list, int start, int stop) {
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            if (i < stop - start) {
                list.set(i, list.get(i + start));
            } else {
                list.remove(stop - start);
            }
        }
    }

    static long _imin_(long a, long b) {
        return a <= b ? a : b;
    }

    static long _imax_(long a, long b) {
        return a >= b ? a : b;
    }


    static long _ibound_(long lower, long middle, long upper) {
        return _imin_(_imax_(lower, middle), upper);
    }

    static int _itimediff(long later, long earlier) {
        return ((int) (later - earlier));
    }



    private class Segment {
        protected long conversation = 0; // 会话序号:接收到的数据包与发送的一致才接受此数据包
        protected long cmd = 0;// command，指令类型：代表这个 segment 的类型
        protected long fragment = 0;// 分段序号
        protected long windowsSize = 0; // windows ，窗口大小
        protected long timestamp = 0;//发送的时间戳
        protected long sequenceNumber = 0;// segment 序号
        protected long unacknowledged = 0; // 当前未收到的序号: 即代表这个序号之前的包均收到
        protected long repetitionSendTimestamp = 0; //重发的时间戳
        protected long rto = 0; // 超时重传的时间间隔 Retransmission TimeOut
        protected long fastAck = 0; // ack 跳过的次数, 用于快速重传
        protected long xmit = 0;// 发送的次数，即重传的次数
        protected byte[] data;

        protected Segment(int size) {
            this.data = new byte[size];
        }


        //---------------------------------------------------------------------
        // ikcp_encode_seg
        //---------------------------------------------------------------------
        // encode a segment into buffer
        protected int encode(byte[] ptr, int offset) {
            int offset_ = offset;

            ikcp_encode32u(ptr, offset, conversation);
            offset += 4;
            ikcp_encode8u(ptr, offset, (byte) cmd);
            offset += 1;
            ikcp_encode8u(ptr, offset, (byte) fragment);
            offset += 1;
            ikcp_encode16u(ptr, offset, (int) windowsSize);
            offset += 2;
            ikcp_encode32u(ptr, offset, timestamp);
            offset += 4;
            ikcp_encode32u(ptr, offset, sequenceNumber);
            offset += 4;
            ikcp_encode32u(ptr, offset, unacknowledged);
            offset += 4;
            ikcp_encode32u(ptr, offset, (long) data.length);
            offset += 4;

            return offset - offset_;
        }
    }


    long conv = 0;
    //long user = user;
    long snd_una = 0;
    long snd_nxt = 0;
    long rcv_nxt = 0;
    long ts_recent = 0;
    long ts_lastack = 0;
    long ts_probe = 0;
    long probe_wait = 0;
    long snd_wnd = IKCP_WND_SND;
    long rcv_wnd = IKCP_WND_RCV;
    long rmt_wnd = IKCP_WND_RCV;
    long cwnd = 0;
    long incr = 0;
    long probe = 0;
    long mtu = IKCP_MTU_DEF;
    long mss = this.mtu - IKCP_OVERHEAD;
    byte[] buffer = new byte[(int) (mtu + IKCP_OVERHEAD) * 3];
    ArrayList<Segment> nrcv_buf = new ArrayList<>(128);
    ArrayList<Segment> nsnd_buf = new ArrayList<>(128);
    ArrayList<Segment> nrcv_que = new ArrayList<>(128);
    ArrayList<Segment> nsnd_que = new ArrayList<>(128);
    long state = 0;
    ArrayList<Long> acklist = new ArrayList<>(128);
    //long ackblock = 0;
    //long ackcount = 0;
    long rx_srtt = 0;
    long rx_rttval = 0;
    long rx_rto = IKCP_RTO_DEF;
    long rx_minrto = IKCP_RTO_MIN;
    long current = 0;
    long interval = IKCP_INTERVAL;
    long ts_flush = IKCP_INTERVAL;
    long nodelay = 0;
    long updated = 0;
    long logmask = 0;
    long ssthresh = IKCP_THRESH_INIT;
    long fastresend = 0;
    long nocwnd = 0;
    long xmit = 0;
    long dead_link = IKCP_DEADLINK;
    //long output = NULL;
    //long writelog = NULL;

    public KCP(long conv_) {
        conv = conv_;
    }

    //---------------------------------------------------------------------
    // user/upper level recv: returns size, returns below zero for EAGAIN
    //---------------------------------------------------------------------
    // 将接收队列中的数据传递给上层引用
    public int Recv(byte[] buffer) {

        if (0 == nrcv_que.size()) {
            return -1;
        }

        int peekSize = 0;//PeekSize();
        if (0 > peekSize) {
            return -2;
        }

        if (peekSize > buffer.length) {
            return -3;
        }

        boolean recover = false;
        if (nrcv_que.size() >= rcv_wnd) {
            recover = true;
        }

        // merge fragment.
        int count = 0;
        int n = 0;
        for (Segment seg : nrcv_que) {
            System.arraycopy(seg.data, 0, buffer, n, seg.data.length);
            n += seg.data.length;
            count++;
            if (0 == seg.fragment) {
                break;
            }
        }

        if (0 < count) {
            slice(nrcv_que, count, nrcv_que.size());
        }

        // move available data from rcv_buf -> nrcv_que
        count = 0;
        for (Segment seg : nrcv_buf) {
            if (seg.sequenceNumber == rcv_nxt && nrcv_que.size() < rcv_wnd) {
                nrcv_que.add(seg);
                rcv_nxt++;
                count++;
            } else {
                break;
            }
        }

        if (0 < count) {
            slice(nrcv_buf, count, nrcv_buf.size());
        }

        // fast recover
        if (nrcv_que.size() < rcv_wnd && recover) {
            // ready to send back IKCP_CMD_WINS in ikcp_flush
            // tell remote my window size
            probe |= IKCP_ASK_TELL;
        }

        return n;
    }

}



















