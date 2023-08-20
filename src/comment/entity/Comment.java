package comment.entity;

import java.time.LocalDate;

public class Comment {
    private  String    comment_id;
    private  String  title;
    private   String   comment_content;
    private   String    comment_state;
    private    String    name;
    private LocalDate cm_create_date;
    private    String   star;
    private    String   img;

    public Comment(){}

    public Comment(String comment_id, String title, String comment_content, String comment_state, String name, LocalDate cm_create_date, String star, String img) {
        this.comment_id = comment_id;
        this.title = title;
        this.comment_content = comment_content;
        this.comment_state = comment_state;
        this.name = name;
        this.cm_create_date = cm_create_date;
        this.star = star;
        this.img = img;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_state() {
        return comment_state;
    }

    public void setComment_state(String comment_state) {
        this.comment_state = comment_state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCm_create_date() {
        return cm_create_date;
    }

    public void setCm_create_date(LocalDate cm_create_date) {
        this.cm_create_date = cm_create_date;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getImg() { return img; }

    public void setImg(String img) { this.img = img; }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id='" + comment_id + '\'' +
                ", title='" + title + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_state='" + comment_state + '\'' +
                ", name='" + name + '\'' +
                ", cm_create_date='" + cm_create_date + '\'' +
                ", star='" + star + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
