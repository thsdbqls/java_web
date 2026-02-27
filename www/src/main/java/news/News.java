package news;

import java.time.LocalDateTime;

public class News {

    private Long id;                // ID
    private String mainImage;       // MAIN_IMAGE
    private String reporterName;    // REPORTER_NAME 기자 명
    private String pressName;       // PRESS_NAME 신문사명
    private String title;           // TITLE
    private String content;         // CONTENT (CLOB)
    private LocalDateTime createdAt; // CREATED_AT
    private LocalDateTime updatedAt; // UPDATED_AT

    public News() {
    }

    public News(Long id, String mainImage, String reporterName, String pressName,
                String title, String content,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.mainImage = mainImage;
        this.reporterName = reporterName;
        this.pressName = pressName;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter & Setter

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

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getPressName() {
        return pressName;
    }

    public void setPressName(String pressName) {
        this.pressName = pressName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", mainImage='" + mainImage + '\'' +
                ", reporterName='" + reporterName + '\'' +
                ", pressName='" + pressName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}