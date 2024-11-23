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
                    case 1 -> buyBallpen();
                    case 2 -> buyNotebook();
                    case 3 -> buyCrayons();
                    case 4 -> buyBondpaper();
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

    private void buyBondpaper() throws IOException {
        List<Product> bondpaperList = ProductRepository.getProduct("Bondpaper");
        if (bondpaperList.isEmpty()) {
            throw new NoAvailableProduct("There is no available products");
        }
        bondpaperList.forEach(Product::displayDetails);
        buy(bondpaperList, "Bondpaper");
    }

    private void buyCrayons() throws IOException {
        List<Product> crayonsList = ProductRepository.getProduct("Crayons");
        if (crayonsList.isEmpty()) {
            throw new NoAvailableProduct("There is no available products");
        }
        crayonsList.forEach(Product::displayDetails);
        buy(crayonsList, "Crayons");
    }

    private void buyNotebook() throws IOException {
        List<Product> notebookList = ProductRepository.getProduct("Notebook");
        if (notebookList.isEmpty()) {
            throw new NoAvailableProduct("There is no available products");
        }
        notebookList.forEach(Product::displayDetails);
        buy(notebookList, "Notebook");
    }

    private void buyBallpen() throws  IOException {
        List<Product> ballpenList = ProductRepository.getProduct("Ballpen");
        if (ballpenList.isEmpty()) {
            throw new NoAvailableProduct("There is no available products");
        }
        ballpenList.forEach(Product::displayDetails);
        buy(ballpenList, "Ballpen");
    }

    public void buy(List<Product> productList, String productType) {
        try {
            System.out.println("--------BUY PRODUCT--------");
            System.out.print("Enter Product Id to buy: ");
            int idToBuy = Integer.parseInt(sc.nextLine());

            boolean isIdExist = productList.stream().anyMatch(product -> idToBuy == product.getId());
            if (!isIdExist) {
                throw new IdNotFoundException("Product Id doesn't exist.");
            }
            System.out.print("Enter how many qty/s you want to buy: ");
            int qtyToBuy = Integer.parseInt(sc.nextLine());
            for (Product product : productList) {
                if (idToBuy == product.getId()) {
                    if (qtyToBuy > product.getQty()) {
                        throw new OutOfStockException("Out Of Stock !!!");
                    }
                }
            }

            productList.stream()
                    .filter(product -> idToBuy == product.getId())
                    .forEach(product -> {
                        try {
                            Payment pay = new Payment(product.getId(), product.getBrandName(), qtyToBuy, product.getPrice());
                            PaymentRepository.doPayment(pay, productType);
                            System.out.println("Purchase Successfully");
                            System.out.println("Total = " + pay.compute());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (InvalidInputException | OutOfStockException | NoAvailableProduct | IdNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
