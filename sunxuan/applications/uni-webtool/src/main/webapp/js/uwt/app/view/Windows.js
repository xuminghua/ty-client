/**
 * 废弃，改用exWindows
 */
var Windows;
Windows = function () {
    var detailWin,detailInfoForm,detailWinBar;
    var historyWin;
    var htmlWin;
    var fileDisplayWin, fileDisplayForm, downloadBtn,uploadBtn,uploadField,deleteBtn;
    return {
        msg: function (title, msg) {
            Ext.Msg.show({
                title: title,
                msg: msg,
                minWidth: 200,
                modal: true,
                icon: Ext.Msg.INFO,
                buttons: Ext.Msg.OK
            });
        },
        alert: function (title, msg) {
            Ext.Msg.alert(title,msg);
        },
        bubblemsg:function (title, msg) {},
        /**
         * 基本工具方法，用于创建/获取一个显示各个field详细信息的window。仅供内部类调用
         */
        getDetailWin: function () {
            if (! detailInfoForm ){
                detailWinBar = Ext.create('Ext.toolbar.Toolbar', {
                    items:[
                        '->',
                        {
                            text: dictionary.pub_submit,
                            //formBind: true, //only enabled once the form is valid
                            id:'win_submit_btn'    ,
                            handler: function() {
                                var form = this.up('form').getForm();
                                if (form.isValid() || form.isValid() ) {
                                    //form.submit({
                                    //    success: function(form, action) {
                                    //        Ext.Msg.alert(dictionary.win_success, action.result.msg);
                                    //    },
                                    //    failure: function(form, action) {
                                    //        Ext.Msg.alert(dictionary.win_failure, action.result.msg);
                                    //    }
                                    //});
                                }
                            }
                        },{
                            text: dictionary.put_reset ,
                            id:'win_reset_btn'    ,
                            handler: function() {
                                this.up('form').getForm().reset();
                            }
                        }, {
                            text: dictionary.pub_close ,
                            id:'win_close_btn'    ,
                            handler: function() {
                                this.up('window').hide();
                            }
                        }]
                });


                detailInfoForm = Ext.widget({
                    xtype: 'form',
                    autoScroll: true,
                    //width: 600
                    id: 'win-result-form',
                    padding : '5 0 0 0',
                    trackResetOnLoad: true,
                    bbar:detailWinBar,
                    layout: {
                        type: 'hbox',
                        padding: '5',
                        align: 'stretch'
                    } ,
                    defaults:{
                        flex:1
                    }
                });
            }
            if (!detailWin){
                detailWin = new Ext.Window({
                    id:'detailWin',
                    //title: dictionary.center_add,
                    layout:'fit',
                    autoScroll :true,
                    height: UWTConfiguration.getWinHight(),
                    width:UWTConfiguration.getWinWidth(),
                    modal:true,
                    resizable: false,
                    closeAction:'hide',
                    items:detailInfoForm
                }); }
        },
        /**
         * 获取添加新纪录的操作窗口
         */
        getAddNewWin: function (controller) {
            //判断inPanel是否需要removeAll并重新添加field
            if(UWTConfiguration.changeColumnForWin()) {
                var formData = UWTConfiguration.getInfoFormWinData();
                if(!formData)return;
                var getFields = UWTWorks.lib.DataPrepareForView.setFieldForInfoForm(
                    detailInfoForm,detailWinBar,formData,true);
                if(!getFields)return;
            }else{
                detailInfoForm.getForm().reset();
            }
            detailWin.show();
        },
        /**
         * 获取显示详细信息的窗口
         * @param rec
         */
        getDetailInfoWin:function (rec) {
            //判断inPanel是否需要removeAll并重新添加field,还是载入数据即可
            if(UWTConfiguration.changeColumnForWin()) {
                var formData = UWTConfiguration.getInfoFormWinData();
                if(!formData)formData = {};
                var getFields = UWTWorks.lib.DataPrepareForView.setFieldForInfoForm(
                    detailInfoForm,detailWinBar,formData,true);
                if(!getFields)return;
            }
            detailInfoForm.getForm().loadRecord(rec);
            detailWin.show();
        } ,
        /**
         * 获取显示历史信息的窗口,grid panel形式, 待完成
         * @param rec
         */
        getHistoryWin: function (rec) {
            if(!historyWin){
                historyWin = new Ext.Window({
                    id:'historyWin',
                    title: dictionary.center_history,
                    layout:'fit',
                    autoScroll :true,
                    width:500,
                    modal:true,
                    resizable: false,
                    closeAction:'hide',
                    html:dictionary.win_undeveloped
                    //items:detailInfoForm
                });
            }
            historyWin.show();

        },
        getHTMLWin: function (title, shtml) {
            if(!htmlWin){
                htmlWin = new Ext.Window({
                    id:'htmlWin',
                    title: title,
                    layout:'fit',
                    autoScroll :true,
                    width:500,
                    modal:true,
                    resizable: false,
                    closeAction:'hide',
                    html:shtml
                    //items:detailInfoForm
                });
            }
        },
        FileDisplayWin: function (fieldName, fileId) {
            var rec = UWTWorks.lib.DataPrepareForView.getFormRec();
            var disableDownload;
            if(!fileId)disableDownload = true;
            if (!fileDisplayWin){
                fileDisplayForm = Ext.widget({
                    xtype: 'form',
                    autoScroll: true,
                    id: 'win-filedisplay-form',
                    padding : '5 0 0 0'});
                fileDisplayWin = new Ext.Window({
                    id:'win-filedisplay',
                    title: dictionary.win_file,
                    layout:'fit',
                    width: 400,
                    height:200,
                    autoScroll :true,
                    modal:true,
                    resizable: false,
                    closeAction:'hide',
                    items:fileDisplayForm
                });
                downloadBtn = Ext.create('Ext.Button',{
                    id:'win-download-btn',
                    text:dictionary.win_downloadFile,
                    disabled:disableDownload,
                    handler:function(){
                    }});
                deleteBtn = Ext.create('Ext.Button',{
                    id:'win-delete-btn',
                    text:dictionary.win_deleteFile,
                    disabled:disableDownload,
                    handler:function(){
                    }});
                uploadBtn =  Ext.create('Ext.Button',{
                    text:dictionary.win_uploadFile1,
                    disabled:true,
                    id:'win-upload-btn'
                });
                uploadField = Ext.create('Ext.form.field.File',{
                    id:'win-upload-field',
                    name:fieldName,
                    fieldLabel:dictionary.win_uploadLabel,
                    listeners :{
                        change: function (thisField, inFileValue) {
                            var uploadBtn = Ext.getCmp('win-upload-btn');
                            if(inFileValue){
                                uploadBtn.setDisabled(false);
                            }else{
                                uploadBtn.setDisabled(true);
                            }
                        }
                    }
                });
            }else{
                downloadBtn.setDisabled(disableDownload);
                deleteBtn.setDisabled(disableDownload);
                uploadField.reset();
                uploadBtn.setDisabled(true);

            }
            var columnId = UWTConfiguration.getCurrentColumn();
            uploadBtn.setHandler(function(o){
                var form = Ext.getCmp("win-filedisplay-form").getForm();
                if(form.isValid()){
                    form.submit({
                        url:'uploadFile',
                        method:'POST',
                        waitMsg : dictionary.center_submit_waitmsg,
                        params: {
                            columnId:columnId,
                            recordId: rec.uuid
                        },
                        success:function(form, action){
                            //var data = Ext.JSON.decode(action.response.responseText);
                            fileDisplayWin.hide();
                            //var UWTCtl = Ext.getCmp('CtlViewManager');
                            var controller = UWTWorks.getApplication().getController('CtlViewManager');
                            controller.loadPanelWorkspaceData();
                        },
                        failure : function(form, action) {
                            //var data = Ext.JSON.decode(action.response.responseText);
                            alert(action.response.responseText);
                        }
                    }) }});
            fileDisplayForm.add(downloadBtn,deleteBtn,uploadField,uploadBtn);
            fileDisplayWin.show();


        }

    }
}();


