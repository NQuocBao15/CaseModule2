package views;

import java.util.Scanner;

public class AdminView {
    private static final Scanner scanner = new Scanner(System.in);
    public static void launch(long userId) {
        boolean checkActionMenu = true;
        do {
            try {
                menuAdminOption();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        UserManageView.launch();
                        break;
                    case 2:
                        ProductManageView.launchAdmin();
                        break;
                    case 3:
                        OrderManageView.launchOrder();
                        break;
                    case 4:
                        MainLauncher.launcher();
                        break;
                    case 0:
                        System.out.println("Đang thoát chương trình...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (checkActionMenu);
    }
    private static void menuAdminOption() {
        System.out.println("══════════════ MENU ADMIN ══════════════");
        System.out.println("║                                      ║");
        System.out.println("║         1. Quản lý tài khoản.        ║");
        System.out.println("║         2. Quản lý sản phẩm.         ║");
        System.out.println("║         3. Quản lý đơn hàng.         ║");
        System.out.println("║         4. Đăng xuất.                ║");
        System.out.println("║         0. Thoát.                    ║");
        System.out.println("║                                      ║");
        System.out.println("════════════════════════════════════════");
        System.out.print("Nhập lựa chọn: ");
    }
}
