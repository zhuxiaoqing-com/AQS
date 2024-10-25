package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBLimitChatUid extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<String, Long> chatUidMap;  // key:玩家uid value:禁言结束时间

    LBLimitChatUid(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.chatUidMap = new java.util.concurrent.ConcurrentHashMap<String, Long>();
	}

	public LBLimitChatUid() {
		this((warrior.mdb.XBean)null);
	}

	public LBLimitChatUid(LBLimitChatUid _o_) {
		this(_o_, null);
	}

	LBLimitChatUid(LBLimitChatUid _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.chatUidMap = new java.util.concurrent.ConcurrentHashMap<String, Long>();
		_o_.chatUidMap.forEach((_k_, _v_) -> this.chatUidMap.put(_k_, _v_));
	}

	public void copyFrom(LBLimitChatUid _o_) {
        this.chatUidMap = new java.util.concurrent.ConcurrentHashMap<String, Long>();
		_o_.chatUidMap.forEach((_k_, _v_) -> this.chatUidMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<String, Long> getChatUidMap(){
		return this.chatUidMap;
	}

	@JSONField(name = "1")
	public void setChatUidMap(java.util.concurrent.ConcurrentHashMap<String, Long> _v_){
		this.chatUidMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBLimitChatUid) {
			LBLimitChatUid _o_ = (LBLimitChatUid)_o1_;
			if (!this.chatUidMap.equals(_o_.chatUidMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.chatUidMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(chatUidMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
