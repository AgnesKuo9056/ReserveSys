package user.service.Impl;

import common.util.FileUploadUtil;
import org.apache.commons.beanutils.BeanUtils;
import user.dao.UserDao;
import user.entitiy.Img;
import user.entitiy.Store;
import user.entitiy.User;
import user.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import db.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UseServiceImpl implements UserService {
    private UserDao userDao = new UserDao();


    @Override
    public void modifyStore(HttpServletRequest request,Data data, JSONObject json) throws JSONException, SQLException {
        //如果 status ==1 则为管理员通过审核 , status中记录的为管理员编号
        if(data.getParam().getString("status").equals("1")){
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("User");
            data.getParam().put("admin_id", user.getUser_id());

        }
        userDao.modifyStore(data,json);
    }

    @Override
    public void get_store_apply(Data data, JSONObject json) throws JSONException, SQLException {
        userDao.get_store(data,json);
    }

    @Override
    public void upload_avatar(HttpServletRequest request, Data data, JSONObject json) throws JSONException, SQLException {
       String file =data.getParam().getString("file");
        List<Map>files = new FileUploadUtil().File_upload(request,file,data);

        //创建img对象用来封装数据
        Img img = new Img();
        try {
            //因为头像依次只能上传一章,所以直接get0 不用遍历
            BeanUtils.populate(img,files.get(0));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("servlet获取的img数据为:"+img);
        //存入session中用户数剧中
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");

        //删除原本头像.避免造成项目越来越庞大
        if(user.getAvatar()!=null&&user.getAvatar()!=""){
            new FileUploadUtil().deleteDirectoryOrFile(file+user.getAvatar());
        }
        user.setAvatar(img.getNewFileName());
        //创建service对象将头像数据存入到表中
        userDao.setavatar(data, json,user);
    }

    @Override
    public Store upload_store_apply(HttpServletRequest request,Data data, JSONObject json) throws JSONException, SQLException {
        System.out.println("upload_store_apply");
        List<Map>files = new FileUploadUtil().File_upload(request,data.getParam().getString("file"),data);
        //拼接数据
        StringBuilder sb = new StringBuilder();
        for (Map<String, String> SingleFile : files) {
            System.out.println("SingleFile =>"+SingleFile);
            String newFileName = SingleFile.get("newFileName");
            if (newFileName != null) {
                sb.append(newFileName).append(";");
            }
            System.out.println("sb =>"+sb);
        }
        String concatenated = sb.toString(); // 用分号拼接所有的newFileName
        if (concatenated.endsWith(";")) {
            concatenated = concatenated.substring(0, concatenated.length() - 1); // 去除最后一个分号
        }
        System.out.println("concatenated =>"+concatenated);
        Store store = new Store();
        store.setCard_num(data.getParam().getString("creditcard"));
        store.setImgstring(concatenated);
        System.out.println("servlet获取的store申请数据为:"+store);
        userDao.addStore(data,json,store);

        return  store;
    }

    @Override
    public void getUserRecord(Data data, JSONObject json) throws SQLException, JSONException {
        userDao.getUserRecord(data, json);
    }

    @Override
    public void Register(Data data, JSONObject json, User user) throws JSONException, SQLException {
        userDao.Register(data, json,user);
    }

    @Override
    public void modifyUserRecord(Data data, JSONObject json) throws JSONException, SQLException {
        userDao.modifyUserRecord(data, json);
    }

    @Override
    public void deleteUserRecord(Data data, JSONObject json) throws JSONException, SQLException {
        userDao.deleteUserRecord(data, json);
    }

    @Override
    public void addUserRecord(Data data, JSONObject json) throws JSONException, SQLException {
        userDao.addUserRecord(data, json);
    }

    @Override
    public void getUserpsw(Data data, JSONObject json) throws JSONException, SQLException {
        userDao.getUserpsw(data, json);
    }

    @Override
    public void modifyuserpsw(Data data, JSONObject json) throws JSONException, SQLException {
        userDao.modifyuserpsw(data, json);
    }

    @Override
    public void getUser_role_type(Data data, JSONObject json) throws JSONException, SQLException {
        userDao.modifyUserRecord(data, json);
    }


    @Override
    public void login( Data data, JSONObject json) throws JSONException, SQLException {
        userDao.login(data, json);

    }
    public void get_storebyid(HttpServletRequest request,Data data, JSONObject json) throws JSONException, SQLException {
        HttpSession session = request.getSession();
        Store store =userDao.get_storebyid(data, json);
        session.setAttribute("Store",store);
    }

    public void storeaplly(Data data, JSONObject json){

    }
}
