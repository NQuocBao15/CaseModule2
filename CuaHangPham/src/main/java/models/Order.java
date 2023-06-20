package models;

import service.IModel;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.Date;

public class Order implements IModel {
    private long id;
    private String fullName;
    private String phone;
    private double grandTotal;
    private LocalDate createAt;

    public Order() {
    }

    public Order(long id, String fullName, String phone, double grandTotal, LocalDate createAt) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.grandTotal = grandTotal;
        this.createAt = createAt;
    }

    public Order(long id, String fullName, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.id,this.fullName,this.phone,this.grandTotal, DateUtils.dateToString(this.createAt));
    }

    @Override
    public Object parseData(String line) {
        String[] array = line.split(",");
        Order order = new Order();
        order.setId(Long.parseLong(array[0]));
        order.setFullName(array[1]);
        order.setPhone(array[2]);
        order.setGrandTotal(Double.parseDouble(array[3]));
        order.setCreateAt(DateUtils.parseDate(array[4]));
        return order;
    }
}
