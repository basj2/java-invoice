package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Collection<Product> products;

	public void addProduct(Product product) {
		// TODO: implement
	}

	public void addProduct(Product product, Integer quantity) {
		// TODO: implement
	}

	public BigDecimal getNetPrice() {
		BigDecimal sum = BigDecimal.ZERO;
		try {
			for (Product product : products) {
				sum = sum.add(product.getPrice());
			}
		} catch (NullPointerException e) {
			System.out.println("NullPointerException caught");
		}
		return sum;
	}

	public BigDecimal getTax() {
		BigDecimal sum = BigDecimal.ZERO;
		try {
			for (Product product : products) {
				sum = sum.add(product.getPrice().multiply(getTax()));
			}
		} catch (NullPointerException e) {
			System.out.println("NullPointerException caught");
		}
		return sum;
	}

	public BigDecimal getGrossPrice() {
		return getNetPrice().add(getTax());
	}
}
