package views;

import service.IOrderService;
import service.OrderService;

import java.util.Scanner;

public class OrderManageView {
    private static Scanner scanner = new Scanner(System.in);
    private static IOrderService orderService = new OrderService();
    private static OrderView orderView = new OrderView();

    public static void launchOrder() {
        boolean checkActionMenu = true;
        do {
            try {
                orderMenu();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        // hien thi danh sach don hang
                        orderView.showOrder(orderService.getAllOrders());
                        break;
                    case 2:
                        // tong doanh thu, 2 option: theo thang / theo nam
                        orderView.totalRevenueByYear();
                        break;
                    case 0:
                        // quay lai
                        checkActionMenu = false;
                        break;
                    default:
                        System.out.println("Nhập sai, vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while(checkActionMenu);
    }
    public static void orderMenu() {
//        System.out.println("MENU QUẢN LÝ ĐƠN HÀNG");
//        System.out.println("1. Hiển thị danh sách đơn hàng");
//        System.out.println("2. Tổng doanh thu theo năm");
//        System.out.println("0. Quay lại");
        System.out.println("═════════ MENU QUẢN LÝ ĐƠN HÀNG ════════");
        System.out.println("║                                      ║");
        System.out.println("║     1. Hiển thị danh sách đơn hàng.  ║");
        System.out.println("║     2. Tổng doanh thu theo năm.      ║");
        System.out.println("║     0. Quay lại.                     ║");
        System.out.println("║                                      ║");
        System.out.println("════════════════════════════════════════");
    }
}
