package Product.Entity;

public class Product {
    private final int id;
    private final String brandName;
    private final int qty;
    private final double price;

    public Product(int id, String brandName, int qty, double price) {
        this.id = id;
        this.brandName = brandName;
        this.qty = qty;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public void displayDetails() {
        System.out.println("----------------------");
        System.out.println("Product Id \t\t = " + id);
        System.out.println("Brandname \t\t = " + brandName);
        System.out.println("Quantity \t\t = " + qty);
        System.out.println("Price \t\t\t = " + price);
    }
}
