package views;

import models.Product;
import service.IProductService;
import service.ProductService;
import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private IProductService productService;
    private static Scanner scanner = new Scanner(System.in);

    public ProductView() {
        productService = ProductService.getInstance();
    }

    public void showProduct(List<Product> products) {
        System.out.println("                            DANH SÁCH SẢN PHẨM");
        System.out.println("=================================================================================");
        System.out.printf("%-12s | %-15s | %-15s | %-12s | %-25s\n", "ID Sản phẩm", "Tên sản phẩm", "Giá sản phẩm", "Số lượng", "Mô tả sản phẩm");
        System.out.println("=================================================================================");
        for (Product product : products) {
            System.out.printf("%-12s | %-15s | %-15s | %-12s | %-25s\n",
                    product.getIdProduct(),
                    product.getName(),
                    CheckUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    product.getDescription());
        }
        System.out.println("=================================================================================");
    }

    public void updateProduct() {
        int choice;
        boolean checkActionMenu = true;
        do {
            try {
                showProduct(productService.getAllProducts());
                long idUpdate = inputId();
                Product product = productService.findProductById(idUpdate);
                menuUpdateProduct();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        updateName(product);
                        checkActionMenu = SelectUtils.isSelect(SelectOption.UPDATE);
                        break;
                    case 2:
                        updatePrice(product);
                        checkActionMenu = SelectUtils.isSelect(SelectOption.UPDATE);
                        break;
                    case 3:
                        updateQuantity(product);
                        checkActionMenu = SelectUtils.isSelect(SelectOption.UPDATE);
                        break;
                    case 4:
                        updateDescription(product);
                        checkActionMenu = SelectUtils.isSelect(SelectOption.UPDATE);
                        break;
                    case 5:
                        checkActionMenu = false;
                        break;
                    default:
                        System.out.println("Nhập sai. Vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu);
    }

    private void updateDescription(Product newProduct) {
        String newDes = inputDescription();
        newProduct.setDescription(newDes);
        productService.update(newProduct);
        System.out.println("Sửa mô tả sản phẩm thành công");
    }

    private void updateQuantity(Product newProduct) {
        int newQuantity = inputQuantity();
        newProduct.setQuantity(newQuantity);
        productService.update(newProduct);
        System.out.println("Sửa số lượng sản phẩm thành công");
    }

    private void updatePrice(Product newProduct) {
        double newPrice = inputPrice();
        newProduct.setPrice(newPrice);
        productService.update(newProduct);
        System.out.println("Sửa giá sản phẩm thành công");
    }

    private void updateName(Product newProduct) {
        String newName = inputProductName();
        newProduct.setName(newName);
        productService.update(newProduct);
        System.out.println("Sửa tên sản phầm thành công");
    }

    private void menuUpdateProduct() {
        System.out.println("===================================");
        System.out.println("||    Menu chỉnh sửa sản phẩm    ||");
        System.out.println("||     1. Sửa tên sản phẩm       ||");
        System.out.println("||     2. Sửa giá sản phẩm       ||");
        System.out.println("||     3. Sửa số lượng sản phẩm  ||");
        System.out.println("||     4. Sửa mô tả sản phẩm     ||");
        System.out.println("||     5. Quay lại               ||");
        System.out.println("===================================");
        System.out.print("Nhập lựa chọn ==>");
    }

    private long inputId() {
        long id;
        System.out.println("Nhập Id: ");
        boolean checkActionMenu = true;
        do {
            id = CheckUtils.longFormatCheck();
            boolean isFindId = productService.existById(id);
            if (isFindId) {
                checkActionMenu = false;
            } else {
                System.out.println("Không tìm thấy. Vui lòng nhập lại!");
            }
        } while (checkActionMenu);
        return id;
    }

    public void deleteProduct() {
        do {
            try {
                showProduct(productService.getAllProducts());
                long idDelete = inputId();
                productService.deleteById(idDelete);
                System.out.println("Xóa sản phẩm thành công");
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (SelectUtils.isSelect(SelectOption.DELETE));
    }

    public void addProduct() {
        do {
            try {
                long id = System.currentTimeMillis() / 10000;
                String productName = inputProductName();
                double productPrice = inputPrice();
                int productQuantity = inputQuantity();
                String productDescription = inputDescription();
                Product product = new Product(id, productName, productPrice, productQuantity, productDescription);
                productService.addProduct(product);
                System.out.println("Thêm sản phẩm thành công");
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (SelectUtils.isSelect(SelectOption.ADD));
    }

    private double inputPrice() {
        System.out.println("Nhập giá của sản phẩm");
        double price;
        do {
            price = CheckUtils.doubleFormatCheck();
            if (price <= 0 || price >= 10000000)
                System.out.println("Giá của sản phẩm phải lớn hơn 0 và bé hơn 10.000.000đ");
        } while (price <= 0 || price >= 10000000);
        return price;
    }

    private int inputQuantity() {
        System.out.println("Nhập số lượng sản phẩm: ");
        int quantity;
        do {
            quantity = CheckUtils.intFormatCheck();
            if (quantity <= 0 || quantity > 1000)
                System.out.println("Số lượng sản phẩm không thể âm và phải nhỏ hơn 1000.");
        } while (quantity <= 0 || quantity > 1000);
        return quantity;
    }

    private String inputProductName() {
        System.out.println("Nhập tên sản phẩm: ");
        String productName;
        do {
            if (ValidateUtils.isProductNameValid(productName = CheckUtils.stringEmpty())) {
                System.out.println("Nhập tên sản phẩm");
            }
            productName = productName.trim();
            if (productService.existByName(productName)) {
                System.out.println("Tên sản phẩm đã tồn tại");
                continue;
            }
            break;
        } while (true);
        return productName;
    }

    private String inputDescription() {
        System.out.println("Nhập mô tả sản phẩm: ");
        String des;
        if (ValidateUtils.isAddressValid(des = CheckUtils.stringEmpty())) {
            System.out.println("Nhập mô tả sản phẩm");
        }
        des = des.trim();
        return des;
    }

    public void findProductByName() {
        do {
            try {
                System.out.println("Nhập tên sản phẩm muốn tìm");
                System.out.print("==> ");
                String name = scanner.next();
                showProduct(productService.findByName(name));
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (SelectUtils.isSelect(SelectOption.FIND));

    }

//    public void findProductByID() {
//        do {
//            try {
//                long id = inputId();
//                productService.findById(id);
//            } catch (Exception e) {
//                System.out.println("Sai cú pháp. Xin mời nhập lại!");
//            }
//        } while (SelectUtils.isSelect(SelectOption.FIND));
//    }

    public void sortProduct() {
        do {
            sortProductMenu();
            int choice;
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    // sap xep theo id
                    sortProductByID();
                    break;
                case 2:
                    // sap xep theo ten
                    sortProductsByName();
                    break;
                case 3:
                    // sap xep theo gia
                    sortProductsByPrice();
                    break;
                case 4:
                    // sap xep theo so luong
                    sortProductsByQuantity();
                    break;
                case 5:
                    // quay lai
                    break;
                default:
                    System.out.println("Nhập sai. Vui lòng nhập lại");
                    break;
            }
        } while (SelectUtils.isSelect(SelectOption.SORT));
    }

    private void sortProductsByQuantity() {
        sortProductByMenu();
        int sortChoice = Integer.parseInt(scanner.nextLine());
        switch (sortChoice) {
            case 1:
                showProduct(productService.sortByQuantity(TypeSort.ASC));
                SelectUtils.isSelect(SelectOption.SORT);
                break;
            case 2:
                showProduct(productService.sortByQuantity(TypeSort.DESC));
                SelectUtils.isSelect(SelectOption.SORT);
                break;
            case 3:
                // quay lai
                break;
        }
    }

    private void sortProductsByPrice() {
        sortProductByMenu();
        int sortChoice = Integer.parseInt(scanner.nextLine());
        switch (sortChoice) {
            case 1:
                showProduct(productService.sortByPrice(TypeSort.ASC));
                SelectUtils.isSelect(SelectOption.SORT);
                break;
            case 2:
                showProduct(productService.sortByPrice(TypeSort.DESC));
                SelectUtils.isSelect(SelectOption.SORT);
                break;
            case 3:
                // quay lai
                break;
        }
    }

    private void sortProductsByName() {
        sortProductByMenu();
        int sortChoice = Integer.parseInt(scanner.nextLine());
        switch (sortChoice) {
            case 1:
                showProduct(productService.sortByName(TypeSort.ASC));
                SelectUtils.isSelect(SelectOption.SORT);
                break;
            case 2:
                showProduct(productService.sortByName(TypeSort.DESC));
                SelectUtils.isSelect(SelectOption.SORT);
                break;
            case 3:
                // quay lai
                break;
        }
    }

    private void sortProductByID() {
        sortProductByMenu();
        int sortChoice = Integer.parseInt(scanner.nextLine());
        switch (sortChoice) {
            case 1:
                showProduct(productService.sortById(TypeSort.ASC));
                SelectUtils.isSelect(SelectOption.SORT);
                break;
            case 2:
                showProduct(productService.sortById(TypeSort.DESC));
                SelectUtils.isSelect(SelectOption.SORT);
                break;
            case 3:
                // quay lai
                break;
        }
    }

    private void sortProductByMenu() {
        System.out.println("==================================");
        System.out.println("||  Chọn kiểu sắp xếp sản phẩm  ||");
        System.out.println("||      1. Sắp xếp tăng dần     ||");
        System.out.println("||      2. Sắp xếp giảm dần     ||");
        System.out.println("||      3. Quay lại             ||");
        System.out.println("==================================");
        System.out.println("Nhập lựa chọn");System.out.print("==> ");
    }

    private void sortProductMenu() {
        System.out.println("==================================");
        System.out.println("||      Menu kiểu sắp xếp       ||");
        System.out.println("||     1. Sắp xếp theo ID       ||");
        System.out.println("||     2. Sắp xếp theo tên      ||");
        System.out.println("||     3. Sắp xếp theo giá      ||");
        System.out.println("||     4. Sắp xếp theo số lượng ||");
        System.out.println("||     5. Quay lại              ||");
        System.out.println("==================================");
        System.out.println("Nhập lựa chọn");System.out.print("==> ");
    }
}
