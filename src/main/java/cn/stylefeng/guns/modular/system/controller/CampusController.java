package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Campus;
import cn.stylefeng.guns.modular.system.service.ICampusService;

/**
 * 校区信息控制器
 *
 * @author fengshuonan
 * @Date 2019-01-23 10:43:27
 */
@Controller
@RequestMapping("/campus")
public class CampusController extends BaseController {

    private String PREFIX = "/system/campus/";

    @Autowired
    private ICampusService campusService;

    /**
     * 跳转到校区信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "campus.html";
    }

    /**
     * 跳转到添加校区信息
     */
    @RequestMapping("/campus_add")
    public String campusAdd() {
        return PREFIX + "campus_add.html";
    }

    /**
     * 跳转到修改校区信息
     */
    @Permission
    @RequestMapping("/campus_update/{campusId}")
    public String campusUpdate(@PathVariable Integer campusId, Model model) {
        Campus campus = campusService.selectById(campusId);
        model.addAttribute("item",campus);
        LogObjectHolder.me().set(campus);
        return PREFIX + "campus_edit.html";
    }

    /**
     * 获取校区信息列表
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return campusService.selectList(null);
    }

    /**
     * 新增校区信息
     */
    @Permission
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Campus campus) {
        campusService.insert(campus);
        return SUCCESS_TIP;
    }

    /**
     * 删除校区信息
     */
    @Permission({Const.ADMIN_NAME})
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer campusId) {
        campusService.deleteById(campusId);
        return SUCCESS_TIP;
    }

    /**
     * 修改校区信息
     */
    @Permission
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Campus campus) {
        campusService.updateById(campus);
        return SUCCESS_TIP;
    }

    /**
     * 校区信息详情
     */
    @Permission
    @RequestMapping(value = "/detail/{campusId}")
    @ResponseBody
    public Object detail(@PathVariable("campusId") Integer campusId) {
        return campusService.selectById(campusId);
    }
}
