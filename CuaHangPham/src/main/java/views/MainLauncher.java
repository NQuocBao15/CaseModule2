package views;

import models.ERole;

import java.util.Scanner;

public class MainLauncher {
    private static final LoginView loginView = new LoginView();

    public static void mainLauncher(ERole role) {
        loginView.login(role);
    }
    public static void launcher() {
        Scanner scan = new Scanner(System.in);
        boolean checkActionMenu = true;
        do {
            try {
                System.out.println("══════════════ MENU ══════════════");
                System.out.println("║       1. Đăng nhập ADMIN.      ║");
                System.out.println("║       2. Mua hàng.             ║");
                System.out.println("║       0. Thoát.                ║");
                System.out.println("══════════════════════════════════");
                int choice = Integer.parseInt(scan.nextLine());
                switch (choice) {
                    case 1:
                        MainLauncher.mainLauncher(ERole.ADMIN);
                        break;
                    case 2:
                        ProductManageView.launchGuest();
                        break;
                    case 3:
                        System.exit(3);
                        break;
                    default:
                        System.out.println("Nhập sai rồi bạn ơi! Vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (checkActionMenu);
    }
}

