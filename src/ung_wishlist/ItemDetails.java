package ung_wishlist;

class ItemDetails {
    private String name;
    private String description;
    private String link;
    private double price;

    public ItemDetails(String name, String description, String link, double price) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}