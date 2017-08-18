/**
 * @class UWTWorks.view.TreeColumns
 * @extends Ext.tree.Panel
 * The task list view.  A tree that displays all of the columns lists.
 */
Ext.define('UWTWorks.view.TreeColumns', {
    extend: 'Ext.tree.Panel',
    xtype: 'treeMapType',
    store: 'TreeColumns',
    rootVisible: false,
    hideHeaders: true,
    listeners: {
        select: function (selModel, record, index, eOpts) {
            if (!record.data.leaf) {
                Windows.msg(dictionary.pub_infomation, dictionary.map_selectLeaf);
                return (false);
            }
            var columnID = record.data.columnId;
            UWTConfiguration.setCurrentColumn(columnID);
            var cController = UWTWorks.getApplication().getController('CtlViewManager');
            //var workspaceStore = pController.myStore;
            //workspaceStore.loadColumn(UWTConfiguration.currentOP, columnID);
            cController.loadCenterWorkspace(UWTConfiguration.getCurrentOP(),columnID)
        } ,
        load: function (store, records, successful) {
            if (!successful) {
                Windows.msg(dictionary.pub_infomation, dictionary.pub_fail);
                return (false);
            }
            UWTConfiguration.setCurrentColumn("");
            var cController = UWTWorks.getApplication().getController('CtlViewManager');
            cController.loadCenterWorkspace(UWTConfiguration.getCurrentOP(),"")

        }
    }


});