var UWTConfiguration = function () {
    var hasInitFromJsp = false,//set true if has init JSP params. Only init 1 time
        casServerLogoutUrl,  //logout URL
        panelWorkspace;      //当前center panel的配置data
    var field_labelWidth = 75; //默认配置
    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
    var currentOP,  //当前选中的tab
        currentColumn,  //当前选中的tree node
        myTabItems,     //所有tab选项，是array
        treeData;       //所有tree数据
    var searchAreaData,resultGridData,infoFormData,HTMLAreaData,infoFormWinData; //当前center panel中各个子panel的Data
    var searchCondition;  //如果用searchArea的条件进行了搜索，存住当前的搜索条件，用于refresh
    var user = {      //当前用户信息,include field  sUserHandle, sUserName, iActorID, sActorName,
             //iDepartmentID, sDepartmentName, sSuperiorHandle,sSuperiorName
    };

    var columnForWin;//弹出窗口所显示的column
    var winHight,winWidth;
    return {

        init: function () {

            //field_labelWidth = 60;

        },
        initFromJsp: function (logoutUrl, tabs, init_TreeColumnMap, init_panelWorkspace) {

            if (hasInitFromJsp)return;
            hasInitFromJsp = true;
            casServerLogoutUrl = logoutUrl;
            myTabItems = tabs;
            if (myTabItems.length > 0)
                currentOP = myTabItems[0].id;
            else {
                Ext.Msg.show({
                    title: dictionary.pub_wrong,
                    msg: dictionary.toolbar_error,
                    minWidth: 200,
                    modal: true,
                    icon: Ext.Msg.INFO,
                    buttons: Ext.Msg.OK
                });
                return;
            }
            currentColumn = "";
            treeData = init_TreeColumnMap;
            this.setPanelWorkspace(init_panelWorkspace);
            this.setWinHight();
            this.setWinWidth();
        },
        //data prepare/getter/setter
        /**
         *
         * @param inUser : include field  sUserHandle, sUserName, iActorID, sActorName,
         * iDepartmentID, sDepartmentName, sSuperiorHandle,sSuperiorName
         */
        setUserinfo: function (inUser) {
            user = inUser;
        },
        getMyTabItems: function () {
            return myTabItems;
        },
        gettreeData: function () {
            return treeData;
        },
        getCasServerLogoutUrl: function () {
            return (casServerLogoutUrl);
        },
        getPanelWorkspace: function () {
            return (panelWorkspace);
        },
        setPanelWorkspace: function (inpanelWorkspace) {
            panelWorkspace =  this.deleteEmptyProperty(inpanelWorkspace);
            searchAreaData = panelWorkspace.searchPanel;
            resultGridData = panelWorkspace.resultGridPanel;
            infoFormData = panelWorkspace.infoFormPanel;
            infoFormWinData =  panelWorkspace.infoFormWindow;
            HTMLAreaData = panelWorkspace.htmlPanel;

        },
        getFieldLabelWidth: function () {
            return (field_labelWidth);

        } ,
        getSearchAreaData: function () {
            return (searchAreaData);
        },
        setSearchAreaData:function (newdata) {
                    searchAreaData = newdata;
                },
        getResultGridData: function () {
            return (resultGridData);
        },
        setResultGridData: function (newdata) {
            resultGridData = newdata;
        } ,
        getInfoFormData:function () {
            return (infoFormData);
        } ,
        setInfoFormData: function (newData) {
            infoFormData = newData;
        },
        getInfoFormWinData:function () {
            return (infoFormWinData);
        } ,
        setInfoFormWinData: function (newData) {
            infoFormWinData = newData;
        },

        isEnableCreateRec: function () {
            return panelWorkspace.createRec;
        },
        isEnableUpdateRec: function () {
            return panelWorkspace.updateRec;

        },
        isEnableDeleteRec: function () {
            return panelWorkspace.deleteRec;

        },
        getHTMLAreaData: function () {
            return (HTMLAreaData);
        } ,
        getCurrentOP: function () {
                    return (currentOP);
                } ,
        setCurrentOP: function (newOP) {
            currentOP = newOP;
                        } ,
        getCurrentColumn: function () {
                    return (currentColumn);
                } ,
        setCurrentColumn: function (newColumn) {
            currentColumn = newColumn;
                        } ,
        changeColumnForWin: function () {
            if(currentColumn != columnForWin){
                columnForWin = currentColumn;
                return true;
            }else{
                return false ;
            }
        },

        getUserName: function () {
            return user.sUserName;
        },
        getUserHandle: function () {
            return user.sUserHandle;
        },
        getActorName: function () {
            return user.sActorName;
        },
        getSuperiorName: function () {
            return user.sSuperiorName;
        },
        getDepartmentName: function () {
            return user.sDepartmentName;
        },
        getDetailWorkcakeType: function () {
            return resultGridData.detailWorkcake? resultGridData.detailWorkcake : "panel";
        },

        getWinWidth: function () {
           return winWidth;
        },
        setWinWidth: function () {
            winWidth = (document.body.clientWidth > 185) ? (document.body.clientWidth - 185) : 185;
        } ,
        getWinHight: function () {
           return winHight;
        },
        setWinHight: function () {
            winHight = (document.body.clientHeight > 185) ? (document.body.clientHeight - 185) : 185;

        } ,
        setSearchCondition: function (value) {
            searchCondition = value;
        },
        getSearchCondition: function () {
                    return searchCondition ;
                },
    //data prepare/getter/setter end

        //util function
        /**
         * set data 之前，需要删除空属性
         * @param object
         */
        deleteEmptyProperty: function (object) {
            for (var i in object) {
                var value = object[i];
                // sodino.com
                // console.log('typeof object[' + i + ']', (typeof value));
                if (typeof value === 'object') {
                    if (Array.isArray(value)) {
                        if (value.length == 0) {
                            delete object[i];
                            //console.log('delete Array', i);
                            continue;
                        }
                    }
                    this.deleteEmptyProperty(value);
                    if (this.isEmpty(value)) {
                        //console.log('isEmpty true', i, value);
                        delete object[i];
                        //console.log('delete a empty object');
                    }
                } else {
                    if (value === '' || value === null || value === undefined) {
                        delete object[i];
                        //console.log('delete ', i);
                    } else {
                        //console.log('check ', i, value);
                    }
                }
            }
            return object;
        },
        isEmpty: function (object) {
            for (var name in object) {
                return false;
            }
            return true;
        },
        getRequiredTpl: function () {
           return required;
        }
        //util function end


    }
}();