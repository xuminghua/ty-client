package com;

import com.songouhe.base.dao.entity.RecordWithTotalCount;
import com.songouhe.base.sso.action.PrivilegeInfo;
import com.songouhe.base.sso.action.SSOUserInfo;
import com.songouhe.base.util.service.SohDateTimeConverter;
import com.songouhe.internal.uwt.action.ViewManager;
import com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource.product.Product_Info;
import com.songouhe.internal.uwt.model.entity.daoentity.uwtDatasource.hr_Columns.User_Base_Info;
import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import com.songouhe.internal.uwt.model.utils.JsonFileUtil;
import com.songouhe.internal.uwt.model.viewconfig.MainViewModel;
import com.songouhe.internal.uwt.model.viewconfig.PanelWorkspaceModel;
import com.songouhe.internal.uwt.model.viewconfig.TabCategoryModel;
import com.songouhe.internal.uwt.model.viewconfig.TreeColumnModel;
import com.songouhe.internal.uwt.model.viewconfig.action.ActionModel;
import com.songouhe.internal.uwt.model.viewconfig.action.OperationSetModel;
import com.songouhe.internal.uwt.view.PanelWorkspaceView;
import com.songouhe.internal.uwt.view.TreeColumnView;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.transformer.BasicDateTransformer;
import flexjson.transformer.BooleanTransformer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sunxuan on 16-7-18.
 */
public class test1 {
    public static void printError(HttpServletRequest request){

        Exception e = (Exception)request.getAttribute("javax.servlet.error.exception");
        e.printStackTrace();
//        System.out.println(e.getMessage());
//        System.out.println(e.getLocalizedMessage());
        ;
           Enumeration<String> a = request.getSession().getAttributeNames();
        System.out.println("Session:");
        while (a.hasMoreElements()){
            System.out.println(a.nextElement());
        }
        Enumeration<String> b = request.getAttributeNames();
        System.out.println("request:");
        while (b.hasMoreElements()){
            System.out.println(b.nextElement());
        }
    }
    public test1(ServletContext servletContext) {
        System.out.print("aaaa");
        WebApplicationContext context =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
        Properties p = (Properties) context.getBean("commons");
        System.out.println(p.getProperty("env.locale"));
    }

    public static void java2Json() {
        MainViewModel mainViewModel = MainViewModel.getInstance();
        PanelWorkspaceModel panelWorkspaceModel = new PanelWorkspaceModel();
        PanelWorkspaceModel panelWorkspaceModel2 = new PanelWorkspaceModel();
//        panelWorkspaceModel.addSubfields(new SubfieldModel(SubFieldType.Text, "text1", "字段1", "", "ALL", "ALL"));
        TabCategoryModel RDTab = new TabCategoryModel("RD", "研发", "ALL", "ALL");
        mainViewModel.setInitPanels(panelWorkspaceModel2);
        TabCategoryModel OPTab = new TabCategoryModel("OP", "运营", "ALL", "ALL");
        TabCategoryModel HRTab = new TabCategoryModel("HR", "人力", "ALL", "ALL");
        TabCategoryModel IDEATab = new TabCategoryModel("IDEA", "金点子", "ALL", "ALL");

        TreeColumnModel treeColumnModel1 = new TreeColumnModel("我的工作单", "tasklist", "ALL", "ALL");
        treeColumnModel1.setPanelWorkspaces(panelWorkspaceModel);
        TreeColumnModel treeColumnModel2 = new TreeColumnModel("分类", "example", "ALL", "ALL");
        TreeColumnModel treeColumnModel3 = new TreeColumnModel("我的工作单2", "example2", "ALL", "ALL");
        treeColumnModel2.addChildren(treeColumnModel3);

        RDTab.addTreeColumn(treeColumnModel1);
        RDTab.addTreeColumn(treeColumnModel2);


        mainViewModel.addTabCategory(RDTab);
        mainViewModel.addTabCategory(OPTab);
        mainViewModel.addTabCategory(HRTab);
        mainViewModel.addTabCategory(IDEATab);

        JSONSerializer serializer = new JSONSerializer();

        System.out.println("Convert Java object to JSON format and save to file");

        try (FileWriter writer = new FileWriter("flexjson.json")) {

            String result = serializer.include("*.store").exclude("*.class","*.orig_fields", "*.privilege",
                    "*.record_privilege", "*.uniFieldView", "*.operations","*.userOperations")
                    .deepSerialize(mainViewModel);
        } catch (IOException e) {

        }
    }

