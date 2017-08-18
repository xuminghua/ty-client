/**
    * @author sunxuan
    * @version 1.0 17-1-25
    */
Ext.define('UWTWorks.lib.MethodsRemote', {
    singleton: true,
    getCenterWorkspace: function (inTabCategoryId,inColumnId,inOnSuccess, inScope ) {
        this.invokeGet('ReadDataManager', 'getPanelWorkspaceData',
            {tabCategoryId: inTabCategoryId, columnID: inColumnId}, inOnSuccess, inScope);

    } ,


    invokeGet:function(inClassName, inMethod, inPara, inOnSuccess, inScope) {
        var params = {
            cName : inClassName,
            cMethod : inMethod,
            para : inPara == null?null:Ext.JSON.encode(inPara)
        };
        Ext.Ajax.request({
            url: 'getDataServ',
            params : params,
            method: 'POST',
            callback:
                    function (request, success, result ) {
                        if(!success){
                            if(result.responseText)
                                Ext.MessageBox.alert('Failed', 'Failed posted form:' + result.responseText);
                            else
                                Ext.MessageBox.alert('Failed', dictionary.pub_logTimeOut);
                            return;
                            
                        }
                        var resultArray;
                        if(!result.responseText )
                            resultArray = [];
                        else
                            resultArray = Ext.decode(result.responseText);
                        if (inOnSuccess)
                            inOnSuccess.call(inScope, resultArray);

                    },
            failure: function (result, request) {
            }
        });


    } ,
   invokeX: function (inColumnId, inPCid, inAction, inPara, inOnSuccess, inScope) {
       var params = {
                                   columnId:inColumnId,
                                   panelcakeId: inPCid,
                                   action:inAction
                               };
       Ext.apply(params, inPara);
       Ext.Ajax.request({
                   url: 'actionX',
                   params : params,
                   method: 'POST',
                   callback:
                           function (request, success, result ) {
                               if(!success){
                                   if(result.responseText)
                                       Ext.MessageBox.alert('Failed', 'Failed posted form:' + result.responseText);
                                   else
                                       Ext.MessageBox.alert('Failed', dictionary.pub_logTimeOut);
                                   return;

                               }
                               var resultArray;
                               if(!result.responseText )
                                   resultArray = [];
                               else
                                   resultArray = Ext.decode(result.responseText);
                               if (inOnSuccess)
                                   inOnSuccess.call(inScope, resultArray);


                           },
                   failure: function (result, request) {
                   }
               });
   } ,
    checkFile: function (inField) {
        var field = inField;
        var inputs = field.getElementsByTagName('input');
        var fileInput = null;
        var il = inputs.length;
        //取出input 类型为file的元素
        for(var i = 0; i < il; i ++){
            if(inputs[i].type == 'file'){
                fileInput = inputs[i];
                break;
            }
        }
        if(fileInput != null){
            var fileSize = this.getFileSize(fileInput);
            //允许上传不大于1M的文件

            if(fileSize > 5000){
                Ext.Msg.alert('提示','文件太大，请选择小于5M的文件！');
                field.setRawValue('');
            }
        }
    },
    //计算文件大小，返回文件大小值，单位K
    getFileSize:function(target){
        var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
        var fs = 0;
        if (isIE && !target.files) {
            var filePath = target.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file = fileSystem.GetFile (filePath);
            fs = file.Size;
        }else if(target.files && target.files.length > 0){
            fs = target.files[0].size;
        }else{
            fs = 0;
        }
        if(fs > 0){
            fs = fs / 1024;
        }
        return fs;
    }
    });
