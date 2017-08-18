package com.songouhe.internal.uwt.model.utils;

import com.songouhe.internal.uwt.model.viewconfig.PanelWorkspaceModel;
import flexjson.JSONDeserializer;
import org.apache.commons.beanutils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

/**
 * @author sunxuan
 * @version 1.0 17-1-16
 */
public class JsonFileUtil {
    private static Logger logger
            = LoggerFactory.getLogger(JsonFileUtil.class);
    public static HashMap getJsonFiles(String path) {
        HashMap result = new HashMap();
        try {
            File file = new File(path);
            InputStream in = null;
            File [] filen=file.listFiles();
            for(int i=0;i<filen.length;i++){
                if(filen[i].isFile()){
                    if(filen[i].getName().endsWith(".json")){
                        int iEnd = filen[i].getName().length() - 5;
                        String skey = filen[i].getName().substring(0,iEnd);
                        result.put(skey, filen[i]);

                    }
                }else{
                    HashMap tabMap = new HashMap();
                    File [] fileColumns =filen[i].listFiles();
                    for(int j=0;j<fileColumns.length;j++){
                        if(fileColumns[j].isFile() && fileColumns[j].getName().endsWith(".json")){
                            int iEnd = fileColumns[j].getName().length() - 5;
                            String skey = fileColumns[j].getName().substring(0,iEnd);
                            tabMap.put(skey, fileColumns[j]);
                        }

                    }

                    result.put(filen[i].getName(), tabMap);
                }

            }
        }catch (Exception e){
            logger.error("error", e);
        }

        return result;
    }

    /**
     * 从json File中获取panel 配置model的function
     * @param inColumnID  要返回的panel model 所属column的columnID。当为null时，map的key设为initPanels进行读取。
     * @param jsonFilesMap  HashMap<columnID: File>
     * @return
     */
    public static PanelWorkspaceModel getPanelsFromJsonFile(String inColumnID, HashMap jsonFilesMap){
        PanelWorkspaceModel result = null;
        String columnID = "";
        BufferedReader readerInitP = null ;
        try {
            if(inColumnID == null || inColumnID.equals("") )columnID = "initPanels"; //初始界面
            else columnID = inColumnID;
            if(jsonFilesMap.get(columnID) == null){
                return result;
            }
            File panels = (File) jsonFilesMap.get(columnID);
            readerInitP = new BufferedReader(new FileReader(panels.getPath()));
            result =
                    new JSONDeserializer<PanelWorkspaceModel>().deserialize(readerInitP, PanelWorkspaceModel.class);
        }catch (Exception e){
            logger.error("column id: " + columnID );
            logger.error("error", e);
        }finally {
            if(readerInitP != null)
                try {
                    readerInitP.close();
                } catch (IOException e) {
                    logger.error("error", e);
                }
        }
        return result;
    }


    public static HashMap getCombosFromJsonFile(String insPath){
        HashMap result = new HashMap();
        File file = new File(insPath);
        BufferedReader readerInitP = null ;
        try {
            if(file.exists() && file.isFile()) {
                readerInitP = new BufferedReader(new FileReader(file));
                result =
                        new JSONDeserializer<HashMap>().deserialize(readerInitP, HashMap.class);
            }
        }catch (Exception e){
            logger.error("error",e);
        }
        return result;
    }


