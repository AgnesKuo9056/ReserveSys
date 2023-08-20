package guesthouse.entitiy;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class Roomtype implements Serializable {

            private  int room_type_id;
            private String room_pic;
            private  String room_name;
            private  String room_type_name;
            private int cus_num;
            private  int bed_num;
            private int room_num;
            private  int price;
            private  int status;

            public  Roomtype(){

            }

    public Roomtype(int room_type_id, String room_pic, String room_name, String room_type_name, int cus_num, int bed_num, int room_num, int price, int status) {
        this.room_type_id = room_type_id;
        this.room_pic = room_pic;
        this.room_name = room_name;
        this.room_type_name = room_type_name;
        this.cus_num = cus_num;
        this.bed_num = bed_num;
        this.room_num = room_num;
        this.price = price;
        this.status = status;
    }

    public String getRoom_type_name() {
        return room_type_name;
    }

    public void setRoom_type_name(String room_type_name) {
        this.room_type_name = room_type_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(int room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getRoom_pic() {
        return room_pic;
    }

    public void setRoom_pic(String room_pic) {
        this.room_pic = room_pic;
    }

    public int getCus_num() {
        return cus_num;
    }

    public void setCus_num(int cus_num) {
        this.cus_num = cus_num;
    }

    public int getBed_num() {
        return bed_num;
    }

    public void setBed_num(int bed_num) {
        this.bed_num = bed_num;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    public String toString() {
//        return JSONObject.toJSONString(this);
//    }

    @Override
    public String toString() {
        return "Roomtype{" +
                "room_type_id=" + room_type_id +
                ", room_pic='" + room_pic + '\'' +
                ", room_name='" + room_name + '\'' +
                ", room_type_name='" + room_type_name + '\'' +
                ", cus_num=" + cus_num +
                ", bed_num=" + bed_num +
                ", room_num=" + room_num +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
