Ext.define('UWTWorks.store.center.PanelWorkspace', {
    extend: 'Ext.data.Store',
    model: 'UWTWorks.model.center.PanelTask',
    proxy: {
        type: 'ajax',
        url: 'getData'
    },

    //new methods
    /*
     invoked after Toolbar OP button clicked. Load tree data by OP id
     */
    loadOP: function (operationID) {
        this.loadColumn(operationID, '')
    },
    loadColumn: function (tabCategoryId, columnId) {
        //console.log('load column: Category=' + tabCategoryId + ', Column=' + columnId);
        if (tabCategoryId == undefined)tabCategoryId = UWTConfiguration.getCurrentOP();
        var limites = {tabCategoryId: tabCategoryId, columnID: columnId};
        var limit = Ext.encode(limites);
        this.load({
            params: {
                para: limit,
                cMethod: 'getPanelWorkspaceData',
                cName: 'ReadDataManager'
            }
        });
    }
});