package views;

import models.Order;
import service.IOrderService;
import service.OrderService;
import utils.CheckUtils;
import utils.DateUtils;
import utils.ValidateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private static Scanner scanner = new Scanner(System.in);
    private static IOrderService orderService = new OrderService();
    private final OrderItemView orderItemView = new OrderItemView();
    public OrderView() {
        orderService = OrderService.getInstance();
    }

    public void showOrder(List<Order> orders) {
        System.out.println("                            DANH SÁCH SẢN PHẨM");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-12s | %-15s | %-15s | %-15s | %-25s\n","ID Đơn hàng","Họ tên","Số điện thoại","Tổng tiền","Ngày tạo đơn hàng");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════");
        for (Order order : orders) {
            System.out.printf("%-12s | %-15s | %-15s | %-15s | %-25s\n",
                    order.getId(),
                    order.getFullName(),
                    order.getPhone(),
                    CheckUtils.doubleToVND(order.getGrandTotal()),
                    DateUtils.dateToString(order.getCreateAt()));
        }
        System.out.println("═════════════════════════════════════════════════════════════════════════════════");
        CheckUtils.pressEnterToContinue();
    }

    public void addOrder() {
        long orderId = 0;
        do {
            try {
                System.out.println("Nhập thông tin của người mua");
                long id = System.currentTimeMillis() / 10000;
                String fullName = inputFullName();
                String phone = inputPhone();
                Order order = new Order(id, fullName, phone);
                order.setCreateAt(LocalDate.now());
                orderService.add(order);
                System.out.println("Tạo đơn hàng thành công! Thêm sản phẩm vào giỏ hàng.");
                orderItemView.addOrderItem(order.getId());
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (isRetryOrder(orderId));
    }

    private boolean isRetryOrder(long orderId) {
        do {
            System.out.println("Bấm 'y' để tiếp tục thêm đơn hàng \t|\t 'b' để quay lại");
            String option = scanner.nextLine();
            switch (option) {
                case "y" -> {
                    return true;
                }
                case "b" -> {
                    return false;
                }
                default -> {
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    System.out.print(" => ");
                }
            }
        } while (true);
    }

    private String inputPhone() {
        System.out.println("Nhập số điện thoại (Số điện thoại bao gồm 10 chữ số và bắt đầu bằng số 0. VD: 0123456789)");
        String phone;
        while (!ValidateUtils.isPhoneValid(phone = scanner.nextLine())) {
            System.out.println("Số điện thoại gồm 10 số và bắt đầu bằng chữ số 0, VD: 0123456789");
        }
        return phone;
    }

    private String inputFullName() {
        System.out.println("Nhập tên (Ký tự đầu của từng từ phải ghi hoa)");
        String fullName;
        while (!ValidateUtils.isNameValid(fullName = CheckUtils.stringEmpty())) {
            System.out.println("Ký tự đầu của từng từ phải ghi hoa");
        }
        return fullName;
    }

    public void menuUpdateOrder() {
        System.out.println("Menu chỉnh sửa đơn hàng");
        System.out.println("1. Sửa họ tên khách hàng");
        System.out.println("2. Sửa số điện thoại");
        System.out.println("3. ");
    }
    private long inputId() {
        long id;
        System.out.println("Nhập Id: ");
        boolean checkActionMenu = true;
        do {
            id = CheckUtils.longFormatCheck();
            boolean isFindId = orderService.existByID(id);
            if (isFindId) {
                checkActionMenu = false;
            } else {
                System.out.println("Không tìm thấy. Vui lòng nhập lại!");
            }
        } while (checkActionMenu);
        return id;
    }

    public void totalRevenueByYear() {
        System.out.println("═════════════ THỐNG KÊ THEO NĂM ═════════════");
        String year = inputYear();
        List<Order> ordersFind = new ArrayList<>();
        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            String createdDate = DateUtils.dateToStringYear(order.getCreateAt());
            if (year.equals(createdDate)) {
                ordersFind.add(order);
            }
        }
        System.out.printf("════════════════════════════════ DOANH THU NĂM %s ═══════════════════════════════\n", year);
        System.out.println("║═════════════════════════════════════════════════════════════════════════════════║");
        System.out.printf("║ %-7s | %-24s | %-14s | %-25s ║\n",
                "STT",
                "KHÁCH HÀNG",
                "SĐT",
                "THÀNH TIỀN"
        );
        System.out.println("║═════════════════════════════════════════════════════════════════════════════════║");
        double revenueTotal = 0;
        for (int i = 0; i < ordersFind.size(); i++) {
            Order order = ordersFind.get(i);
            revenueTotal += order.getGrandTotal();
            System.out.printf("║ %-7s | %-24s | %-14s | %-22s ║\n",
                    i + 1,
                    order.getFullName(),
                    order.getPhone(),
                    CheckUtils.doubleToVND(order.getGrandTotal())
            );
        }
        System.out.println("║═════════════════════════════════════════════════════════════════════════════════║");
        System.out.println("║                                                                                 ║");
        System.out.println("║═════════════════════════════════════════════════════════════════════════════════║");
        System.out.printf("║                                     TỔNG DOANH THU: %-26s  ║\n", CheckUtils.doubleToVND(revenueTotal));
        System.out.println("║═════════════════════════════════════════════════════════════════════════════════║");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════");
    }

    private String inputYear() {
        System.out.println("Nhập năm (Ví Dụ: 2023): ");
        String year;
        while (!ValidateUtils.isYearValid(year = scanner.nextLine().trim())) {
            System.out.println("Năm gồm 4 chữ số (Ví Dụ: 2023).");
        }
        return year;
    }
}
