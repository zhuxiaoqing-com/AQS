package com.example.networkProtocol.kcp;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/17 16:48
 * @Description:
 */

import java.util.ArrayList;

/**
 * https://blog.csdn.net/iteye_19871/article/details/81575044
 * <p>
 * 净负荷是相对开销而言的。因为互联网是分层结构，数据自上向下转换时，每层都会加上一定的开销(overhead)，
 * 而原来的数据就被称为净荷(payload)。对于 IP 来说，一个 IP 包由头和数据组成，头即是开销，
 * 而后面的数据即是 IP 包的净荷。当然 IP 的净荷实际上也包括上层（如 TCP、UDP 等）的开销，
 * 而整个 IP 包又是其下层（如以太网 MAC）的净荷。
 */

/**
 * |<------------ 4 bytes ------------>|
 * +--------+--------+--------+--------+
 * |               conv                | conv：Conversation, 会话序号，用于标识收发数据包是否一致
 * +--------+--------+--------+--------+ cmd: command，用于标识指令，例如：push，ack等
 * |  cmd   |  frg   |       wnd       | frg: Fragment, 分段序号，序号从大到小
 * +--------+--------+--------+--------+ wnd: 接收窗口大小
 * |                ts                 | ts: 发送的时间戳
 * +--------+--------+--------+--------+
 * |                sn                 | sn: Segment序号
 * +--------+--------+--------+--------+
 * |                una                | una:下一个可接收的序列号。其实就是确认号，收到sn=10的包，una为11
 * +--------+--------+--------+--------+
 * |                len                | len: data数据的长度
 * +--------+--------+--------+--------+
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

    /**
     * 一个 byte 8字节
     * 要获取一个 32 位无符号数字; 就得返回 long 64 位
     * 网络默认为 大端序号
     * 大端小端区别，单位为字节 一个字节一个字节区分的
     * 比如 2 字节的数据 (0000 1111) (0000 0000)  括号内是一个字节(一个字节 8 bit )
     * 大端 是大端在前面 就是所谓的  0000 1111       0000 0000
     * 小端 是小端在前面 就是       0000 0000       0000 1111
     * 这就是大小端的区别，是以字节为单位的 大端在前 还是小端在前
     */
    /* decode 32 bits unsigned int (msb) */
    public static long ikcp_decode32u(byte[] p, int offset) {
        long ret = (p[offset + 0] & 0xFFL) << 24
                | (p[offset + 1] & 0xFFL) << 16
                | (p[offset + 2] & 0xFFL) << 8
                | p[offset + 3] & 0xFFL;
        return ret;
    }

    // 把 客户端已经确认接受到的数据删除

    /**
     * 其实做这么多就是把 start 前面的数据删了， 然后把 start 后面的数据移动到前面去
     */
    public static void slice(ArrayList list, int start, int stop) {
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            if (i < stop - start) {
                // 吧 start 后面的数据移动到前面来
                list.set(i, list.get(i + start));
            } else {
                // 把 start 后面的数据全部删掉
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
        protected long fragment = 0;// 分段序号 frg fragment
        protected long windowsSize = 0; // windows ，窗口大小
        protected long timestamp = 0;//发送的时间戳
        protected long sequenceNumber = 0;// segment 序号 message分片segment的序号，按1累次递增。
        protected long unacknowledged = 0; // 待接收消息序号(接收滑动窗口左端)。对于未丢包的网络来说，una是下一个可接收的序号，如收到sn=10的包，una为11。
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


    long conv = 0; // 会话id
    //long user = user;
    long snd_una = 0; // 第一个未确认的包
    long snd_nxt = 0; // 待发送包的序号
    long rcv_nxt = 0; // 待接收消息的序号
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
    // 已经接受的缓存
    ArrayList<Segment> nrcv_buf = new ArrayList<>(128);
    // 已经发送的缓存
    ArrayList<Segment> nsnd_buf = new ArrayList<>(128);
    ArrayList<Segment> nrcv_que = new ArrayList<>(128);
    ArrayList<Segment> nsnd_que = new ArrayList<>(128);
    long state = 0; // 连接状态
    ArrayList<Long> acklist = new ArrayList<>(128);
    //long ackblock = 0;
    //long ackcount = 0;
    long rx_srtt = 0; // ack接收rtt平滑值(smoothed)
    long rx_rttval = 0; // ack接收rtt浮动值 rtt 偏差？
    long rx_rto = IKCP_RTO_DEF; // 由ack接收延迟计算出来的复原时间
    long rx_minrto = IKCP_RTO_MIN; // 最小复原时间 最小 rto 时间。 rto 拿来计算超时以后的重发协议时间
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

        int peekSize = PeekSize();
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


    //---------------------------------------------------------------------
    // peek data size
    //---------------------------------------------------------------------
    // check the size of next message in the recv queue
    // 计算接收队列中有多少可用的数据
    public int PeekSize() {
        if (0 == nrcv_que.size()) {
            return -1;
        }

        Segment seq = nrcv_que.get(0);

        if (0 == seq.fragment) {
            return seq.data.length;
        }

        if (nrcv_que.size() < seq.fragment + 1) {
            return -1;
        }

        int length = 0;

        for (Segment item : nrcv_que) {
            length += item.data.length;
            if (0 == item.fragment) {
                break;
            }
        }

        return length;
    }

    //---------------------------------------------------------------------
    // user/upper level send, returns below zero for error
    //---------------------------------------------------------------------
    // 上层要发送的数据丢给发送队列，发送队列会根据mtu大小分片
    public int Send(byte[] buffer) {

        if (0 == buffer.length) {
            return -1;
        }

        int count;

        // 根据mss大小分片
        if (buffer.length < mss) {
            count = 1;
        } else {
            count = (int) (buffer.length + mss - 1) / (int) mss;
        }

        if (255 < count) {
            return -2;
        }

        if (0 == count) {
            count = 1;
        }

        int offset = 0;

        // 分片后加入到发送队列
        int length = buffer.length;
        for (int i = 0; i < count; i++) {
            int size = (int) (length > mss ? mss : length);
            Segment seg = new Segment(size);
            System.arraycopy(buffer, offset, seg.data, 0, size);
            offset += size;
            seg.fragment = count - i - 1;
            nsnd_que.add(seg);
            length -= size;
        }
        return 0;
    }

    //---------------------------------------------------------------------
    // parse ack

    /**
     * RTP:RTO主要考虑RTT的期望值和偏差
     * 综上的理解，我们就可以很快理解并记住一下公式。
     * 第一次测量时候，RTTm是我们测量的结果，RTTs=RTTm,RTTD=RTTm/2
     *
     * 以后每次测量采用以下公式
     * 	RTTs=(1-a)*RTTs+a*RTTm, a通常为1/8,即0.125 ...... 公式1
     * 	RTTD=(1-b)*RTTD+b*|RTTm-RTTs|, b通常为1/4,即0.25 ...... 公式2
     * 	RTO = RTTs+4RTTD ...... 公式3
     *
     * 	说明：公式3不管是第一次计算还是最后一次，都是采用该公式。
     */
    void update_ack(int rtt) {
        // 如果rtt为零 初始化 rtt
        if (0 == rx_srtt) {
            rx_srtt = rtt;
            rx_rttval = rtt / 2;
        } else {
            int delta = (int) (rtt - rx_srtt);
            if (delta < 0) {
                delta = -delta;
            }

            rx_rttval = (3 * rx_rttval + delta) / 4;
            rx_srtt = (7 * rx_srtt + rtt) / 8;
            if (rx_srtt < 1) {
                rx_srtt = 1;
            }
        }

        int rto = (int) (rx_srtt + _imax_(1, 4 * rx_rttval));
        rx_rto = _ibound_(rx_minrto, rto, IKCP_RTO_MAX);
    }

    // 计算本地真实 snd_una ;   更新 snd_una
    void shrink_buf() {
        if (nsnd_buf.size() > 0) {
            snd_una = nsnd_buf.get(0).sequenceNumber;
        } else {
            snd_una = snd_nxt;
        }
    }

    // 对端返回的ack, 确认发送成功时，对应包从发送缓存中移除
    void parse_ack(long sn) {
        // 当前序列号比未发送的
        if (_itimediff(sn, snd_una) < 0 || _itimediff(sn, snd_nxt) >= 0) {
            return;
        }

        int index = 0;
        for (Segment seg : nsnd_buf) {
            if (_itimediff(sn, seg.sequenceNumber) < 0) {
                break;
            }

            // 原版ikcp_parse_fastack&ikcp_parse_ack逻辑重复
            seg.fastAck++;

            if (sn == seg.sequenceNumber) {
                nsnd_buf.remove(index);
                break;
            }
            index++;
        }
    }

    // 通过对端传回的una 将已经确认发送成功包从发送缓存中移除
    void parse_una(long una) {
        int count = 0;
        for (Segment seg : nsnd_buf) {
            if (_itimediff(una, seg.sequenceNumber) > 0) {
                count++;
            } else {
                break;
            }
        }

        if (count > 0) {
            slice(nsnd_buf, count, nsnd_buf.size());
        }
    }

    //---------------------------------------------------------------------
    // ack append
    //---------------------------------------------------------------------
    // 收数据包后需要给对端回ack，flush时发送出去
    void ack_push(long sn, long ts) {
        // c原版实现中按*2扩大容量
        acklist.add(sn);
        acklist.add(ts);
    }

    //---------------------------------------------------------------------
    // parse data
    //---------------------------------------------------------------------
    // 用户数据包解析
    void parse_data(Segment newseg) {
        long sn = newseg.sequenceNumber;
        boolean repeat = false;

        if (_itimediff(sn, rcv_nxt + rcv_wnd) >= 0 || _itimediff(sn, rcv_nxt) < 0) {
            return;
        }

        int n = nrcv_buf.size() - 1;
        int after_idx = -1;

        // 判断是否是重复包，并且计算插入位置
        for (int i = n; i >= 0; i--) {
            Segment seg = nrcv_buf.get(i);
            if (seg.sequenceNumber == sn) {
                repeat = true;
                break;
            }

            if (_itimediff(sn, seg.sequenceNumber) > 0) {
                after_idx = i;
                break;
            }
        }

        // 如果不是重复包，则插入
        if (!repeat) {
            if (after_idx == -1) {
                nrcv_buf.add(0, newseg);
            } else {
                nrcv_buf.add(after_idx + 1, newseg);
            }
        }

        // move available data from nrcv_buf -> nrcv_que
        // 将连续包加入到接收队列
        int count = 0;
        for (Segment seg : nrcv_buf) {
            if (seg.sequenceNumber == rcv_nxt && nrcv_que.size() < rcv_wnd) {
                nrcv_que.add(seg);
                rcv_nxt++;
                count++;
            } else {
                break;
            }
        }

        // 从接收缓存中移除
        if (0 < count) {
            slice(nrcv_buf, count, nrcv_buf.size());
        }
    }

    // when you received a low level packet (eg. UDP packet), call it
    //---------------------------------------------------------------------
    // input data
    //---------------------------------------------------------------------
    // 底层收包后调用，再由上层通过Recv获得处理后的数据
    public int Input(byte[] data) {
        // 当前未收到的序列号，即该序列号前的数据均已收到
        long s_una = snd_una;
        // 数据长度没有 kcp 的头大
        if (data.length < IKCP_OVERHEAD) {
            return 0;
        }

        int offset = 0;

        while (true) {
            long timestamp, sequenceNumber, length, unacknowledged, conversation;
            int wnd;
            byte cmd, frg;

            if (data.length - offset < IKCP_OVERHEAD) {
                break;
            }

            // 获取kcp 会话序号;会话序号不同说明不是同一个 kcp 连接
            conversation = ikcp_decode32u(data, offset);
            offset += 4;
            if (conv != conversation) {
                return -1;
            }
            //cmd command，用于标识指令，例如：push，ack等
            cmd = ikcp_decode8u(data, offset);
            offset += 1;
            //Fragment, 分段序号，序号从大到小
            frg = ikcp_decode8u(data, offset);
            offset += 1;
            // wnd: 接收窗口大小
            wnd = ikcp_decode16u(data, offset);
            offset += 2;
            // 发送的时间戳
            timestamp = ikcp_decode32u(data, offset);
            offset += 4;
            // sn: Segment序号 和 tcp 的序列号一样
            sequenceNumber = ikcp_decode32u(data, offset);
            offset += 4;
            //  Unacknowledged, 当前未收到的序号，即代表这个序号之前的包均收到
            unacknowledged = ikcp_decode32u(data, offset);
            offset += 4;
            // data 数据的长度 (消息体 不包括消息头)
            length = ikcp_decode32u(data, offset);
            offset += 4;

            // 如果消息体长度不对 udp 要么全部收到 要么全部丢失 不会有丢一半这样的;
            if (data.length - offset < length) {
                return -2;
            }
            // 如果是未知的指令
            if (cmd != IKCP_CMD_PUSH && cmd != IKCP_CMD_ACK && cmd != IKCP_CMD_WASK && cmd != IKCP_CMD_WINS) {
                return -3;
            }
            // 记录当前发送方的 当前窗口大小
            rmt_wnd = (long) wnd;
            // 根据客户端已经接收到的序号，删除缓存中客户端已经已经接受到的数据
            parse_una(unacknowledged);
            shrink_buf();

            if (IKCP_CMD_ACK == cmd) {
                if (_itimediff(current, timestamp) >= 0) {
                    // 计算 rto
                    update_ack(_itimediff(current, timestamp));
                }
                parse_ack(sequenceNumber);
                shrink_buf();
            } else if (IKCP_CMD_PUSH == cmd) {
                if (_itimediff(sequenceNumber, rcv_nxt + rcv_wnd) < 0) {
                    ack_push(sequenceNumber, timestamp);
                    if (_itimediff(sequenceNumber, rcv_nxt) >= 0) {
                        Segment seg = new Segment((int) length);
                        seg.conversation = conversation;
                        seg.cmd = cmd;
                        seg.fragment = frg;
                        seg.windowsSize = wnd;
                        seg.timestamp = timestamp;
                        seg.sequenceNumber = sequenceNumber;
                        seg.unacknowledged = unacknowledged;

                        if (length > 0) {
                            System.arraycopy(data, offset, seg.data, 0, (int) length);
                        }

                        parse_data(seg);
                    }
                }
            } else if (IKCP_CMD_WASK == cmd) {
                // ready to send back IKCP_CMD_WINS in Ikcp_flush
                // tell remote my window size
                probe |= IKCP_ASK_TELL;
            } else if (IKCP_CMD_WINS == cmd) {
                // do nothing
            } else {
                return -3;
            }

            offset += (int) length;
        }

        if (_itimediff(snd_una, s_una) > 0) {
            if (cwnd < rmt_wnd) {
                long mss_ = mss;
                if (cwnd < ssthresh) {
                    cwnd++;
                    incr += mss_;
                } else {
                    if (incr < mss_) {
                        incr = mss_;
                    }
                    incr += (mss_ * mss_) / incr + (mss_ / 16);
                    if ((cwnd + 1) * mss_ <= incr) {
                        cwnd++;
                    }
                }
                if (cwnd > rmt_wnd) {
                    cwnd = rmt_wnd;
                    incr = rmt_wnd * mss_;
                }
            }
        }

        return 0;
    }

    // 接收窗口可用大小
    int wnd_unused() {
        if (nrcv_que.size() < rcv_wnd) {
            return (int) (int) rcv_wnd - nrcv_que.size();
        }
        return 0;
    }

    //---------------------------------------------------------------------
    // ikcp_flush
    //---------------------------------------------------------------------
    void flush() {
        long current_ = current;
        byte[] buffer_ = buffer;
        int change = 0;
        int lost = 0;

        // 'ikcp_update' haven't been called.
        if (0 == updated) {
            return;
        }

        Segment seg = new Segment(0);
        seg.conversation = conv;
        seg.cmd = IKCP_CMD_ACK;
        seg.windowsSize = (long) wnd_unused();
        seg.unacknowledged = rcv_nxt;

        // flush acknowledges
        // 将acklist中的ack发送出去
        int count = acklist.size() / 2;
        int offset = 0;
        for (int i = 0; i < count; i++) {
            if (offset + IKCP_OVERHEAD > mtu) {
                output(buffer, offset);
                offset = 0;
            }
            // ikcp_ack_get
            seg.sequenceNumber = acklist.get(i * 2 + 0);
            seg.timestamp = acklist.get(i * 2 + 1);
            offset += seg.encode(buffer, offset);
        }
        acklist.clear();

        // probe window size (if remote window size equals zero)
        // rmt_wnd=0时，判断是否需要请求对端接收窗口
        if (0 == rmt_wnd) {
            if (0 == probe_wait) {
                probe_wait = IKCP_PROBE_INIT;
                ts_probe = current + probe_wait;
            } else {
                // 逐步扩大请求时间间隔
                if (_itimediff(current, ts_probe) >= 0) {
                    if (probe_wait < IKCP_PROBE_INIT) {
                        probe_wait = IKCP_PROBE_INIT;
                    }
                    probe_wait += probe_wait / 2;
                    if (probe_wait > IKCP_PROBE_LIMIT) {
                        probe_wait = IKCP_PROBE_LIMIT;
                    }
                    ts_probe = current + probe_wait;
                    probe |= IKCP_ASK_SEND;
                }
            }
        } else {
            ts_probe = 0;
            probe_wait = 0;
        }

        // flush window probing commands
        // 请求对端接收窗口
        if ((probe & IKCP_ASK_SEND) != 0) {
            seg.cmd = IKCP_CMD_WASK;
            if (offset + IKCP_OVERHEAD > mtu) {
                output(buffer, offset);
                offset = 0;
            }
            offset += seg.encode(buffer, offset);
        }

        // flush window probing commands(c#)
        // 告诉对端自己的接收窗口
        if ((probe & IKCP_ASK_TELL) != 0) {
            seg.cmd = IKCP_CMD_WINS;
            if (offset + IKCP_OVERHEAD > mtu) {
                output(buffer, offset);
                offset = 0;
            }
            offset += seg.encode(buffer, offset);
        }

        probe = 0;

        // calculate window size
        long cwnd_ = _imin_(snd_wnd, rmt_wnd);
        // 如果采用拥塞控制
        if (0 == nocwnd) {
            cwnd_ = _imin_(cwnd, cwnd_);
        }

        count = 0;
        // move data from snd_queue to snd_buf
        for (Segment nsnd_que1 : nsnd_que) {
            if (_itimediff(snd_nxt, snd_una + cwnd_) >= 0) {
                break;
            }
            Segment newseg = nsnd_que1;
            newseg.conversation = conv;
            newseg.cmd = IKCP_CMD_PUSH;
            newseg.windowsSize = seg.windowsSize;
            newseg.timestamp = current_;
            newseg.sequenceNumber = snd_nxt;
            newseg.unacknowledged = rcv_nxt;
            newseg.repetitionSendTimestamp = current_;
            newseg.rto = rx_rto;
            newseg.fastAck = 0;
            newseg.xmit = 0;
            nsnd_buf.add(newseg);
            snd_nxt++;
            count++;
        }

        if (0 < count) {
            slice(nsnd_que, count, nsnd_que.size());
        }

        // calculate resent
        long resent = (fastresend > 0) ? fastresend : 0xffffffff;
        long rtomin = (nodelay == 0) ? (rx_rto >> 3) : 0;

        // flush data segments
        for (Segment segment : nsnd_buf) {
            boolean needsend = false;
            if (0 == segment.xmit) {
                // 第一次传输
                needsend = true;
                segment.xmit++;
                segment.rto = rx_rto;
                segment.repetitionSendTimestamp = current_ + segment.rto + rtomin;
            } else if (_itimediff(current_, segment.repetitionSendTimestamp) >= 0) {
                // 丢包重传
                needsend = true;
                segment.xmit++;
                xmit++;
                if (0 == nodelay) {
                    segment.rto += rx_rto;
                } else {
                    segment.rto += rx_rto / 2;
                }
                segment.repetitionSendTimestamp = current_ + segment.rto;
                lost = 1;
            } else if (segment.fastAck >= resent) {
                // 快速重传
                needsend = true;
                segment.xmit++;
                segment.fastAck = 0;
                segment.repetitionSendTimestamp = current_ + segment.rto;
                change++;
            }

            if (needsend) {
                segment.timestamp = current_;
                segment.windowsSize = seg.windowsSize;
                segment.unacknowledged = rcv_nxt;

                int need = IKCP_OVERHEAD + segment.data.length;
                if (offset + need >= mtu) {
                    output(buffer, offset);
                    offset = 0;
                }

                offset += segment.encode(buffer, offset);
                if (segment.data.length > 0) {
                    System.arraycopy(segment.data, 0, buffer, offset, segment.data.length);
                    offset += segment.data.length;
                }

                if (segment.xmit >= dead_link) {
                    state = -1; // state = 0(c#)
                }
            }
        }

        // flash remain segments
        if (offset > 0) {
            output(buffer, offset);
        }

        // update ssthresh
        // 拥塞避免
        if (change != 0) {
            long inflight = snd_nxt - snd_una;
            ssthresh = inflight / 2;
            if (ssthresh < IKCP_THRESH_MIN) {
                ssthresh = IKCP_THRESH_MIN;
            }
            cwnd = ssthresh + resent;
            incr = cwnd * mss;
        }

        if (lost != 0) {
            ssthresh = cwnd / 2;
            if (ssthresh < IKCP_THRESH_MIN) {
                ssthresh = IKCP_THRESH_MIN;
            }
            cwnd = 1;
            incr = mss;
        }

        if (cwnd < 1) {
            cwnd = 1;
            incr = mss;
        }
    }

    //---------------------------------------------------------------------
    // update state (call it repeatedly, every 10ms-100ms), or you can ask
    // ikcp_check when to call it again (without ikcp_input/_send calling).
    // 'current' - current timestamp in millisec.
    //---------------------------------------------------------------------
    public void Update(long current_) {

        current = current_;

        // 首次调用Update
        if (0 == updated) {
            updated = 1;
            ts_flush = current;
        }

        // 两次更新间隔
        int slap = _itimediff(current, ts_flush);

        // interval设置过大或者Update调用间隔太久
        if (slap >= 10000 || slap < -10000) {
            ts_flush = current;
            slap = 0;
        }

        // flush同时设置下一次更新时间
        if (slap >= 0) {
            ts_flush += interval;
            if (_itimediff(current, ts_flush) >= 0) {
                ts_flush = current + interval;
            }
            flush();
        }
    }

    //---------------------------------------------------------------------
    // Determine when should you invoke ikcp_update:
    // returns when you should invoke ikcp_update in millisec, if there
    // is no ikcp_input/_send calling. you can call ikcp_update in that
    // time, instead of call update repeatly.
    // Important to reduce unnacessary ikcp_update invoking. use it to
    // schedule ikcp_update (eg. implementing an epoll-like mechanism,
    // or optimize ikcp_update when handling massive kcp connections)
    //---------------------------------------------------------------------
    public long Check(long current_) {

        long ts_flush_ = ts_flush;
        long tm_flush = 0x7fffffff;
        long tm_packet = 0x7fffffff;
        long minimal;

        if (0 == updated) {
            return current_;
        }

        if (_itimediff(current_, ts_flush_) >= 10000 || _itimediff(current_, ts_flush_) < -10000) {
            ts_flush_ = current_;
        }

        if (_itimediff(current_, ts_flush_) >= 0) {
            return current_;
        }

        tm_flush = _itimediff(ts_flush_, current_);

        for (Segment seg : nsnd_buf) {
            int diff = _itimediff(seg.repetitionSendTimestamp, current_);
            if (diff <= 0) {
                return current_;
            }
            if (diff < tm_packet) {
                tm_packet = diff;
            }
        }

        minimal = tm_packet < tm_flush ? tm_packet : tm_flush;
        if (minimal >= interval) {
            minimal = interval;
        }

        return current_ + minimal;
    }

    // change MTU size, default is 1400
    public int SetMtu(int mtu_) {
        if (mtu_ < 50 || mtu_ < (int) IKCP_OVERHEAD) {
            return -1;
        }

        byte[] buffer_ = new byte[(mtu_ + IKCP_OVERHEAD) * 3];
        if (null == buffer_) {
            return -2;
        }

        mtu = (long) mtu_;
        mss = mtu - IKCP_OVERHEAD;
        buffer = buffer_;
        return 0;
    }

    public int Interval(int interval_) {
        if (interval_ > 5000) {
            interval_ = 5000;
        } else if (interval_ < 10) {
            interval_ = 10;
        }
        interval = (long) interval_;
        return 0;
    }

    // fastest: ikcp_nodelay(kcp, 1, 20, 2, 1)
    // nodelay: 0:disable(default), 1:enable
    // interval: internal update timer interval in millisec, default is 100ms
    // resend: 0:disable fast resend(default), 1:enable fast resend
    // nc: 0:normal congestion control(default), 1:disable congestion control
    public int NoDelay(int nodelay_, int interval_, int resend_, int nc_) {

        if (nodelay_ >= 0) {
            nodelay = nodelay_;
            if (nodelay_ != 0) {
                rx_minrto = IKCP_RTO_NDL;
            } else {
                rx_minrto = IKCP_RTO_MIN;
            }
        }

        if (interval_ >= 0) {
            if (interval_ > 5000) {
                interval_ = 5000;
            } else if (interval_ < 10) {
                interval_ = 10;
            }
            interval = interval_;
        }

        if (resend_ >= 0) {
            fastresend = resend_;
        }

        if (nc_ >= 0) {
            nocwnd = nc_;
        }

        return 0;
    }

    // set maximum window size: sndwnd=32, rcvwnd=32 by default
    public int WndSize(int sndwnd, int rcvwnd) {
        if (sndwnd > 0) {
            snd_wnd = (long) sndwnd;
        }

        if (rcvwnd > 0) {
            rcv_wnd = (long) rcvwnd;
        }
        return 0;
    }

    // get how many packet is waiting to be sent
    public int WaitSnd() {
        return nsnd_buf.size() + nsnd_que.size();
    }
}



















