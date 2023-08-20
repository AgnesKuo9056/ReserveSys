package sue.entity;

import java.time.LocalDate;

public class Sue {

    private String sue_id;
    private String user_id;
    private String type;
    private String type_name;
    private String  content;
    private String  ad_note;
    private String  ad_response;
    private LocalDate sue_create_date;
    private String  deal_status;

    /*
   type: 投诉类型

        1.服务态度：包括民宿或旅游服务人员的不礼貌、态度恶劣等问题。
        2.卫生问题：包括房间、床品等清洁卫生问题。
        3.设施问题：包括房间设施、电器设备等问题。
        4.安全问题：包括房间或景点存在安全隐患等问题。
        5.费用问题：包括价格争议、费用透明度等问题。
        6.广告虚假宣传：包括虚假宣传、误导消费者等问题。
        7.其他问题：包括未被包含在以上分类中的其他问题。

     */
    public  Sue(){

    }

    public Sue(String sue_id, String user_id, String type, String type_name, String content, String ad_note, String ad_response, LocalDate sue_create_date, String deal_status) {
        this.sue_id = sue_id;
        this.user_id = user_id;
        this.type = type;
        this.type_name = type_name;
        this.content = content;
        this.ad_note = ad_note;
        this.ad_response = ad_response;
        this.sue_create_date = sue_create_date;
        this.deal_status = deal_status;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getSue_id() {
        return sue_id;
    }

    public void setSue_id(String sue_id) {
        this.sue_id = sue_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAd_note() {
        return ad_note;
    }

    public void setAd_note(String ad_note) {
        this.ad_note = ad_note;
    }

    public String getAd_response() {
        return ad_response;
    }

    public void setAd_response(String ad_response) {
        this.ad_response = ad_response;
    }

    public LocalDate getSue_create_date() {
        return sue_create_date;
    }

    public void setSue_create_date(LocalDate create_time) {
        this.sue_create_date = sue_create_date;
    }

    public String getDeal_status() {
        return deal_status;
    }


    public void setDeal_status(String deal_status) {
        this.deal_status = deal_status;
    }

    @Override
    public String toString() {
        return "Sue{" +
                "sue_id='" + sue_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", type='" + type + '\'' +
                ", type_name='" + type_name + '\'' +
                ", content='" + content + '\'' +
                ", ad_note='" + ad_note + '\'' +
                ", ad_response='" + ad_response + '\'' +
                ", sue_create_date='" + sue_create_date + '\'' +
                ", deal_status='" + deal_status + '\'' +
                '}';
    }


}
