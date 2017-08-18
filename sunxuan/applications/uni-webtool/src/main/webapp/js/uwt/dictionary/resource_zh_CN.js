var Resource = function () {
    return {
        init: function () {
            this.pub_warn = "\u8b66\u544a";
        }
    };
}();

Ext.EventManager.onDocumentReady(Resource.init, Resource, true);
