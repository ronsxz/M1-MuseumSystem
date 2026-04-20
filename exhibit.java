package model;

public class Exhibit {
    private int id;
    private String title;
    private String description;
    private String image;

    public Exhibit(int id, String title, String description, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImage() { return image; }
}

