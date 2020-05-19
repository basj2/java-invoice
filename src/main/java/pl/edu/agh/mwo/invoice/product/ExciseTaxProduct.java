package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class ExciseTaxProduct extends Product {
    public ExciseTaxProduct(String name, BigDecimal price, BigDecimal tax) {
        super(name, price, tax);
    }

    public BigDecimal getPriceWithTax() {
        return price.multiply(taxPercent).add(price).add(new BigDecimal("5.56"));
    }

}
