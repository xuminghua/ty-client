Ext.define('UWTWorks.controller.CtlViewManager', {
    extend: 'Ext.app.Controller',

    models: ['TreeColumn'],
    stores: ['TreeColumns'],

    views: [
        'TreeColumns','FileDisplay'
    ],
    requires: [
        'UWTWorks.lib.DataPrepareForView',
        'UWTWorks.lib.MethodsRemote'
    ],
    //save my params for fast use, create only once after init page
    myStore: null,             //store for west tree
    myTreepanel: null,         //west tree view
    myWorkspace: null,         //center panal
    mySearchArea: null,        //search condition panel
    myResultGrid:null,         //grid panel
    myAddNewRecBtn:null,
    myInfoForm:null,           //fieldset panel for detail infomation
    myHTMLArea:null,           //html panel for other show
    myGridTbar:null,            //grid panel tbar
    infoFormBbar:null,
    myLoadMask:null,
    //overide exist methods
    init: function () {
        this.myStore = this.getTreeColumnsStore();
        this.control({
            'treeMapType': {
                //afterrender: this.handleAfterListTreeRender,
                //selectionchange: this.filterTaskPanels
            }
        });
        //for west tree view
        if (this.myStore.isLoading()) {
            this.myStore.on('load', this.handleTreeLoad, this);
        } else {
            this.handleTreeLoad(this.myStore);
        }


    },
    //new methods
    //create north toolbar used in viewport, before viewport created.
    getToolbarNorth: function () {
        //data prepare
        var myTabItems = UWTConfiguration.getMyTabItems();
        UWTConfiguration.setCurrentOP(myTabItems[0].id);
        //tab change function definition
        var changeOP = function (btn, pressed) {
            if (pressed) {
                var newOP = btn.id;
                if (UWTConfiguration.getCurrentOP() == newOP) {
                    return (false);
                }
                UWTConfiguration.setCurrentOP(newOP);
                var controller = UWTWorks.getApplication().getController('CtlViewManager');
                var treeStore = controller.myStore;
                //var workspaceStore = UWTWorks.getApplication().getController('PanelWorkspace').myStore;
                //var panelWestTreemap = Ext.getCmp('west-region-container') ;
                //var nodeRoot = panelWestTreemap.getRootNode();
                //nodeRoot.removeAll();
                //nodeRoot.appendChild(UWTConfiguration.gettreeData(treeRootId));
                treeStore.loadOP(newOP);
                //[ {
                //            defaults:{
                //                margin: '0 0 5 15',
                //                minWidth:100,
                //                xtype: 'textfield'
                //            },
                //
                //            items:[
                //                {
                //                    name:'FID',//submit parmater name
                //                    fieldLabel: 'FID'
                //                },
                //                {
                //                    name:'FE_Handle',
                //                    fieldLabel: 'FE Handle'
                //                },
                //                {
                //                    name:'Manager_Handle',
                //                    fieldLabel: 'Manager Handle'
                //                },
                //                {
                //                    name:'Feature_Status',
                //                    fieldLabel: 'Feature状态' ,
                //                    store:["a","b"],
                //                    xtype:'combobox'
                //                }]
                //        }];
                //controller.reConfigSearchArea();
                //controller.reConfigInfoForm();
                //{FID:'1',F_Title:'测试项目1',
                // F_Status:'UT',F_Dev_MGER:'sun',F_PE_MGER:'sun',Producters:'sun, liu'}
                //workspaceStore.loadOP(newOP);
            } else {
            }
        };
        var toolbar = Ext.widget({
            xtype: 'toolbar',
            defaultType: 'button',
            region: 'north',     // position for region
            margins: '0 5 5 5'
        });
        var items = UWTWorks.lib.DataPrepareForView.getToolbarItems(myTabItems, changeOP);
        toolbar.add(items);
        return toolbar;
    },
    getTreeMap: function () {
        if (this.myTreepanel == undefined || this.myTreepanel == {} || !this.myTreepanel.isPanel) {
            this.myTreepanel = Ext.widget({
                title: dictionary.map_title,
                region: 'west',
                xtype: 'treeMapType',
                margins: '5 0 0 5',
                split: true,         // enable resizing
                width: 200,
                height: 700,
                collapsible: true,   // make collapsible
                id: 'west-region-container',
                layout: 'fit'
            });
        }
        return this.myTreepanel;
    },
    getPanelWorkspace: function () {
        if (!this.myWorkspace ) {
            //create panel
            this.myWorkspace = Ext.widget({
                region: 'center',     // center region is required, no width/height specified
                xtype: 'panel',
                autoScroll: true,   //加了autoscroll之后，mySearchArea的field(flex属性)只能自适应变宽无法自适应变窄，因为变窄事件由滚动条响应了。
                id: 'center-workspace' ,
                bodyPadding: 5
                //layout: {
                //    type: 'vbox',
                //    padding: '5',
                //    align: 'stretch'
                //}
                //items: UWTConfiguration.getPanelWorkspace(),
                //margins: '5 5 0 0'
            });
            this.myLoadMask =  new Ext.LoadMask(this.myWorkspace, {
                msg : dictionary.center_load_waitmsg
                //removeMask : true// 完成后移除
            });
            this.getSearchArea();
            this.getResultGridArea();
            this.getInfoFormArea();
            this.getHTMLArea();
            Windows.getDetailWin();
            this.myWorkspace.add(this.mySearchArea,this.myResultGrid, this.myInfoForm,this.myHTMLArea);
        }else{
            this.myLoadMask .show();
            //reload panel
            if(!UWTConfiguration.getResultGridData() || !UWTConfiguration.getResultGridData().fieldsMap){
                this.myResultGrid.setVisible(false);
            }else {
                this.getResultGridArea();
                this.myResultGrid.setVisible(true);
            }
            if(!UWTConfiguration.getInfoFormData() || !UWTConfiguration.getInfoFormData().fieldsMap){
                this.myInfoForm.setVisible(false);
            }else {
                this.getInfoFormArea();
                this.myInfoForm.setVisible(true);
            }
            if(!UWTConfiguration.getSearchAreaData() || !UWTConfiguration.getSearchAreaData().fieldsMap){
                this.mySearchArea.setVisible(false);
            }else {
                this.getSearchArea();
                this.mySearchArea.setVisible(true);
            }
            if(!UWTConfiguration.getHTMLAreaData()){
                this.myHTMLArea.setVisible(false);
            }else {
                this.getHTMLArea();
                this.myHTMLArea.setVisible(true);
            }
        }

        if(this.myLoadMask.isVisible( ) )this.myLoadMask.hide();
        return this.myWorkspace;
    },

    getSearchArea: function(){
        var grid_title,hidePanel;
        var initSearchAreaData = UWTConfiguration.getSearchAreaData();
        var subfields = UWTWorks.lib.DataPrepareForView.getSearchAreaItems(this);
        //若panel不包括SearchAreaPanel，则生成1个最简单的grid例子，再hide
        if (!initSearchAreaData || !subfields ) {
            subfields = [{name:'FID',fieldLabel: 'Feature ID'}];
            grid_title = dictionary.center_searchTitle;
            hidePanel = true;
        }else{
            grid_title = initSearchAreaData.title;
        }

        if(!this.mySearchArea){
            this.mySearchFields = Ext.widget({
                xtype: 'fieldset',
                id: 'search-fieldset',
                //margins: '5 5 0 0',
                //layout: 'hbox',
                //layout: 'anchor',
                fieldDefaults: {
                    labelWidth: UWTConfiguration.getFieldLabelWidth(),
                    labelAlign: 'top',
                    flex: 1
                },
                defaults:
                {xtype:'fieldcontainer',
                    layout: 'hbox'}

            });
            this.mySearchArea = Ext.widget({
                xtype: 'form',
                id: 'search-form',
                border:false,
                hidden: hidePanel,
                //margins: '5 5 0 0',
                //layout: 'hbox',
                //layout: 'anchor',
                items:this.mySearchFields

            });
            this.mySearchFields.add(subfields);
            this.mySearchArea.add(this.mySearchFields);
        }else{
            this.mySearchFields.removeAll();
            this.mySearchFields.add(subfields);
        }
        this.mySearchArea.setTitle(grid_title);

        return this.mySearchArea;
    },
    getResultGridStore: function () {
        if(! this.myResultGrid)return;
        return this.myResultGrid.getStore( );
    },
    getResultGridArea: function () {
        var resultGridData = UWTConfiguration.getResultGridData();
        var columns,storeFields,data,hidePanel,disableCreate,disableDelete,grid_title = "";
        //若panel不包括gridPanel，则生成1个最简单的grid例子，再hide
        if(!resultGridData || !resultGridData.fieldsMap) {
            columns = [{text:'FID',dataIndex: 'FID'}];
            storeFields = [{"name": "FID"}];
            data = [];
            hidePanel = true;
        }else{
            disableDelete = resultGridData.readOnly || !UWTConfiguration.isEnableDeleteRec();
            columns = UWTWorks.lib.DataPrepareForView.getResultGridColumn(disableDelete);
            storeFields = resultGridData.fieldsMap.fields;
            data = resultGridData.fieldsMap.data ? resultGridData.fieldsMap.data : [];

            if(!columns || !storeFields){
                columns = [{text:'FID',dataIndex: 'FID'}];
                storeFields = [{"name": "FID"}];
                hidePanel = true;
            }
            grid_title = resultGridData.title;
        }
        var columnId = (UWTConfiguration.getCurrentColumn()== null ? "":UWTConfiguration.getCurrentColumn());
        var panalID = ((resultGridData == null || resultGridData.id == null) ? "":resultGridData.id);
        var store = this.createGridStore(storeFields,data,columnId,panalID);
        if(!this.myGridTbar)
            this.myGridTbar = this.createPagingbar(store);

        this.myGridTbar.getComponent("refresh")
            .setVisible(false);
        if (! this.myResultGrid){
            this.myResultGrid = Ext.widget({
                xtype: 'grid',
                autoScroll: true,
                padding : '15 0 0 0',
                //height: 600,
                //width: 600,
                columns: columns,
                hidden: hidePanel,
                title:grid_title,
                id: 'result-grid1',
                tools:[{
                    type:'refresh',
                    tooltip: dictionary.pub_refresh,
                    // hidden:true,
                    handler: function(event, toolEl, panel){
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
                        }}
                }],
                minWidth :1000, //最小宽度为最大column个数10 * 100
                border:true,
                bbar:this.myGridTbar,
                store:store
            });

        }else{
            this.myResultGrid.setTitle(grid_title);
            this.myResultGrid.reconfigure(store,columns);
            this.myGridTbar.bindStore(store);
        }
        return this.myResultGrid;
    },
    createPagingbar: function (store) {
        return Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            displayMsg: 'Displaying topics {0} - {1} of {2}',
            emptyMsg: "No topics to display"
        });
    } ,
    createGridStore: function(inputFields,inputData, columnId, panelcakeId){
        //var data = [],
        //    i = 0,
        //    usedNames = {},
        //    usedCities = {};
        //
        //for (; i < 7; ++i) {
        //    data.push({
        //        city: this.getUniqueCity(usedCities),
        //        manager: this.getUniqueName(usedNames).join(' '),
        //        totalEmployees: Ext.Number.randomInt(10, 25)
        //    });
        //}
        if(columnId == "" || panelcakeId == ""){
            return new Ext.data.Store({
                fields: inputFields  ,
                data: inputData
            });
        }else{
            return new Ext.data.Store({
                pageSize: 10,
                fields: inputFields  ,
                autoLoad :true,
                proxy: {
                    type: 'ajax',
                    url: 'actionX',
                    reader: {
                        root: 'items',
                        totalProperty: 'totalCount'
                    },
                    //reader:'json',
                    extraParams: {
                        columnId:columnId,
                        panelcakeId: panelcakeId,
                        action:"search"

                    }
                }
            });
        }
    },

    getInfoFormArea: function(){
        if (! this.myInfoForm ){
            var hidePanel;
            if(!UWTConfiguration.getInfoFormData() )hidePanel = true;
            this.infoFormBbar = Ext.create('Ext.toolbar.Toolbar', {
                items:['->',
                    {
                        text: dictionary.put_reset,
                        id:'infoform_reset_btn',
                        handler: function() {
                            this.up('form').getForm().reset();
                        }
                    },
                    {
                        text: dictionary.center_update,
                        id:'infoform_update_btn'
                    },
                    {
                        text: dictionary.center_add,
                        id:'infoform_create_btn'
                    }
                    //,{  text: "upload",
                    //handler: function() {
                    //    var form = this.up('form').getForm();
                    //    if(form.isValid()){
                    //        form.submit({
                    //            //url: 'file-upload.php',
                    //            waitMsg: dictionary.center_uploadFile1,
                    //            success: function(fp, o) {
                    //                Windows.msg(dictionary.pub_infomation, o.result.file + dictionary.center_uploadFile2 );
                    //            }
                    //                //msg('Success', tpl.apply(o.result));
                    //            });
                    //        };
                    //    }
                    //}
                ]
            });
            this.myInfoForm = Ext.widget({
                xtype: 'form',
                autoScroll: true,
                //height: 600,
                //width: 600
                border:true,
                id: 'result-form',
                padding : '15 0 0 0',
                trackResetOnLoad: true,
                hidden: hidePanel,
                layout: {
                    type: 'hbox',
                    padding: '5',
                    align: 'stretch'
                } ,
                bbar:this.infoFormBbar,
                defaults:{
                    flex:1
                }
            });
        }
        //set panel title
        var infoFormData = UWTConfiguration.getInfoFormData();
        var form_title = "";
        if(infoFormData && infoFormData.title)
            form_title = infoFormData.title;
        this.myInfoForm.setTitle(form_title);
        //get info form fields dynamicly
        if(UWTConfiguration.getInfoFormData() )
            UWTWorks.lib.DataPrepareForView.setFieldForInfoForm(this.myInfoForm,
                this.infoFormBbar,UWTConfiguration.getInfoFormData(),false);
        //get info form data dynamicly
        var rec = UWTWorks.lib.DataPrepareForView.getFormRec();
        if(!rec)
            this.myInfoForm.getForm().setValues(rec);
        return this.myInfoForm;
    },

    getHTMLArea:function(){
        var htmldata = UWTConfiguration.getHTMLAreaData();
        var hidePanel = !htmldata;
        var html_title = htmldata.title ? htmldata.title : "";
        if (! this.myHTMLArea){
            this.myHTMLArea = Ext.widget({
                xtype: 'panel',
                border:false,
                padding : '15 0 0 0',
                autoScroll: true,
                hidden: hidePanel,
                id: 'result-panel'

            });
        }else{
            this.myHTMLArea.removeAll();
        }
        this.myHTMLArea.setTitle(html_title);
        this.myHTMLArea.add({html:htmldata});
        return this.myHTMLArea;
    },
    loadPanelWorkspaceData: function () {
        if(this.myResultGrid.isVisible()){
            //load grid data
        }
        if(this.myInfoForm.isVisible()){
            var rec = UWTWorks.lib.DataPrepareForView.getFormRec();
            if(!rec)
                this.myInfoForm.getForm().setValues(rec);
        }
    },
    loadInfoFormPanelData: function (rec) {
        if(this.myInfoForm.isVisible() && rec){
            this.myInfoForm.getForm().loadRecord(rec);
        }
    },
    loadGridStroe: function () {
        this.myStore
    } ,
    testHide:function(){
        this.myInfoForm.setVisible(false);
        this.mySearchArea.setVisible(false);
        this.myResultGrid.setVisible(true);
        this.myHTMLArea.setVisible(true);
    },
    getAddNewWin: function () {
        Windows.getAddNewWin(this);
    } ,
    loadCenterWorkspace: function (OperationId, ColumnId){
        UWTWorks.lib.MethodsRemote.getCenterWorkspace(OperationId, ColumnId,function(result){
            UWTConfiguration.setPanelWorkspace(result)  ;
            var workspace = this.getPanelWorkspace();


        },this);
    },

    handleTreeLoad: function (treeStore, success, operation) {
        if (this.myStore == {}) {
            this.myStore = this.getTreeColumnsStore();
        }


    }

});
