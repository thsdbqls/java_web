package news;

import java.util.List;

public interface NewsDAO {
	public int save(News news);
	public List<News> findAll();
	public News findById(Long id);
	public int update(News news);
	public int delete(Long id);

}
