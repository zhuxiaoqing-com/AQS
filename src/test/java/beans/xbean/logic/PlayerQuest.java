package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerQuest extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, QuestContainer> quest;  // key：任务类型 value:任务容器
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> gainTypeHistoryMap;  // 获取奖励类型历史数据

    PlayerQuest(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.quest = new java.util.concurrent.ConcurrentHashMap<Integer, QuestContainer>();
		this.gainTypeHistoryMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerQuest() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerQuest(PlayerQuest _o_) {
		this(_o_, null);
	}

	PlayerQuest(PlayerQuest _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.quest = new java.util.concurrent.ConcurrentHashMap<Integer, QuestContainer>();
		_o_.quest.forEach((_k_, _v_) -> this.quest.put(_k_, new QuestContainer(_v_)));
		this.gainTypeHistoryMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.gainTypeHistoryMap.forEach((_k_, _v_) -> this.gainTypeHistoryMap.put(_k_, _v_));
	}

	public void copyFrom(PlayerQuest _o_) {
        this.quest = new java.util.concurrent.ConcurrentHashMap<Integer, QuestContainer>();
		_o_.quest.forEach((_k_, _v_) -> this.quest.put(_k_, new QuestContainer(_v_)));
        this.gainTypeHistoryMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.gainTypeHistoryMap.forEach((_k_, _v_) -> this.gainTypeHistoryMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, QuestContainer> getQuest(){
		return this.quest;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getGainTypeHistoryMap(){
		return this.gainTypeHistoryMap;
	}

	@JSONField(name = "1")
	public void setQuest(java.util.concurrent.ConcurrentHashMap<Integer, QuestContainer> _v_){
		this.quest = _v_;
	}

	@JSONField(name = "2")
	public void setGainTypeHistoryMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.gainTypeHistoryMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerQuest) {
			PlayerQuest _o_ = (PlayerQuest)_o1_;
			if (!this.quest.equals(_o_.quest)) return false;
			if (!this.gainTypeHistoryMap.equals(_o_.gainTypeHistoryMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.quest.hashCode();
		_h_ += _h_ * 31 + this.gainTypeHistoryMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(quest).append(",");
		_sb_.append(gainTypeHistoryMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
