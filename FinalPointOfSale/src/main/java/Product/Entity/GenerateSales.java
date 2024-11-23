package Product.Entity;

import java.io.IOException;
import java.util.List;

import Product.*;

public class GenerateSales {
    private List<Payment> paymentList;

    public GenerateSales() {

    }

    public double computeSales(String productChoice) {
        try {
            this.paymentList = PaymentRepository.getPayments(productChoice);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paymentList.stream()
                .mapToDouble(Payment::compute)
                .sum();
    }
}
