-- ==== 调试精准版：多显示 UTC + 北京时间 + 原始值 ====
local myws = Proto("MyWS_Debug_Time", "【调试版】WS协议 + 1010时间戳(多时区显示)")

myws.fields = {
    ProtoField.uint32("myws.len",      "总长度", base.DEC),
    ProtoField.uint32("myws.cmdid",    "CmdID", base.DEC),
    ProtoField.uint16("myws.seq",      "Sequence", base.DEC),
    ProtoField.bytes ("myws.body",     "Body")
}

function myws.dissector(buf, pinfo, tree)
    if buf:len() < 10 then return false end

    local pktlen = buf(0,4):uint()
    if pktlen < 10 or pktlen > buf:len() or pktlen > 2000000 then return false end

    local cmdid = buf(4,4):uint()
    local seq   = buf(8,2):uint()

    local subtree = tree:add(myws, buf(0, pktlen),
        string.format("MyWS → CmdID=%d Len=%d Seq=%d", cmdid, pktlen, seq))

    subtree:add(myws.fields[1], buf(0,4)):append_text(string.format(" (0x%08X)", pktlen))
    subtree:add(myws.fields[2], buf(4,4)):append_text(string.format(" (0x%08X)", cmdid))
    subtree:add(myws.fields[3], buf(8,2))

    if pktlen > 10 then
        subtree:add(myws.fields[4], buf(10, pktlen-10))
    end

    -- ================ CmdID=1010 时间戳调试解析（Kryo 大端）===============
    if cmdid == 1010 and pktlen >= 18 then
        local ts_ms = buf(10,8):uint64()  -- 大端 long
        local ts_num = ts_ms:tonumber()   -- 原始毫秒值
        local sec_utc = ts_num // 1000
        local ms = ts_num % 1000

        -- UTC 时间（os.date 默认本地，但用 ! 强制 UTC）
        local utc_str = os.date("!%Y-%m-%d %H:%M:%S", sec_utc) .. string.format(".%03d UTC", ms)

        -- 北京时间：UTC 秒数 + 8 小时（28800 秒）= 强制 +8 时区，毫秒不变
        local sec_beijing = sec_utc + 28800  -- +8 小时
        local beijing_str = os.date("!%Y-%m-%d %H:%M:%S", sec_beijing) .. string.format(".%03d 北京", ms)

        -- 显示节点（调试用，原始值最重要！）
        local node_raw = subtree:add(buf(10,8), string.format("原始时间戳: %d ms (0x%016X)", ts_num, ts_num))
        node_raw:set_generated()

        local node_utc = subtree:add(buf(10,8), "UTC 时间: " .. utc_str)
        node_utc:set_generated()

        local node_bj = subtree:add(buf(10,8), "北京时间: " .. beijing_str .. "  Seq=" .. seq)
        node_bj:set_generated()

        -- Info 列显示原始值 + 北京时间（方便你对比日志）
        pinfo.cols.info:append(string.format(" | 原始=%d | 北京=%s", ts_num, beijing_str))
    end

    pinfo.cols.protocol = "MyWS"
    pinfo.cols.info = string.format("CmdID=%d Len=%d Seq=%d", cmdid, pktlen, seq)

    return true
end

myws:register_heuristic("ws", myws.dissector)

print("【时间调试版加载成功！】")
print("   → 显示原始时间戳数值（对比你的服务器日志）")
print("   → UTC + 北京时间双显示，如果不对请告诉我实际时间，我调 epoch！")