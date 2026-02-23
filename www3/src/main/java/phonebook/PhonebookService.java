package phonebook;

import java.util.List;

public class PhonebookService {

	PhonebookDAO dao;
	
	public PhonebookService() {
		dao = new PhonebookDAOH2();
		//dao = new PhonebookDAOOracle();
	}

	public List<PhonebookVO> getPhonebooks() {
		return dao.findAll();
	}

	public boolean insert(int id, String name, String hp, String email, String memo) {
		PhonebookVO pb = new PhonebookVO(id, name, hp, email, memo);
		int result = dao.save(pb);
		if(result>0) return true;
		else return false;
	}

	public PhonebookVO getPhonebooks(int _id) {
		// TODO Auto-generated method stub
		return dao.findById(_id);
	}

	public PhonebookVO getPhonebook(int _id) {
		// TODO Auto-generated method stub
		return dao.findById(_id);
	}

	public boolean delete(int id) {
		// 정수를 리턴 받은 후 true, false 로 다시 값을 전달해야 하는 경우
		// 삼항조건문
		return dao.delete(id)>0 ? true : false;
	}

	public boolean update(PhonebookVO pb) {
		return dao.update(pb)>0 ? true : false;
		
	}

}
