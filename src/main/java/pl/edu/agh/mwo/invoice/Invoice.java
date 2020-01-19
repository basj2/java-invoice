package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Map<Product, Integer> products = new HashMap<>();

	public void addProduct(Product product) {
		this.addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity cannot be zero or negative.");
		}
		this.products.put(product, quantity);
	}

	public BigDecimal getNetPrice() {
		BigDecimal sum = BigDecimal.ZERO;
			for (Product product : this.products.keySet()) {
				Integer quantity = this.products.get(product);
				sum = sum.add(product.getPrice().multiply(new BigDecimal (quantity)));
			}
		return sum;
	}

	public BigDecimal getTax() {
		BigDecimal sum = BigDecimal.ZERO;
			for (Product product : products.keySet()) {
				sum = sum.add(product.getTaxPercent()
						.multiply(new BigDecimal (products.get(product))
								.multiply(product.getPrice())));
			}
		return sum;
	}

	public BigDecimal getGrossPrice() {
		return getNetPrice().add(getTax());
	}
}
