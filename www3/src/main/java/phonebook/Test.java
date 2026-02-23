package phonebook;

public class Test {

	public static void main(String[] args) {
		PhonebookDAO dao=new PhonebookDAOH2();
		//PhonebookDAO dao=new PhonebookDAOOracle();
		dao.save(new PhonebookVO(14, "a", "010-1234-5678", "aaa@aaa", "ccc"));
		System.out.println(dao.findAll());
		

	}

}
