public class MenuItem {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price, String remarks) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return String.format("%s: %s - %.2f", this,name, this.description, this.price);
    }
}
