package comment.service.Impl;

import comment.dao.CommentDao;
import comment.entity.Comment;
import comment.service.CommentService;
import common.util.JsonMapUtil;
import db.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONException;
import org.json.JSONObject;
import sue.dao.SueDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao = new CommentDao();

    /**调用前要在 jsom中先放入cm_create_date当前时间的string类型*/
    @Override
    public void addComment(Data data, JSONObject json ) throws SQLException, InvocationTargetException, IllegalAccessException, JSONException {

        Map<String, Object> properties = JsonMapUtil.JsonToMap(data.getParam());
        // 2. 实例化目标JavaBean对象
        Comment comment =new Comment();
        // 3. 使用BeanUtils的populate()方法注入属性值
        BeanUtils.populate(comment, properties);

        String order_id =data.getParam().getString("order_id");
        if(order_id != null && !order_id.isEmpty()){
        commentDao.addComment(data, json, order_id,comment);
        json.put("msg", "添加成功");
        }else {
            json.put("msg", "订单编号为空");
        }
    }

    @Override
    public void deleteComment(Data data, JSONObject json ) throws SQLException, JSONException {
        commentDao.deleteComment(data,json);
        json.put("msg", "删除成功");
    }

    @Override
    public void modifyComment(Data data, JSONObject json) throws SQLException, JSONException, InvocationTargetException, IllegalAccessException {

        Map<String, Object> properties = JsonMapUtil.JsonToMap(data.getParam());

        // 2. 实例化目标JavaBean对象
        Comment comment =new Comment();

        // 3. 使用BeanUtils的populate()方法注入属性值
        BeanUtils.populate(comment, properties);

        String order_id =data.getParam().getString("order_id");
        if(order_id != null && !order_id.isEmpty()){
            commentDao.modifyComment(data, json, order_id,comment);
            json.put("msg", "添加成功");
        }else {
            json.put("msg", "订单编号为空");
        }
    }

    /** 操作为:在servlet中直接 取出 Comment 放入 seesin中 , 前端用 jstl表达式取出循环就好**/
    @Override
    public void getComment(Data data, JSONObject json) throws SQLException, JSONException {
        Comment comment = commentDao.getCommentById(data,json);
        json.put("Comment", comment);
    }


    ///照片墙 只要取出status为1的数据就好
    @Override
    public List<Comment> listComment(Data data, JSONObject json) throws SQLException, JSONException, ParseException {
        List<Comment> Comments = commentDao.listComments( data,  json);
        json.put("Comments", Comments);
        return Comments;
    }

    @Override
    public List<Comment> owner_listComments(Data data, JSONObject json) throws SQLException, JSONException, ParseException {
        List<Comment> Comments = commentDao.owner_listComments( data,  json);
        json.put("Comments", Comments);
        return Comments;
    }


}
