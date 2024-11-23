package PointOfSale;

import Exceptions.DuplicateIdException;
import Exceptions.InvalidInputException;
import Product.Entity.Product;

import static Login.Login.sc;

import Product.*;

import java.io.IOException;
import java.util.List;

public class AddProduct {


    public AddProduct() {
        while (true) {
            try {
                System.out.println("--------ADD PRODUCT--------");
                System.out.println("Enter Product Choice");
                System.out.println("[1] - Ballpen ");
                System.out.println("[2] - Notebook ");
                System.out.println("[3] - Crayons ");
                System.out.println("[4] - Bondpaper ");
                System.out.println("[5] - Back");
                System.out.print("Choice: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> addProduct("Ballpen");
                    case 2 -> addProduct("Notebook");
                    case 3 -> addProduct("Crayons");
                    case 4 -> addProduct("Bondpaper");
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid Choice");
                }
            } catch (InvalidInputException | DuplicateIdException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addProduct(String productType) throws IOException {
        List<Product> productList = ProductRepository.getProducts(productType);
        if (productList.isEmpty()){
            System.out.println("No Product To Display.");
        }
        productList.forEach(Product::displayDetails);
        System.out.println("----------------------");
        ProductRepository.addProduct(inputs(productType), productType);
        System.out.println("Successfully Added");
    }


    private Product inputs(String productType) throws DuplicateIdException, InvalidInputException, NumberFormatException {
        System.out.print("Enter Id: ");
        int id = Integer.parseInt(sc.nextLine());

        if (ProductRepository.isIdExist(id, productType)) {
            throw new DuplicateIdException("Duplicate ID. Try Again.");
        }

        System.out.print("Enter BrandName: ");
        String bname = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Price: ");
        double price = Double.parseDouble(sc.nextLine());


        return new Product(id, bname, qty, price);
    }
}
