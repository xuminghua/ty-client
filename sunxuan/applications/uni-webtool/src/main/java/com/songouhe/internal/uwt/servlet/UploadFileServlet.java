package com.songouhe.internal.uwt.servlet;


import com.songouhe.base.dao.BaseDao;
import com.songouhe.internal.uwt.model.entity.daoentity.uwtDatasource.common.Common_File_Info;
import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import com.songouhe.internal.uwt.model.utils.JsonFileUtil;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.UUID;


public class UploadFileServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {


    private static org.slf4j.Logger logger
                = LoggerFactory.getLogger(MainServlet.class);

    public UploadFileServlet() {
        super();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doProcess(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doProcess(req, res);
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            //执行操作前准备参数
            String sColumnId = request.getParameter("columnId");                 // panelcake id


            logger.info("**UploadFileServlet**columnId:" + sColumnId );
            String realPath = ConfigUtil.getFileBasePath();

            ServletFileUpload upload = new ServletFileUpload();
            // set max file size to MB:
            upload.setFileSizeMax(ConfigUtil.getFileMaxSize() * 1024 * 1024);
            FileItemIterator it = upload.getItemIterator(request);
            // handle with each file:
            while (it.hasNext()) {
                FileItemStream fileItemStream = it.next();
                if (! fileItemStream.isFormField()) {
                    // it is a file upload:
                    handleFileItem(fileItemStream, realPath, sColumnId);

                }
            }

        } catch (Exception e) {
            logger.error("error", e);
            PrintWriter toClient = response.getWriter();
            toClient.write(e.getMessage());
            toClient.close();
            return;
        }
        response.setContentType("text/html; charset=gbk");

        PrintWriter writer = response.getWriter();
        writer.print(true);
        writer.close();


    }
    void handleFileItem(FileItemStream item, String uploadDir, String columnId) throws IOException {
        String fileName = item.getName();
        int index = fileName.lastIndexOf(".");
        Timestamp modified = new Timestamp(System.currentTimeMillis());
        Common_File_Info commonFileInfo = new Common_File_Info(
                UUID.randomUUID().toString(),fileName,"columnId","filepath",1,modified,null);


        System.out.println("upload file: " + item.getName());
        String path = uploadDir + "/" + columnId + "/" + commonFileInfo.getUuid() + "/" + fileName;
        File newUploadFile = new File(path );
        byte[] buffer = new byte[4096];
        InputStream input = null;
        OutputStream output = null;
        boolean writeFile = false;
        String folderPath = uploadDir + "/" + columnId + "/" + commonFileInfo.getUuid();
        try {
            input = item.openStream();
            output = new BufferedOutputStream(new FileOutputStream(newUploadFile));
            for (;;) {
                int n = input.read(buffer);
                if (n==(-1))
                    break;
                output.write(buffer, 0, n);
            }
            writeFile = true;
            logger.debug("上传" + item.getName() + ",fileID=" + commonFileInfo.getUuid());
            BaseDao baseDao = ConfigUtil.getBaseDao();
            Object[] objForCreate = {commonFileInfo};
            baseDao.createByObj(objForCreate);

            //            List records = baseDao.getQueryByObj(new BaseUserInfo());
            //            for(Object ue:records){
            //                logger.info(((BaseUserInfo) ue).getName());
            //            }

        }catch (Exception e){
            if(writeFile)JsonFileUtil.delFolderMethod(folderPath);
        }
        finally {
            if (input!=null) {
                try {
                    input.close();
                }
                catch (IOException e) {}
            }
            if (output!=null) {
                try {
                    output.close();
                }
                catch (IOException e) {}
            }
        }
    }
}
