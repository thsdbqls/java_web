package www2;

public class Phonebook {

	private String name;
	private String hp;
	private String email;
	
	public Phonebook() {}

	public Phonebook(String name, String hp, String email) {
		super();
		this.name = name;
		this.hp = hp;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getHp() {
		return hp;
	}

	public String getEmail() {
		return email;
	}
	
	
}
