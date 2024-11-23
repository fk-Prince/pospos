package Product;

import Product.Entity.Product;

import java.io.*;
import java.util.List;

public class ProductRepository {

    public static File productFile = new File("" +
            "product.txt");

    public static List<Product> getProduct(String name) throws IOException {
        if (!productFile.exists()) productFile.createNewFile();
        return new BufferedReader(new FileReader(productFile))
                .lines()
                .map(lines -> lines.split(","))
                .filter(lines -> name.equalsIgnoreCase(lines[0]))
                .map(lines -> new Product(Integer.parseInt(lines[1]), lines[2], Integer.parseInt(lines[3]), Double.parseDouble(lines[4])))
                .toList();
    }

    public static void addProduct(Product product, String productType) throws IOException {
        if (!productFile.exists()) productFile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(productFile, true));
        bw.write(productType + "," + product.getId() + "," + product.getBrandName() + "," + product.getQty() + "," + product.getPrice() + "\n");
        bw.close();
    }

    public static boolean isIdExist(int id, String type) {
        try {
            if (!productFile.exists()) productFile.createNewFile();
            return new BufferedReader(new FileReader(productFile))
                    .lines()
                    .map(lines -> lines.split(","))
                    .anyMatch(lines -> id == Integer.parseInt(lines[1]) && type.equalsIgnoreCase(lines[0]));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
