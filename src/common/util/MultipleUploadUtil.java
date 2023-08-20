package common.util;
import db.Data;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import user.entitiy.Img;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.List;

public class MultipleUploadUtil {
    public MultipleUploadUtil() {
    }

    public List<Img> File_upload(HttpServletRequest request, Data data) throws Exception {


// ...
        List<Img> imgs =new ArrayList<>();
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            int countroom_num =0;

            List<FileItem> items = upload.parseRequest(request);
            List<Map> filesmap = new ArrayList<>();
            for (FileItem fileItem : items) {
                Img img =new Img();
                Map<String, String> map = new TreeMap<String, String>();
                if (fileItem.isFormField()) {
                    // 处理普通表单项
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    System.out.println("fieldName:" + fieldName + "  value:" + value);
                    map.put(fieldName,value);
                    data.getParam().put(fieldName,value);
                    // 处理表单数据
                } else {
                    // 处理上传的文件
                    String fileName2 = fileItem.getName();
                    String contentType = fileItem.getContentType();
                    InputStream fileContent = fileItem.getInputStream();
                    System.out.println("原文件名：" + fileName2 + "  contentType  " + contentType + "  fileContent " + fileContent);// Koala.jpg
                    // 处理上传文件的内容
                    String fileName = fileItem.getName();// 文件名称
                    System.out.println("原文件名：" + fileName);// Koala.jpg

                    String suffix = fileName.substring(fileName.lastIndexOf('.'));
                    System.out.println("扩展名：" + suffix);// .jpg

                    // 新文件名（唯一）
                    String newFileName = new Date().getTime() + suffix;
                    System.out.println("新文件名：" + newFileName);// image\1478509873038.jpg
                    String context;
                    if(data.getParam().getString("action").equals("add_room_record")){
                        context = "/assets/global/img/room_pic/" + newFileName;
                        countroom_num++;

                    }else {
                        context = "/assets/global/img/gh_pic/" + newFileName;
                        data.getParam().put("newFileName",newFileName);
                    }

                    // 5. 调用FileItem的write()方法，写入文件

                    System.out.println("图片的路径为" + context);
                    File file = new File(context);
                    System.out.println(file.getAbsolutePath());
                    fileItem.write(file);
                    img.setNewFileName(newFileName);
                }
                imgs.add(img);
            }

        }
        return imgs;
    }
}
