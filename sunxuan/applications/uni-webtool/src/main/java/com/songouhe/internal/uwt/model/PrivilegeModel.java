package com.songouhe.internal.uwt.model;


import java.io.Serializable;

/**
 * @author sunxuan
 * @version 1.0 16-11-29
 */
public class PrivilegeModel implements Serializable {

    private String readPrivilege;
    private String writePrivilege;

    public PrivilegeModel(String readPrivilege, String writePrivilege) {
        this.readPrivilege = readPrivilege;
        this.writePrivilege = writePrivilege;
    }

    /**
     * 以下为privilege的常量的string值，可用于view和record
     * @return
     */
    public static String ALL(){
        return "ALL";
    }

    /**
     * 以下为privilege的常量的string值，仅可用于record(即PanelWorkcake，Field中可用)
     * @return
     */
    public static String SELF(){
        return "SELF";
    }

    public static String TEAMER(){
        return "TEAMER";
    }

    public PrivilegeModel() {
        this.readPrivilege = ALL();
        this.writePrivilege = ALL();

    }

    public String getReadPrivilege() {
        return readPrivilege;
    }

    public void setReadPrivilege(String readPrivilege) {
        this.readPrivilege = readPrivilege;
    }

    public String getWritePrivilege() {
        return writePrivilege;
    }

    public void setWritePrivilege(String writePrivilege) {
        this.writePrivilege = writePrivilege;
    }

}
