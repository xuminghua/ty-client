Ext.define('UWTWorks.store.TreeColumns', {
    extend: 'Ext.data.TreeStore',
    model: 'UWTWorks.model.TreeColumn',

    root: {
        expanded: true,
        id: -1,
        name: 'All Lists',
        text: 'all lists',
        rootVisible: false,
        children: UWTConfiguration.gettreeData()
    },

    //new methods
    /*
     invoked after Toolbar OP button clicked. Load tree data by OP id
     */
    loadOP: function (tabCategory) {
        console.log('load op: ' + tabCategory);
        var limites = {tabCategory: tabCategory};
        var limit = Ext.encode(limites);

        this.load({
            url: 'getDataServ',
            params: {
                para: limit,
                cMethod: 'getTreeColumnMapData',
                cName: 'ReadDataManager'
            }
        });
    }

});