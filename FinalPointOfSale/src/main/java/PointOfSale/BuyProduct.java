package PointOfSale;

import Exceptions.IdNotFoundException;
import Exceptions.InvalidInputException;
import Exceptions.NoAvailableProduct;
import Exceptions.OutOfStockException;
import Product.Entity.Payment;
import Product.Entity.Product;
import Product.ProductRepository;
import Product.PaymentRepository;

import java.io.IOException;
import java.util.List;

import static Login.Login.sc;

public class BuyProduct {

    public BuyProduct() {
        while (true) {
            try {
                System.out.println("------------------------------");
                System.out.println("Enter Product Choice");
                System.out.println("[1] - Ballpen ");
                System.out.println("[2] - Notebook ");
                System.out.println("[3] - Crayons ");
                System.out.println("[4] - Bondpaper ");
                System.out.println("[5] - Back");
                System.out.print("Choice: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> buyProduct("Ballpen");
                    case 2 -> buyProduct("Notebook");
                    case 3 -> buyProduct("Crayons");
                    case 4 -> buyProduct("Bondpaper");
                    case 5 -> {
                        return;
                    }
                }
            } catch (InvalidInputException | NoAvailableProduct e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void buyProduct(String productType) throws IOException, NoAvailableProduct {
        List<Product> productList = ProductRepository.getProducts(productType);
        if (productList.isEmpty()) {
            throw new NoAvailableProduct("There is no available products for " + productType);
        }
        productList.forEach(Product::displayDetails);
        buy(productList, productType);
    }

    private void buy(List<Product> productList, String productType) {
        try {
            System.out.println("--------BUY PRODUCT--------");
            System.out.print("Enter Product Id to buy: ");
            int idToBuy = Integer.parseInt(sc.nextLine());

            Product productSelected = productList.stream()
                    .filter(product -> idToBuy == product.getId())
                    .findFirst()
                    .orElseThrow(() -> new IdNotFoundException("Product Id doesn't exist."));

            System.out.print("Enter how many qty/s you want to buy: ");
            int qtyToBuy = Integer.parseInt(sc.nextLine());

            if (qtyToBuy > productSelected.getQty()) {
                throw new OutOfStockException("Out of Stock!");
            }

            Payment payment = new Payment(productSelected.getId(), productSelected.getBrandName(), qtyToBuy, productSelected.getPrice());
            PaymentRepository.doPayment(payment, productType);
            System.out.println("Total = " + payment.compute());
            System.out.println("Purchase Successful");
        } catch (InvalidInputException | OutOfStockException | NoAvailableProduct | IdNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
