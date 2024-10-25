package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensWarBattleInfo extends warrior.mdb.XBean {
    private long time; 
    private int attackMaxNum; 
    private long attackGens; 
    private String attackGensName; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> attackGensIconMap;  // //工会图标
    private int attackCurNum; 
    private long defenceGens; 
    private String defenceGensName; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> defenceGensIconMap;  // //工会图标
    private int defenceCurNum; 
    private int defenceMaxNum; 
    private java.util.concurrent.CopyOnWriteArrayList<GensBattleRoleInfo> ranks;  // 攻方角色列表
    private int attackScore;  // 攻方积分
    private int defenceScore;  // 守方积分
    private int attackInitDonate;  // 攻方初始捐献积分
    private int defenceInitDonate;  // 守方初始捐献积分
    private java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean> attackDonateList;  // 攻方捐赠信息
    private java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean> defenceDonateList; 

    GensWarBattleInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.attackGensName = "";
		this.attackGensIconMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.defenceGensName = "";
		this.defenceGensIconMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.ranks = new java.util.concurrent.CopyOnWriteArrayList<GensBattleRoleInfo>();
		this.attackDonateList = new java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean>();
		this.defenceDonateList = new java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean>();
	}

	public GensWarBattleInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensWarBattleInfo(GensWarBattleInfo _o_) {
		this(_o_, null);
	}

	GensWarBattleInfo(GensWarBattleInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.time = _o_.time;
		this.attackMaxNum = _o_.attackMaxNum;
		this.attackGens = _o_.attackGens;
		this.attackGensName = _o_.attackGensName;
		this.attackGensIconMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.attackGensIconMap.forEach((_k_, _v_) -> this.attackGensIconMap.put(_k_, _v_));
		this.attackCurNum = _o_.attackCurNum;
		this.defenceGens = _o_.defenceGens;
		this.defenceGensName = _o_.defenceGensName;
		this.defenceGensIconMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.defenceGensIconMap.forEach((_k_, _v_) -> this.defenceGensIconMap.put(_k_, _v_));
		this.defenceCurNum = _o_.defenceCurNum;
		this.defenceMaxNum = _o_.defenceMaxNum;
		this.ranks = new java.util.concurrent.CopyOnWriteArrayList<GensBattleRoleInfo>();
		_o_.ranks.forEach(_v_ -> this.ranks.add(new GensBattleRoleInfo(_v_)));
		this.attackScore = _o_.attackScore;
		this.defenceScore = _o_.defenceScore;
		this.attackInitDonate = _o_.attackInitDonate;
		this.defenceInitDonate = _o_.defenceInitDonate;
		this.attackDonateList = new java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean>();
		_o_.attackDonateList.forEach(_v_ -> this.attackDonateList.add(new GensWarDonateBean(_v_)));
		this.defenceDonateList = new java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean>();
		_o_.defenceDonateList.forEach(_v_ -> this.defenceDonateList.add(new GensWarDonateBean(_v_)));
	}

	public void copyFrom(GensWarBattleInfo _o_) {
        this.time = _o_.time;
        this.attackMaxNum = _o_.attackMaxNum;
        this.attackGens = _o_.attackGens;
        this.attackGensName = _o_.attackGensName;
        this.attackGensIconMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.attackGensIconMap.forEach((_k_, _v_) -> this.attackGensIconMap.put(_k_, _v_));
        this.attackCurNum = _o_.attackCurNum;
        this.defenceGens = _o_.defenceGens;
        this.defenceGensName = _o_.defenceGensName;
        this.defenceGensIconMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.defenceGensIconMap.forEach((_k_, _v_) -> this.defenceGensIconMap.put(_k_, _v_));
        this.defenceCurNum = _o_.defenceCurNum;
        this.defenceMaxNum = _o_.defenceMaxNum;
        this.ranks = new java.util.concurrent.CopyOnWriteArrayList<GensBattleRoleInfo>();
		_o_.ranks.forEach(_v_ -> this.ranks.add(new GensBattleRoleInfo(_v_)));
        this.attackScore = _o_.attackScore;
        this.defenceScore = _o_.defenceScore;
        this.attackInitDonate = _o_.attackInitDonate;
        this.defenceInitDonate = _o_.defenceInitDonate;
        this.attackDonateList = new java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean>();
		_o_.attackDonateList.forEach(_v_ -> this.attackDonateList.add(new GensWarDonateBean(_v_)));
        this.defenceDonateList = new java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean>();
		_o_.defenceDonateList.forEach(_v_ -> this.defenceDonateList.add(new GensWarDonateBean(_v_)));
	}


	@JSONField(name = "1")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "2")
	public int getAttackMaxNum(){
		return this.attackMaxNum;
	}

	@JSONField(name = "3")
	public long getAttackGens(){
		return this.attackGens;
	}

	@JSONField(name = "4")
	public String getAttackGensName(){
		return this.attackGensName;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getAttackGensIconMap(){
		return this.attackGensIconMap;
	}

	@JSONField(name = "6")
	public int getAttackCurNum(){
		return this.attackCurNum;
	}

	@JSONField(name = "7")
	public long getDefenceGens(){
		return this.defenceGens;
	}

	@JSONField(name = "8")
	public String getDefenceGensName(){
		return this.defenceGensName;
	}

	@JSONField(name = "9")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDefenceGensIconMap(){
		return this.defenceGensIconMap;
	}

	@JSONField(name = "10")
	public int getDefenceCurNum(){
		return this.defenceCurNum;
	}

	@JSONField(name = "11")
	public int getDefenceMaxNum(){
		return this.defenceMaxNum;
	}

	@JSONField(name = "12")
	public java.util.concurrent.CopyOnWriteArrayList<GensBattleRoleInfo> getRanks(){
		return this.ranks;
	}

	@JSONField(name = "13")
	public int getAttackScore(){
		return this.attackScore;
	}

	@JSONField(name = "14")
	public int getDefenceScore(){
		return this.defenceScore;
	}

	@JSONField(name = "15")
	public int getAttackInitDonate(){
		return this.attackInitDonate;
	}

	@JSONField(name = "16")
	public int getDefenceInitDonate(){
		return this.defenceInitDonate;
	}

	@JSONField(name = "17")
	public java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean> getAttackDonateList(){
		return this.attackDonateList;
	}

	@JSONField(name = "18")
	public java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean> getDefenceDonateList(){
		return this.defenceDonateList;
	}

	@JSONField(name = "1")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@JSONField(name = "2")
	public void setAttackMaxNum(int _v_){
		this.attackMaxNum = _v_;
	}

	@JSONField(name = "3")
	public void setAttackGens(long _v_){
		this.attackGens = _v_;
	}

	@JSONField(name = "4")
	public void setAttackGensName(String _v_){
		this.attackGensName = _v_;
	}

	@JSONField(name = "5")
	public void setAttackGensIconMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.attackGensIconMap = _v_;
	}

	@JSONField(name = "6")
	public void setAttackCurNum(int _v_){
		this.attackCurNum = _v_;
	}

	@JSONField(name = "7")
	public void setDefenceGens(long _v_){
		this.defenceGens = _v_;
	}

	@JSONField(name = "8")
	public void setDefenceGensName(String _v_){
		this.defenceGensName = _v_;
	}

	@JSONField(name = "9")
	public void setDefenceGensIconMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.defenceGensIconMap = _v_;
	}

	@JSONField(name = "10")
	public void setDefenceCurNum(int _v_){
		this.defenceCurNum = _v_;
	}

	@JSONField(name = "11")
	public void setDefenceMaxNum(int _v_){
		this.defenceMaxNum = _v_;
	}

	@JSONField(name = "12")
	public void setRanks(java.util.concurrent.CopyOnWriteArrayList<GensBattleRoleInfo> _v_){
		this.ranks = _v_;
	}

	@JSONField(name = "13")
	public void setAttackScore(int _v_){
		this.attackScore = _v_;
	}

	@JSONField(name = "14")
	public void setDefenceScore(int _v_){
		this.defenceScore = _v_;
	}

	@JSONField(name = "15")
	public void setAttackInitDonate(int _v_){
		this.attackInitDonate = _v_;
	}

	@JSONField(name = "16")
	public void setDefenceInitDonate(int _v_){
		this.defenceInitDonate = _v_;
	}

	@JSONField(name = "17")
	public void setAttackDonateList(java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean> _v_){
		this.attackDonateList = _v_;
	}

	@JSONField(name = "18")
	public void setDefenceDonateList(java.util.concurrent.CopyOnWriteArrayList<GensWarDonateBean> _v_){
		this.defenceDonateList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensWarBattleInfo) {
			GensWarBattleInfo _o_ = (GensWarBattleInfo)_o1_;
			if (this.time != _o_.time) return false;
			if (this.attackMaxNum != _o_.attackMaxNum) return false;
			if (this.attackGens != _o_.attackGens) return false;
			if (!this.attackGensName.equals(_o_.attackGensName)) return false;
			if (!this.attackGensIconMap.equals(_o_.attackGensIconMap)) return false;
			if (this.attackCurNum != _o_.attackCurNum) return false;
			if (this.defenceGens != _o_.defenceGens) return false;
			if (!this.defenceGensName.equals(_o_.defenceGensName)) return false;
			if (!this.defenceGensIconMap.equals(_o_.defenceGensIconMap)) return false;
			if (this.defenceCurNum != _o_.defenceCurNum) return false;
			if (this.defenceMaxNum != _o_.defenceMaxNum) return false;
			if (!this.ranks.equals(_o_.ranks)) return false;
			if (this.attackScore != _o_.attackScore) return false;
			if (this.defenceScore != _o_.defenceScore) return false;
			if (this.attackInitDonate != _o_.attackInitDonate) return false;
			if (this.defenceInitDonate != _o_.defenceInitDonate) return false;
			if (!this.attackDonateList.equals(_o_.attackDonateList)) return false;
			if (!this.defenceDonateList.equals(_o_.defenceDonateList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		_h_ += _h_ * 31 + (int)(this.attackMaxNum);
		_h_ += _h_ * 31 + (int)(attackGens ^ (attackGens >>> 32));
		_h_ += _h_ * 31 + this.attackGensName.hashCode();
		_h_ += _h_ * 31 + this.attackGensIconMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.attackCurNum);
		_h_ += _h_ * 31 + (int)(defenceGens ^ (defenceGens >>> 32));
		_h_ += _h_ * 31 + this.defenceGensName.hashCode();
		_h_ += _h_ * 31 + this.defenceGensIconMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.defenceCurNum);
		_h_ += _h_ * 31 + (int)(this.defenceMaxNum);
		_h_ += _h_ * 31 + this.ranks.hashCode();
		_h_ += _h_ * 31 + (int)(this.attackScore);
		_h_ += _h_ * 31 + (int)(this.defenceScore);
		_h_ += _h_ * 31 + (int)(this.attackInitDonate);
		_h_ += _h_ * 31 + (int)(this.defenceInitDonate);
		_h_ += _h_ * 31 + this.attackDonateList.hashCode();
		_h_ += _h_ * 31 + this.defenceDonateList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(time).append(",");
		_sb_.append(attackMaxNum).append(",");
		_sb_.append(attackGens).append(",");
		_sb_.append("T").append(attackGensName.length()).append(",");
		_sb_.append(attackGensIconMap).append(",");
		_sb_.append(attackCurNum).append(",");
		_sb_.append(defenceGens).append(",");
		_sb_.append("T").append(defenceGensName.length()).append(",");
		_sb_.append(defenceGensIconMap).append(",");
		_sb_.append(defenceCurNum).append(",");
		_sb_.append(defenceMaxNum).append(",");
		_sb_.append(ranks).append(",");
		_sb_.append(attackScore).append(",");
		_sb_.append(defenceScore).append(",");
		_sb_.append(attackInitDonate).append(",");
		_sb_.append(defenceInitDonate).append(",");
		_sb_.append(attackDonateList).append(",");
		_sb_.append(defenceDonateList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
