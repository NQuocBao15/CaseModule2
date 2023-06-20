package service;

import models.Product;
import utils.TypeSort;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    void addProduct(Product product);
    Product findById(long idProduct);
    boolean existByName(String product);
    boolean existById(long idProduct);
    void update(Product newProduct);
    void deleteById(long idProduct);
    List<Product> findByName(String product);
    List<Product> sortById(TypeSort product);
    List<Product> sortByName(TypeSort product);
    List<Product> sortByQuantity(TypeSort product);
    List<Product> sortByPrice(TypeSort product);

    Product findProductById(long product);
}