    /**
     * HashMap转换成JavaBean
     *
     * @author hailan
     * @time 下午05:57:16
     * @param map
     * @param cls
     * @return
     */
    public static Object hashMapToJavaBean(HashMap<?,?> map, Class<?> cls) {
        Object obj = null;
        try {
            obj = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ConvertUtils.register(new Converter() {

                public Object convert(Class type, Object value) {
                    String parseValue = value.toString();
                    if (parseValue.length() == 10) {
                        parseValue += " 00:00:00";
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        return Timestamp.valueOf(parseValue);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                }
            }, Timestamp.class);
            BeanUtils.populate(obj, (Map<String, ? extends Object>) map);
        } catch (Exception e) {
            System.out.println("transMap2Bean2 Error " + e);
        }

        return obj;
    }

    /**
     * 调用底层方法设置值
     *
     * @author hailan
     * @time 下午06:01:56
     * @param type
     * @param value
     * @param i
     * @param method
     * @param bean
     * @throws Exception
     */
    private static void setValue(String type, Object value, int i, Method[] method,
                                 Object bean) throws Exception {
        if (value != null && !value.equals("")) {
            try {
                Object object = value;
                if(value.getClass().isArray() ) {
                    object = ((Object[])value)[0];
                }
                if (type.equals("String")) {
                    // 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
                    method[i].invoke(bean, new Object[] { object });
                } else if (type.equals("int") || type.equals("Integer")) {
                    method[i].invoke(bean, new Object[] { new Integer(""
                            + object) });
                } else if (type.equals("BigDecimal")) {
                    method[i].invoke(bean, new Object[] { new BigDecimal((String)object) });
                } else if (type.equals("long") || type.equals("Long")) {
                    method[i].invoke(bean,
                            new Object[] { new Long("" + object) });
                } else if (type.equals("boolean") || type.equals("Boolean")) {
                    method[i].invoke(bean, new Object[] { Boolean.valueOf(""
                            + object) });
                } else if (type.equals("Date")) {
                    Date date = null;
                    if (object.getClass().getName().equals("java.util.Date")) {
                        date = (Date) object;
                    } else {
                        //根据文件内的格式不同修改，时间格式太多在此不做通用格式处理。
                        if (object.toString().length() > 10){
                            String format = "yyyy-MM-dd HHmmss";
                            date = parseDateTime("" + object, format);
                        } else if (object.toString().length() == 10){
                            String format = "yyyy-MM-dd";
                            date = parseDateTime("" + object, format);
                        } else  if (object.toString().length() == 8){
                            String format = "yyyyMMdd";
                            date = parseDateTime("" + object, format);
                        } else  if (object.toString().length() == 14){
                            String format = "yyyyMMddHHmmss";
                            date = parseDateTime("" + object, format);
                        }else  if (object.toString().length() == 6){
                            String format = "HHmmss";
                            date = parseDateTime("" + object, format);
                        }
                    }
                    if (date != null) {
                        method[i].invoke(bean, new Object[] { date });
                    }
                } else if (type.equals("byte[]")) {
                    method[i].invoke(bean,
                            new Object[] { new String(object + "").getBytes() });
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    /**
     * 日期格式转换
     *
     * @author hailan
     * @time 下午06:02:59
     * @param dateValue
     * @param format
     * @return
     */
    private static Date parseDateTime(String dateValue, String format) {
        SimpleDateFormat obj = new SimpleDateFormat(format);
        try {
            java.util.Date utilDate = obj.parse(dateValue);
            Date sqlDate=new java.sql.Date(utilDate.getTime());
            return sqlDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Map<String, Object> JavaBeanToHashMap(Object ojt) {
        Class<?> cls = ojt.getClass();
        Field[] field = cls.getDeclaredFields();

        HashMap<String, Object> mapbean = new HashMap<String, Object>();
        for(int i=0;i<field.length;i++){
            Field f = field[i];
            f.setAccessible(true);
            try {
                mapbean.put(f.getName(), f.get(ojt));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mapbean;
    }

    /**
     * 删除文件夹
     * @param folderPath
     * @return
     */
    public static boolean delFolderMethod(String folderPath) {
        boolean result = false;
        try {
            logger.debug("start delete folder: " + folderPath);
            delAllFileMethod(folderPath);
            File myFilePath = new File(folderPath);
            myFilePath.delete();
            logger.debug("complete delete folder:" + myFilePath.getName());
            result = true;
        }
        catch (Exception e) {
            logger.error("error", e);


        }
        return result;

    }

    /**
     * 删除文件路径下所有文件和文件夹
     * @param path
     * @return
     */
    public static boolean delAllFileMethod(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (!file.isDirectory()) {
            return false;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
                logger.debug("delete file: " + temp.getName());

            }
            if (temp.isDirectory()) {
                delAllFileMethod(path + File.separator + tempList[i]);
                delFolderMethod(path + File.separator + tempList[i]);
            }
        }
        return true;
    }

}
