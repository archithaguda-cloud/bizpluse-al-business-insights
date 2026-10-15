package com.bizpulse.service;
import com.bizpulse.entity.Product;
import com.bizpulse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            seedDefaultProducts();
            products = productRepository.findAll();
        }
        return products;
    }
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    public Product restock(String id, int additionalUnits) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
        int newStock = (product.getStock() != null ? product.getStock() : 0) + additionalUnits;
        product.setStock(newStock);
        if (newStock > 30) {
            product.setStatus("In Stock");
        } else if (newStock > 15) {
            product.setStatus("Low Stock");
        }
        return productRepository.save(product);
    }
    private void seedDefaultProducts() {
        productRepository.saveAll(List.of(
                new Product("PRD-001", "OmniDesk Pro Smart Hub", "Electronics", 249.99, 420, 104995.80, 45, "Low Stock", "High Demand (+24%)", "42%"),
                new Product("PRD-002", "ErgoFlex Lumbar Task Chair", "Furniture", 389.00, 312, 121368.00, 128, "In Stock", "Stable Demand", "38%"),
                new Product("PRD-003", "AeroNoise ANC Headphones", "Audio", 179.50, 680, 122060.00, 12, "Critical Alert", "Surge Detected (+68%)", "55%"),
                new Product("PRD-004", "TitanCore 4K Thunderbolt Dock", "Accessories", 129.99, 510, 66294.90, 89, "In Stock", "Normal", "32%"),
                new Product("PRD-005", "BioSensor Ergonomic Vertical Mouse", "Accessories", 69.99, 840, 58791.60, 210, "In Stock", "Price Elasticity Opportunity", "61%"),
                new Product("PRD-006", "UltraWide Curved 34\" Monitor", "Displays", 549.00, 185, 101565.00, 19, "Low Stock", "High Margin Driver", "46%")
        ));
    }
}