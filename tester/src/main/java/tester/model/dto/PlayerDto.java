package tester.model.dto;

public class PlayerDto {
	int p_no;
	String m_nick;
	String m_img;
	int wins;
	int total;
	
	public PlayerDto() {
		// TODO Auto-generated constructor stub
	}

	public PlayerDto(int p_no, String m_nick, String m_img, int wins, int total) {
		super();
		this.p_no = p_no;
		this.m_nick = m_nick;
		this.m_img = m_img;
		this.wins = wins;
		this.total = total;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
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

	@Override
	public String toString() {
		return "PlayerDto [p_no=" + p_no + ", m_nick=" + m_nick + ", m_img=" + m_img + ", wins=" + wins + ", total="
				+ total + "]";
	}	
}
