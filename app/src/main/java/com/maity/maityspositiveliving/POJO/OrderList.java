package com.maity.maityspositiveliving.POJO;

public class OrderList {
    String order_id,order_note,paid,amount,order_date,order_head_title,order_status;

    public OrderList(String order_id, String order_note, String paid, String amount, String order_date, String order_head_title, String order_status) {
        this.order_id = order_id;
        this.order_note = order_note;
        this.paid = paid;
        this.amount = amount;
        this.order_date = order_date;
        this.order_head_title = order_head_title;
        this.order_status = order_status;
    }

    public String getOrder_head_title() {
        return order_head_title;
    }

    public void setOrder_head_title(String order_head_title) {
        this.order_head_title = order_head_title;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
