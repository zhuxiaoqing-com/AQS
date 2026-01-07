-- 【Heuristic 终极版】强制解析 WebSocket binary CmdID（4.6.x 专用）
local myws = Proto("MyWS_Heuristic_Final", "【Heuristic版】WebSocket 4B长度+4B CmdID")

myws.fields = {
    ProtoField.uint32("myws.len", "总长度", base.DEC),
    ProtoField.uint32("myws.cmdid", "CmdID", base.DEC),
    ProtoField.bytes("myws.body", "Body")
}

function myws.dissector(buf, pinfo, tree)
    if buf:len() < 8 then return false end
    local pktlen = buf(0,4):uint()
    if pktlen < 8 or pktlen > buf:len() or pktlen > 1000000 then return false end  -- 防异常

    local cmdid = buf(4,4):uint()
    local subtree = tree:add(myws, buf(0, pktlen), string.format("MyWS → CmdID=%d 长度=%d", cmdid, pktlen))

    subtree:add(myws.fields[1], buf(0,4)):append_text(string.format(" (0x%08X)", pktlen))
    subtree:add(myws.fields[2], buf(4,4)):append_text(string.format(" (0x%08X)", cmdid))
    if pktlen > 8 then
        subtree:add(myws.fields[3], buf(8, pktlen-8))
    end

    pinfo.cols.protocol = "MyWS"
    pinfo.cols.info = string.format("CmdID=%d Len=%d", cmdid, pktlen)
    return true  -- 告诉 Wireshark "我吃掉了这个 payload"
end

-- 关键：Heuristic 注册到 WebSocket（4.6.x 标准，绕过所有表坑）
myws:register_heuristic("ws", myws.dissector)

print("【Heuristic 终极版加载成功！】所有 WebSocket binary payload (opcode=2) 已接管，CmdID 实时显示。抓包测试吧！")