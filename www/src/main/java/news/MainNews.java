package news;

public class MainNews {
	 private Long id;
	 private String mainImage; 
	 private String reporterName; 
	 private String title;
	 
	 public MainNews() {
		// TODO Auto-generated constructor stub
	}

	 public MainNews(Long id, String mainImage, String pressName, String title) {
		super();
		this.id = id;
		this.mainImage = mainImage;
		this.reporterName = pressName;
		this.title = title;
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getMainImage() {
		 return mainImage;
	 }

	 public void setMainImage(String mainImage) {
		 this.mainImage = mainImage;
	 }

	 public String getreporterName() {
		 return reporterName;
	 }

	 public void setreporterName(String reporterName) {
		 this.reporterName = reporterName;
	 }

	 public String getTitle() {
		 return title;
	 }

	 public void setTitle(String title) {
		 this.title = title;
	 }

	 @Override
	 public String toString() {
		return "MainNews [id=" + id + ", mainImage=" + mainImage + ", reporterName=" + reporterName + ", title=" + title
				+ "]";
	 }
	 
	 
}
