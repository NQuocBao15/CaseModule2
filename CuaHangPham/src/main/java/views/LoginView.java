package views;

import models.ERole;
import models.User;
import service.IUserService;
import service.UserService;
import utils.CheckUtils;

import java.util.Scanner;

public class LoginView {
    private final static Scanner scanner = new Scanner(System.in);
    private IUserService userService;

    public static User user = new User();

    public LoginView() {
        userService = UserService.getInstance();
    }

    public void login(ERole role) {
        int choice = -1;
        do {
            menuLogin();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> loginProgram(role);
                    case 0 -> {
                        System.out.println("Đang thoát chương trình...");
                        System.exit(0);
                    }
                    default -> System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                }
            } catch (Exception exception) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (choice != 0);
    }

    private void loginProgram(ERole role) {
        String userName, passWord;
        System.out.println("═════════ ĐĂNG NHẬP ═════════");
        System.out.print("Tài khoản: ");
        userName = CheckUtils.stringEmpty();
        System.out.print("Mật khẩu:  ");
        passWord = CheckUtils.stringEmpty();
        user = userService.login(userName, passWord, role);
        long userId = user.getId();
        if (user == null) {
            System.out.println("Tài khoản hoặc mật khẩu không đúng!");
            CheckUtils.pressEnterToContinue();
        } else {
            System.out.println("\n════ ĐĂNG NHẬP THÀNH CÔNG ════");
            CheckUtils.pressEnterToContinue();
            if (role == ERole.ADMIN) {
                AdminView.launch(userId);
            }
        }
    }

    private static void menuLogin() {
        System.out.println("══════════════ MENU ══════════════");
        System.out.println("║       1. Đăng nhập.            ║");
        System.out.println("║       0. Thoát.                ║");
        System.out.println("══════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }
}
