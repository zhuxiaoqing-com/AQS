package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensRecruitInfo extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<Integer> memberRewardList;  // 成员奖励领取信息
    private java.util.concurrent.CopyOnWriteArrayList<Integer> createrRewardList;  // 军团长奖励领取信息
    private xbean.logic.LGActLogicInfo actInfo;  // 活动数据

    GensRecruitInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.memberRewardList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		this.createrRewardList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		this.actInfo = new LGActLogicInfo(this);
	}

	public GensRecruitInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensRecruitInfo(GensRecruitInfo _o_) {
		this(_o_, null);
	}

	GensRecruitInfo(GensRecruitInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.memberRewardList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.memberRewardList.forEach(_v_ -> this.memberRewardList.add(_v_));
		this.createrRewardList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.createrRewardList.forEach(_v_ -> this.createrRewardList.add(_v_));
		this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
	}

	public void copyFrom(GensRecruitInfo _o_) {
        this.memberRewardList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.memberRewardList.forEach(_v_ -> this.memberRewardList.add(_v_));
        this.createrRewardList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.createrRewardList.forEach(_v_ -> this.createrRewardList.add(_v_));
        this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getMemberRewardList(){
		return this.memberRewardList;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getCreaterRewardList(){
		return this.createrRewardList;
	}

	@JSONField(name = "3")
	public xbean.logic.LGActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "1")
	public void setMemberRewardList(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.memberRewardList = _v_;
	}

	@JSONField(name = "2")
	public void setCreaterRewardList(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.createrRewardList = _v_;
	}

	@JSONField(name = "3")
	public void setActInfo(xbean.logic.LGActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensRecruitInfo) {
			GensRecruitInfo _o_ = (GensRecruitInfo)_o1_;
			if (!this.memberRewardList.equals(_o_.memberRewardList)) return false;
			if (!this.createrRewardList.equals(_o_.createrRewardList)) return false;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.memberRewardList.hashCode();
		_h_ += _h_ * 31 + this.createrRewardList.hashCode();
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(memberRewardList).append(",");
		_sb_.append(createrRewardList).append(",");
		_sb_.append(actInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
