package favorite.entitiy;

import guesthouse.entitiy.Guesthouse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*该类代表的是购物车*/
public class Favorite {

    //关键字是书籍的id，值是书
    private Map<String, FavoriteItem> favorite = new LinkedHashMap<>();


    public Favorite() {
    }

    public Favorite(Map<String, FavoriteItem> favoriteItem) {
        this.favorite = favoriteItem;
    }

    public Map<String, FavoriteItem> getFavorite() {
        return favorite;
    }

    public void setFavorite(Map<String, FavoriteItem> favorite) {
        this.favorite = favorite;
    }


    /**
     * 添加房型
     */
    public void addItem(Favorite favoritenow,FavoriteItem favoriteItem,String room_name , int roon_num) {
        System.out.println("favorite"+favoritenow.getFavorite());
            FavoriteItem favoriteItemNEW = favoritenow.getFavorite().get(String.valueOf(favoriteItem.getGh_id()));
            System.out.println(favoriteItemNEW);
            //            this.favoriteItem=favoriteItemNEW;
        if(favoriteItemNEW==null){

            favoriteItemNEW = new FavoriteItem(favoriteItem.getGh_id(),favoriteItem.getOwner_id(),favoriteItem.getGh_name(),favoriteItem.getGh_address(),favoriteItem.getGh_img(),favoriteItem.getTags(),favoriteItem.getGrade());
            favoriteItemNEW.setTags(room_name,roon_num);
            favoritenow.getFavorite().put(String.valueOf(favoriteItem.getGh_id()), favoriteItemNEW);

        }else {
            favoriteItemNEW.addTags(room_name,roon_num);
            System.out.println("已收藏该民宿");
        }

    }
    public boolean hasGH(int gh_id){
        return favorite.get(gh_id)!=null;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "favorite=" + favorite +
                '}';
    }
}