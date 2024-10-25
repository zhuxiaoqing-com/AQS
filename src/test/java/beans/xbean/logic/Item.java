package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Item extends warrior.mdb.XBean {
    private long srl;  // 道具实例唯一id
    private int id;  // 道具模型id
    private int num;  // 道具数量
    private int bstate;  // 道具绑定状态 -1不区分 0不绑定 1绑定
    private long cread;  // 道具获得时间
    private int loc = -1;  // 道具在背包的位置
    private int loc2 = -1;  // 主背包主页签的位置
    private float durable;  // 道具耐久度
    private int bulletId;  // 弹夹中子弹ID
    private int bulletNum;  // 弹夹中子弹个数
    private xbean.logic.MAttr attr;  // 装备属性
    private int attrSlotNum;  // 装备属性配件槽数
    private int useAttrSlotNum;  // 已使用的装备属性配件槽数
    private int skillSlotNum;  // 装备技能配件槽数
    private int useSkillSlotNum;  // 已使用的装备技能配件槽数
    private boolean isNew;  // 是否是新获得的道具
    private boolean isStuck;  // 是否卡壳
    private java.util.concurrent.ConcurrentHashMap<Integer, Pendant> pendants;  // 配件
    private int repairNum;  // 已经修理过的次数
    private xbean.logic.MAttr switchAttr;  // 可切换的武器属性
    private long switchSrl;  // 可切换的武器srl
    private int quality;  // 装备的品质
    private int star;  // 装备的星级 （如果是挂件 这个就是挂件的等级）
    private int exp = 0;  // 装备的经验（挂件的经验）
    private float maxDurable;  // 装备的最大耐久
    private float restSuffer;  // 防具此时累计未计入耐久损耗的伤害量
    private int source;  // 装备来源
    private float intervalPct;  // 区间百分比（用于装备升品生成属性）
    private String maker;  // 制造者名称

    Item(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.attr = new MAttr(this);
		this.pendants = new java.util.concurrent.ConcurrentHashMap<Integer, Pendant>();
		this.switchAttr = new MAttr(this);
		this.maker = "";
	}

	public Item() {
		this((warrior.mdb.XBean)null);
	}

	public Item(Item _o_) {
		this(_o_, null);
	}

	Item(Item _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.id = _o_.id;
		this.num = _o_.num;
		this.bstate = _o_.bstate;
		this.cread = _o_.cread;
		this.loc = _o_.loc;
		this.loc2 = _o_.loc2;
		this.durable = _o_.durable;
		this.bulletId = _o_.bulletId;
		this.bulletNum = _o_.bulletNum;
		this.attr = new MAttr(_o_.attr, this);
		this.attrSlotNum = _o_.attrSlotNum;
		this.useAttrSlotNum = _o_.useAttrSlotNum;
		this.skillSlotNum = _o_.skillSlotNum;
		this.useSkillSlotNum = _o_.useSkillSlotNum;
		this.isNew = _o_.isNew;
		this.isStuck = _o_.isStuck;
		this.pendants = new java.util.concurrent.ConcurrentHashMap<Integer, Pendant>();
		_o_.pendants.forEach((_k_, _v_) -> this.pendants.put(_k_, new Pendant(_v_)));
		this.repairNum = _o_.repairNum;
		this.switchAttr = new MAttr(_o_.switchAttr, this);
		this.switchSrl = _o_.switchSrl;
		this.quality = _o_.quality;
		this.star = _o_.star;
		this.exp = _o_.exp;
		this.maxDurable = _o_.maxDurable;
		this.restSuffer = _o_.restSuffer;
		this.source = _o_.source;
		this.intervalPct = _o_.intervalPct;
		this.maker = _o_.maker;
	}

	public void copyFrom(Item _o_) {
        this.srl = _o_.srl;
        this.id = _o_.id;
        this.num = _o_.num;
        this.bstate = _o_.bstate;
        this.cread = _o_.cread;
        this.loc = _o_.loc;
        this.loc2 = _o_.loc2;
        this.durable = _o_.durable;
        this.bulletId = _o_.bulletId;
        this.bulletNum = _o_.bulletNum;
        this.attr = new MAttr(_o_.attr, this);
        this.attrSlotNum = _o_.attrSlotNum;
        this.useAttrSlotNum = _o_.useAttrSlotNum;
        this.skillSlotNum = _o_.skillSlotNum;
        this.useSkillSlotNum = _o_.useSkillSlotNum;
        this.isNew = _o_.isNew;
        this.isStuck = _o_.isStuck;
        this.pendants = new java.util.concurrent.ConcurrentHashMap<Integer, Pendant>();
		_o_.pendants.forEach((_k_, _v_) -> this.pendants.put(_k_, new Pendant(_v_)));
        this.repairNum = _o_.repairNum;
        this.switchAttr = new MAttr(_o_.switchAttr, this);
        this.switchSrl = _o_.switchSrl;
        this.quality = _o_.quality;
        this.star = _o_.star;
        this.exp = _o_.exp;
        this.maxDurable = _o_.maxDurable;
        this.restSuffer = _o_.restSuffer;
        this.source = _o_.source;
        this.intervalPct = _o_.intervalPct;
        this.maker = _o_.maker;
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "3")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "4")
	public int getBstate(){
		return this.bstate;
	}

	@JSONField(name = "5")
	public long getCread(){
		return this.cread;
	}

	@JSONField(name = "6")
	public int getLoc(){
		return this.loc;
	}

	@JSONField(name = "7")
	public int getLoc2(){
		return this.loc2;
	}

	@JSONField(name = "8")
	public float getDurable(){
		return this.durable;
	}

	@JSONField(name = "9")
	public int getBulletId(){
		return this.bulletId;
	}

	@JSONField(name = "10")
	public int getBulletNum(){
		return this.bulletNum;
	}

	@JSONField(name = "11")
	public xbean.logic.MAttr getAttr(){
		return this.attr;
	}

	@JSONField(name = "12")
	public int getAttrSlotNum(){
		return this.attrSlotNum;
	}

	@JSONField(name = "13")
	public int getUseAttrSlotNum(){
		return this.useAttrSlotNum;
	}

	@JSONField(name = "14")
	public int getSkillSlotNum(){
		return this.skillSlotNum;
	}

	@JSONField(name = "15")
	public int getUseSkillSlotNum(){
		return this.useSkillSlotNum;
	}

	@JSONField(name = "16")
	public boolean getIsNew(){
		return this.isNew;
	}

	@JSONField(name = "17")
	public boolean getIsStuck(){
		return this.isStuck;
	}

	@JSONField(name = "18")
	public java.util.concurrent.ConcurrentHashMap<Integer, Pendant> getPendants(){
		return this.pendants;
	}

	@JSONField(name = "19")
	public int getRepairNum(){
		return this.repairNum;
	}

	@JSONField(name = "20")
	public xbean.logic.MAttr getSwitchAttr(){
		return this.switchAttr;
	}

	@JSONField(name = "21")
	public long getSwitchSrl(){
		return this.switchSrl;
	}

	@JSONField(name = "22")
	public int getQuality(){
		return this.quality;
	}

	@JSONField(name = "23")
	public int getStar(){
		return this.star;
	}

	@JSONField(name = "24")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "25")
	public float getMaxDurable(){
		return this.maxDurable;
	}

	@JSONField(name = "26")
	public float getRestSuffer(){
		return this.restSuffer;
	}

	@JSONField(name = "27")
	public int getSource(){
		return this.source;
	}

	@JSONField(name = "28")
	public float getIntervalPct(){
		return this.intervalPct;
	}

	@JSONField(name = "29")
	public String getMaker(){
		return this.maker;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "3")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@JSONField(name = "4")
	public void setBstate(int _v_){
		this.bstate = _v_;
	}

	@JSONField(name = "5")
	public void setCread(long _v_){
		this.cread = _v_;
	}

	@JSONField(name = "6")
	public void setLoc(int _v_){
		this.loc = _v_;
	}

	@JSONField(name = "7")
	public void setLoc2(int _v_){
		this.loc2 = _v_;
	}

	@JSONField(name = "8")
	public void setDurable(float _v_){
		this.durable = _v_;
	}

	@JSONField(name = "9")
	public void setBulletId(int _v_){
		this.bulletId = _v_;
	}

	@JSONField(name = "10")
	public void setBulletNum(int _v_){
		this.bulletNum = _v_;
	}

	@JSONField(name = "11")
	public void setAttr(xbean.logic.MAttr _v_){
		this.attr = _v_;
	}

	@JSONField(name = "12")
	public void setAttrSlotNum(int _v_){
		this.attrSlotNum = _v_;
	}

	@JSONField(name = "13")
	public void setUseAttrSlotNum(int _v_){
		this.useAttrSlotNum = _v_;
	}

	@JSONField(name = "14")
	public void setSkillSlotNum(int _v_){
		this.skillSlotNum = _v_;
	}

	@JSONField(name = "15")
	public void setUseSkillSlotNum(int _v_){
		this.useSkillSlotNum = _v_;
	}

	@JSONField(name = "16")
	public void setIsNew(boolean _v_){
		this.isNew = _v_;
	}

	@JSONField(name = "17")
	public void setIsStuck(boolean _v_){
		this.isStuck = _v_;
	}

	@JSONField(name = "18")
	public void setPendants(java.util.concurrent.ConcurrentHashMap<Integer, Pendant> _v_){
		this.pendants = _v_;
	}

	@JSONField(name = "19")
	public void setRepairNum(int _v_){
		this.repairNum = _v_;
	}

	@JSONField(name = "20")
	public void setSwitchAttr(xbean.logic.MAttr _v_){
		this.switchAttr = _v_;
	}

	@JSONField(name = "21")
	public void setSwitchSrl(long _v_){
		this.switchSrl = _v_;
	}

	@JSONField(name = "22")
	public void setQuality(int _v_){
		this.quality = _v_;
	}

	@JSONField(name = "23")
	public void setStar(int _v_){
		this.star = _v_;
	}

	@JSONField(name = "24")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@JSONField(name = "25")
	public void setMaxDurable(float _v_){
		this.maxDurable = _v_;
	}

	@JSONField(name = "26")
	public void setRestSuffer(float _v_){
		this.restSuffer = _v_;
	}

	@JSONField(name = "27")
	public void setSource(int _v_){
		this.source = _v_;
	}

	@JSONField(name = "28")
	public void setIntervalPct(float _v_){
		this.intervalPct = _v_;
	}

	@JSONField(name = "29")
	public void setMaker(String _v_){
		this.maker = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Item) {
			Item _o_ = (Item)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.id != _o_.id) return false;
			if (this.num != _o_.num) return false;
			if (this.bstate != _o_.bstate) return false;
			if (this.cread != _o_.cread) return false;
			if (this.loc != _o_.loc) return false;
			if (this.loc2 != _o_.loc2) return false;
			if (this.durable != _o_.durable) return false;
			if (this.bulletId != _o_.bulletId) return false;
			if (this.bulletNum != _o_.bulletNum) return false;
			if (!this.attr.equals(_o_.attr)) return false;
			if (this.attrSlotNum != _o_.attrSlotNum) return false;
			if (this.useAttrSlotNum != _o_.useAttrSlotNum) return false;
			if (this.skillSlotNum != _o_.skillSlotNum) return false;
			if (this.useSkillSlotNum != _o_.useSkillSlotNum) return false;
			if (this.isNew != _o_.isNew) return false;
			if (this.isStuck != _o_.isStuck) return false;
			if (!this.pendants.equals(_o_.pendants)) return false;
			if (this.repairNum != _o_.repairNum) return false;
			if (!this.switchAttr.equals(_o_.switchAttr)) return false;
			if (this.switchSrl != _o_.switchSrl) return false;
			if (this.quality != _o_.quality) return false;
			if (this.star != _o_.star) return false;
			if (this.exp != _o_.exp) return false;
			if (this.maxDurable != _o_.maxDurable) return false;
			if (this.restSuffer != _o_.restSuffer) return false;
			if (this.source != _o_.source) return false;
			if (this.intervalPct != _o_.intervalPct) return false;
			if (!this.maker.equals(_o_.maker)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.num);
		_h_ += _h_ * 31 + (int)(this.bstate);
		_h_ += _h_ * 31 + (int)(cread ^ (cread >>> 32));
		_h_ += _h_ * 31 + (int)(this.loc);
		_h_ += _h_ * 31 + (int)(this.loc2);
		_h_ += _h_ * 31 + Float.floatToIntBits(this.durable);
		_h_ += _h_ * 31 + (int)(this.bulletId);
		_h_ += _h_ * 31 + (int)(this.bulletNum);
		_h_ += _h_ * 31 + this.attr.hashCode();
		_h_ += _h_ * 31 + (int)(this.attrSlotNum);
		_h_ += _h_ * 31 + (int)(this.useAttrSlotNum);
		_h_ += _h_ * 31 + (int)(this.skillSlotNum);
		_h_ += _h_ * 31 + (int)(this.useSkillSlotNum);
		_h_ += _h_ * 31 + (this.isNew ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.isStuck ? 1231 : 1237);
		_h_ += _h_ * 31 + this.pendants.hashCode();
		_h_ += _h_ * 31 + (int)(this.repairNum);
		_h_ += _h_ * 31 + this.switchAttr.hashCode();
		_h_ += _h_ * 31 + (int)(switchSrl ^ (switchSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.quality);
		_h_ += _h_ * 31 + (int)(this.star);
		_h_ += _h_ * 31 + (int)(this.exp);
		_h_ += _h_ * 31 + Float.floatToIntBits(this.maxDurable);
		_h_ += _h_ * 31 + Float.floatToIntBits(this.restSuffer);
		_h_ += _h_ * 31 + (int)(this.source);
		_h_ += _h_ * 31 + Float.floatToIntBits(this.intervalPct);
		_h_ += _h_ * 31 + this.maker.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(id).append(",");
		_sb_.append(num).append(",");
		_sb_.append(bstate).append(",");
		_sb_.append(cread).append(",");
		_sb_.append(loc).append(",");
		_sb_.append(loc2).append(",");
		_sb_.append(durable).append(",");
		_sb_.append(bulletId).append(",");
		_sb_.append(bulletNum).append(",");
		_sb_.append(attr).append(",");
		_sb_.append(attrSlotNum).append(",");
		_sb_.append(useAttrSlotNum).append(",");
		_sb_.append(skillSlotNum).append(",");
		_sb_.append(useSkillSlotNum).append(",");
		_sb_.append(isNew).append(",");
		_sb_.append(isStuck).append(",");
		_sb_.append(pendants).append(",");
		_sb_.append(repairNum).append(",");
		_sb_.append(switchAttr).append(",");
		_sb_.append(switchSrl).append(",");
		_sb_.append(quality).append(",");
		_sb_.append(star).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(maxDurable).append(",");
		_sb_.append(restSuffer).append(",");
		_sb_.append(source).append(",");
		_sb_.append(intervalPct).append(",");
		_sb_.append("T").append(maker.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
