package model.dto;

public class BoardDto {
	private int b_no ; 		//게시물 번호
	private String b_title; //제목
	private String b_content;  // 내용
	private String b_file;  	// 첨부파일
	private String b_date ; // 작성날짜
	private int  m_no;		//작성 회원 번호
	private String m_id; 
	//빈생성자
	public BoardDto() { 
		super();
	}
	//풀생성자
	public BoardDto(int b_no, String b_title, String b_content, String b_file, String b_date, int m_no) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_file = b_file;
		this.b_date = b_date;
		this.m_no = m_no;
	}
	
	public BoardDto(int b_no, String b_title, String b_date, String m_id) {//게시물 출력용 생성자
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_date = b_date;
		this.m_id = m_id;
	}
	//getter setter
	public int getB_no() {
		return b_no;
	}
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String mid) {
		this.m_id = mid;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public String getB_file() {
		return b_file;
	}
	public void setB_file(String b_file) {
		this.b_file = b_file;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	//toString
	@Override
	public String toString() {
		return "BoardDto [b_no=" + b_no + ", b_title=" + b_title + ", b_content=" + b_content + ", b_file=" + b_file
				+ ", b_date=" + b_date + ", m_no=" + m_no + "]";
	}

	
	
	
	
}
