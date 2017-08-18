var Resource = function () {
    return {
        init: function () {
            this.pub_wait = "���b�[��, �еy��...";
            this.pub_submit = "����";
            this.pub_resubmit = "���s����";
            this.pub_close = "����";
            this.pub_basic = "�򥻫H��";
            this.pub_detail = "�ԲӫH��";
            this.pub_no = "�Ǹ�";
            this.pub_warn = "ĵ�i";
            this.pub_fresh = "��s";
            this.pub_add = "�K�[";
            this.pub_modify = "�ק�";
            this.pub_delete = "�R��";
            this.pub_remove = "���h";
            this.pub_back = "��^";
            this.pub_export = "�ɥX";
            this.pub_confirm = "�̻{";
            this.pub_confirm_operate = "�̻{�ާ@";
            this.pub_startdate = "�}�l���";
            this.pub_enddate = "�I����";
            this.pub_metadata = "�s�ثH��";
            this.pub_keyframe = "����V";
            this.pub_fileinfo = "���H��";
            this.pub_related = "������";
            this.pub_specialsequence = "���D��q";
            this.pub_tapeinfo = "�w�ЫH��";
            this.pub_dyacinfo = "�k�ɫH��";
            this.pub_other = "��L";
            this.pub_tip = "���ܫH��";
            this.pub_type = "����";
            this.pub_clear = "�M��";
            this.pub_save = "�O�s";
            this.pub_quickview = "�ֳt�s���";
            this.pub_reference = "�ޥβv"
            this.pub_items = "��";
            this.pub_the = "��";
            this.pub_total = "�@";
            this.pub_click = "���I��";
            this.pub_here = "�o��";
            this.pub_records = "�O��";
            this.pub_select = "�п��...";
            this.pub_hello = "�z�n";
            this.pub_counts = "��";
            this.pub_project = "����";
            this.pub_new = "�s��";
            this.pub_open = "���}";

            this.logic_and = "�O";
            this.logic_or = "��";
            this.logic_equal = "���_";
            this.logic_notequal = "�����_";
            this.logic_like = "�ۦ�_";
            this.logic_include = "�]�t";
            this.logic_larger = "�j�_";
            this.logic_smaller = "�p�_";
            this.logic_between = "���_";
            this.logic_to = "���_";

            this.orderby = "�Ƨ�";
            this.orderby_created_desc = "�Ыخɶ�����";
            this.orderby_created_esc = "�Ыخɶ��ɧ�";
            this.orderby_hits = "�d�ݲv";
            this.orderby_downloads = "�ޥβv";

            this.basicinfo_name = "�W��";
            this.basicinfo_inpoint = "�J�I";
            this.basicinfo_outpoint = "�X�I";
            this.basicinfo_duration = "�ɪ�";
            this.basicinfo_created = "�Ыخɶ�";
            this.basicinfo_description = "�y�z";
            this.basicinfo_hits = "�I���v";
            this.basicinfo_downloads = "�ޥβv";
            this.basicinfo_filestatus = "��󪬺A";
            this.basicinfo_lastmodify = "�̷s�ק�ɶ�";

            this.tags_type_usual = "�`�μ�ñ";
            this.tags_type_hot = "�����ñ";

            this.fileinfo_name = "���W��";
            this.fileinfo_type = "����";
            this.fileinfo_size = "�j�p";
            this.fileinfo_status = "���A";
            this.fileinfo_created = "�Ыخɶ�";
            this.fileinfo_get = "���b�����H��, �еy��..";

            this.query_select_cc = "�п�ܽs����...";
            this.query_use_homophone = "�P���r";
            this.query_fulltext = "�����˯�";
            this.query_results = "�˯����G";
            this.query_match = "�ŦX";
            this.query_result_counts = "�����G�@��";
            this.query_usetime = "��   �ή�";
            this.query_seconds = "��";
            this.query_research = "���s�j��";
            this.query_search_in_results = "�b���G���j��";
            this.query_export_tip = "�z�i�H�N�˯����G�ɥX��excel���A�O�s�쥻�a";
            this.query_orderby_tip = "�z�i�H���ӳЫخɶ��B�I���v�ΤU��v�A���˯����G�i��Ƨ�";
            this.query_current_results = "��e���G��";
            this.query_structure_attribute = "�ݩ�";
            this.query_structure_attributegroup = "�ݩʲ�";
            this.query_type_tag = "��ñ�˯�";
            this.query_tag_hot = "���I��ñ";
            this.query_tag_no = "�t�Τ��S����ñ!";
            this.query_more_condition = "��h�˯����";
            this.query_simple_condition = "²���˯����";

            this.top10_latest = "�̷sTop10";
            this.top10_latest_download = "�̪�U��Top10";
            this.top10_hits = "�I���vTop10";
            this.top10_downloads = "�ޥβvTop10";
            this.top10_my_latest_views = "�ڳ̪��s��Top10";
            this.top10_my_latest_downloads = "�ڳ̪�U��Top10";

            this.downloadtask = "����";
            this.downloadtask_name = "���ȦW��";
            this.downloadtask_inpoint = "�J�I";
            this.downloadtask_duration = "�ɪ�";
            this.downloadtask_target = "�U��ؼ�";
            this.downloadtask_destination = "�ؼ�����";
            this.downloadtask_type = "��������";
            this.downloadtask_priority = "ɬ���";
            this.downloadtask_submittime = "����ɶ�";
            this.downloadtask_submiter = "����H";
            this.downloadtask_grade = "�O���";
            this.downloadtask_objectname = "�`�ئW��";
            this.downloadtask_destinfo = "�ت��a�y�z";
            this.downloadtask_expired = "�L���ɶ�";
            this.downloadtask_destpath = "�ؼи��|";
            this.downloadtask_department = "����";
            this.downloadtask_column = "���";
            this.downloadtask_resultinfo = "���G����";
            this.downloadtask_filename = "�U����W��";
            this.downloadtask_targetfilename = "�ؼФ��W";
            this.downloadtask_purpose = "�U��γ~";
            this.downloadtask_charge = "�O��";
            this.downloadtask_memo = "���ȴy�z";
            this.downloadtask_status = "���A";
            this.downloadtask_bcchannel = "�����W�D";
            this.downloadtask_bcprogtype = "�����`������";

            this.workfolder = "�ڪ��u�@��";
            this.workfolder_add = "�[�J�u�@��";
            this.workfolder_addtime = "�K�[�ɶ�";
            this.workfolder_category = "����";
            this.workfolder_category_management = "�����޲z";
            this.workfolder_add_success = "�w�g���\�K�[��u�@��, �Y�N�����ӵ��f, �еy��....";
            this.workfolder_add_success2 = "�z�w�g���\�N��H�[�J�u�@��";
            this.workfolder_fresh_tip = "�b�z��s���Z�A�N��ܤu�@�������Ҧ����";
            this.workfolder_delete_tip = "�z�襤����رN�|�Q�R��";
            this.workfolder_clear_tip = "�u�@�������Ҧ���رN�|�Q�M��";
            this.workfolder_export_tip = "�z�i�H�N�u�@����ؾɥX��excel���A�O�s�쥻�a";
            this.workfolder_getting = "���b���u�@���H��, �еy��...";
            this.workfolder_clearing = "���b�M�Ťu�@���H��, �еy��...";
            this.workfolder_delete_confirm = "�z�̹�n�N�襤����رq�u�@�������h��?";
            this.workfolder_clear_confirm = "�z�u���n�M�Ťu�@�������Ҧ���ض�?";
            this.workfolder_export_tip1 = "�z�襤���u�@����رN�|�Q�ɥX��excel���, �O�s�쥻�a.";
            this.workfolder_export_tip2 = "�p�G�z�S���襤������, ��ӵ��G���N�Q�ɥX.";
            this.workfolder_mode_normal = "���q�Ҧ�";
            this.workfolder_mode_quicklist = "��s��Ҧ�";

            this.quicklist = "��s��";
            this.quicklist_open = "�п�ܱz�ݭn���}����s��";
            this.quicklist_name = "��s��W��";
            this.quicklist_name_input = "�Ь��z����s��R�W:";
            this.quicklist_name_cannot_null = "��s�檺�W�٤��ର��";
            this.quicklist_have_noitems = "�z����s�樽�S��������";
            this.quicklist_save_from_storyboard = "�z�n�O�s�G�ƪO�W����s���?";

            this.download = "�U��";
            this.download_to = "�U���";
            this.download_submitter = "����H";
            this.download_ing = "���b�U��, ���\�Z�N�����ӵ��f, �еy��...";
            this.download_select_files = "�п�ܭn�U����";
            this.download_program = "��_�`��";
            this.download_need_to_select_files = "�z�ܤֻݭn��ܤ@�Ӥ��i��U��";
            this.download_submit_success = "�z�w�g���\�U��ӹ�H";
            this.download_has_downloaded = "�@�Q�U��L";
            this.download_detail_display1 = "�U���N��ܨ�̪�Q�U��";
            this.download_detail_display2 = "��O��";
            this.download_detail_display3 = "�U���N��ܨ�U��O��";

            this.downloadcheck = "�U��f��";
            this.downloadcheck_accept = "�f�ֳq�L";
            this.downloadcheck_refuse = "�f�֤��q�L";
            this.downloadcheck_fresh_tip = "�b�z��s���Z�A�N�|��ܩҦ��ݼf�֪��U�����";
            this.downloadcheck_accept_tip = "�z�{�i�襤���U����ȡA�o�ǥ��ȱN�i�J�U�@�����`�B�z";
            this.downloadcheck_refuse_tip = "�z���{�i�襤���U����ȡA�o�ǥ��ȵL�k�i�J�U�@�����`";
            this.downloadcheck_fresh_wait = "���b��s�U��f�֫H��, �еy��...";
            this.downloadcheck_accept_confirm = "�z�̻{�n�q�L�襤���U����ȶ�?";
            this.downloadcheck_refuse_confirm = "�z�u���n�ڵ��q�L�ҿ襤���U����ȶ�?";

            this.downloadhistory = "�U���v";
            this.downloadhistory_fresh_tip = "�b�z��s���Z�A�N�|��ܩҦ����U���v����";
            this.downloadhistory_resubmit_tip = "�z�襤����رN�|�Q���s����U��";
            this.downloadhistory_delete_tip = "�z�襤����رN�|�q�U���v���Ȥ��R��";
            this.downloadhistory_export_tip = "�z�i�H�N��e���U���v���ȾɥX��excel���A�O�s�쥻�a";
            this.downloadhistory_fresh_wait = "���b��s�U���v�H��, �еy��...";
            this.downloadhistory_resubmit_confirm = "�z�̻{�n���s����襤���U����ȶ�?";
            this.downloadhistory_delete_confirm = "�z�u���n�R���ҿ襤���U���v���ȶ�?";
            this.downloadhistory_detail_loading = "���b�[��U���v�ԲӫH��,�еy��...";

            this.related_no_views = "�L�����s��!";
            this.related_no_downloads = "�L�����U��!";
            this.related_no_resources = "�L�����귽!";
            this.related_views = "�����s��";
            this.related_downloads = "�����U��";
            this.related_resources = "�����귽";
            this.related_views_tip = "�s��L�Ӹ`�ت��H���s��L...";
            this.related_downloads_tip = "�U��L�Ӹ`�ت��H�٤U��L...";
            this.related_resources_tip = "�]�t�ۦP��ñ���`���٦�...";
            this.related_getting = "���b������p�H��, �еy��...";
            this.related_tags = "������ñ";

            this.config = "�өʰt�m";
            this.config_condition = "�˯����t�m";
            this.config_results = "�˯����G�t�m";
            this.config_detail = "�ԲӤ��e�t�m";
            this.config_notconfiged = "���w��W��";
            this.config_configed = "�w�w��W��";
            this.config_tree_notconfiged = "����U�h�ƿ�W��";
            this.config_tree_configed = "����U�h�W��";
            this.config_tree_layersize = "�h��";
            this.config_tree_the = "��";
            this.config_tree_layer = "�h";
            this.config_move2left = "����";
            this.config_move2right = "�k��";
            this.config_getting = "���b���t�m�H��, �еy��...";
            this.config_condition_upperlimit1 = "���F��K�z���˯��A�z�̦h�i�H�t�m5���˯����.(�z�ثe�w�g�t�m�F";
            this.config_condition_upperlimit2 = "���˯����)";
            this.config_condition_tree_upperlimit1 = "���F��K�z���˯��A�z������̦h�i�H�t�m3�h.(�z�ثe�w�g�t�m�F";
            this.config_condition_tree_upperlimit2 = "�h )";
            this.config_input_tree_name = "�ж�g���𪺦W��";

            this.projecttree_save_tip = "�O�s�Ҧ����D�D";
            this.projecttree_add_tip = "�s�W�D�D";
            this.projecttree_delete_tip = "�R���D�D";
            this.projecttree_delete_warn = "�z�u���n�R���ӥD�D��?";

            this.keyframe_no = "�Ӹ`�بS������V";
            this.keyframe_get = "���b�������V�H��, �еy��...";

            this.detail_tip = "�z�N�i�@�B�s����e�`�ت����W�B�`�ؾ�B����s�ثH���B����V���ԲӤ��e";

            this.logout_confirm = "�z�n�h�XiSearch�t�ζ�?";

            this.export_confirm = "�ɥX�̻{";
            this.export_current_page_confirm = "�z�̩w�n�ɥX��e���ܡH";
            this.export_time_consuming = "(�z�ɥX����ظ�h�A�Ӯɸ��)";
            this.export_top1 = "�z�̩w�n�ɥX�e";
            this.export_top2 = "���?";
            this.export_currentpage = "�ɥX��e��";
            this.export_top = "�ɥX�e";
            this.export_input_range = "��J�S��";

            this.warn_program_no = "�S���ŦX��󪺸`��";
            this.warn_cannot_view_detail = "�z�٨S���s�����`��, �L�k�d�ݸԲӫH��!";
            this.warn_cannot_add2wf = "�z�٨S���s�����`��, �L�k�[�J�u�@��!";
            this.warn_not_select_file = "�z�S���Ŀ������";
            this.warn_not_select_item = "�z�S���襤������";
            this.warn_not_select_item_download = "�z�٨S����w����`��, �L�k�i��U��!";
            this.warn_not_select_anyfiles_download = "�z�ܤֻݭn��ܤ@�Ӥ��i��U��!";
            this.warn_player_cannot_get_inoutpoint = "�L�k�q�������J�X�I�A���ˬd����!";
            this.warn_input_error = "�z����J�����̡A�Ы����ܿ�J���̪��ƭȡI";
            this.warn_no_externsaid = "�Х�b�C��޲z����x�t�m�~���s�x��!";
            this.warn_bcchannel_cannnot_be_null = "�z�ݭn�ܤֿ�ܤ@�Ӽ����W�D!";
            this.warn_bcprogtype_cannnot_be_null = "�z�ݭn��ܤ@���`������!";
            this.warn_dltargetpath_must_be_selected = "�z�ݭn��ܤ@�ӥؼи��|�i��U��!";

            this.error_title_0 = "�v��";
            this.error_title_100 = "�ݱ`";

            this.error_description_100 = "�{�ǥi��ȮɵL�k�B�z�z���ާ@, ���O�޲z���p�t, ���¦X�@!";

            this.need_privilege = "�ݭn�v��";
            this.need_privilege_confirm = "���̻{�z�O�_�㦳���v��, ���¦X�@";

            this.operate_searcher = "�i�JiSearch�t��"

        }
    };
}();

Ext.EventManager.onDocumentReady(Resource.init, Resource, true);
