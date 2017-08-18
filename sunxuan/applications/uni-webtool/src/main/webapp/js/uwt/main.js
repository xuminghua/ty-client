Ext.onReady(function () {

    dictionary.init();
    //if (!Ext.supports.LocalStorage) {
    //    alert('Simple Tasks is configured to use HTML5 Local Storage, but your browser does not support Local Storage');
    //} else {

        var uwtApplication = Ext.application({
            name: 'UWTWorks',
            appFolder: 'js/uwt/app',
            controllers: ['CtlViewManager'],

            launch: function () {
               Ext.tip.QuickTipManager.init();
                Ext.Loader.setConfig({
                  enabled: true
                });

                var pHeight = (document.body.clientHeight > 185) ? (document.body.clientHeight - 185) : 185;
                //tree做完之后调整最小高度为tree的高度，自适应高度为正好的高度


                //create toolbar
                var tController = this.getController('CtlViewManager');
                var toolbar = tController.getToolbarNorth();
                // create the west tree panel
                var treeMap = tController.getTreeMap();
                // create the center panel
                var pWorkspace = tController.getPanelWorkspace();
                var mainP = Ext.create('Ext.panel.Panel', {
                    layout: 'border',
                    id:'main_panel',
                    items: [toolbar,
                        treeMap,
                        pWorkspace],

                    renderTo: 'mainpanel',
                    listeners:{'beforerender':function(view) {
                        Ext.get('loading-mask').fadeOut({remove:true});
                        Ext.get('loading').remove();
                    }},
//                    width: 540,
                    height: pHeight
                });

                //自适应浏览器大小改变
                Ext.EventManager.onWindowResize(function(){
                    Ext.getCmp('main_panel').doLayout();
                });

            }

        });


    //}
});
