package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;

import java.util.ArrayList;
import java.util.List;

public class InvoiceFactory {
    public Invoice create(String clientEmail, List<Product> products) {
        List<InvoiceLine> lines = new ArrayList<>();
        for (Product product : products) {
            InvoiceLine line = new InvoiceLine(product.getName(), product.getPrice());
            lines.add(line);
        }
        return new Invoice(clientEmail, lines);
    }
}