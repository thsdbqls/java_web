package news;

import java.util.ArrayList;
import java.util.List;

public class NewsService {

	private NewsDAO dao;
	
	public NewsService() {
		dao = new NewsDAOH2();
	}
	
	public List<MainNews> getMainNews() {
		List<News> news = dao.findAll();
		List<MainNews> list = new ArrayList<MainNews>();
		for(News n:news) {
			list.add(new MainNews(n.getId(), n.getMainImage(), n.getReporterName(), n.getTitle()));
		}
		return list;
	}

	public News getSubNews(Long id) {
		try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}



}
