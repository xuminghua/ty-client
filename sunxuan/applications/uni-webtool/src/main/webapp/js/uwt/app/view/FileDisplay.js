/**
 * @author sunxuan
 * @version 1.0 17-2-10
 */
Ext.define('UWTWorks.view.FileDisplay', {
    extend: 'Ext.form.field.Display',
    xtype: 'filedisplay',
    fieldSubTpl: '<b><a onclick=Windows.FileDisplayWin("{name}","{value}")  href="#">详细</a></b>',
    listeners: {

        change: function ( fileField, newValue, oldValue) {

        }
    }
});