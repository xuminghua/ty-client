/**
 * 当UWTConfiguration存储的data无法直接用于view时，需要调用本类进行data适配转换
 */
Ext.define('UWTWorks.lib.DataPrepareForView', {
    requires: [
        'UWTWorks.lib.MethodsRemote'
    ],
    singleton: true,
    /**
     *
     * @param myTabItems, tab的配置data
     * @param changeOP, 点击一个tab选项时要触发的function
     * @returns {Array} toolbar的items
     */
    getToolbarItems: function (myTabItems, changeOP) {
        var results = [];
        var strCurrentTab = myTabItems[0].id;
        for (var intTabs = 0; intTabs < myTabItems.length; intTabs++) {
            results.push({
                xtype: 'button',
                text: myTabItems[intTabs].title,
                id: myTabItems[intTabs].id,
                margin: '0 5 0 0',
                pressed: strCurrentTab == myTabItems[intTabs].id,
                iconCls: "cls-RD",
                toggleGroup: 'toolbarTabBtns',
                listeners: {
                    toggle: changeOP
                }
            });
            results.push('-');
        }
        results.push('->',{
            xtype: 'tbtext',
            text: dictionary.toolbar_name + ": " + UWTConfiguration.getUserName()
        },'-',{
            xtype: 'tbtext',
            text: "Handle: " + UWTConfiguration.getUserHandle()
        },'-',{
            xtype: 'tbtext',
            text: dictionary.toolbar_actor + ": " + UWTConfiguration.getActorName()
        },'-',{
            xtype: 'tbtext',
            text: dictionary.toolbar_superior + ": " + UWTConfiguration.getSuperiorName()
        },'-',{
            xtype: 'tbtext',
            text: dictionary.toolbar_department + ": " + UWTConfiguration.getDepartmentName()
        },'-',{
            text: dictionary.toolbar_logout,
            id: "btnLogout",
            margin: '0 5 0 0',
            iconCls: 'cls-RD',
            enableToggle: false,
            listeners: {
                click: function (btn) {
                    Ext.MessageBox.show({
                        title: dictionary.pub_confirm,
                        msg: dictionary.toolbar_logoutmsg,
                        buttons: Ext.MessageBox.YESNO,
                        fn: function (confirmbtn) {
                            if (confirmbtn == 'yes') {
                                window.location.href = UWTConfiguration.getCasServerLogoutUrl();
                            }
                        }
                    })


                }
            }
        }) ;
        return results;
    },
    /**
     * 后端传过来的data是单纯的field的配置array，本函数将其组装成4个field一组的fieldset，并添加上submit button
     *  一个field的配置数据为 {name:'FID1', fieldLabel: 'FID1'}即为textfield类型;
     *  若需其他类型的配置，直接按照ext语法添加即可
     *  @returns {*} searchArea panel的items
     */
    getSearchAreaItems: function (controller) {
        var result = [];
        var initSearchAreaData = UWTConfiguration.getSearchAreaData();
        if(! initSearchAreaData || ! initSearchAreaData.fieldsMap
            || ! initSearchAreaData.fieldsMap.fields
            || ! initSearchAreaData.fieldsMap.fields.length)return null;
        var initData = initSearchAreaData.fieldsMap.fields;
        var a = parseInt(initData.length/4);
        var b = initData.length % 4;
        for(var indx = 0; indx < a; indx ++){
            var itemidx = indx * 4;
            var item = {
                defaults:{
                    margin: '0 0 5 15',
                    minWidth:100,
                    xtype: 'textfield'
                },
                items:[initData[itemidx],initData[itemidx + 1],
                    initData[itemidx + 2],initData[itemidx + 3]]
            };
            result.push(item);
        }

        var finalItems = [], flexwidth = 4 - b ;
        if( b > 0 ){
            for(var idx = 0; idx < b; idx ++){
                finalItems.push( initData[a * 4 + idx] );
            }
        }
        //bottom button container 当field正好4的倍数时，buttons重启1行，否则就跟在最后1行
        finalItems.push({   xtype:'fieldcontainer',
            flex: flexwidth ,
            layout: {
                type: 'hbox',
                pack:'end'
            },
            defaults:{
                margin: '20 0 5 15',
                xtype: 'button'
            },
            items:[{
                text: dictionary.center_searchBtn,
                handler: function() {
                    var form = this.up('form').getForm();
                    if(form.isDirty() && form.isValid()){
                        var condition =
                            Ext.JSON.encode(this.up('form').getForm().getFieldValues(true));
                        var gridStore = controller.getResultGridStore();
                        var conditionObj = {column_id:UWTConfiguration.getCurrentColumn(),
                            condition:condition};
                        UWTConfiguration.setSearchCondition(conditionObj);
                        gridStore.load({
                            params : {condition: condition}});
                    }

                }} ,
                {
                    text: dictionary.put_reset,
                    handler: function() {
                        this.up('form').getForm().reset();
                    }
                }]
        });
        var finalfieldset = {
            defaults:{
                margin: '0 0 5 15',
                minWidth:100,
                xtype: 'textfield'
            },
            items:finalItems
        };
        result.push(finalfieldset);

        return result;

    } ,
    getResultGridColumn: function (disableDelete) {
        var result = [];
        var initData = UWTConfiguration.getResultGridData();
        if(! initData || ! initData.fieldsMap )return null;
        var columns = initData.fieldsMap.columns;
        if(! columns )return null;
        //var detailWorkcakeType = UWTConfiguration.getDetailWorkcakeType();
        var items = new Array();
        var detailBtnObj = {
                                        iconCls: 'cls-itemdetail',
                                        tooltip: dictionary.center_detail,
                                        handler: function(grid, rowIndex, colIndex) {
                                            var rec = grid.getStore().getAt(rowIndex);
                                            //if(detailWorkcakeType == "window"){
                                            Windows.getDetailInfoWin(rec);
                                            //}
                                            //Ext.Msg.alert('Sell', 'Sell ' + rec.get('company'));
                                        }
                                    };
        var deleteBtnObj =  {
                                iconCls: 'cls-itemdelete',
                                //hidden: disableDelete,
                                tooltip: dictionary.pub_delete,
                                handler: function(grid, rowIndex, colIndex) {
                                    var columnId = UWTConfiguration.getCurrentColumn();
                                    var panelcakeId = UWTConfiguration.getInfoFormData().id;
                                    var rec = grid.getStore().getAt(rowIndex);

                                    Ext.Msg.confirm(dictionary.pub_warn, dictionary.win_deleteconfirm,function(btn){
                                        if (btn == 'yes') {
                                            //controller.deleteRec(rec);
                                            UWTWorks.lib.MethodsRemote.invokeX( columnId,panelcakeId,
                                                "delete", {id:rec.get("id")},function(result){
                                                    if(result && result.success){
                                                        Ext.bubblebox.msg(dictionary.pub_success,result.msg);
                                                        if(UWTWorks && UWTWorks.getApplication() &&
                                                            UWTWorks.getApplication().getController('CtlViewManager')){
                                                            var controller = UWTWorks.getApplication().getController('CtlViewManager');
                                                            var gridStore = controller.getResultGridStore();
                                                            var conditionObj = UWTConfiguration.getSearchCondition();
                                                            if(conditionObj &&
                                                                conditionObj.column_id == UWTConfiguration.getCurrentColumn()){
                                                                gridStore.load({
                                                                    params : {condition: conditionObj.condition}});
                                                            }else{
                                                                gridStore.load();
                                                            }

                                                        }
                                                    }else{
                                                        Windows.alert(dictionary.pub_fail,result.msg);
                                                    }
                                                });
                                        }
                                    });
                                    //Ext.Msg.alert('Sell', 'Sell ' + rec.get('company'));
                                }
                            };
        var historyBtnObj = {
                            iconCls: 'cls-itemhistory',
                            tooltip: dictionary.center_history,
                            //visible:false,
                            handler: function(grid, rowIndex, colIndex) {
                                var rec = grid.getStore().getAt(rowIndex);
                                //Ext.Msg.alert('Sell', 'Sell ' + rec.get('company'));
                                Windows.getHistoryWin(rec);
                            }
                        };
        items.push(detailBtnObj);
        if(!disableDelete)items.push(deleteBtnObj);
        result = columns.concat({
            text: dictionary.center_detailOp,
            menuDisabled: true,
            dataIndex :"deleteRec",   //每个data rec中包括1个deleteRec属性
            renderer: function (value, meta, record) {
                //判断是否置灰delete button
            },
            sortable: false,
            altText: dictionary.center_detail,
            xtype: 'actioncolumn',
            items: items
        });
        return result;
    } ,
    /**
     * 创建field来展示详细信息，每次更换显示内容时会被重建
     * @param inPanel, 父panel
     * @param bbar
     * @param InfoFormData
     */
    setFieldForInfoForm: function(inPanel, bbar, InfoFormData, isWin){
        //获取field的配置数据并校验有效性
        if (!InfoFormData) return false;
        var fieldsObject = InfoFormData.fieldsMap;
        if (!fieldsObject) return false;
        if(!fieldsObject.fields)return false;

        //设置field读写属性
        var defaultConf,fieldDefaultsConf;
        if(InfoFormData.readOnly)
            defaultConf = {xtype:'displayfield',
                cls : ".cls-readOnly",
                //dirtyCls: ".cls-readOnly",
                readOnly:true,
                hideTrigger:true   //这3行都是readonly的配置;如果可write，只需要xtype:'textfield'
            };
        else
            defaultConf = {xtype:'textfield',editable:false};
        fieldDefaultsConf = {
            labelWidth: UWTConfiguration.getFieldLabelWidth(),
            anchor: '100%'
        } ;
        inPanel.removeAll(true);
        if(InfoFormData.layout == 'hbox'){//竖向分列布局。每个fielditems是1列
            for (x in fieldsObject.fields)
            {
                var fielditems = fieldsObject.fields[x];
                this.formatItem(fielditems.items);
                inPanel.add({xtype:'fieldset',
                    title:fielditems.title,
                    margin:5,
                    fieldDefaults: fieldDefaultsConf,
                    defaults:defaultConf,
                    items:fielditems.items});
            }
        }else{                           //横向分行布局.每个fielditems是1行
            for (x in fieldsObject.fields)
            {
                var fielditems = fieldsObject.fields[x];
                this.formatItem(fielditems.items);
                var fieldcontainer = {
                    xtype:'fieldcontainer',
                    title:fielditems.title,
                    defaults:defaultConf,
                    fieldDefaults: fieldDefaultsConf,
                    layout:'hbox',
                    items:fielditems.items
                };
                inPanel.add(fieldcontainer);
                inPanel.setTitle(InfoFormData.title);
            }
        }
        if(!isWin)this.setInfoFormBtn(inPanel,bbar,InfoFormData);
        else {
            if( inPanel.rendered ) {
                this.setInfoFormWinBtn(inPanel,bbar,InfoFormData);
            }else{
                inPanel.on('afterrender', function (panel) {
                    UWTWorks.lib.DataPrepareForView.setInfoFormWinBtn(
                        inPanel,bbar,InfoFormData);
                })
            }
        }
        return true;
    },
    setInfoFormBtn: function (inPanel,bbar,InfoFormData) {
        //开始设置bbar上的button
        for(index in bbar.items.items){
            if (index == 0)continue;
            var btn = bbar.items.items[index];
            if( !!btn && btn.isVisible())
                btn.setVisible(false);
        }
        if(!InfoFormData.operationType)return true;

        var columnId = UWTConfiguration.getCurrentColumn();
        var panelcakeId = UWTConfiguration.getInfoFormData().id;
        var actionX = InfoFormData.operationType;
        var operationBtn;
        switch (InfoFormData.operationType){
            case "create":
                //set resetbtn
                var resetbtn = bbar.getChildByElement("infoform_reset_btn");
                if(resetbtn)resetbtn.setVisible(true);
                //set operationBtn
                operationBtn = bbar.getChildByElement("infoform_create_btn");
                break;
            case "update":
                operationBtn = bbar.getChildByElement("infoform_update_btn");
                break;

        }
        if(operationBtn){
            operationBtn.setVisible(true);
            operationBtn.setHandler(function(btn,e){
                var form = this.up('form').getForm();

                if(!form.isValid() || !form.isDirty()){
                    Windows.alert(dictionary.pub_warn,dictionary.center_warnFieldError);
                }else{
                    form.submit({
                        url: 'actionX',
                        waitMsg : dictionary.center_submit_waitmsg,
                        params: {
                            columnId:columnId,
                            panelcakeId: panelcakeId,
                            action:actionX
                        },
                        getParams: function(useModelValues) {
                            var falseVal = false;
                            var fieldParams = this.form.getValues(falseVal, true, this.submitEmptyText !== falseVal, useModelValues,  true);
                            return Ext.apply(this.params, fieldParams);
                        },
                        success: function(form, action) {
                            Ext.bubblebox.msg(dictionary.pub_success,action.result.msg);
                            if(UWTWorks && UWTWorks.getApplication() &&
                                UWTWorks.getApplication().getController('CtlViewManager')){
                                var controller = UWTWorks.getApplication().getController('CtlViewManager');
                                var gridStore = controller.getResultGridStore();
                                var conditionObj = UWTConfiguration.getSearchCondition();
                                if(conditionObj &&
                                    conditionObj.column_id == UWTConfiguration.getCurrentColumn()){
                                    gridStore.load({
                                        params : {condition: conditionObj.condition}});
                                }else{
                                    gridStore.load();
                                }                            }
                        },
                        failure: function(form, action) {
                            Windows.msg(dictionary.pub_fail, action.result.msg);

                        }
                    })
                }
            });
        }

    },

    setInfoFormWinBtn: function (inPanel,bbar,InfoFormData) {
        //开始设置bbar上的button
        for(index in bbar.items.items){
            if (index == 0)continue;
            var btn = bbar.items.items[index];
            if( !!btn && btn.isVisible())
                btn.setVisible(false);
        }
        if(!InfoFormData.operationType)return true;
        var closeBtn = bbar.getChildByElement("win_close_btn");
        if(closeBtn)closeBtn.setVisible(true);

        var columnId = UWTConfiguration.getCurrentColumn();
        var panelcakeId = UWTConfiguration.getInfoFormData().id;
        var actionX = InfoFormData.operationType;
        var operationBtn;
        switch (InfoFormData.operationType){
            case "create":
                //set resetbtn
                var resetbtn = bbar.getChildByElement("infoform_reset_btn");
                if(resetbtn)resetbtn.setVisible(true);
                //set operationBtn
                operationBtn = bbar.getChildByElement("infoform_create_btn");
                break;
            case "update":
                operationBtn =  bbar.getChildByElement("win_submit_btn");
                var disableUpdate = InfoFormData.readOnly ||
                    (InfoFormData.operationType == "update" && !UWTConfiguration.isEnableUpdateRec());
                if(disableUpdate){
                    operationBtn.setVisible(false);
                    return;
                }
                break;
        }
        if(operationBtn){
            operationBtn.setVisible(true);
            operationBtn.setHandler(function(btn,e){
                var form = this.up('form').getForm();
                var rec = form.getRecord( );
                var id = null,submitPara,conditionPara;
                if(rec) {
                    id = rec.get('id');
                }
                if(id){
                    submitPara = {
                        columnId:columnId,
                        panelcakeId: panelcakeId,
                        action:actionX,
                        id:id
                    };
                }else{
                    submitPara = {
                        columnId:columnId,
                        panelcakeId: panelcakeId,
                        action:actionX};
                }
                if(!form.isValid() || !form.isDirty()){
                    Windows.alert(dictionary.pub_warn,dictionary.center_warnFieldError);
                }else{
                    form.submit({
                        url: 'actionX',
                        params: submitPara,
                        waitMsg : dictionary.center_submit_waitmsg,
                        getParams: function(useModelValues) {
                            var falseVal = false;
                            var fieldParams = this.form.getValues(falseVal, true, this.submitEmptyText !== falseVal, useModelValues,  true);
                            return Ext.apply(this.params, fieldParams);
                        },
                        success: function(form, action) {
                            Ext.bubblebox.msg(dictionary.pub_success,action.result.msg);
                            if(UWTWorks && UWTWorks.getApplication() &&
                                UWTWorks.getApplication().getController('CtlViewManager')){
                                var controller = UWTWorks.getApplication().getController('CtlViewManager');
                                var gridStore = controller.getResultGridStore();
                                var conditionObj = UWTConfiguration.getSearchCondition();
                                if(conditionObj &&
                                    conditionObj.column_id == UWTConfiguration.getCurrentColumn()){
                                    gridStore.load({
                                        params : {condition: conditionObj.condition}});
                                }else{
                                    gridStore.load();
                                }                            }

                        },
                        failure: function(form, action) {
                            if(!action || !action.result){
                                Windows.msg(dictionary.pub_fail, dictionary.pub_fail_msg);
                            }else
                                Windows.msg(dictionary.pub_fail, action.result.msg);

                        }
                    })
                }
            });
        }


    },
    getFormRec: function () {
        var InfoFormData= UWTConfiguration.getInfoFormData();
        if (!InfoFormData) return null;
        var fieldsObject = InfoFormData.fieldsMap;
        if (!fieldsObject) return null;
        if(!fieldsObject.data)return null;
        return fieldsObject.data;

    } ,
    disableCreateRec: function () {
        return true;
    },
    disableUpdateRec: function () {
        return true;

    },
    disableDeleteRec: function () {
        return true;

    },
    formatItem: function (items) {
        if(!items || items.length == 0)return;
        for(var n in items){
            var item = items[n];
            if(item.notNull){
                item.afterLabelTextTpl= UWTConfiguration.getRequiredTpl();
                item.allowBlank = false;
            }
            if(item.xtype == "numberfield"){
                item.hideTrigger = true;
                item.editable = true;
                item.nanText=dictionary.center_field_numbertext;
            }
            if(item.xtype == "combobox"){
                item.listeners={
                    change: function (field,newValue,oldvalue) {
                        if(field.findRecordByDisplay(newValue))return;
                        var newmodel = field.findRecordByValue(newValue);
                        //store.select(newmodel);
                        field.setValue(newmodel);
                    }
                }
            }
            //else if(item.xtype == "datefield" || item.xtype == "combobox" ){
            //    item.editable = false;
            //}
        }
    }
});