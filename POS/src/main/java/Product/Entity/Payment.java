package Product.Entity;

public class Payment extends Product {
    public Payment(int productId, String productName, int productQty, double productPrice) {
        super(productId, productName, productQty, productPrice);
    }

    public double compute() {
        double tax = 0.12;
        double subtotal = getQty() * getPrice();
        double total = subtotal * tax;
        return total + subtotal;
    }
}
