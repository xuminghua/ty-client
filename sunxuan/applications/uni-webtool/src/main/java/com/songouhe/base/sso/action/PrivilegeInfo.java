package com.songouhe.base.sso.action;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * @author sunxuan
 * @version 1.0 17-2-15
 */
public class PrivilegeInfo {
        /*
    用户权限组，根据privilege code进行升序排序
     */
    private TreeMap<Integer, String> mapPrivileges = new TreeMap<Integer, String>();;
    /*
    用户权限组
     */
    private List<String> privileges;

    public TreeMap<Integer, String> getMapPrivileges() {
        return mapPrivileges;
    }

    public void setMapPrivileges(TreeMap<Integer, String> mapPrivileges) {
        this.mapPrivileges = mapPrivileges;
    }

    public List getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }

    public PrivilegeInfo(List<String> inPrivileges, HashMap inPrivilegeBaseSet) {
        this.privileges = inPrivileges;
        inPrivilegeBaseSet.entrySet();
        for(String key: privileges){
              if(inPrivilegeBaseSet.containsKey(key)){
                  mapPrivileges.put((Integer) inPrivilegeBaseSet.get(key), key);
              }
        }
    }

}
