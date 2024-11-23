package Product;

import Product.Entity.Product;

import java.io.*;
import java.util.List;

public class ProductRepository {

    public static File basePath = new File("products");

    public static List<Product> getProducts(String productType) throws IOException {
        File productFile = createPath(productType);
        return new BufferedReader(new FileReader(productFile))
                .lines()
                .map(lines -> lines.split(","))
                .map(lines -> new Product(Integer.parseInt(lines[0]), lines[1], Integer.parseInt(lines[2]), Double.parseDouble(lines[3])))
                .toList();
    }

    public static void addProduct(Product product, String productType) throws IOException {
        File productFile = createPath(productType);
        BufferedWriter bw = new BufferedWriter(new FileWriter(productFile, true));
        bw.write(product.getId() + "," + product.getBrandName() + "," + product.getQty() + "," + product.getPrice() + "\n");
        bw.close();
    }

    public static boolean isIdExist(int id, String productType) {
        try {
            File productFile = createPath(productType);
            return new BufferedReader(new FileReader(productFile))
                    .lines()
                    .map(lines -> lines.split(","))
                    .anyMatch(lines -> id == Integer.parseInt(lines[0]));
        } catch (Exception e) {
            return false;
        }
    }

    public static File createPath(String productType) throws IOException {
        if (!basePath.exists()) basePath.mkdir();
        File productFile = new File(basePath, productType + ".txt");
        if (!productFile.exists()) productFile.createNewFile();
        return productFile;
    }
}
