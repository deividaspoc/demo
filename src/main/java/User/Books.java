package User;

public class Books {

    private int id;
    private String name;
    private String summary;
    private String isbn;
    private String photo;
    private int pages;
    private String category;
    private int user_id;

    public Books() {
    }

    public Books(int id, String name, String summary, String isbn, String photo, int pages, String category, int user_id) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.isbn = isbn;
        this.photo = photo;
        this.pages = pages;
        this.category = category;
        this.user_id = user_id;
    }
    public Books(int id, String name, String summary, String isbn, String photo, int pages, String category) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.isbn = isbn;
        this.photo = photo;
        this.pages = pages;
        this.category = category;
    }

    public Books(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Books(String name, String summary, String isbn, String photo, int pages, String category, int user_id) {
        this.name = name;
        this.summary = summary;
        this.isbn = isbn;
        this.photo = photo;
        this.pages = pages;
        this.category = category;
        this.user_id = user_id;
    }

    public Books(String name, String summary, String isbn, String photo, int pages, String category) {
        this.name = name;
        this.summary = summary;
        this.isbn = isbn;
        this.photo = photo;
        this.pages = pages;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", isbn='" + isbn + '\'' +
                ", photo='" + photo + '\'' +
                ", pages=" + pages +
                ", category='" + category + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
