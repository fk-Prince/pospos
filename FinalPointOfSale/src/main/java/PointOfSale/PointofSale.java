package PointOfSale;

import Exceptions.InvalidInputException;
import Product.Entity.Product;
import Product.ProductRepository;

import java.io.IOException;
import java.util.List;


import static Login.Login.sc;

public class PointofSale {


    public PointofSale() throws IOException {

    }



    public void run() {
        while (true) {
            try {
                System.out.println("------------------------------");
                System.out.println("[1] - Add Product");
                System.out.println("[2] - Buy Product");
                System.out.println("[3] - Generate Sales");
                System.out.println("[4] - Sign-out");
                System.out.print("Choice: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> new AddProduct();
                    case 2 -> new BuyProduct();
                    case 3 -> new Sales();
                    case 4 -> {
                        return;
                    }
                    default -> throw new InvalidInputException("Invalid Choice");
                }

            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Choice");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
