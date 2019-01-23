/**
 * 校区信息管理初始化
 */
var Campus = {
    id: "CampusTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Campus.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '校区名称', field: 'campusName', visible: true, align: 'center', valign: 'middle'},
            {title: '校区类型', field: 'campusType', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'isDel', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Campus.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Campus.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加校区信息
 */
Campus.openAddCampus = function () {
    var index = layer.open({
        type: 2,
        title: '添加校区信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/campus/campus_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看校区信息详情
 */
Campus.openCampusDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '校区信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/campus/campus_update/' + Campus.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除校区信息
 */
Campus.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/campus/delete", function (data) {
            Feng.success("删除成功!");
            Campus.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("campusId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询校区信息列表
 */
Campus.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Campus.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Campus.initColumn();
    var table = new BSTable(Campus.id, "/campus/list", defaultColunms);
    table.setPaginationType("client");
    Campus.table = table.init();
});
