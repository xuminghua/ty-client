Ext.define('UWTWorks.model.TreeColumn', {
    extend: 'Ext.data.Model',
    requires: [
        'Ext.data.proxy.LocalStorage',
        'Ext.data.proxy.Ajax'
    ],
    fields: [
        {name: 'id', type: 'int'},
        {name: 'columnId'},
        {name: 'text', type: 'string'}

    ]
});