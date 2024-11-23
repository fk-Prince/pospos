package Exceptions;

public class NoAvailableProduct extends RuntimeException {
    public NoAvailableProduct(String message) {
        super(message);
    }
}
