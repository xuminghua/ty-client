var Resource = function () {
    return {
        init: function () {
            this.pub_wait = "\u6b63\u5728\u52a0\u8f09, \u8acb\u7a0d\u5019...";
            this.pub_submit = "\u63d0\u4ea4";
            this.pub_resubmit = "\u91cd\u65b0\u63d0\u4ea4";
            this.pub_close = "\u95dc\u9589";
            this.pub_basic = "\u57fa\u672c\u4fe1\u606f";
            this.pub_detail = "\u8a73\u7d30\u4fe1\u606f";
            this.pub_no = "\u5e8f\u865f";
            this.pub_warn = "\u8b66\u544a";
            this.pub_fresh = "\u5237\u65b0";
            this.pub_add = "\u6dfb\u52a0";
            this.pub_modify = "\u4fee\u6539";
            this.pub_delete = "\u522a\u9664";
            this.pub_remove = "\u79fb\u53bb";
            this.pub_back = "\u8fd4\u56de";
            this.pub_export = "\u5c0e\u51fa";
            this.pub_confirm = "\u786e\u8a8d";
            this.pub_confirm_operate = "\u786e\u8a8d\u64cd\u4f5c";
            this.pub_startdate = "\u958b\u59cb\u65e5\u671f";
            this.pub_enddate = "\u622a\u6b62\u65e5\u671f";
            this.pub_metadata = "\u7de8\u76ee\u4fe1\u606f";
            this.pub_keyframe = "\u95dc\u9375\u5e40";
            this.pub_fileinfo = "\u6587\u4ef6\u4fe1\u606f";
            this.pub_related = "\u76f8\u95dc\u7684";
            this.pub_specialsequence = "\u554f\u984c\u7247\u6bb5";
            this.pub_tapeinfo = "\u5eab\u623f\u4fe1\u606f";
            this.pub_dyacinfo = "\u6b78\u6a94\u4fe1\u606f";
            this.pub_other = "\u5176\u4ed6";
            this.pub_tip = "\u63d0\u793a\u4fe1\u606f";
            this.pub_type = "\u985e\u578b";
            this.pub_clear = "\u6e05\u7a7a";
            this.pub_save = "\u4fdd\u5b58";
            this.pub_quickview = "\u5feb\u901f\u700f\u89bd\u5340";
            this.pub_reference = "\u5f15\u7528\u7387"
            this.pub_items = "\u689d";
            this.pub_the = "\u7b2c";
            this.pub_total = "\u5171";
            this.pub_click = "\u8acb\u9ede\u64ca";
            this.pub_here = "\u9019\u91cc";
            this.pub_records = "\u8a18\u9304";
            this.pub_select = "\u8acb\u9078\u64c7...";
            this.pub_hello = "\u60a8\u597d";
            this.pub_counts = "\u6b21";
            this.pub_project = "\u9805\u76ee";
            this.pub_new = "\u65b0\u5efa";
            this.pub_open = "\u6253\u958b";

            this.logic_and = "\u4e0e";
            this.logic_or = "\u6216";
            this.logic_equal = "\u7b49\u4e8e";
            this.logic_notequal = "\u4e0d\u7b49\u4e8e";
            this.logic_like = "\u76f8\u4f3c\u4e8e";
            this.logic_include = "\u5305\u542b";
            this.logic_larger = "\u5927\u4e8e";
            this.logic_smaller = "\u5c0f\u4e8e";
            this.logic_between = "\u4ecb\u4e8e";
            this.logic_to = "\u4ecb\u4e8e";

            this.orderby = "\u6392\u5e8f";
            this.orderby_created_desc = "\u5275\u5efa\u6642\u9593\u964d\u5e8f";
            this.orderby_created_esc = "\u5275\u5efa\u6642\u9593\u5347\u5e8f";
            this.orderby_hits = "\u67e5\u770b\u7387";
            this.orderby_downloads = "\u5f15\u7528\u7387";

            this.basicinfo_name = "\u540d\u7a31";
            this.basicinfo_inpoint = "\u5165\u9ede";
            this.basicinfo_outpoint = "\u51fa\u9ede";
            this.basicinfo_duration = "\u6642\u9577";
            this.basicinfo_created = "\u5275\u5efa\u6642\u9593";
            this.basicinfo_description = "\u63cf\u8ff0";
            this.basicinfo_hits = "\u9ede\u64ca\u7387";
            this.basicinfo_downloads = "\u5f15\u7528\u7387";
            this.basicinfo_filestatus = "\u6587\u4ef6\u72c0\u614b";
            this.basicinfo_lastmodify = "\u6700\u65b0\u4fee\u6539\u6642\u9593";

            this.tags_type_usual = "\u5e38\u7528\u6a19\u7c3d";
            this.tags_type_hot = "\u71b1\u9580\u6a19\u7c3d";

            this.fileinfo_name = "\u6587\u4ef6\u540d\u7a31";
            this.fileinfo_type = "\u985e\u578b";
            this.fileinfo_size = "\u5927\u5c0f";
            this.fileinfo_status = "\u72c0\u614b";
            this.fileinfo_created = "\u5275\u5efa\u6642\u9593";
            this.fileinfo_get = "\u6b63\u5728\u7372\u53d6\u6587\u4ef6\u4fe1\u606f, \u8acb\u7a0d\u5019..";

            this.query_select_cc = "\u8acb\u9078\u64c7\u7de8\u76ee\u985e...";
            this.query_use_homophone = "\u540c\u97f3\u5b57";
            this.query_fulltext = "\u5168\u6587\u6aa2\u7d22";
            this.query_results = "\u6aa2\u7d22\u7d50\u679c";
            this.query_match = "\u7b26\u5408";
            this.query_result_counts = "\u7684\u7d50\u679c\u5171\u6709";
            this.query_usetime = "\u689d   \u7528\u6642";
            this.query_seconds = "\u79d2";
            this.query_research = "\u91cd\u65b0\u641c\u7d22";
            this.query_search_in_results = "\u5728\u7d50\u679c\u4e2d\u641c\u7d22";
            this.query_export_tip = "\u60a8\u53ef\u4ee5\u5c07\u6aa2\u7d22\u7d50\u679c\u5c0e\u51fa\u6210excel\u6587\u4ef6\uff0c\u4fdd\u5b58\u5230\u672c\u5730";
            this.query_orderby_tip = "\u60a8\u53ef\u4ee5\u6309\u7167\u5275\u5efa\u6642\u9593\u3001\u9ede\u64ca\u7387\u6216\u4e0b\u8f09\u7387\uff0c\u5c0d\u6aa2\u7d22\u7d50\u679c\u9032\u884c\u6392\u5e8f";
            this.query_current_results = "\u7576\u524d\u7d50\u679c\u96c6";
            this.query_structure_attribute = "\u5c6c\u6027";
            this.query_structure_attributegroup = "\u5c6c\u6027\u7d44";
            this.query_type_tag = "\u6a19\u7c3d\u6aa2\u7d22";
            this.query_tag_hot = "\u71b1\u9ede\u6a19\u7c3d";
            this.query_tag_no = "\u7cfb\u7d71\u4e2d\u6c92\u6709\u6a19\u7c3d!";
            this.query_more_condition = "\u66f4\u591a\u6aa2\u7d22\u689d\u4ef6";
            this.query_simple_condition = "\u7c21\u5316\u6aa2\u7d22\u689d\u4ef6";

            this.top10_latest = "\u6700\u65b0Top10";
            this.top10_latest_download = "\u6700\u8fd1\u4e0b\u8f09Top10";
            this.top10_hits = "\u9ede\u64ca\u7387Top10";
            this.top10_downloads = "\u5f15\u7528\u7387Top10";
            this.top10_my_latest_views = "\u6211\u6700\u8fd1\u700f\u89bdTop10";
            this.top10_my_latest_downloads = "\u6211\u6700\u8fd1\u4e0b\u8f09Top10";

            this.downloadtask = "\u4efb\u52d9";
            this.downloadtask_name = "\u4efb\u52d9\u540d\u7a31";
            this.downloadtask_inpoint = "\u5165\u9ede";
            this.downloadtask_duration = "\u6642\u9577";
            this.downloadtask_target = "\u4e0b\u8f09\u76ee\u6a19";
            this.downloadtask_destination = "\u76ee\u6a19\u985e\u578b";
            this.downloadtask_type = "\u4efb\u52d9\u985e\u578b";
            this.downloadtask_priority = "\u4f18\u5148\u7d1a";
            this.downloadtask_submittime = "\u63d0\u4ea4\u6642\u9593";
            this.downloadtask_submiter = "\u63d0\u4ea4\u4eba";
            this.downloadtask_grade = "\u8a18\u9304\u7d1a";
            this.downloadtask_objectname = "\u7bc0\u76ee\u540d\u7a31";
            this.downloadtask_destinfo = "\u76ee\u7684\u5730\u63cf\u8ff0";
            this.downloadtask_expired = "\u904e\u671f\u6642\u9593";
            this.downloadtask_destpath = "\u76ee\u6a19\u8def\u5f91";
            this.downloadtask_department = "\u90e8\u9580";
            this.downloadtask_column = "\u6b04\u76ee";
            this.downloadtask_resultinfo = "\u7d50\u679c\u8aaa\u660e";
            this.downloadtask_filename = "\u4e0b\u8f09\u6587\u4ef6\u540d\u7a31";
            this.downloadtask_targetfilename = "\u76ee\u6a19\u6587\u4ef6\u540d";
            this.downloadtask_purpose = "\u4e0b\u8f09\u7528\u9014";
            this.downloadtask_charge = "\u8cbb\u7528";
            this.downloadtask_memo = "\u4efb\u52d9\u63cf\u8ff0";
            this.downloadtask_status = "\u72c0\u614b";
            this.downloadtask_bcchannel = "\u64ad\u63a7\u983b\u9053";
            this.downloadtask_bcprogtype = "\u64ad\u63a7\u7bc0\u76ee\u985e\u578b";

            this.workfolder = "\u6211\u7684\u5de5\u4f5c\u593e";
            this.workfolder_add = "\u52a0\u5165\u5de5\u4f5c\u593e";
            this.workfolder_addtime = "\u6dfb\u52a0\u6642\u9593";
            this.workfolder_category = "\u5206\u985e";
            this.workfolder_category_management = "\u5206\u985e\u7ba1\u7406";
            this.workfolder_add_success = "\u5df2\u7d93\u6210\u529f\u6dfb\u52a0\u5230\u5de5\u4f5c\u593e, \u5373\u5c07\u95dc\u9589\u8a72\u7a97\u53e3, \u8acb\u7a0d\u5019....";
            this.workfolder_add_success2 = "\u60a8\u5df2\u7d93\u6210\u529f\u5c07\u5c0d\u8c61\u52a0\u5165\u5de5\u4f5c\u593e";
            this.workfolder_fresh_tip = "\u5728\u60a8\u5237\u65b0\u4e4b\u540e\uff0c\u5c07\u986f\u793a\u5de5\u4f5c\u593e\u91cc\u7684\u6240\u6709\u689d\u76ee";
            this.workfolder_delete_tip = "\u60a8\u9078\u4e2d\u7684\u689d\u76ee\u5c07\u6703\u88ab\u522a\u9664";
            this.workfolder_clear_tip = "\u5de5\u4f5c\u593e\u91cc\u7684\u6240\u6709\u689d\u76ee\u5c07\u6703\u88ab\u6e05\u7a7a";
            this.workfolder_export_tip = "\u60a8\u53ef\u4ee5\u5c07\u5de5\u4f5c\u593e\u689d\u76ee\u5c0e\u51fa\u6210excel\u6587\u4ef6\uff0c\u4fdd\u5b58\u5230\u672c\u5730";
            this.workfolder_getting = "\u6b63\u5728\u7372\u53d6\u5de5\u4f5c\u593e\u4fe1\u606f, \u8acb\u7a0d\u5019...";
            this.workfolder_clearing = "\u6b63\u5728\u6e05\u7a7a\u5de5\u4f5c\u593e\u4fe1\u606f, \u8acb\u7a0d\u5019...";
            this.workfolder_delete_confirm = "\u60a8\u786e\u5be6\u8981\u5c07\u9078\u4e2d\u7684\u689d\u76ee\u5f9e\u5de5\u4f5c\u593e\u4e2d\u79fb\u53bb\u55ce?";
            this.workfolder_clear_confirm = "\u60a8\u771f\u7684\u8981\u6e05\u7a7a\u5de5\u4f5c\u593e\u91cc\u7684\u6240\u6709\u689d\u76ee\u55ce?";
            this.workfolder_export_tip1 = "\u60a8\u9078\u4e2d\u7684\u5de5\u4f5c\u593e\u689d\u76ee\u5c07\u6703\u88ab\u5c0e\u51fa\u6210excel\u6587\u4ef6, \u4fdd\u5b58\u5230\u672c\u5730.";
            this.workfolder_export_tip2 = "\u5982\u679c\u60a8\u6c92\u6709\u9078\u4e2d\u4efb\u4f55\u689d\u76ee, \u6574\u500b\u7d50\u679c\u96c6\u5c07\u88ab\u5c0e\u51fa.";
            this.workfolder_mode_normal = "\u666e\u901a\u6a21\u5f0f";
            this.workfolder_mode_quicklist = "\u8349\u7de8\u55ae\u6a21\u5f0f";

            this.quicklist = "\u8349\u7de8\u55ae";
            this.quicklist_open = "\u8acb\u9078\u64c7\u60a8\u9700\u8981\u6253\u958b\u7684\u8349\u7de8\u55ae";
            this.quicklist_name = "\u8349\u7de8\u55ae\u540d\u7a31";
            this.quicklist_name_input = "\u8acb\u70ba\u60a8\u7684\u8349\u7de8\u55ae\u547d\u540d:";
            this.quicklist_name_cannot_null = "\u8349\u7de8\u55ae\u7684\u540d\u7a31\u4e0d\u80fd\u70ba\u7a7a";
            this.quicklist_have_noitems = "\u60a8\u7684\u8349\u7de8\u55ae\u91cc\u6c92\u6709\u4efb\u4f55\u689d\u76ee";
            this.quicklist_save_from_storyboard = "\u60a8\u8981\u4fdd\u5b58\u6545\u4e8b\u677f\u4e0a\u7684\u8349\u7de8\u55ae\u55ce?";

            this.download = "\u4e0b\u8f09";
            this.download_to = "\u4e0b\u8f09\u5230";
            this.download_submitter = "\u63d0\u4ea4\u4eba";
            this.download_ing = "\u6b63\u5728\u4e0b\u8f09, \u6210\u529f\u540e\u5c07\u95dc\u9589\u8a72\u7a97\u53e3, \u8acb\u7a0d\u5019...";
            this.download_select_files = "\u8acb\u9078\u64c7\u8981\u4e0b\u8f09\u7684\u6587\u4ef6";
            this.download_program = "\u5c0d\u4e8e\u7bc0\u76ee";
            this.download_need_to_select_files = "\u60a8\u81f3\u5c11\u9700\u8981\u9078\u64c7\u4e00\u500b\u6587\u4ef6\u9032\u884c\u4e0b\u8f09";
            this.download_submit_success = "\u60a8\u5df2\u7d93\u6210\u529f\u4e0b\u8f09\u8a72\u5c0d\u8c61";
            this.download_has_downloaded = "\u5171\u88ab\u4e0b\u8f09\u904e";
            this.download_detail_display1 = "\u4e0b\u9762\u5c07\u986f\u793a\u5176\u6700\u8fd1\u88ab\u4e0b\u8f09\u7684";
            this.download_detail_display2 = "\u689d\u8a18\u9304";
            this.download_detail_display3 = "\u4e0b\u9762\u5c07\u986f\u793a\u5176\u4e0b\u8f09\u8a18\u9304";

            this.downloadcheck = "\u4e0b\u8f09\u5be9\u6838";
            this.downloadcheck_accept = "\u5be9\u6838\u901a\u904e";
            this.downloadcheck_refuse = "\u5be9\u6838\u4e0d\u901a\u904e";
            this.downloadcheck_fresh_tip = "\u5728\u60a8\u5237\u65b0\u4e4b\u540e\uff0c\u5c07\u6703\u986f\u793a\u6240\u6709\u5f85\u5be9\u6838\u7684\u4e0b\u8f09\u4efb\u52d9";
            this.downloadcheck_accept_tip = "\u60a8\u8a8d\u53ef\u9078\u4e2d\u7684\u4e0b\u8f09\u4efb\u52d9\uff0c\u9019\u4e9b\u4efb\u52d9\u5c07\u9032\u5165\u4e0b\u4e00\u500b\u74b0\u7bc0\u8655\u7406";
            this.downloadcheck_refuse_tip = "\u60a8\u4e0d\u8a8d\u53ef\u9078\u4e2d\u7684\u4e0b\u8f09\u4efb\u52d9\uff0c\u9019\u4e9b\u4efb\u52d9\u7121\u6cd5\u9032\u5165\u4e0b\u4e00\u500b\u74b0\u7bc0";
            this.downloadcheck_fresh_wait = "\u6b63\u5728\u5237\u65b0\u4e0b\u8f09\u5be9\u6838\u4fe1\u606f, \u8acb\u7a0d\u5019...";
            this.downloadcheck_accept_confirm = "\u60a8\u786e\u8a8d\u8981\u901a\u904e\u9078\u4e2d\u7684\u4e0b\u8f09\u4efb\u52d9\u55ce?";
            this.downloadcheck_refuse_confirm = "\u60a8\u771f\u7684\u8981\u62d2\u7d55\u901a\u904e\u6240\u9078\u4e2d\u7684\u4e0b\u8f09\u4efb\u52d9\u55ce?";

            this.downloadhistory = "\u4e0b\u8f09\u6b77\u53f2";
            this.downloadhistory_fresh_tip = "\u5728\u60a8\u5237\u65b0\u4e4b\u540e\uff0c\u5c07\u6703\u986f\u793a\u6240\u6709\u7684\u4e0b\u8f09\u6b77\u53f2\u4efb\u52d9";
            this.downloadhistory_resubmit_tip = "\u60a8\u9078\u4e2d\u7684\u689d\u76ee\u5c07\u6703\u88ab\u91cd\u65b0\u63d0\u4ea4\u4e0b\u8f09";
            this.downloadhistory_delete_tip = "\u60a8\u9078\u4e2d\u7684\u689d\u76ee\u5c07\u6703\u5f9e\u4e0b\u8f09\u6b77\u53f2\u4efb\u52d9\u4e2d\u522a\u9664";
            this.downloadhistory_export_tip = "\u60a8\u53ef\u4ee5\u5c07\u7576\u524d\u7684\u4e0b\u8f09\u6b77\u53f2\u4efb\u52d9\u5c0e\u51fa\u6210excel\u6587\u4ef6\uff0c\u4fdd\u5b58\u5230\u672c\u5730";
            this.downloadhistory_fresh_wait = "\u6b63\u5728\u5237\u65b0\u4e0b\u8f09\u6b77\u53f2\u4fe1\u606f, \u8acb\u7a0d\u5019...";
            this.downloadhistory_resubmit_confirm = "\u60a8\u786e\u8a8d\u8981\u91cd\u65b0\u63d0\u4ea4\u9078\u4e2d\u7684\u4e0b\u8f09\u4efb\u52d9\u55ce?";
            this.downloadhistory_delete_confirm = "\u60a8\u771f\u7684\u8981\u522a\u9664\u6240\u9078\u4e2d\u7684\u4e0b\u8f09\u6b77\u53f2\u4efb\u52d9\u55ce?";
            this.downloadhistory_detail_loading = "\u6b63\u5728\u52a0\u8f09\u4e0b\u8f09\u6b77\u53f2\u8a73\u7d30\u4fe1\u606f,\u8acb\u7a0d\u5019...";

            this.related_no_views = "\u7121\u76f8\u95dc\u700f\u89bd!";
            this.related_no_downloads = "\u7121\u76f8\u95dc\u4e0b\u8f09!";
            this.related_no_resources = "\u7121\u76f8\u95dc\u8cc7\u6e90!";
            this.related_views = "\u76f8\u95dc\u700f\u89bd";
            this.related_downloads = "\u76f8\u95dc\u4e0b\u8f09";
            this.related_resources = "\u76f8\u95dc\u8cc7\u6e90";
            this.related_views_tip = "\u700f\u89bd\u904e\u8a72\u7bc0\u76ee\u7684\u4eba\u9084\u700f\u89bd\u904e...";
            this.related_downloads_tip = "\u4e0b\u8f09\u904e\u8a72\u7bc0\u76ee\u7684\u4eba\u9084\u4e0b\u8f09\u904e...";
            this.related_resources_tip = "\u5305\u542b\u76f8\u540c\u6a19\u7c3d\u7684\u7bc0\u76ee\u9084\u6709...";
            this.related_getting = "\u6b63\u5728\u7372\u53d6\u95dc\u806f\u4fe1\u606f, \u8acb\u7a0d\u5019...";
            this.related_tags = "\u76f8\u95dc\u6a19\u7c3d";

            this.config = "\u500b\u6027\u914d\u7f6e";
            this.config_condition = "\u6aa2\u7d22\u689d\u4ef6\u914d\u7f6e";
            this.config_results = "\u6aa2\u7d22\u7d50\u679c\u914d\u7f6e";
            this.config_detail = "\u8a73\u7d30\u5167\u5bb9\u914d\u7f6e";
            this.config_notconfiged = "\u672a\u5b9a\u5236\u540d\u7a31";
            this.config_configed = "\u5df2\u5b9a\u5236\u540d\u7a31";
            this.config_tree_notconfiged = "\u689d\u4ef6\u6a39\u5404\u5c64\u5099\u9078\u540d\u7a31";
            this.config_tree_configed = "\u689d\u4ef6\u6a39\u5404\u5c64\u540d\u7a31";
            this.config_tree_layersize = "\u5c64\u6578";
            this.config_tree_the = "\u7b2c";
            this.config_tree_layer = "\u5c64";
            this.config_move2left = "\u5de6\u79fb";
            this.config_move2right = "\u53f3\u79fb";
            this.config_getting = "\u6b63\u5728\u7372\u53d6\u914d\u7f6e\u4fe1\u606f, \u8acb\u7a0d\u5019...";
            this.config_condition_upperlimit1 = "\u70ba\u4e86\u65b9\u4fbf\u60a8\u7684\u6aa2\u7d22\uff0c\u60a8\u6700\u591a\u53ef\u4ee5\u914d\u7f6e5\u500b\u6aa2\u7d22\u689d\u4ef6.(\u60a8\u76ee\u524d\u5df2\u7d93\u914d\u7f6e\u4e86";
            this.config_condition_upperlimit2 = "\u500b\u6aa2\u7d22\u689d\u4ef6)";
            this.config_condition_tree_upperlimit1 = "\u70ba\u4e86\u65b9\u4fbf\u60a8\u7684\u6aa2\u7d22\uff0c\u60a8\u7684\u689d\u4ef6\u6a39\u6700\u591a\u53ef\u4ee5\u914d\u7f6e3\u5c64.(\u60a8\u76ee\u524d\u5df2\u7d93\u914d\u7f6e\u4e86";
            this.config_condition_tree_upperlimit2 = "\u5c64 )";
            this.config_input_tree_name = "\u8acb\u586b\u5beb\u689d\u4ef6\u6a39\u7684\u540d\u7a31";

            this.projecttree_save_tip = "\u4fdd\u5b58\u6240\u6709\u7684\u4e3b\u984c";
            this.projecttree_add_tip = "\u65b0\u589e\u4e3b\u984c";
            this.projecttree_delete_tip = "\u522a\u9664\u4e3b\u984c";
            this.projecttree_delete_warn = "\u60a8\u771f\u7684\u8981\u522a\u9664\u8a72\u4e3b\u984c\u55ce?";

            this.keyframe_no = "\u8a72\u7bc0\u76ee\u6c92\u6709\u95dc\u9375\u5e40";
            this.keyframe_get = "\u6b63\u5728\u7372\u53d6\u95dc\u9375\u5e40\u4fe1\u606f, \u8acb\u7a0d\u5019...";

            this.detail_tip = "\u60a8\u5c07\u9032\u4e00\u6b65\u700f\u89bd\u5230\u7576\u524d\u7bc0\u76ee\u7684\u8996\u97f3\u983b\u3001\u7bc0\u76ee\u6a39\u3001\u5b8c\u6574\u7de8\u76ee\u4fe1\u606f\u3001\u95dc\u9375\u5e40\u7b49\u8a73\u7d30\u5167\u5bb9";

            this.logout_confirm = "\u60a8\u8981\u9000\u51faiSearch\u7cfb\u7d71\u55ce?";

            this.export_confirm = "\u5c0e\u51fa\u786e\u8a8d";
            this.export_current_page_confirm = "\u60a8\u786e\u5b9a\u8981\u5c0e\u51fa\u7576\u524d\u9801\u55ce\uff1f";
            this.export_time_consuming = "(\u60a8\u5c0e\u51fa\u7684\u689d\u76ee\u8f03\u591a\uff0c\u8017\u6642\u8f03\u9577)";
            this.export_top1 = "\u60a8\u786e\u5b9a\u8981\u5c0e\u51fa\u524d";
            this.export_top2 = "\u689d\u55ce?";
            this.export_currentpage = "\u5c0e\u51fa\u7576\u524d\u9801";
            this.export_top = "\u5c0e\u51fa\u524d";
            this.export_input_range = "\u8f38\u5165\u8303\u570d";

            this.warn_program_no = "\u6c92\u6709\u7b26\u5408\u689d\u4ef6\u7684\u7bc0\u76ee";
            this.warn_cannot_view_detail = "\u60a8\u9084\u6c92\u6709\u700f\u89bd\u4efb\u4f55\u7bc0\u76ee, \u7121\u6cd5\u67e5\u770b\u8a73\u7d30\u4fe1\u606f!";
            this.warn_cannot_add2wf = "\u60a8\u9084\u6c92\u6709\u700f\u89bd\u4efb\u4f55\u7bc0\u76ee, \u7121\u6cd5\u52a0\u5165\u5de5\u4f5c\u593e!";
            this.warn_not_select_file = "\u60a8\u6c92\u6709\u52fe\u9078\u4efb\u4f55\u6587\u4ef6";
            this.warn_not_select_item = "\u60a8\u6c92\u6709\u9078\u4e2d\u4efb\u4f55\u689d\u76ee";
            this.warn_not_select_item_download = "\u60a8\u9084\u6c92\u6709\u9078\u5b9a\u4efb\u4f55\u7bc0\u76ee, \u7121\u6cd5\u9032\u884c\u4e0b\u8f09!";
            this.warn_not_select_anyfiles_download = "\u60a8\u81f3\u5c11\u9700\u8981\u9078\u64c7\u4e00\u500b\u6587\u4ef6\u9032\u884c\u4e0b\u8f09!";
            this.warn_player_cannot_get_inoutpoint = "\u7121\u6cd5\u5f9e\u64ad\u653e\u5668\u7372\u53d6\u5165\u51fa\u9ede\uff0c\u8acb\u6aa2\u67e5\u64ad\u653e\u5668!";
            this.warn_input_error = "\u60a8\u7684\u8f38\u5165\u4e0d\u6b63\u786e\uff0c\u8acb\u6309\u63d0\u793a\u8f38\u5165\u6b63\u786e\u7684\u6578\u503c\uff01";
            this.warn_no_externsaid = "\u8acb\u5148\u5728\u5a92\u8cc7\u7ba1\u7406\u63a7\u5236\u53f0\u914d\u7f6e\u5916\u90e8\u5b58\u5132\u5340!";
            this.warn_bcchannel_cannnot_be_null = "\u60a8\u9700\u8981\u81f3\u5c11\u9078\u64c7\u4e00\u500b\u64ad\u63a7\u983b\u9053!";
            this.warn_bcprogtype_cannnot_be_null = "\u60a8\u9700\u8981\u9078\u64c7\u4e00\u79cd\u64ad\u63a7\u7bc0\u76ee\u985e\u578b!";
            this.warn_dltargetpath_must_be_selected = "\u60a8\u9700\u8981\u9078\u64c7\u4e00\u500b\u76ee\u6a19\u8def\u5f91\u9032\u884c\u4e0b\u8f09!";

            this.error_title_0 = "\u6b0a\u9650";
            this.error_title_100 = "\u5f02\u5e38";

            this.error_description_100 = "\u7a0b\u5e8f\u53ef\u80fd\u66ab\u6642\u7121\u6cd5\u8655\u7406\u60a8\u7684\u64cd\u4f5c, \u8acb\u4e0e\u7ba1\u7406\u54e1\u806f\u7cfb, \u8b1d\u8b1d\u5408\u4f5c!";

            this.need_privilege = "\u9700\u8981\u6b0a\u9650";
            this.need_privilege_confirm = "\u8acb\u786e\u8a8d\u60a8\u662f\u5426\u5177\u6709\u8a72\u6b0a\u9650, \u8b1d\u8b1d\u5408\u4f5c";

            this.operate_searcher = "\u9032\u5165iSearch\u7cfb\u7d71"

        }
    };
}();

Ext.EventManager.onDocumentReady(Resource.init, Resource, true);
