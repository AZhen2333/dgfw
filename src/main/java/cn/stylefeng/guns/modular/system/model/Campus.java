package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 校区信息表
 * </p>
 *
 * @author czz
 * @since 2019-01-23
 */
@TableName("fw_campus")
public class Campus extends Model<Campus> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 校区名称
     */
    @TableField("campus_name")
    private String campusName;
    /**
     * 校区类型：0-自营，1-合作，2-加盟
     */
    @TableField("campus_type")
    private Integer campusType;
    /**
     * 是否删除：0-正常，1-删除
     */
    @TableField("is_del")
    private Integer isDel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public Integer getCampusType() {
        return campusType;
    }

    public void setCampusType(Integer campusType) {
        this.campusType = campusType;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Campus{" +
        ", id=" + id +
        ", campusName=" + campusName +
        ", campusType=" + campusType +
        ", isDel=" + isDel +
        "}";
    }
}
