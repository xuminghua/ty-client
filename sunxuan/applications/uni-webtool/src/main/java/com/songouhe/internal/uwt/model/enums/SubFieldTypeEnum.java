package com.songouhe.internal.uwt.model.enums;

/**
 * @author sunxuan
 * @version 1.0 16-11-24
 */
public enum SubFieldTypeEnum {
    //infoFormPanel 使用的xtype
    textareafield("textareafield"),
    numberfield("numberfield"),
    datefield("datefield"),

    //searchPanel 使用的xtype
    combobox("combobox"),
    //file upload field 注意file upload不能使用ajax，且servlet不同，所以必须单独一个form进行提交。
    //所以在add操作时一般不能包括file upload，而在change操作中进行upload file操作。包括所有的文件格式
    filedisplay("filedisplay"),

    //resultGridPanel 使用的xtype
    datecolumn("datecolumn");

    private String _name;

    private SubFieldTypeEnum(String name) {
        _name = name;
    }


    public String getName() {
        return _name;
    }
}
