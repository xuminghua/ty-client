package com.songouhe.internal.uwt.model.entity.daoentity.classes;

/**
 * @author sunxuan
 * @version 1.0 17-7-19
 */
public abstract class AbstractCommonClass {
    protected String id;
    protected String name;
    protected String hint;

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getHint() {
         return hint;
     }

     public void setHint(String hint) {
         this.hint = hint;
     }
}
