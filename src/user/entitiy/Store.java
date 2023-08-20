package user.entitiy;

import java.util.ArrayList;
import java.util.List;

public class Store {
//    银行卡
    private  String card_num;
//    营业证照 人像照片 等 url
    private List<Img> imgs;

    private  String imgstring;
//    商家状态 0:待审核 1:审核失败 2:审核成功 3:切换用户
    private int status;


    public Store(){
        this.status = 0;
    }

    public Store(String card_num, List<Img> imgs, String imgstring, int status) {
        this.card_num = card_num;
        this.imgs = imgs;
        this.imgstring = imgstring;
        this.status = status;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public List<Img> getImgs() {
        return imgs;
    }
///重写方法 因为后台查询数据返回为 多个路径' ; '拼接而成 ,需要重新整理填装入javabean中
    public void setImgs(String imgPaths) {
        List<Img> imgs = new ArrayList<>();
        if (imgPaths != null && !imgPaths.isEmpty()) {
            String[] paths = imgPaths.split(";");
            for (String path : paths) {
                Img img = new Img();
                img.setNewFileName(path);
                imgs.add(img);
            }
        }
        this.imgs = imgs;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setImgs(List<Img> imgs) {
        this.imgs = imgs;
    }

    public String getImgstring() {
        return imgstring;
    }

    public void setImgstring(String imgstring) {
        this.imgstring = imgstring;
    }

    @Override
    public String toString() {
        return "Store{" +
                "card_num='" + card_num + '\'' +
                ", imgs=" + imgs +
                ", status='" + status + '\'' +
                '}';
    }
}
