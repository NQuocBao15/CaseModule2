package views;

import service.UserService;

import java.util.Scanner;

public class UserManageView {
    private static Scanner scanner = new Scanner(System.in);
    private static UserView userView = new UserView();
    private static UserService userService = new UserService();
    public static void launch() {
        boolean checkActionMenu = true;
        do {
            menuUserManager();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        userView.showUser(userService.getAllUsers());
                        break;
                    case 2:
                        userView.addUser();
                        break;
                    case 3:
                        userView.updateUser();
                        break;
                    case 4:
                        userView.deleteUser();
                        break;
                    case 5:
                        checkActionMenu = false;
                        break;
                    default:
                        System.out.println("Lựa chọn sai. Xin mời nhập lại!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu);
    }

    private static void menuUserManager() {
        System.out.println("═══════════ QUẢN LÝ TÀI KHOẢN ═══════════");
        System.out.println("║                                       ║");
        System.out.println("║       1. Hiện danh sách tài khoản.    ║");
        System.out.println("║       2. Thêm tài khoản.              ║");
        System.out.println("║       3. Chỉnh sửa tài khoản          ║");
        System.out.println("║       4. Xóa tài khoản.               ║");
        System.out.println("║       5. Quay lại.                    ║");
        System.out.println("║                                       ║");
        System.out.println("═════════════════════════════════════════");
        System.out.print("Nhập lựa chọn: ");
    }
}
