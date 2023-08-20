package common.util;
import db.Data;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
public class FileUploadUtil {

    public  FileUploadUtil(){

    }
    public List<Map> File_upload(HttpServletRequest request, String filepath, Data data) {
        //判断上传的表单是否为multipart/form-data类型
        if (ServletFileUpload.isMultipartContent(request)) {

            try {
                //1.创建DiskFileItemFactory对象,设置缓冲区大小和临时目录文件
                DiskFileItemFactory factory = new DiskFileItemFactory();
                //2.创建ServletFileUpload对象，并设置上传文件的大小限制
                ServletFileUpload sfu = new ServletFileUpload(factory);
                sfu.setSizeMax(10 * 1024 * 1024);//以byte为单位 1024byte->1KB*1024=1M->1M*10=10M
                sfu.setHeaderEncoding("utf-8");

                //3.调用ServletFileUpload.parseRequest方法来解析对象，得到一个保存了所有上传内容的List对象
                List<FileItem> fileItemList = sfu.parseRequest(request);
                Iterator<FileItem> fileItems = fileItemList.iterator();

                //创建一个Map集合，用于添加表单元素


                //
                List<Map> filesmap = new ArrayList<>();
                //4.遍历fileItems，每迭代一个对象，调用其isFormField方法判断是否是上传文件
                while ((fileItems.hasNext())) {

                    Map<String, String> map = new TreeMap<String, String>();
                    FileItem fileItem = fileItems.next();
                    try{
                        //普通的表单元素
                        if (fileItem.isFormField()) {
                            String name = fileItem.getFieldName();//name的属性值
                            String value = fileItem.getString("utf-8");//name对应的value值
                            System.out.println("name"+name+"value="+value);
                            data.getParam().put(name,value);
                            //添加进Map集合中
                            map.put(name, value);
                       }
//                        else if(fileItem.getFieldName().equals("multi")){
//                            //循环数组得到文件并保存
//                            System.out.println("收到nuilti"+fileItem);
//                            InputStream inputStream = fileItem.getInputStream();
//                            String fileName = fileItem.getName();// 文件名称
//                            System.out.println("原文件名：" + fileName);// Koala.jpg
//
//                            String suffix = fileName.substring(fileName.lastIndexOf('.'));
//                            System.out.println("扩展名：" + suffix);// .jpg
//
//                            // 新文件名（唯一）
//                            String newFileName = new Date().getTime() + suffix;
//                            System.out.println("新文件名：" + newFileName);// image\1478509873038.jpg
//
//                            //将文件名存入到数组中
//                            map.put("fileName", fileName);
//                            map.put("newFileName", newFileName);
//
//
//                            // 5. 调用FileItem的write()方法，写入文件
//                            String context = filepath+newFileName ;
//                            System.out.println("图片的路径为"+context);
//                            File file = new File(context);
//                            OutputStream outputStream = new FileOutputStream(file);
//                            byte[] buffer = new byte[1024];
//                            int length;
//                            while ((length = inputStream.read(buffer)) != -1) {
//                                outputStream.write(buffer, 0, length);
//                            }
//                            inputStream.close();
//                            outputStream.close();
//
//                            filesmap.add(map);
//
//                        }
                       else {//否则即为<input type="file">上传的文件
                            System.out.println("一个file"+fileItem);

                            if(fileItem.getName()==null||fileItem.getFieldName()==null){
                                map.put("fileName","empty");
                                map.put("newFileName","empty");
                            }else {
                                String fileName = fileItem.getName();// 文件名称
                                System.out.println("原文件名：" + fileName);// Koala.jpg

                                String suffix = fileName.substring(fileName.lastIndexOf('.'));
                                System.out.println("扩展名：" + suffix);// .jpg

                                // 新文件名（唯一）
                                String newFileName = new Date().getTime() + suffix;
                                System.out.println("新文件名：" + newFileName);// image\1478509873038.jpg

                                //将文件名存入到数组中
                                map.put("fileName", fileName);
                                map.put("newFileName", newFileName);


                                // 5. 调用FileItem的write()方法，写入文件
                                String context = filepath+newFileName ;
                                System.out.println("图片的路径为"+context);
                                File file = new File(context);
                                System.out.println(file.getAbsolutePath());
                                fileItem.write(file);

                                //判断该文件是否为head_img下默认的头像，如果不是才执行删除
                                if(!fileName.contains("empty")|| !newFileName.contains("empty")){
                                    // 6. 调用FileItem的delete()方法，删除临时文件
                                    fileItem.delete();
                                }

                            }

                        }
                    }catch (StringIndexOutOfBoundsException e ){
                        //若为空指指针
                        //未上传图片则按原来的图片显示
                        //设置为false,在进行数据库操作时不对图片进行操作
                        System.out.println("出现异常");
                        map.put("fileName","empty");
                        map.put("newFileName","empty");
                        e.printStackTrace();
                    }
                    filesmap.add(map);
                }
                return filesmap;
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  null;
    }
    public boolean deleteFiles(String pathName){
        boolean flag = false;
        //根据路径创建文件对象
        File file = new File(pathName);
        //路径是个文件且不为空时删除文件
        if(file.isFile()&&file.exists()){
            flag = file.delete();
        }
        //删除失败时，返回false
        return flag;
    }

    /**
     * 删除目录本身以及目录下的所有文件及文件夹
     * @param pathName  目录名
     * @return
     */
    public boolean deleteDiretory(String pathName){
        boolean flag = false;
        //根据路径创建文件对象
        File directory = new File(pathName);
        //如果路径是一个目录且不为空时，删除目录
        if(directory.isDirectory()&&directory.exists()){
            //获取目录下的所有的目录和文件，放入数组files中
            File[] files = directory.listFiles();
            //遍历目录下的所有的文件和目录
            for(int i= 0;i<files.length;i++){
                //如果目录下是文件时，调用deleteFiles（）方法，删除单个文件
                if (files[i].isFile()){
                    flag = deleteFiles(files[i].getAbsolutePath());
                }//如果目录下是目录时，调用自身deleteDirectory()，形成递归调用
                else{
                    flag = deleteDiretory(files[i].getAbsolutePath());
                }
            }

            //删除目录本身，如果想要保留目录只删除文件，此句可以不要
            flag = directory.delete();
        }
        //删除成功时返回true，失败时返回false
        return flag;
    }

    /**
     * 删除文件或者目录
     * @param pathName   路径名
     * @return
     */
    public boolean deleteDirectoryOrFile(String pathName){
        boolean flag = false;
        File file = new File(pathName);
        //如果路径是一个文件则调用deleteFiles()
        if (file.isFile()&&file.exists()){
            flag = deleteFiles(pathName);
        }//如果路径是目录则调用deleteDirectory()
        else if(file.isDirectory()&&file.exists()){
            flag = deleteDiretory(pathName);
        }

        return flag;
    }


}


