package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMentorship extends warrior.mdb.XBean {
    private boolean graduate;  // 是否毕业
    private java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> mentors; 
    private java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> students; 
    private java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> myMentorApplyList;  // 别人申请当我导师的列表
    private java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> myStudentApplyList;  // 别人申请当我学生的列表
    private java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> graduateStudents;  // 毕业的学员
    private java.util.concurrent.ConcurrentHashMap<Integer, MentorTask> studentTask;  // 学员任务
    private java.util.concurrent.ConcurrentHashMap<Integer, MentorFinishedStudent> mentorTask;  // 导师任务(记录完成的人的名字)
    private long graduateTime;  // 毕业时间

    PlayerMentorship(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.mentors = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		this.students = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		this.myMentorApplyList = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		this.myStudentApplyList = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		this.graduateStudents = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		this.studentTask = new java.util.concurrent.ConcurrentHashMap<Integer, MentorTask>();
		this.mentorTask = new java.util.concurrent.ConcurrentHashMap<Integer, MentorFinishedStudent>();
	}

	public PlayerMentorship() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMentorship(PlayerMentorship _o_) {
		this(_o_, null);
	}

	PlayerMentorship(PlayerMentorship _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.graduate = _o_.graduate;
		this.mentors = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.mentors.forEach((_k_, _v_) -> this.mentors.put(_k_, new MentorPlayerInfo(_v_)));
		this.students = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.students.forEach((_k_, _v_) -> this.students.put(_k_, new MentorPlayerInfo(_v_)));
		this.myMentorApplyList = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.myMentorApplyList.forEach((_k_, _v_) -> this.myMentorApplyList.put(_k_, new MentorPlayerInfo(_v_)));
		this.myStudentApplyList = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.myStudentApplyList.forEach((_k_, _v_) -> this.myStudentApplyList.put(_k_, new MentorPlayerInfo(_v_)));
		this.graduateStudents = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.graduateStudents.forEach((_k_, _v_) -> this.graduateStudents.put(_k_, new MentorPlayerInfo(_v_)));
		this.studentTask = new java.util.concurrent.ConcurrentHashMap<Integer, MentorTask>();
		_o_.studentTask.forEach((_k_, _v_) -> this.studentTask.put(_k_, new MentorTask(_v_)));
		this.mentorTask = new java.util.concurrent.ConcurrentHashMap<Integer, MentorFinishedStudent>();
		_o_.mentorTask.forEach((_k_, _v_) -> this.mentorTask.put(_k_, new MentorFinishedStudent(_v_)));
		this.graduateTime = _o_.graduateTime;
	}

	public void copyFrom(PlayerMentorship _o_) {
        this.graduate = _o_.graduate;
        this.mentors = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.mentors.forEach((_k_, _v_) -> this.mentors.put(_k_, new MentorPlayerInfo(_v_)));
        this.students = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.students.forEach((_k_, _v_) -> this.students.put(_k_, new MentorPlayerInfo(_v_)));
        this.myMentorApplyList = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.myMentorApplyList.forEach((_k_, _v_) -> this.myMentorApplyList.put(_k_, new MentorPlayerInfo(_v_)));
        this.myStudentApplyList = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.myStudentApplyList.forEach((_k_, _v_) -> this.myStudentApplyList.put(_k_, new MentorPlayerInfo(_v_)));
        this.graduateStudents = new java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo>();
		_o_.graduateStudents.forEach((_k_, _v_) -> this.graduateStudents.put(_k_, new MentorPlayerInfo(_v_)));
        this.studentTask = new java.util.concurrent.ConcurrentHashMap<Integer, MentorTask>();
		_o_.studentTask.forEach((_k_, _v_) -> this.studentTask.put(_k_, new MentorTask(_v_)));
        this.mentorTask = new java.util.concurrent.ConcurrentHashMap<Integer, MentorFinishedStudent>();
		_o_.mentorTask.forEach((_k_, _v_) -> this.mentorTask.put(_k_, new MentorFinishedStudent(_v_)));
        this.graduateTime = _o_.graduateTime;
	}


	@JSONField(name = "1")
	public boolean getGraduate(){
		return this.graduate;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> getMentors(){
		return this.mentors;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> getStudents(){
		return this.students;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> getMyMentorApplyList(){
		return this.myMentorApplyList;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> getMyStudentApplyList(){
		return this.myStudentApplyList;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> getGraduateStudents(){
		return this.graduateStudents;
	}

	@JSONField(name = "7")
	public java.util.concurrent.ConcurrentHashMap<Integer, MentorTask> getStudentTask(){
		return this.studentTask;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Integer, MentorFinishedStudent> getMentorTask(){
		return this.mentorTask;
	}

	@JSONField(name = "9")
	public long getGraduateTime(){
		return this.graduateTime;
	}

	@JSONField(name = "1")
	public void setGraduate(boolean _v_){
		this.graduate = _v_;
	}

	@JSONField(name = "2")
	public void setMentors(java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> _v_){
		this.mentors = _v_;
	}

	@JSONField(name = "3")
	public void setStudents(java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> _v_){
		this.students = _v_;
	}

	@JSONField(name = "4")
	public void setMyMentorApplyList(java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> _v_){
		this.myMentorApplyList = _v_;
	}

	@JSONField(name = "5")
	public void setMyStudentApplyList(java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> _v_){
		this.myStudentApplyList = _v_;
	}

	@JSONField(name = "6")
	public void setGraduateStudents(java.util.concurrent.ConcurrentHashMap<Long, MentorPlayerInfo> _v_){
		this.graduateStudents = _v_;
	}

	@JSONField(name = "7")
	public void setStudentTask(java.util.concurrent.ConcurrentHashMap<Integer, MentorTask> _v_){
		this.studentTask = _v_;
	}

	@JSONField(name = "8")
	public void setMentorTask(java.util.concurrent.ConcurrentHashMap<Integer, MentorFinishedStudent> _v_){
		this.mentorTask = _v_;
	}

	@JSONField(name = "9")
	public void setGraduateTime(long _v_){
		this.graduateTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMentorship) {
			PlayerMentorship _o_ = (PlayerMentorship)_o1_;
			if (this.graduate != _o_.graduate) return false;
			if (!this.mentors.equals(_o_.mentors)) return false;
			if (!this.students.equals(_o_.students)) return false;
			if (!this.myMentorApplyList.equals(_o_.myMentorApplyList)) return false;
			if (!this.myStudentApplyList.equals(_o_.myStudentApplyList)) return false;
			if (!this.graduateStudents.equals(_o_.graduateStudents)) return false;
			if (!this.studentTask.equals(_o_.studentTask)) return false;
			if (!this.mentorTask.equals(_o_.mentorTask)) return false;
			if (this.graduateTime != _o_.graduateTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (this.graduate ? 1231 : 1237);
		_h_ += _h_ * 31 + this.mentors.hashCode();
		_h_ += _h_ * 31 + this.students.hashCode();
		_h_ += _h_ * 31 + this.myMentorApplyList.hashCode();
		_h_ += _h_ * 31 + this.myStudentApplyList.hashCode();
		_h_ += _h_ * 31 + this.graduateStudents.hashCode();
		_h_ += _h_ * 31 + this.studentTask.hashCode();
		_h_ += _h_ * 31 + this.mentorTask.hashCode();
		_h_ += _h_ * 31 + (int)(graduateTime ^ (graduateTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(graduate).append(",");
		_sb_.append(mentors).append(",");
		_sb_.append(students).append(",");
		_sb_.append(myMentorApplyList).append(",");
		_sb_.append(myStudentApplyList).append(",");
		_sb_.append(graduateStudents).append(",");
		_sb_.append(studentTask).append(",");
		_sb_.append(mentorTask).append(",");
		_sb_.append(graduateTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
