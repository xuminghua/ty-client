/**
 * Created by sunxuan on 16-11-13.
 * Defination for model of PanelWorkspace MVC.
 */
Ext.define('UWTWorks.model.center.PanelTask', {
    extend: 'Ext.data.Model',
    requires: [
        'Ext.data.proxy.Ajax'
    ],
    fields: [
        {name: 'id', type: 'string'},
        {name: 'subxtype', type: 'string'},
        //{name: 'subfields', type: 'myfieldmap', defaultValue: []},
        {name: 'title', type: 'string'}

    ]
});