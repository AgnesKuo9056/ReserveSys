package favorite.entitiy;

import guesthouse.entitiy.*;

import java.util.ArrayList;
import java.util.List;

public class FavoriteItem {
    //关键字是书籍的id，值是书
    //private List<Guesthouse> guesthouses;

    private String gh_id;
    private String owner_id;
    private String gh_name;
    private String gh_address;
    private String gh_img;
    //  private List<Roomtype> roomtype;
    private  String tags ="";
    private  String grade; //评分

    public FavoriteItem(){

    }
    public FavoriteItem(Guesthouse guesthouse){
        this.gh_id = String.valueOf(guesthouse.getGh_id());
        this.owner_id = String.valueOf(guesthouse.getOwner().getUser_id());
        this.gh_name = guesthouse.getGh_name();
        this.gh_address = guesthouse.getGh_address();
        this.gh_img = guesthouse.getGh_img();
        this.tags = initTags(guesthouse.getRoomtype());
        this.grade = guesthouse.getGrade();
    }

    public FavoriteItem(String gh_id, String owner_id, String gh_name, String gh_address, String gh_img, String tags, String grade) {
        this.gh_id = gh_id;
        this.owner_id = owner_id;
        this.gh_name = gh_name;
        this.gh_address = gh_address;
        this.gh_img = gh_img;
        this.tags = tags;
        this.grade = grade;
    }

    public String initTags(List<Roomtype> roomtypes){
        String s ="房型  ";
        for(Roomtype R :roomtypes){
                s+=  R.getRoom_name()+" 有 "+R.getRoom_num() +"间  ;";
        }
        return  s;

    }

    public void addTags(String room_name , int roon_num){

        this.tags+=  room_name+" 有 "+roon_num +"间 ;";

    }
    public String getGh_id() {
        return gh_id;
    }

    public void setGh_id(String gh_id) {
        this.gh_id = gh_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
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

    public String getGh_img() {
        return gh_img;
    }

    public void setGh_img(String gh_img) {
        this.gh_img = gh_img;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setTags(String room_name , int roon_num) {
        this.tags =  room_name+" 有 "+roon_num +"间 ;";
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "FavoriteItem{" +
                "gh_id=" + gh_id +
                ", owner_id=" + owner_id +
                ", gh_name='" + gh_name + '\'' +
                ", gh_address='" + gh_address + '\'' +
                ", gh_img='" + gh_img + '\'' +
                ", tags='" + tags + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
