package common.util;

import org.json.JSONException;
import org.json.JSONObject;
import user.entitiy.User;

public class AuthorityUtil {
    public static void Author(User user, JSONObject json)throws JSONException {
        String role_name = user.getRole_name();

        if (role_name.equals("super_administrator")) {
            json.put("is_admin",true);
            json.put("is_super",true);
            System.out.print("登陆身份为 管理员 ");

        }else if (role_name.equals("administrator")){
            json.put("is_admin",true);
            json.put("is_store",true);
            System.out.print("登陆身份为 商家 ");
        }else {
            System.out.print("登陆身份为 普通用户 ");
        }
        System.out.print( user.getUser_name());

    }
}
