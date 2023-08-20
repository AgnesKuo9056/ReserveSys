package sue.service.Impl;

import common.util.JsonMapUtil;
import db.Data;
import guesthouse.entitiy.Guesthouse;
import map.dao.MapDao;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONException;
import org.json.JSONObject;
import sue.dao.SueDao;
import sue.entity.Sue;
import sue.service.SueService;
import user.entitiy.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SueServiceImpl implements SueService {

    private SueDao sueDao = new SueDao();

    /**调用前要在 jsom中先放入cm_create_date当前时间的string类型*/
    @Override
    public void addSue(Data data, JSONObject json ) throws SQLException, InvocationTargetException, IllegalAccessException, JSONException {

        Map<String, Object> properties = JsonMapUtil.JsonToMap(data.getParam());
        // 2. 实例化目标JavaBean对象
        Sue sue =new Sue();
        // 3. 使用BeanUtils的populate()方法注入属性值
        BeanUtils.populate(sue, properties);

       sue.setUser_id(data.getParam().getString("user_id"));
        if(sue.getUser_id() != null && !sue.getUser_id().isEmpty()){
            sue.setSue_create_date(LocalDate.now());
            sueDao.addSue(data, json ,sue);
            json.put("msg", "添加成功");
        }else {
            json.put("msg", "用户登录过期");
            json.put("result_code", 1);
        }
    }

    @Override
    public void deleteSue(Data data, JSONObject json ) throws SQLException, JSONException {
        sueDao.deleteSue(data,json);
        json.put("msg", "删除成功");
    }

    @Override
    public void modifySue(Data data, JSONObject json) throws SQLException, JSONException, InvocationTargetException, IllegalAccessException {

        Map<String, Object> properties = JsonMapUtil.JsonToMap(data.getParam());

        // 2. 实例化目标JavaBean对象
        Sue sue =new Sue();

        // 3. 使用BeanUtils的populate()方法注入属性值
        BeanUtils.populate(sue, properties);

        String sue_id =data.getParam().getString("sue_id");
        if(sue_id != null && !sue_id.isEmpty()){
            sueDao.modifySue(data, json ,sue);
            json.put("msg", "添加成功");
        }else {
            json.put("msg", "订单编号为空");
        }
    }

    /** 操作为:在servlet中直接 取出 Sue 放入 seesin中 , 前端用 jstl表达式取出循环就好**/
    @Override
    public void getSue(Data data, JSONObject json) throws SQLException, JSONException, ParseException {
        List<Sue> Sues  = sueDao.getSueById(data,json);
        json.put("UserSues", Sues);
    }


    ///照片墙 只要取出status为1的数据就好
    @Override
    public List<Sue> listSue(Data data, JSONObject json) throws SQLException, JSONException, ParseException {
        List<Sue> Sues = sueDao.listSues( data,  json);
        json.put("Sues", Sues);
        return Sues;
    }
}
