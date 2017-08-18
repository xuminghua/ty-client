/**
 * Created by sunxuan on 16-11-15.
 */

/*
 * This is for form/grid field defination. It's configurable in every sub panel.
 * example: [{fieldtype:'int',  //xtype
 *              fieldid:'id',  //data name
 *              fieldtext:'号码',// name on page
 *              fielddefaultvalue:0
 *              },  //
 *              {fieldtype:'string',fieldid:'creater',fieldtext: '创建者'}]
 */
Ext.data.Types.MYFIELDMAP = {
    isValid: function (v) {
        if (v == undefined || !Ext.isArray(v) || v.length == 0) {
            Windows.msg(dictionary.pub_wrong,
                dictionary.center_fieldmaperror + v);
            return (false);
        }
        Ext.Array.each(v, function (record, index) {
            var invalid = false, fieldname = '';
            if (record.fieldtype == undefined) {
                invalid = true;
                fieldname = 'fieldtype';
            } else if (record.fieldid == undefined) {
                invalid = true;
                fieldname = 'fieldid';
            } else if (record.fieldtext == undefined) {
                invalid = true;
                fieldname = 'fieldtext';
            }
            if (invalid) {
                var msg = dictionary.center_fielderror1 + index + dictionary.center_fielderror2
                    + fieldname;
                Windows.msg(dictionary.pub_wrong, msg);
                return (false);
            }
        });
        return (true);

    },
    convert: function (v, data) {
        if (!this.type.isValid(v)) {
            return [];
        }
        return v;
    },
    sortType: function (v) {
        return 0;
        //return v.Latitude;
    },
    type: 'myfieldmap'
};