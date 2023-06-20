package views;

import models.ERole;
import models.User;
import service.IUserService;
import service.UserService;
import utils.CheckUtils;
import utils.SelectOption;
import utils.SelectUtils;
import utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private IUserService userService;
    private static Scanner scanner = new Scanner(System.in);
    public UserView() {
        userService = UserService.getInstance();
    }

    public void showUser(List<User> users) {
        System.out.println("════════════════════════════════════════════════════════ DANH SÁCH TÀI KHOẢN ══════════════════════════════════════════════════════");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-17s | %-25s | %-15s | %-20s | %-15s |\n",
                "ID",
                "TÀI KHOẢN",
                "TÊN",
                "SĐT",
                "ĐỊA CHỈ",
                "QUYỀN"
        );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        for (User user : users) {
            System.out.printf("| %-12s | %-17s | %-25s | %-15s | %-20s | %-15s |\n",
                    user.getId(),
                    user.getNameAccount(),
                    user.getNameUser(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getRole()
            );
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        CheckUtils.pressEnterToContinue();
    }
    public void addUser() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String nameAccount = inputNameAccount();
                String password = inputPassWord();
                String nameUser = inputNameUser();
                String phone = inputPhone();
                String address = inputAddress();
                User user = new User(id, nameAccount, password, nameUser, phone, address, ERole.ADMIN);
                user.setRole(ERole.ADMIN);
                userService.add(user);
                System.out.println("Thêm tài khoản " + user.getNameAccount() + " thành công!");
                CheckUtils.pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (SelectUtils.isSelect(SelectOption.ADD));
    }

    private String inputAddress() {
        System.out.println("Nhập địa chỉ (Ký tự đầu của từng từ phải viết hoa, VD: Huế)");
        String address;
        while (!ValidateUtils.isAddressValid(address = CheckUtils.stringEmpty())) {
            System.out.println("Địa chỉ không đúng định dạng, vui lòng nhập lại!");
        }
        return address;
    }

    private String inputPhone() {
        System.out.println("Nhập số điện thoại (Số điện thoại bao gồm 10 chữ số và bắt đầu bằng số 0");
        String phone;
        do {
            if (!ValidateUtils.isPhoneValid(phone = CheckUtils.stringEmpty())) {
                System.out.println("Số điện thoại bao gồm 10 chữ số và bắt đầu bằng số 0. Xin mời nhập lại!");
                continue;
            }
            if (userService.existsByPhone(phone)) {
                System.out.println("Số điện thoại đã tồn tại. Xin mời nhập lại!");
                continue;
            }
            break;
        } while (true);
        return phone;
    }

    private String inputNameUser() {
        System.out.println("Nhập tên (Ký tự đầu của từng từ phải ghi hoa)");
        String nameUser;
        while (!ValidateUtils.isNameValid(nameUser = CheckUtils.stringEmpty())) {
            System.out.println("Ký tự đầu của từng từ phải ghi hoa");
        }
        return nameUser;
    }

    private String inputPassWord() {
        System.out.println("Mật khẩu >= 8 kí tự trong đó chứa  " +
                "ít nhất 1 ký tự viết hoa, viết thường, chữ số và kí tự đặt biệt");
        String password;
        while (!ValidateUtils.isPassWordValid(password = scanner.nextLine())) {
            System.out.println("Mật khẩu >= 8 kí tự trong đó chứa  " +
                    "ít nhất 1 ký tự viết hoa, viết thường, chữ số và kí tự đặt biệt");
        }
        return password;
    }

    private String inputNameAccount() {
        System.out.println("Nhập tài khoản (VD: vidu123)");
        String nameAccount;
        do {
            if (!ValidateUtils.isUserNameValid(nameAccount = CheckUtils.stringEmpty())) {
                System.out.println(nameAccount + " không đúng định dạng. Xin mời nhập lại!");
                continue;
            }
            if (userService.existsByUserName(nameAccount)) {
                System.out.println(nameAccount + " đã tồn tại. Xin mời nhập lại!");
                continue;
            }
            break;
        } while (true);
        return nameAccount;
    }

    public void updateUser() {
        int choice;
        boolean checkActionMenu = true;
        do {
            try {
                showUser(userService.getAllUsers());
                long id = inputId();
                User newUser = userService.findById(id);
                menuUpdateUser();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        updateNameUser(newUser);
                        checkActionMenu = false;
                        break;
                    case 2:
                        updatePhone(newUser);
                        checkActionMenu = false;
                        break;
                    case 3:
                        updateAddress(newUser);
                        checkActionMenu = false;
                        break;
                    case 4:
                        checkActionMenu = false;
                        break;
                    default:
                        System.out.println("Nhập sai. Xin mời nhập lại!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu);
    }

    private void updateAddress(User newUser) {
        String oldAddress = newUser.getAddress();
        String address = inputAddress();
        newUser.setAddress(address);
        userService.update(newUser);
        System.out.printf("Đã thay đổi địa chỉ từ %s thành %s\n", oldAddress, address);
        CheckUtils.pressEnterToContinue();
    }

    private void updatePhone(User newUser) {
        String oldPhone = newUser.getPhone();
        String phone = inputPhone();
        newUser.setPhone(phone);
        userService.update(newUser);
        System.out.printf("Đã thay đổi số điện thoại từ %s thành %s\n", oldPhone, phone);
        CheckUtils.pressEnterToContinue();
    }

    private void updateNameUser(User newUser) {
        String oldName = newUser.getNameUser();
        String name = inputNameUser();
        newUser.setNameUser(name);
        userService.update(newUser);
        System.out.printf("Đã thay đổi tên từ %s thành '%s'.\n", oldName, name);
        CheckUtils.pressEnterToContinue();
    }

    private long inputId() {
        long id;
        System.out.println("Nhập Id: ");
        boolean checkActionMenu = true;
        do {
            id = CheckUtils.longFormatCheck();
            boolean isFindId = userService.existById(id);
            if (isFindId) {
                checkActionMenu = false;
            } else {
                System.out.println("Không tìm thấy. Vui lòng nhập lại!");
            }
        } while (checkActionMenu);
        return id;
    }
    private static void menuUpdateUser() {
        System.out.println("═══════ CHỈNH SỬA TÀI KHOẢN ═══════");
        System.out.println("║    1. Chỉnh sửa tên.            ║");
        System.out.println("║    2. Chỉnh sửa số điện thoại.  ║");
        System.out.println("║    3. Chỉnh sửa địa chỉ.        ║");
        System.out.println("║    4. Trở lại.                  ║");
        System.out.println("═══════════════════════════════════");
        System.out.print("Nhập lựa chọn: ");
    }

    public void deleteUser() {
        boolean isRetry = true;
        do {
            showUser(userService.getAllUsers());
            long id = inputId();
            User user = userService.findById(id);
            int choice;
            boolean checkActionMenu = true;
            do {
                try {
                    menuDelete();
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1 -> {
                            if (user.getRole() != null) {
                                System.out.println("Xóa tài khoản thành công!");
                                userService.deleteById(id);
                            }
                            CheckUtils.pressEnterToContinue();
                            checkActionMenu = false;
                        }
                        case 2 -> checkActionMenu = false;
                        default -> System.out.println("Nhập sai. Xin mời nhập lại!");
                    }
                } catch (Exception e) {
                    System.out.println("Sai cú pháp. Xin mời nhập lại!");
                }
            } while (checkActionMenu);
        } while (isRetry == SelectUtils.isSelect(SelectOption.DELETE));
    }

    private void menuDelete() {
        System.out.println("═════ BẠN CÓ MUỐN XÓA KHÔNG? ═════");
        System.out.println("║            1. Có.              ║");
        System.out.println("║            2. Không.           ║");
        System.out.println("══════════════════════════════════");
        System.out.print("Nhập lựa chọn: ");

    }
}
