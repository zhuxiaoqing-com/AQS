package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AssistPlayer extends warrior.mdb.XBean {
    private xbean.lobby.PlayerBaseInfo player;  // 协助玩家数据
    private int loc;  // 位置
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> talentMap;  // 天赋id等级
    private java.util.concurrent.ConcurrentHashMap<Integer, Float> talentAttr;  // 天赋属性加成
    private float makeTimeReduce;  // 制造时间减少
    private float makeCostReduce;  // 制造消耗减少

    AssistPlayer(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.player = new PlayerBaseInfo(this);
		this.talentMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.talentAttr = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
	}

	public AssistPlayer() {
		this((warrior.mdb.XBean)null);
	}

	public AssistPlayer(AssistPlayer _o_) {
		this(_o_, null);
	}

	AssistPlayer(AssistPlayer _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.player = new PlayerBaseInfo(_o_.player, this);
		this.loc = _o_.loc;
		this.talentMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.talentMap.forEach((_k_, _v_) -> this.talentMap.put(_k_, _v_));
		this.talentAttr = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.talentAttr.forEach((_k_, _v_) -> this.talentAttr.put(_k_, _v_));
		this.makeTimeReduce = _o_.makeTimeReduce;
		this.makeCostReduce = _o_.makeCostReduce;
	}

	public void copyFrom(AssistPlayer _o_) {
        this.player = new PlayerBaseInfo(_o_.player, this);
        this.loc = _o_.loc;
        this.talentMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.talentMap.forEach((_k_, _v_) -> this.talentMap.put(_k_, _v_));
        this.talentAttr = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.talentAttr.forEach((_k_, _v_) -> this.talentAttr.put(_k_, _v_));
        this.makeTimeReduce = _o_.makeTimeReduce;
        this.makeCostReduce = _o_.makeCostReduce;
	}


	@JSONField(name = "1")
	public xbean.lobby.PlayerBaseInfo getPlayer(){
		return this.player;
	}

	@JSONField(name = "2")
	public int getLoc(){
		return this.loc;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTalentMap(){
		return this.talentMap;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, Float> getTalentAttr(){
		return this.talentAttr;
	}

	@JSONField(name = "6")
	public float getMakeTimeReduce(){
		return this.makeTimeReduce;
	}

	@JSONField(name = "7")
	public float getMakeCostReduce(){
		return this.makeCostReduce;
	}

	@JSONField(name = "1")
	public void setPlayer(xbean.lobby.PlayerBaseInfo _v_){
		this.player = _v_;
	}

	@JSONField(name = "2")
	public void setLoc(int _v_){
		this.loc = _v_;
	}

	@JSONField(name = "4")
	public void setTalentMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.talentMap = _v_;
	}

	@JSONField(name = "5")
	public void setTalentAttr(java.util.concurrent.ConcurrentHashMap<Integer, Float> _v_){
		this.talentAttr = _v_;
	}

	@JSONField(name = "6")
	public void setMakeTimeReduce(float _v_){
		this.makeTimeReduce = _v_;
	}

	@JSONField(name = "7")
	public void setMakeCostReduce(float _v_){
		this.makeCostReduce = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AssistPlayer) {
			AssistPlayer _o_ = (AssistPlayer)_o1_;
			if (!this.player.equals(_o_.player)) return false;
			if (this.loc != _o_.loc) return false;
			if (!this.talentMap.equals(_o_.talentMap)) return false;
			if (!this.talentAttr.equals(_o_.talentAttr)) return false;
			if (this.makeTimeReduce != _o_.makeTimeReduce) return false;
			if (this.makeCostReduce != _o_.makeCostReduce) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.player.hashCode();
		_h_ += _h_ * 31 + (int)(this.loc);
		_h_ += _h_ * 31 + this.talentMap.hashCode();
		_h_ += _h_ * 31 + this.talentAttr.hashCode();
		_h_ += _h_ * 31 + Float.floatToIntBits(this.makeTimeReduce);
		_h_ += _h_ * 31 + Float.floatToIntBits(this.makeCostReduce);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(player).append(",");
		_sb_.append(loc).append(",");
		_sb_.append(talentMap).append(",");
		_sb_.append(talentAttr).append(",");
		_sb_.append(makeTimeReduce).append(",");
		_sb_.append(makeCostReduce).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
