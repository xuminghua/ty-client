package com.songouhe.internal.uwt.model.entity.daoentity.uwtDatasource.common;

import java.sql.Timestamp;

/**
 * @author sunxuan
 * @version 1.0 17-7-17
 */
public class Common_File_Info {
    private int id;
    private String uuid;
    private String name;
    private String column_id;
    private String file_path;
    private int status;
    private Timestamp modified;
    private Timestamp deleted;

    public Common_File_Info(){

    }
    public Common_File_Info(String uuid,String name, String column_id,
                            String file_path,int status,
                            Timestamp modified,Timestamp deleted){

        this.uuid = uuid;
        this.name = name;
        this.column_id = column_id;
        this.file_path = file_path;
        this.status = status;
        this.modified = modified;
        this.deleted = deleted;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumn_id() {
        return column_id;
    }

    public void setColumn_id(String column_id) {
        this.column_id = column_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Timestamp getDeleted() {
        return deleted;
    }

    public void setDeleted(Timestamp deleted) {
        this.deleted = deleted;
    }
}
