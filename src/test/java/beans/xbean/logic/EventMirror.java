package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class EventMirror extends warrior.mdb.XBean {
    private int eventId; 
    private int count; 
    private int elapsedMill;  // 时间定时器运行了多少时间了

    EventMirror(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public EventMirror() {
		this((warrior.mdb.XBean)null);
	}

	public EventMirror(EventMirror _o_) {
		this(_o_, null);
	}

	EventMirror(EventMirror _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.eventId = _o_.eventId;
		this.count = _o_.count;
		this.elapsedMill = _o_.elapsedMill;
	}

	public void copyFrom(EventMirror _o_) {
        this.eventId = _o_.eventId;
        this.count = _o_.count;
        this.elapsedMill = _o_.elapsedMill;
	}


	@JSONField(name = "1")
	public int getEventId(){
		return this.eventId;
	}

	@JSONField(name = "2")
	public int getCount(){
		return this.count;
	}

	@JSONField(name = "3")
	public int getElapsedMill(){
		return this.elapsedMill;
	}

	@JSONField(name = "1")
	public void setEventId(int _v_){
		this.eventId = _v_;
	}

	@JSONField(name = "2")
	public void setCount(int _v_){
		this.count = _v_;
	}

	@JSONField(name = "3")
	public void setElapsedMill(int _v_){
		this.elapsedMill = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EventMirror) {
			EventMirror _o_ = (EventMirror)_o1_;
			if (this.eventId != _o_.eventId) return false;
			if (this.count != _o_.count) return false;
			if (this.elapsedMill != _o_.elapsedMill) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.eventId);
		_h_ += _h_ * 31 + (int)(this.count);
		_h_ += _h_ * 31 + (int)(this.elapsedMill);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(eventId).append(",");
		_sb_.append(count).append(",");
		_sb_.append(elapsedMill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
