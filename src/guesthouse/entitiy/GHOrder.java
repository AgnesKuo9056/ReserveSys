package guesthouse.entitiy;

import user.entitiy.User;

import java.time.LocalDate;

public class GHOrder {
    private int order_id;
    private String cus_name;
    private String cus_phone_num;
    private int gh_id;
    private User owner;
    private String room_name;
    private int room_type_id;
    private LocalDate reserve_date;
    private LocalDate check_in_date;
    private LocalDate check_out_date;
    private int status;
    private int total;
    private String note;
    private String comment;

    public GHOrder() {

    }

    public GHOrder(int order_id, String cus_name, String cus_phone_num, int gh_id, User owner, String room_name, int room_type_id, LocalDate reserve_date, LocalDate check_in_date, LocalDate check_out_date, int status, int total, String note, String comment) {
        this.order_id = order_id;
        this.cus_name = cus_name;
        this.cus_phone_num = cus_phone_num;
        this.gh_id = gh_id;
        this.owner = owner;
        this.room_name = room_name;
        this.room_type_id = room_type_id;
        this.reserve_date = reserve_date;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.status = status;
        this.total = total;
        this.note = note;
        this.comment = comment;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getCus_name() { return cus_name; }

    public void setCus_name(String cus_name) { this.cus_name = cus_name; }

    public String getCus_phone_num() { return cus_phone_num; }

    public void setCus_phone_num(String cus_phone_num) { this.cus_phone_num = cus_phone_num; }

    public int getGh_id() {
        return gh_id;
    }

    public void setGh_id(int gh_id) {
        this.gh_id = gh_id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(int room_type_id) {
        this.room_type_id = room_type_id;
    }

    public LocalDate getReserve_date() {
        return reserve_date;
    }

    public void setReserve_date(LocalDate reserve_date) {
        this.reserve_date = reserve_date;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return "GHOrder{" +
                "order_id=" + order_id +
                ", cus_name='" + cus_name + '\'' +
                ", cus_phone_num='" + cus_phone_num + '\'' +
                ", gh_id=" + gh_id +
                ", owner=" + owner +
                ", room_name='" + room_name + '\'' +
                ", room_type_id=" + room_type_id +
                ", reserve_date=" + reserve_date +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", status=" + status +
                ", total=" + total +
                ", note='" + note + '\'' +
                ", Comment='" + comment + '\'' +
                '}';
    }
}