package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 学生基本表 t_student
 *
 * @author wubin
 * @date 2019-08-13
 */
public class Student extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 学生性别
     */
    private String stuSex;
    /**
     * 学生电话
     */
    private String stuTel;
    /**
     * 学生年龄
     */
    private String stuAge;
    /**
     * 出生年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date stuBirth;
    /**
     * 学生邮箱
     */
    private String stuEmail;
    /**
     * 学生学号
     */
    private String stuNum;
    /**
     * 家庭住址
     */
    private String stuAddress;
    /**
     * 学生照片
     */
    private String stuPhoto;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel;
    }

    public String getStuTel() {
        return stuTel;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuBirth(Date stuBirth) {
        this.stuBirth = stuBirth;
    }

    public Date getStuBirth() {
        return stuBirth;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuPhoto(String stuPhoto) {
        this.stuPhoto = stuPhoto;
    }

    public String getStuPhoto() {
        return stuPhoto;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public Student() {
    }

    public Student(String id, String stuName, String stuSex, String stuTel, String stuAge, Date stuBirth, String stuEmail, String stuNum, String stuAddress, String stuPhoto, String delFlag) {
        this.id = id;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuTel = stuTel;
        this.stuAge = stuAge;
        this.stuBirth = stuBirth;
        this.stuEmail = stuEmail;
        this.stuNum = stuNum;
        this.stuAddress = stuAddress;
        this.stuPhoto = stuPhoto;
        this.delFlag = delFlag;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("stuName", getStuName())
                .append("stuSex", getStuSex())
                .append("stuTel", getStuTel())
                .append("stuAge", getStuAge())
                .append("stuBirth", getStuBirth())
                .append("stuEmail", getStuEmail())
                .append("stuNum", getStuNum())
                .append("stuAddress", getStuAddress())
                .append("stuPhoto", getStuPhoto())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
