package common.util;

import db.Data;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import user.entitiy.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class MyExcelUtil {
    public MyExcelUtil(String s) {
    }

    public void exportData(Data data, JSONObject json) throws IOException, JSONException {
        //创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
//创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet0");
//写表头======================================================
        JSONArray arrayTile = json.getJSONArray("aaFieldName");
        //创建HSSRow 对象
        HSSFRow rowTitle = sheet.createRow(0);
        for(int i =0;i<arrayTile.length();i++){
            //创建cell对象
            HSSFCell cell = rowTitle.createCell(i);
            //设置单元格的值
            cell.setCellValue((String)arrayTile.get(i));
            System.out.println("表头字段="+(String) arrayTile.get(i));

        }
//写表头结束======================================================
        //因为0行为表头字段 因此团冲数据从row=1开始
    JSONArray array =json.getJSONArray("aaData");


        for(int i=1;i<array.length()+1;i++){
            //创建HSSFRow对象
            HSSFRow row = sheet.createRow(i);


            HashMap<String,String> record = ( HashMap<String,String>)array.get(i-1);
            for(HashMap.Entry<String,String> entry : record.entrySet()){
                System.out.println("key=" +entry.getKey()+"-----value"+entry.getValue());
                //和表头名称比对,名一样才写入
                for(int same =0;same<arrayTile.length();same++){

                    if(entry.getKey().equals(arrayTile.get(same).toString())){
                        System.out.println(entry.getKey().toString()+"=="+arrayTile.get(same).toString());
                        //创建HSSFCell 对象
                        HSSFCell  cell  = row.createCell(same);
                        //设置单元格的值
                        cell.setCellValue(entry.getValue());
                    }
                }


            }
        }
//输出Excel文件
        FileOutputStream output=new FileOutputStream(json.getString("file_path"));
        wb.write(output);
        output.flush();
        output.close();
    }
}
