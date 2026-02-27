package phonebookv2;

import java.util.List;


public class PhonebookService {

	PhonebookDAO dao;

	public PhonebookService() {
		dao = new PhonebookDAOH2();
	}
	
	public List<PhonebookVO> getPhonebooks() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public boolean insert(int id, String name, String hp, String email, String memo, String pic) {
		PhonebookVO pb=new PhonebookVO(id, name, hp, email, memo, pic);
		int result=dao.save(pb);
		if(result>0) return true;
		else return false;
	}

	public PhonebookVO getPhonebook(int id) {
		return dao.findById(id);
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return dao.delete(id)>0 ?  true : false;
	}

	public boolean update(PhonebookVO pb) {
		return dao.update(pb)>0 ?  true : false;
		
	}
	
	
}
