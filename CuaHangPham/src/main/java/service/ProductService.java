package service;

import models.Product;
import utils.FileUtils;
import utils.TypeSort;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public final String pathProduct = "D:\\CodeGym\\CaseModule2\\CuaHangPham\\data\\products.csv";
    private static ProductService instance;
    public static ProductService getInstance() {
        if (instance == null)
            instance = new ProductService();
        return instance;
    }

    @Override
    public List<Product> getAllProducts() {
        return FileUtils.readData(pathProduct, Product.class);
    }

    @Override
    public void addProduct(Product product) {
        List<Product> products = getAllProducts();
        products.add(product);
        FileUtils.writeDataToFile(pathProduct, products);
    }

    @Override
    public Product findById(long id) {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getIdProduct() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean existByName(String productName) {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existById(long id) {
        return findById(id) != null;
    }

    @Override
    public void update(Product newProduct) {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getIdProduct() == newProduct.getIdProduct()) {
                product.setName(newProduct.getName());
                product.setPrice(newProduct.getPrice());
                product.setQuantity(newProduct.getQuantity());
                product.setDescription(newProduct.getDescription());
                FileUtils.writeDataToFile(pathProduct,products);
                break;
            }
        }
    }

    @Override
    public void deleteById(long id) {
        List<Product> products = getAllProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getIdProduct() == id){
                products.remove(products.get(i));
            }
        }
        FileUtils.writeDataToFile(pathProduct, products);
    }

    @Override
    public List<Product> findByName(String productName) {
        List<Product> products = getAllProducts();
        List<Product> productsFind = new ArrayList<>();
        for (Product item : products) {
            if ((item.getName().toUpperCase().contains(productName.toUpperCase()))) {
                productsFind.add(item);
            }
        }
        if (productsFind.isEmpty()) {
            return null;
        }
        return productsFind;
    }

    @Override
    public List<Product> sortById(TypeSort typeSort) {
        List<Product> products = getAllProducts();
        if (typeSort == TypeSort.ASC) {
            products.sort((o1, o2) -> {
                double result = o1.getIdProduct() - o2.getIdProduct();
                if (result == 0) {
                    return 0;
                }
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            products.sort((o1, o2) -> {
                double result = o1.getIdProduct() - o2.getIdProduct();
                if (result == 0) {
                    return 0;
                }
                return result > 0 ? -1 : 1;
            });
        }
        return products;
    }

    @Override
    public List<Product> sortByName(TypeSort typeSort) {
        List<Product> products = getAllProducts();
        if (typeSort == TypeSort.ASC) {
            products.sort((o1, o2) -> {
                double result = o1.getName().compareTo(o2.getName());
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            products.sort((o1, o2) -> {
                double result = o1.getName().compareTo(o2.getName());
                if (result == 0)
                    return 0;
                return result > 0 ? -1 : 1;
            });
        }
        return products;
    }

    @Override
    public List<Product> sortByQuantity(TypeSort typeSort) {
        List<Product> products = getAllProducts();
        if (typeSort == TypeSort.ASC) {
            products.sort((o1, o2) -> {
                double result = o1.getQuantity() - o2.getQuantity();
                if (result == 0) {
                    return 0;
                }
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            products.sort((o1, o2) -> {
                double result = o1.getQuantity() - o2.getQuantity();
                if (result == 0) {
                    return 0;
                }
                return result > 0 ? -1 : 1;
            });
        }
        return products;
    }

    @Override
    public List<Product> sortByPrice(TypeSort typeSort) {
        List<Product> products = getAllProducts();
        if (typeSort == TypeSort.ASC) {
            products.sort((o1, o2) -> {
                double result = o1.getPrice() - o2.getPrice();
                if (result == 0) {
                    return 0;
                }
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            products.sort((o1, o2) -> {
                double result = o1.getPrice() - o2.getPrice();
                if (result == 0) {
                    return 0;
                }
                return result > 0 ? -1 : 1;
            });
        }
        return products;
    }

    @Override
    public Product findProductById(long idProduct) {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getIdProduct() == idProduct) {
                return product;
            }
        }
        return null;
    }
}
