package ung_wishlist;

class ItemDetails {
	private long ID;
    private String name;
    private String description;
    private String link;
    private double price;
    private boolean purchased;

    public ItemDetails(Long ID, String name, String description, String link, double price, boolean purchased) {
        this.ID = ID;
    	this.name = name;
        this.description = description;
        this.link = link;
        this.price = price;
        this.purchased = purchased;
    }
    
    public long getID() {
    	return ID;
    }
    
    public void setID(long ID) {
    	this.ID = ID;
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
    
    public void setPurchased(boolean purchased) {
    	this.purchased = purchased;
    }
    
    public boolean getPurchased() {
    	return purchased;
    }
}