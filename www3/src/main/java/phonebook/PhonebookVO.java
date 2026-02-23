package phonebook;

public class PhonebookVO {
	private int id;
	private String name;
	private String hp;
	private String email;
	private String memo;
	
	public PhonebookVO() {
		// TODO Auto-generated constructor stub
	}

	public PhonebookVO(int id, String name, String hp, String email, String memo) {
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.email = email;
		this.memo = memo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "PhonebookVO [id=" + id + ", name=" + name + ", hp=" + hp + ", email=" + email + ", memo=" + memo + "]";
	}
	
	
}
