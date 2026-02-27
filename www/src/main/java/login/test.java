package login;

public class test {

	public static void main(String[] args) {
		UserDAO dao = new UserDAOH2();
		
		//System.out.println(dao.findAll());
		System.out.println(dao.findById(1));
		System.out.println(dao.findByUsername("admin"));

	}

}
