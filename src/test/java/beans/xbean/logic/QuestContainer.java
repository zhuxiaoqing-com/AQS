package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class QuestContainer extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Quest> quest;  // key:任务id value：任务详细
    private java.util.concurrent.CopyOnWriteArraySet<Integer> accepted;  // 进行中的任务(作为一个索引, 在 updateCondition 的时候使用)
    private long lastRefresh;  // 上一次刷新时间
    private java.util.concurrent.CopyOnWriteArraySet<Integer> submitQuest;  // 已经提交的任务列表
    private int dailyAcceptNum;  // 当天已接取任务次数

    QuestContainer(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.quest = new java.util.concurrent.ConcurrentHashMap<Integer, Quest>();
		this.accepted = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.submitQuest = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public QuestContainer() {
		this((warrior.mdb.XBean)null);
	}

	public QuestContainer(QuestContainer _o_) {
		this(_o_, null);
	}

	QuestContainer(QuestContainer _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.quest = new java.util.concurrent.ConcurrentHashMap<Integer, Quest>();
		_o_.quest.forEach((_k_, _v_) -> this.quest.put(_k_, new Quest(_v_)));
		this.accepted = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.accepted.forEach(_v_ -> this.accepted.add(_v_));
		this.lastRefresh = _o_.lastRefresh;
		this.submitQuest = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.submitQuest.forEach(_v_ -> this.submitQuest.add(_v_));
		this.dailyAcceptNum = _o_.dailyAcceptNum;
	}

	public void copyFrom(QuestContainer _o_) {
        this.quest = new java.util.concurrent.ConcurrentHashMap<Integer, Quest>();
		_o_.quest.forEach((_k_, _v_) -> this.quest.put(_k_, new Quest(_v_)));
        this.accepted = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.accepted.forEach(_v_ -> this.accepted.add(_v_));
        this.lastRefresh = _o_.lastRefresh;
        this.submitQuest = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.submitQuest.forEach(_v_ -> this.submitQuest.add(_v_));
        this.dailyAcceptNum = _o_.dailyAcceptNum;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Quest> getQuest(){
		return this.quest;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getAccepted(){
		return this.accepted;
	}

	@JSONField(name = "3")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getSubmitQuest(){
		return this.submitQuest;
	}

	@JSONField(name = "5")
	public int getDailyAcceptNum(){
		return this.dailyAcceptNum;
	}

	@JSONField(name = "1")
	public void setQuest(java.util.concurrent.ConcurrentHashMap<Integer, Quest> _v_){
		this.quest = _v_;
	}

	@JSONField(name = "2")
	public void setAccepted(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.accepted = _v_;
	}

	@JSONField(name = "3")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@JSONField(name = "4")
	public void setSubmitQuest(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.submitQuest = _v_;
	}

	@JSONField(name = "5")
	public void setDailyAcceptNum(int _v_){
		this.dailyAcceptNum = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof QuestContainer) {
			QuestContainer _o_ = (QuestContainer)_o1_;
			if (!this.quest.equals(_o_.quest)) return false;
			if (!this.accepted.equals(_o_.accepted)) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			if (!this.submitQuest.equals(_o_.submitQuest)) return false;
			if (this.dailyAcceptNum != _o_.dailyAcceptNum) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.quest.hashCode();
		_h_ += _h_ * 31 + this.accepted.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		_h_ += _h_ * 31 + this.submitQuest.hashCode();
		_h_ += _h_ * 31 + (int)(this.dailyAcceptNum);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(quest).append(",");
		_sb_.append(accepted).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(submitQuest).append(",");
		_sb_.append(dailyAcceptNum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
