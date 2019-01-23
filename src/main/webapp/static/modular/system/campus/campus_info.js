/**
 * 初始化校区信息详情对话框
 */
var CampusInfoDlg = {
    campusInfoData : {}
};

/**
 * 清除数据
 */
CampusInfoDlg.clearData = function() {
    this.campusInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CampusInfoDlg.set = function(key, val) {
    this.campusInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CampusInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CampusInfoDlg.close = function() {
    parent.layer.close(window.parent.Campus.layerIndex);
}

/**
 * 收集数据
 */
CampusInfoDlg.collectData = function() {
    this
    .set('id')
    .set('campusName')
    .set('campusType')
    .set('isDel');
}

/**
 * 提交添加
 */
CampusInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/campus/add", function(data){
        Feng.success("添加成功!");
        window.parent.Campus.table.refresh();
        CampusInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.campusInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CampusInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/campus/update", function(data){
        Feng.success("修改成功!");
        window.parent.Campus.table.refresh();
        CampusInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.campusInfoData);
    ajax.start();
}

$(function() {

});