    public static void json2Java() {

                try (BufferedReader reader = new BufferedReader(new FileReader("flexjson.json"))) {

                    PanelWorkspaceModel pm = new JSONDeserializer<PanelWorkspaceModel>().deserialize(reader, PanelWorkspaceModel.class);

                    PanelWorkspaceView pv = new PanelWorkspaceView();
//                    pv.
//                    JSONDeserializer<PanelWorkspaceView>().deserialize(reader, PanelWorkspaceView.class);
                    System.out.println(pv.isCompleteRec());


                } catch (Exception e) {
//                    logger.error("error", e);
                    e.printStackTrace();
                }

//        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
//
//            mainViewModel = new JSONDeserializer<MainViewModel>().deserialize(reader, MainViewModel.class);
//            List<TabOperationModel> tabOps = mainViewModel.getTabOperations();
//            for (TabOperationModel n : tabOps) {
//                System.out.println(n.getTitle());
//
//            }


//            JSONSerializer serializer = new JSONSerializer();
//            String tabs = serializer.exclude("class", "*.class", "treeColumnMap").include("tabOperations").serialize(mainView);
//            System.out.println(tabs);

//
//        } catch (FileNotFoundException e) {
//
//        } catch (IOException e) {
//
//        }
    }

    public static void testUserMainView() {
        //build
        ConfigUtil.setViewConfigPath("./src/main/webapp/WEB-INF/view-json-configuration");
        ViewManager vm = new ViewManager();
              String s = vm.getJSONArrayTreeColumnMap("HR");

        //tab get
//        System.out.println(s);
        //privilege filter
        SSOUserInfo ui = new SSOUserInfo();
        List pris = new ArrayList();
        pris.add("HR_BASE");
        HashMap<String, Integer> utilPrivs = new HashMap<String, Integer>();
        utilPrivs.put("HR_BASE",900);
        utilPrivs.put("RD_BASE",800);

        PrivilegeInfo pi = new PrivilegeInfo(pris,utilPrivs);
        ui.setPrivilege(pi);
        vm.setUserViewFromPrivilege(ui);
//        s = vm.getJSONArrayPanelWorkspace(pris, "HR", "hr_Clm_newstaff");
        try {
            s = vm.getJSONArrayPanelWorkspace(pi, "HR", "hr_Clm_newstaff");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void testViewOperations2json() {
        OperationSetModel osm = new OperationSetModel();
        ActionModel am1 = new ActionModel();
        String[] fields = {"FID","F_Status"};
        am1.setSql("SELECT * FROM TABLE1 WHERE ");
        HashMap<String, ActionModel[]> actionmap1 = new HashMap<String, ActionModel[]>();
        ActionModel[] actions = {am1};
        actionmap1.put("ALL", actions);
        osm.setSearch(actionmap1);

        HashMap<String, String[]> actionmap2 = new HashMap<String,  String[]>();
        actionmap2.put("ALL", fields);
        osm.setDisabledFields(actionmap2);

        ActionModel am2 = new ActionModel();
        am2.setSql("INSERT INTO TABLE1 VALUES (?,?) ");
        HashMap<String, ActionModel[]> actionmap3 = new HashMap<String, ActionModel[]>();
        ActionModel[] actions2 = {am2};
        actionmap3.put("ALL", actions2);
        osm.setCreate(actionmap3);

        ActionModel am3 = new ActionModel();
        am3.setSql("UPDATE TABLE1 SET ");
        String[] fields2 = new String[]{"F_CreatedDay", "F_UT_DeadlineDay"};
        HashMap<String, ActionModel[]> actionmap4 = new HashMap<String, ActionModel[]>();
        ActionModel[] action3s = {am3};
        actionmap4.put("ALL", action3s);
        osm.setUpdate(actionmap4);




        JSONSerializer serializer = new JSONSerializer();

        System.out.println("Convert Java object to JSON format and save to file");

        try (FileWriter writer = new FileWriter("flexjson.json")) {

            serializer.exclude("class", "*.class").deepSerialize(osm, writer);

        } catch (IOException e) {

        }
    }
    public static void testMultipleBeanFromOneJson(){
        String inputJson = "{\"Handle\":\"handle1\",\"Department_Id\":\"did1\",\"Phone\":\"0102222\"}";
        User_Base_Info info = new JSONDeserializer<User_Base_Info>().deserialize(inputJson, User_Base_Info.class);
//        User_Dynamic_Info info_2 = new JSONDeserializer<User_Dynamic_Info>().deserialize(inputJson, User_Dynamic_Info.class);
        System.out.println(info.getId());
//        System.out.println(info_2.getHome_Address());
    }

    public static void testModel2View() {
        try (BufferedReader reader = new BufferedReader(new FileReader("flexjson.json"))) {

            PanelWorkspaceModel model = new JSONDeserializer<PanelWorkspaceModel>().deserialize(reader, PanelWorkspaceModel.class);
            TreeColumnModel tm = new TreeColumnModel();

            tm.setPanelWorkspaces(model);
            //验证view的转换性：java2json
            JSONSerializer serializer = new JSONSerializer();
            String s = serializer.exclude("class", "*.class").deepSerialize(tm);
//            System.out.print(s);
                               //验证view的转换性：json2java
            TreeColumnView view = new JSONDeserializer<TreeColumnView>().deserialize(s,TreeColumnView.class);

            PanelWorkspaceView pv = view.getPanelWorkspaces();
            HashMap combos = new HashMap();
            List ucd = new ArrayList();
            String[] dpt1 = {"hr","部门1"};
            ucd.add(dpt1);
            combos.put("User_Class_Department",ucd) ;
            ConfigUtil.setCombosForView(combos);
            pv.buildFieldForView();
            String result = serializer.include("*.fieldsMap.fields.*.store").exclude("*.class","*.orig_fields", "*.privilege",
                                "*.record_privilege", "*.uniFieldView", "*.operations","*.userOperations")
                                .deepSerialize(pv);
            System.out.print(result);


        } catch (Exception e) {
//                    logger.error("error", e);
            e.printStackTrace();
        }

    }
    public static void testMap2JavaBean() {
        HashMap map = new HashMap();

        map.put("id","11");
        map.put("name","dptid");
        map.put("title", "13822221122");
        map.put("content", "contentcontentcontentcontentcontent");
        map.put("price", "300.01");
        map.put("discount", "0.3");
        map.put("is_Top", "true");
        map.put("is_Self", "false");
        map.put("audit_Status", "2");
        map.put("ages", "1-4");
        map.put("link", "http://sss.com");
        map.put("likes", "13822221");
        map.put("address_Point", "13822221122");
        map.put("address", "addressaddressaddress");
        map.put("city", "beijing");
        map.put("type", "1");
        map.put("status", "2");
        map.put("end_time", "2017-08-09");
        map.put("expiry_time", "2017-08-19");
        map.put("create_time", new Timestamp(System.currentTimeMillis()));
        map.put("views", "1382");

        try{
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
            Product_Info pi = new Product_Info();
        BeanUtils.populate(pi, map);
            List records = new ArrayList();
            records.add(pi);
            RecordWithTotalCount record = new RecordWithTotalCount();
            record.setItems(records);
            record.setTotalCount(1);
            JSONSerializer serializer = new JSONSerializer().exclude("*.class")
                                .transform(new BasicDateTransformer(), Timestamp.class)
                                .transform(new DateTransformer("yyyy-MM-dd"),
                                        Timestamp.class);
            String s = serializer.deepSerialize(record);
            System.out.println(s);

    } catch (Exception e) {
        System.out.println("transMap2Bean2 Error " + e);
    }
//        User_Base_Info ubi =
//                (User_Base_Info)JsonFileUtil.hashMapToJavaBean(map,User_Base_Info.class);
//        User_Dynamic_Info udi = (User_Dynamic_Info)JsonFileUtil.hashMapToJavaBean(map,User_Dynamic_Info.class);
//        System.out.println(ubi.getId());
//        System.out.println(udi.getPhone());
    }


    public static void main(String[] args) {

//        testModel2View();
//        testMap2JavaBean();
//        testUserMainView();
//        java2Json();
        testMap2JavaBean();
//        setConfigUtil();
        System.out.println("*********************************");
//
//
//        vm.getPanelWorkspaceView("HR","hr_pri_info_password");
//        System.out.println("*********************************");
//        vm.getJSONArrayInitPanelWorkspace("RD");

    }
}
