package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerSkin extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<Integer> activatedSkin;  // 激活的皮肤
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> useSkin;  // 使用的普通皮肤 k:装扮类型 v:皮肤Id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> useItemSkin;  // 使用的道具皮肤 k:道具id v:皮肤id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> useCarrierSkin;  // 使用的载具皮肤 k:载具id v:皮肤id

    PlayerSkin(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.activatedSkin = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.useSkin = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.useItemSkin = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.useCarrierSkin = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerSkin() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerSkin(PlayerSkin _o_) {
		this(_o_, null);
	}

	PlayerSkin(PlayerSkin _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.activatedSkin = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.activatedSkin.forEach(_v_ -> this.activatedSkin.add(_v_));
		this.useSkin = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.useSkin.forEach((_k_, _v_) -> this.useSkin.put(_k_, _v_));
		this.useItemSkin = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.useItemSkin.forEach((_k_, _v_) -> this.useItemSkin.put(_k_, _v_));
		this.useCarrierSkin = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.useCarrierSkin.forEach((_k_, _v_) -> this.useCarrierSkin.put(_k_, _v_));
	}

	public void copyFrom(PlayerSkin _o_) {
        this.activatedSkin = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.activatedSkin.forEach(_v_ -> this.activatedSkin.add(_v_));
        this.useSkin = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.useSkin.forEach((_k_, _v_) -> this.useSkin.put(_k_, _v_));
        this.useItemSkin = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.useItemSkin.forEach((_k_, _v_) -> this.useItemSkin.put(_k_, _v_));
        this.useCarrierSkin = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.useCarrierSkin.forEach((_k_, _v_) -> this.useCarrierSkin.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getActivatedSkin(){
		return this.activatedSkin;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getUseSkin(){
		return this.useSkin;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getUseItemSkin(){
		return this.useItemSkin;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getUseCarrierSkin(){
		return this.useCarrierSkin;
	}

	@JSONField(name = "1")
	public void setActivatedSkin(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.activatedSkin = _v_;
	}

	@JSONField(name = "2")
	public void setUseSkin(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.useSkin = _v_;
	}

	@JSONField(name = "3")
	public void setUseItemSkin(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.useItemSkin = _v_;
	}

	@JSONField(name = "4")
	public void setUseCarrierSkin(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.useCarrierSkin = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerSkin) {
			PlayerSkin _o_ = (PlayerSkin)_o1_;
			if (!this.activatedSkin.equals(_o_.activatedSkin)) return false;
			if (!this.useSkin.equals(_o_.useSkin)) return false;
			if (!this.useItemSkin.equals(_o_.useItemSkin)) return false;
			if (!this.useCarrierSkin.equals(_o_.useCarrierSkin)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.activatedSkin.hashCode();
		_h_ += _h_ * 31 + this.useSkin.hashCode();
		_h_ += _h_ * 31 + this.useItemSkin.hashCode();
		_h_ += _h_ * 31 + this.useCarrierSkin.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(activatedSkin).append(",");
		_sb_.append(useSkin).append(",");
		_sb_.append(useItemSkin).append(",");
		_sb_.append(useCarrierSkin).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
