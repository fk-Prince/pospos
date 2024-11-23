package PointOfSale;

import Exceptions.InvalidInputException;
import Product.Entity.GenerateSales;


import static Login.Login.sc;

public class Sales {

    public Sales() {
        while (true) {
            try {
                System.out.println("[1] - Generate the Sales of Ballpen ");
                System.out.println("[2] - Generate the Sales of Notebook ");
                System.out.println("[3] - Generate the Sales of Crayons ");
                System.out.println("[4] - Generate the Sales of Bondpaper ");
                System.out.println("[5] - Generate all Product Sales ");
                System.out.println("[6] - Back");
                System.out.print("Choices: ");
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 6) {
                    return;
                } else if (choice > 6) {
                    throw new InvalidInputException("Invalid Choice");
                }
                generate(choice);

            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Choice");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void generate(int choice) {
        GenerateSales sales = new GenerateSales();
        double overallTotalSales;
        String productType;
        System.out.println("-----------------------------------------------------------------");
        switch (choice) {
            case 1 -> {
                overallTotalSales = sales.computeSales("Ballpen");
                System.out.printf("Total sales of Ballpen is %.2f", overallTotalSales);
            }
            case 2 -> {
                overallTotalSales = sales.computeSales("Notebook");
                System.out.printf("Total sales of Notebook is %.2f", overallTotalSales);
            }
            case 3 -> {
                overallTotalSales = sales.computeSales("Crayons");
                System.out.printf("Total sales of Crayons is %.2f", overallTotalSales);
            }
            case 4 -> {
                overallTotalSales = sales.computeSales("Bondpaper");
                System.out.printf("Total sales of Bondpaper is %.2f", overallTotalSales);
            }
            case 5 -> {
                overallTotalSales = sales.computeSales("All");
                System.out.printf("Total sales of All Product is %.2f", overallTotalSales);
            }
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------");
    }
}
