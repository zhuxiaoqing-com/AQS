package com.example.networkProtocol.kcp.simple;

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
        protected long fragment = 0;// 分段序号 frg fragment 从大到小,比如 数据 123; 分片 frg 序号就是 2(1) 1(2) 0(3)
        protected long windowsSize = 0; // windows ，窗口大小
        /*
        push ： 发送的时间
        ack ： 发送方发送push的时间
         */
        protected long timestamp = 0;//发送的时间戳
        /*
        push 当前发送的   发送方的sn
        ack  当前接收到的 接收方的sn
         */
        protected long sequenceNumber = 0;//  如果是 Push 就是发送方当前发送的数据的序号(是发送方的sn);  如果是ack就是确认发送方的序号(是接受方的sn)
        /*
        待接收消息序号(接收滑动窗口左端)。对于未丢包的网络来说，
        una是下一个可接收的序号(永远都是发送方的una)，una:接受窗口左端;una 前面的sn已经全部被接收
        如收到sn=10的包，una为11。 已经确认对方 udp 包的序号+1; 如收到sn=10的包，una为11。
         */

        protected long unacknowledged = 0;
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


    private long conv = 0; // 会话id
    //long user = user;
    private long snd_una = 0; // 第一个未确认的包
    private long snd_nxt = 0; // 待发送包的序号
    private long rcv_nxt = 0; // 待接收消息的序号
    long ts_recent = 0;
    long ts_lastack = 0;
    private long ts_probe = 0; // 下次探查窗口的时间戳
    private long probe_wait = 0; // 探查窗口需要等待的时间
    private long snd_wnd = IKCP_WND_SND; // 发送窗口大小
    private long rcv_wnd = IKCP_WND_RCV; // 接收窗口大小
    private long rmt_wnd = IKCP_WND_RCV; // 远端接收窗口大小
    private long cwnd = 0; // 拥塞窗口大小
    private long incr = 0; //  可发送的最大数据量
    private long probe = 0;
    private int mtu = IKCP_MTU_DEF;
    private int mss = this.mtu - IKCP_OVERHEAD;
    private byte[] buffer = new byte[(mtu + IKCP_OVERHEAD) * 3];
    // 已经接受的缓存
    private ArrayList<Segment> nrcv_buf = new ArrayList<>(128);
    // 已经发送但是未收到ack的缓存
    private ArrayList<Segment> nsnd_buf = new ArrayList<>(128);
    private ArrayList<Segment> nrcv_que = new ArrayList<>(128);
    private ArrayList<Segment> nsnd_que = new ArrayList<>(128);
    private long state = 0; // 连接状态 0xFFFFFFFF (-1)表示断开连接
    private ArrayList<Long> acklist = new ArrayList<>(128);
    //long ackblock = 0;
    //long ackcount = 0;
    private long rx_srtt = 0; // ack接收rtt平滑值(smoothed)
    private long rx_rttval = 0; // ack接收rtt浮动值 rtt 偏差？
    private long rx_rto = IKCP_RTO_DEF; // 由ack接收延迟计算出来的复原时间
    private long rx_minrto = IKCP_RTO_MIN; // 最小复原时间 最小 rto 时间。 rto 拿来计算超时以后的重发协议时间
    private long current = 0;
    private long interval = IKCP_INTERVAL; // 刷新间隔
    private long ts_flush = IKCP_INTERVAL; // 下次flush刷新时间戳
    private long nodelay = 0; // 	是否启动无延迟模式
    private boolean updated = true; // 是否是首次调用 update 函数的标识
    long logmask = 0;
    private long ssthresh = IKCP_THRESH_INIT; // 慢启动值
    long fastresend = 0; //  触发快速重传的重复ack个数
    private long nocwnd = 0; // 	取消拥塞控制
    //long xmit = 0;
    private long dead_link = IKCP_DEADLINK; // 死亡的连接
    //long output = NULL;
    //long writelog = NULL;

    /*
    慢启动和阻塞避免
    ssthresh 慢启动值 ;   cwnd 阻塞避免窗口
    1、对于一个给定的连接, 初始化 cwnd 为 1 个报文段; ssthresh 为 65535 字节;
    2、 TCP 输出例程的输出不能超过 cwnd 和 接收方 通告窗口的大小。
        拥塞避免是发送方使用的流量控制, 而通告窗口则是接收方进行的流量控制。
        前者是发送方感受到的网络拥塞的估计, 而后者则是与接收方在该连接上的可用缓存大小有关

    3、当拥塞发生时(超时或收到重复确认),ssthresh 被设置为当前窗口大小的一半(cwnd 和接收方通告窗口大小的最小值，但最少为2个报文段)。
       此外，如果超时引起了拥塞，则 cwnd 被设置为 1 个报文段(慢启动)

    4、当新的数据被对方确认时，就增加 cwnd，但增加的方法依赖于我们是否正在进行慢启动或拥塞避免。
    如果 cwnd <= ssthresh, 则正在进行慢启动，否则正在进行拥塞避免。
    慢启动一直持续到我们回到当拥塞发生时所处位置的一半的时候才停止,(因为我们记录了在步骤2中给我们制造麻烦的窗口大小的一半），然后转为执行拥塞避免。


    慢启动算法初始设置 cwnd 为1个报文段，此后每收到一个确认就加 1。
    这会使窗口按指数方式增长：发送1个报文段，然后是2个，接着是4个......。

    拥塞避免算法要求每次收到一个确认时将cwnd增加1/cwnd。
    与慢启动的指数增加比起来，这是一种加性增长(additive increase)。
    我们希望在一个往返时间内最多为cwnd增加1个报文段（不管在这个RTT中收到了多少个ACK），
    然而慢启动将根据这个往返时间中所收到的确认的个数增加cwnd。
     */

    /*
    快速恢复算法
    1、 当收到第3个重复的ACK时，将ssthresh设置为当前拥塞窗口cwnd的一半。重传丢失的报文段。设置cwnd为ssthresh加上3倍的报文段大小。
    2、每次收到另一个重复的ACK时，cwnd增加1个报文段大小并发送1个分组（如果新的cwnd允许发送）。
    3、当下一个确认新数据的ACK到达时，设置cwnd为ssthresh（在第1步中设置的值）。
    这个ACK应该是在进行重传后的一个往返时间内对步骤1中重传的确认。
    另外，这个ACK也应该是对丢失的分组和收到的第1个重复的ACK之间的所有中间报文段的确认。
    这一步采用的是拥塞避免，因为当分组丢失时我们将当前的速率减半。

    为啥要 对步骤1中重传的确认以后才将 cwnd 减半呢?
    因为没有收到确认的时候可能发送缓存(已经发送还未收到ack的数据),可能已经占满了 cwnd 了;
    如果这个时候就降低 cwnd, 那是不是如果没有响应(对步骤1中重传的确认), 就永远也发送不了新的数据了呢?

    等到 对步骤1中重传的确认) 回来以后, 发送缓存就可以清一清,然后再降低 cwnd, 就算cwnd降低了，也还是可以发送新的数据了
     */
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
        if (peekSize < 0) {
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

        // 窗口大小更新
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

        if (seq.fragment == 0) {
            return seq.data.length;
        }

        // nrcv_que 还没有收集到所有的 fragment
        if (seq.fragment + 1 > nrcv_que.size()) {
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
            // 相当于 Math.ceil()
            count = (int) (buffer.length + mss - 1) / (int) mss;
        }
        // 数据太大
        if (count > 255) {
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

    /**
     * 该版本不支持 流模式, input 那里没法保存没有读取完的流;
     * 需要上层搭建一个缓存 缓存未读完的数据流;
     * 流模式和普通模式的对比
     * 普通模式：如果需要拆包;就拆分为多个 kcp 包; 一个 kcp 包(拆分后的包)就是一个 udp 包
     * 流模式：  因为是流模式;所有就尽量填满上一个待发送的包的数据;
     * 1、先判断上一个待发送的包的数据达到了 mss 没有，没有的话就先把上一个待发送的包的数据填充满;
     * 2、然后再正常分包
     *
     * @param buffer
     * @return
     */
    // 上层要发送的数据丢给发送队列，发送队列会根据mtu大小分片
    public int SendByStream(byte[] buffer) {

        if (0 == buffer.length) {
            return -1;
        }

        boolean stream = true;
        // append to previous segment in streaming mode (if possible)
        // 如果是流模式就将上一个的数据包数据填满
        if (stream && !nsnd_que.isEmpty()) {
            Segment segment = nsnd_que.get(nrcv_que.size() - 1);
            // 或者判断 cmd 是不是 ack
            if (segment.data != null && segment.data.length < mss) {
                int capacity = mss - segment.data.length;
                int extend = (buffer.length < capacity) ? buffer.length : capacity;
                // 将需要发送的数据添加到 segment 里面去
                //segment.data.add(buffer);
                // 如果已经被全部填充完了 直接 return
                if (buffer.length == 0) {
                    return 0;
                }
            }
        }

        int count;

        // 根据mss大小分片
        if (buffer.length < mss) {
            count = 1;
        } else {
            // 相当于 Math.ceil()
            count = (int) (buffer.length + mss - 1) / (int) mss;
        }
        // 数据太大
        if (count > 255) {
            return -2;
        }

        if (count == 0) {
            count = 1;
        }
        // fragment
        for (int i = 0; i < count; i++) {
            int size = buffer.length > mss ? mss : buffer.length;
            Segment seg = new Segment(size);
            //seg.data = (buffer, size);
            //seg.frg = this.stream ? 0 : count - i - 1;
            nrcv_que.add(seg);
        }

        return 0;
    }

    //---------------------------------------------------------------------
    // parse ack

    /**
     * RTP:RTO主要考虑RTT的期望值和偏差
     * 综上的理解，我们就可以很快理解并记住一下公式。
     * 第一次测量时候，RTTm是我们测量的结果，RTTs=RTTm,RTTD=RTTm/2
     * <p>
     * 以后每次测量采用以下公式
     * RTTs=(1-a)*RTTs+a*RTTm, a通常为1/8,即0.125 ...... 公式1
     * RTTD=(1-b)*RTTD+b*|RTTm-RTTs|, b通常为1/4,即0.25 ...... 公式2
     * RTO = RTTs+4RTTD ...... 公式3
     * <p>
     * 说明：公式3不管是第一次计算还是最后一次，都是采用该公式。
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
        // 当前ack确认的序列号  已经被确认收到了; || 当前ack确认的序列号 我们并没有发送过
        if (_itimediff(sn, snd_una) < 0 || _itimediff(sn, snd_nxt) >= 0) {
            return;
        }

        int index = 0;
        for (Segment seg : nsnd_buf) {
            if (_itimediff(sn, seg.sequenceNumber) < 0) {
                break;
            }
            // 把比 当前ack确认的序列号 小的序列号的快速重传 全部+1
            // 原版ikcp_parse_fastack&ikcp_parse_ack逻辑重复
            seg.fastAck++;

            // 当前序号已经确认了 已发送未确认窗口中删除
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
        // 如果当前发送过来的sn 比接收窗口大  ||   收到了已经收到的 sn  ; rcv_nxt 前面的包均已经收到
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

            // 获取插入位置
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
            // 接收队列也不能比接收窗口大
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
                // 当前序号比接受窗口大; 说明窗口满了 无法再接受当前序号了
                // 只有当前接受的序号比当前接受窗口小 才能接受
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
        // 用于缓存将要发送的字节流
        byte[] buffer_ = buffer;
        int fastReSendCount = 0; // 快速重传次数
        int timeoutCount = 0;

        // 'ikcp_update' haven't been called.
        if (updated) {
            return;
        }

        // 将ack 发送出去
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
                output(buffer_, offset);
                offset = 0;
            }
            // ikcp_ack_get
            seg.sequenceNumber = acklist.get(i * 2 + 0);
            seg.timestamp = acklist.get(i * 2 + 1);
            offset += seg.encode(buffer_, offset);
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
                    // tcp 是 指数退让; kcp 是 0.5 倍
                    probe_wait += probe_wait / 2;
                    if (probe_wait > IKCP_PROBE_LIMIT) {
                        probe_wait = IKCP_PROBE_LIMIT;
                    }
                    ts_probe = current + probe_wait;
                    // 表示需要发送窗口检测
                    probe |= IKCP_ASK_SEND;
                }
            }
        } else {
            // 如果对方接受窗口不为零; 不需要定时坚持器
            ts_probe = 0;
            probe_wait = 0;
        }

        // flush window probing commands
        // 请求对端接收窗口
        if ((probe & IKCP_ASK_SEND) != 0) {
            seg.cmd = IKCP_CMD_WASK;
            if (offset + IKCP_OVERHEAD > mtu) {
                output(buffer_, offset);
                offset = 0;
            }
            offset += seg.encode(buffer_, offset);
        }

        // flush window probing commands(c#)
        // 告诉对端自己的接收窗口
        if ((probe & IKCP_ASK_TELL) != 0) {
            seg.cmd = IKCP_CMD_WINS;
            if (offset + IKCP_OVERHEAD > mtu) {
                output(buffer_, offset);
                offset = 0;
            }
            offset += seg.encode(buffer_, offset);
        }

        // 初始化命令
        probe = 0;

        // calculate window size
        long cwnd_ = _imin_(snd_wnd, rmt_wnd);
        // 如果采用拥塞控制
        if (0 == nocwnd) {
            //cwnd=min(snd_wnd,rmt_wnd,cwnd)的最小值
            cwnd_ = _imin_(cwnd, cwnd_);
        }

        count = 0;
        // move data from snd_queue to snd_buf
        for (Segment nsnd_que1 : nsnd_que) {
            // 如果要发送的数据大于拥塞窗口
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

        if (count > 0) {
            slice(nsnd_que, count, nsnd_que.size());
        }

        // calculate resent
        // 触发快速重传的重复ack个数 ; 如果次数为0, 就给一个超级大的数
        long resent = (fastresend > 0) ? fastresend : 0xffffffff;
        // 如果没有开启无延迟; 就按照 rto /2^3  8;
        long rtomin = (nodelay == 0) ? (rx_rto >> 3) : 0;

        // flush data segments
        for (Segment segment : nsnd_buf) {
            boolean needsend = false;
            if (0 == segment.xmit) { // 重传的次数
                // 第一次传输
                needsend = true;
                segment.xmit++;
                segment.rto = rx_rto;
                // 设置重发的时间戳
                segment.repetitionSendTimestamp = current_ + segment.rto + rtomin;
            } else if (_itimediff(current_, segment.repetitionSendTimestamp) >= 0) {
                // 丢包重传
                needsend = true;
                segment.xmit++;
                //xmit++;
                if (0 == nodelay) {
                    // 如果不是无延迟 每次加倍
                    segment.rto += rx_rto;
                } else {
                    // 无延迟模式 每次加 0.5
                    segment.rto += rx_rto / 2;
                }
                segment.repetitionSendTimestamp = current_ + segment.rto;
                timeoutCount = 1;
            } else if (segment.fastAck >= resent) {
                // 快速重传
                needsend = true;
                segment.xmit++;
                segment.fastAck = 0;
                segment.repetitionSendTimestamp = current_ + segment.rto;
                fastReSendCount++;
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

                // 重发次数超过了 dead_link
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
        // 如果进行了快速重传
        if (fastReSendCount != 0) {
            // 直接取当前发送窗口大小
            long inflight = snd_nxt - snd_una;
            ssthresh = inflight / 2;
            if (ssthresh < IKCP_THRESH_MIN) {
                ssthresh = IKCP_THRESH_MIN;
            }
            cwnd = ssthresh + resent;
            incr = cwnd * mss;
        }

        if (timeoutCount != 0) {
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
    public void Update1(long current_) {
        current = current_;

        // 首次调用Update
        if (updated) {
            updated = false;
            ts_flush = current;
        }

        // 两次更新间隔
        int slap = _itimediff(current, ts_flush);

        // interval设置过大或者Update调用间隔太久
        if (slap >= 10000 || slap < -10000) {
            ts_flush = current;
            slap = 0;
        }

        // 这样设置是 为了保证尽量可以保证刷新频率
        // flush同时设置下一次更新时间
        if (slap >= 0) {
            ts_flush += interval;
            if (_itimediff(current, ts_flush) >= 0) {
                ts_flush = current + interval;
            }
            flush();
        }
    }

    // 改版刷新时间
    public void Update(long current_) {
        current = current_;

        // 首次调用Update
        if (updated) {
            updated = false;
            ts_flush = current;
        }

        // 如果没有到刷新时间
        if (current_ < ts_flush) {
            return;
        }
        // 更新刷新时间
        ts_flush = current_ + interval;
        flush();
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

        if (updated) {
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
            // 找到一个最小的将要发送的包
            if (diff < tm_packet) {
                tm_packet = diff;
            }
        }
        // 如果最小将要发送的包 < 当前时间距离下次刷新时间的时间间隔
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

        mtu = mtu_;
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



















