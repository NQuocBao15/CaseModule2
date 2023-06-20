package views;

import service.ProductService;
import utils.CheckUtils;

import java.util.Scanner;

public class ProductManageView {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductView productView = new ProductView();
    private static ProductService productService = new ProductService();
    private static OrderView orderView = new OrderView();

    public static void launchAdmin() {
        boolean checkActionMenu = true;
        int choice;
        do {
            menuForAdmin();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productView.showProduct(productService.getAllProducts());
                        CheckUtils.pressEnterToContinue();
                        break;
                    case 2:
                        productView.addProduct();
                        break;
                    case 3:
                        productView.updateProduct();
                        break;
                    case 4:
                        productView.deleteProduct();
                        break;
                    case 5:
                        productView.findProductByName();
                        break;
                    case 6:
                        productView.sortProduct();
                        break;
                    case 7:
                        checkActionMenu = false;
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Nhập sai, vui lòng nhập lại");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu);
    }

    public static void launchGuest() {
        boolean checkActionMenu = true;
        do {
            menuForGuest();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productView.showProduct(productService.getAllProducts());
                        CheckUtils.pressEnterToContinue();
                        break;
                    case 2:
                        productView.findProductByName();
                        break;
                    case 3:
                        productView.sortProduct();
                        break;
                    case 4:
                        // MUA HANG
                        orderView.addOrder();
                        break;
                    case 5:
                        checkActionMenu = false;
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Nhập sai, vui lòng nhập lại");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu);
    }

    public static void menuForAdmin() {
        System.out.println("═══════════ ADMIN MENU PRODUCT ═══════════");
        System.out.println("║                                        ║");
        System.out.println("║       1. Hiện danh sách sản phẩm.      ║");
        System.out.println("║       2. Thêm sản phẩm.                ║");
        System.out.println("║       3. Sửa sản phẩm.                 ║");
        System.out.println("║       4. Xóa sản phẩm.                 ║");
        System.out.println("║       5. Tìm kiếm sản phẩm theo tên.   ║");
        System.out.println("║       6. Sắp xếp sản phẩm.             ║");
        System.out.println("║       7. Trở lại.                      ║");
        System.out.println("║       0. Thoát.                        ║");
        System.out.println("║                                        ║");
        System.out.println("══════════════════════════════════════════");
        System.out.println("Nhập lựa chọn");
        System.out.print("==> ");
    }

    public static void menuForGuest() {
        System.out.println("══════════════ MENU FOR GUEST ════════════");
        System.out.println("║                                        ║");
        System.out.println("║       1. Hiện danh sách sản phẩm.      ║");
        System.out.println("║       2. Tìm kiếm sản phẩm theo tên.   ║");
        System.out.println("║       3. Sắp xếp sản phẩm.             ║");
        System.out.println("║       4. Mua sản phẩm.                 ║");
        System.out.println("║       5. Trở lại.                      ║");
        System.out.println("║       0. Thoát.                        ║");
        System.out.println("║                                        ║");
        System.out.println("══════════════════════════════════════════");
        System.out.println("Nhập lựa chọn");
        System.out.print("==> ");
    }
}
