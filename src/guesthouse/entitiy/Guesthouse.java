package guesthouse.entitiy;



import com.alibaba.fastjson.JSONObject;
import user.entitiy.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Guesthouse implements Serializable {
    private static final Random random = new Random(System.currentTimeMillis());

    private int gh_id;
    private User owner;
    private String gh_name;
    private  String gh_intro;
    private String gh_address;
    private String gh_phone;
    private String gh_img;
    private List<Roomtype> roomtype;
    private String gh_examine_admin;
    private int examine_state;
    private  String tags ="";
    private LocalDate createdAt; // 创建时间
    private LocalDate updatedAt; // 更新时间
    private  String grade; //评分
    private String gh_x;
    private String gh_y;
    private  int status;

   public   Guesthouse(){

   }
    public   Guesthouse(String waitaddroom){
        this.gh_id = generate();
    }


    public Guesthouse(int gh_id, User owner, String gh_name, String gh_intro, String gh_address, String gh_phone, String gh_img, List<Roomtype> roomtype, String gh_examine_admin, int examine_state, String tags, LocalDate createdAt, LocalDate updatedAt, String grade, String gh_x, String gh_y, int status) {
        this.gh_id = gh_id;
        this.owner = owner;
        this.gh_name = gh_name;
        this.gh_intro = gh_intro;
        this.gh_address = gh_address;
        this.gh_phone = gh_phone;
        this.gh_img = gh_img;
        this.roomtype = roomtype;
        this.gh_examine_admin = gh_examine_admin;
        this.examine_state = examine_state;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.grade = grade;
        this.gh_x = gh_x;
        this.gh_y = gh_y;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    public int getGh_id() {
        return gh_id;
    }

    public void setGh_id(int gh_id) {
        this.gh_id = gh_id;
    }

    public String getGh_intro() {
        return gh_intro;
    }

    public void setGh_intro(String gh_intro) {
        this.gh_intro = gh_intro;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getGh_name() {
        return gh_name;
    }

    public void setGh_name(String gh_name) {
        this.gh_name = gh_name;
    }

    public String getGh_address() {
        return gh_address;
    }

    public void setGh_address(String gh_address) {
        this.gh_address = gh_address;
    }

    public String getGh_phone() {
        return gh_phone;
    }

    public void setGh_phone(String gh_phone) {
        this.gh_phone = gh_phone;
    }

    public String getGh_img() {
        return gh_img;
    }

    public void setGh_img(String gh_img) {
        this.gh_img = gh_img;
    }

    public List<Roomtype> getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(List<Roomtype> roomtype) {
        this.roomtype = roomtype;
    }

    public String getGh_examine_admin() {
        return gh_examine_admin;
    }

    public void setGh_examine_admin(String gh_examine_admin) {
        this.gh_examine_admin = gh_examine_admin;
    }

    public int getExamine_state() {
        return examine_state;
    }

    public void setExamine_state(int examine_state) {
        this.examine_state = examine_state;
    }

    public String getGh_x() {
        return gh_x;
    }

    public void setGh_x(String gh_x) {
        this.gh_x = gh_x;
    }

    public String getGh_y() {
        return gh_y;
    }

    public void setGh_y(String gh_y) {
        this.gh_y = gh_y;
    }
    public void addTag(String s){
       tags +=" ";
       tags += s ;
    }
    /**
     * 添加房型
     */
    public void addItem(Roomtype roomtype_single) {
            if(roomtype==null){
                List<Roomtype> roomtypeNEW = new ArrayList<>() ;
                this.roomtype=roomtypeNEW;
                Roomtype temp = new Roomtype(roomtype_single.getRoom_type_id(),roomtype_single.getRoom_pic(),roomtype_single.getRoom_name(),roomtype_single.getRoom_type_name(),roomtype_single.getCus_num(),roomtype_single.getBed_num(),roomtype_single.getRoom_num(),roomtype_single.getPrice(),roomtype_single.getStatus());
                System.out.println("该民宿原本为0房间:"+temp.toString());
                roomtype.add(temp);
                addTag(String.valueOf( temp.getCus_num()));

            }else {
                int i = 0;
                System.out.println(roomtype_single);
                // 遍历集合查看是否已经存在添加的商品
                for (; i < roomtype.size(); i++) {
                    Roomtype temp = roomtype.get(i);
                    if (temp.getRoom_type_id() == roomtype_single.getRoom_type_id()) {
                        System.out.println("已有该房型");
                        temp.setRoom_num(temp.getRoom_num() + 1);
                        break;
                    }
                }
                if (i >= roomtype.size()) {
                    Roomtype temp =  new Roomtype(roomtype_single.getRoom_type_id(),roomtype_single.getRoom_pic(),roomtype_single.getRoom_name(),roomtype_single.getRoom_type_name(),roomtype_single.getCus_num(),roomtype_single.getBed_num(),roomtype_single.getRoom_num(),roomtype_single.getPrice(),roomtype_single.getStatus());
                    System.out.println("添加新房型"+temp.toString());
                    roomtype.add(temp);
                    addTag(String.valueOf( temp.getCus_num()));
                }
            }
        System.out.println("最新RoomtypeList:"+roomtype);
        }

    /**
     * 从该民宿中移除该房型
     */
    public void removeItem(int roomtype_id){
        for (int i = 0; i < roomtype.size(); i++) {
            Roomtype temp = (Roomtype) roomtype.get(i);
            if (temp.getRoom_type_id()== roomtype_id) {

                if(temp.getRoom_num()>1){
                    temp.setRoom_num(temp.getRoom_num() - 1);
                    break;
                }
                else if(temp.getRoom_num()==1){
                    roomtype.remove(i);
                }
            }

        }

    }


    /**
     * 清空所有房间
     */
    public void clearCar(){
        roomtype.clear();
    }

    @Override
    public String toString() {
        return "Guesthouse{" +
                "gh_id=" + gh_id +
                ", owner=" + owner +
                ", gh_name='" + gh_name + '\'' +
                ", gh_intro='" + gh_intro + '\'' +
                ", gh_address='" + gh_address + '\'' +
                ", gh_phone='" + gh_phone + '\'' +
                ", gh_img='" + gh_img + '\'' +
                ", roomtype=" + roomtype +
                ", gh_examine_admin='" + gh_examine_admin + '\'' +
                ", examine_state=" + examine_state +
                ", tags='" + tags + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", grade='" + grade + '\'' +
                ", gh_x='" + gh_x + '\'' +
                ", gh_y='" + gh_y + '\'' +
                ", status=" + status +
                '}';
    }
    public static Random getRandom() {
        return random;
    }

    public   int generate() {
//随机生成与时间有关的6位int位类uuid
        int randomNumber = random.nextInt(100000000);
        long timestamp = System.currentTimeMillis();
        String orderedIdStr = String.format("%d%06d", timestamp, randomNumber);
        int orderedId = Integer.parseInt(orderedIdStr.substring(0, 8));
        return orderedId;
    }
}