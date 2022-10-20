package model.dto;

public class BoardDto {
	private int cNo;
	private String title;
	private String content;
	private String writer;
	private String password;
	private String cdate;
	private int view;
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardDto(String title, String content, String writer, String password) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
	}

	public BoardDto(int cNo, String title, String content, String writer, String password, String cdate, int view) {
		super();
		this.cNo = cNo;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.cdate = cdate;
		this.view = view;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	@Override
	public String toString() {
		return "BoardDto [cNo=" + cNo + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", password=" + password + ", cdate=" + cdate + ", view=" + view + "]";
	}	
	
}
