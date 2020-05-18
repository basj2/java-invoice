package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public String getProductList() {
        String productList = number + "\n";
        for (Product product : products.keySet()) {
            BigDecimal totalGross = product.getPriceWithTax()
                    .multiply(new BigDecimal(products.get(product)));
            String productLine = String.format("%-20s %5d x%6.2f %-6.2f\n", 
                    product.getName(), products.get(product),
                    product.getPriceWithTax(), totalGross);
            productList += productLine;
        }
        productList += "Liczba pozycji: " + products.size();
        return productList;
    }
    
    public void printProductList() {
        System.out.println(getProductList());
    }
}
