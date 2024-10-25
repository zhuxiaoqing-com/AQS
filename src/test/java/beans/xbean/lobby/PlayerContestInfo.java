package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerContestInfo extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<Long> supportRound1;  // 第1轮支持军团id
    private java.util.concurrent.CopyOnWriteArraySet<Long> supportRound2; 
    private long supportRound3; 
    private long lastWeekFresh;  // 上次周刷新
    private int round1PredictWin;  // 第一轮预测胜利次数
    private int round2PredictWin;  // 第二轮预测胜利次数

    PlayerContestInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.supportRound1 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.supportRound2 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
	}

	public PlayerContestInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerContestInfo(PlayerContestInfo _o_) {
		this(_o_, null);
	}

	PlayerContestInfo(PlayerContestInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.supportRound1 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.supportRound1.forEach(_v_ -> this.supportRound1.add(_v_));
		this.supportRound2 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.supportRound2.forEach(_v_ -> this.supportRound2.add(_v_));
		this.supportRound3 = _o_.supportRound3;
		this.lastWeekFresh = _o_.lastWeekFresh;
		this.round1PredictWin = _o_.round1PredictWin;
		this.round2PredictWin = _o_.round2PredictWin;
	}

	public void copyFrom(PlayerContestInfo _o_) {
        this.supportRound1 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.supportRound1.forEach(_v_ -> this.supportRound1.add(_v_));
        this.supportRound2 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.supportRound2.forEach(_v_ -> this.supportRound2.add(_v_));
        this.supportRound3 = _o_.supportRound3;
        this.lastWeekFresh = _o_.lastWeekFresh;
        this.round1PredictWin = _o_.round1PredictWin;
        this.round2PredictWin = _o_.round2PredictWin;
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getSupportRound1(){
		return this.supportRound1;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getSupportRound2(){
		return this.supportRound2;
	}

	@JSONField(name = "3")
	public long getSupportRound3(){
		return this.supportRound3;
	}

	@JSONField(name = "5")
	public long getLastWeekFresh(){
		return this.lastWeekFresh;
	}

	@JSONField(name = "6")
	public int getRound1PredictWin(){
		return this.round1PredictWin;
	}

	@JSONField(name = "7")
	public int getRound2PredictWin(){
		return this.round2PredictWin;
	}

	@JSONField(name = "1")
	public void setSupportRound1(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.supportRound1 = _v_;
	}

	@JSONField(name = "2")
	public void setSupportRound2(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.supportRound2 = _v_;
	}

	@JSONField(name = "3")
	public void setSupportRound3(long _v_){
		this.supportRound3 = _v_;
	}

	@JSONField(name = "5")
	public void setLastWeekFresh(long _v_){
		this.lastWeekFresh = _v_;
	}

	@JSONField(name = "6")
	public void setRound1PredictWin(int _v_){
		this.round1PredictWin = _v_;
	}

	@JSONField(name = "7")
	public void setRound2PredictWin(int _v_){
		this.round2PredictWin = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerContestInfo) {
			PlayerContestInfo _o_ = (PlayerContestInfo)_o1_;
			if (!this.supportRound1.equals(_o_.supportRound1)) return false;
			if (!this.supportRound2.equals(_o_.supportRound2)) return false;
			if (this.supportRound3 != _o_.supportRound3) return false;
			if (this.lastWeekFresh != _o_.lastWeekFresh) return false;
			if (this.round1PredictWin != _o_.round1PredictWin) return false;
			if (this.round2PredictWin != _o_.round2PredictWin) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.supportRound1.hashCode();
		_h_ += _h_ * 31 + this.supportRound2.hashCode();
		_h_ += _h_ * 31 + (int)(supportRound3 ^ (supportRound3 >>> 32));
		_h_ += _h_ * 31 + (int)(lastWeekFresh ^ (lastWeekFresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.round1PredictWin);
		_h_ += _h_ * 31 + (int)(this.round2PredictWin);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(supportRound1).append(",");
		_sb_.append(supportRound2).append(",");
		_sb_.append(supportRound3).append(",");
		_sb_.append(lastWeekFresh).append(",");
		_sb_.append(round1PredictWin).append(",");
		_sb_.append(round2PredictWin).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
