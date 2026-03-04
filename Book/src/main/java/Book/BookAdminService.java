package Book;

public class BookAdminService {
	
	BookDAO dao;
	
	public BookAdminService() {
		dao = new BookDAOH2();
	}
	
	public boolean update(BookVO bookVO) {
		return dao.update(bookVO)>0 ?  true : false;
		
	}

	public boolean delete(int id) {
		return dao.delete(id)>0 ?  true : false;
	}

	public Object getBooks() {
		return dao.findAll();
	}

	public BookVO getBook(int id) {
		return dao.findById(id);
	}

}
