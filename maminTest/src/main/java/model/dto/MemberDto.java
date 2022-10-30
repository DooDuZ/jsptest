package model.dto;

public class MemberDto {

	private int m_no;			// 회원 번호
	private String m_id;		//아이디
	private String m_password;	//비밀번호
	private String m_email; 	//이메일
	private int m_point	;		// 보유 포인트
	private String m_nick;		// 닉네임
	private String m_img ;		// 캐릭터 이미지
	private String m_profile ;	// 자기소개
	private int	 wins;			// 1등 횟수
	private int total ;			//총 게임 횟수
    
    
    //빈생성자
	public MemberDto() {
		super();
	}

	//풀생성자
	public MemberDto(int m_no, String m_id, String m_password, String m_email, int m_point, String m_nick, String m_img,
			String m_profile, int wins, int total) {
		super();
		this.m_no = m_no;
		this.m_id = m_id;
		this.m_password = m_password;
		this.m_email = m_email;
		this.m_point = m_point;
		this.m_nick = m_nick;
		this.m_img = m_img;
		this.m_profile = m_profile;
		this.wins = wins;
		this.total = total;
	}
	//getter setter
	public int getM_no() {
		return m_no;
	}

	
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public int getM_point() {
		return m_point;
	}

	public void setM_point(int m_point) {
		this.m_point = m_point;
	}

	public String getM_nick() {
		return m_nick;
	}

	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}

	public String getM_img() {
		return m_img;
	}

	public void setM_img(String m_img) {
		this.m_img = m_img;
	}

	public String getM_profile() {
		return m_profile;
	}

	public void setM_profile(String m_profile) {
		this.m_profile = m_profile;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	//toString
	@Override
	public String toString() {
		return "MemberDto [m_no=" + m_no + ", m_id=" + m_id + ", m_password=" + m_password + ", m_email=" + m_email
				+ ", m_point=" + m_point + ", m_nick=" + m_nick + ", m_img=" + m_img + ", m_profile=" + m_profile
				+ ", wins=" + wins + ", total=" + total + "]";
	}

	
	
}
