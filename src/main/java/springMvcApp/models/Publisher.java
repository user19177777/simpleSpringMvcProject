package springMvcApp.models;

public class Publisher {
    private int publisher_id;
    private String publisher_title;

    public Publisher() {
    }

    public Publisher(int publisher_id, String publisher_title) {
        this.publisher_id = publisher_id;
        this.publisher_title = publisher_title;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPublisher_title() {
        return publisher_title;
    }

    public void setPublisher_title(String publisher_title) {
        this.publisher_title = publisher_title;
    }
}
