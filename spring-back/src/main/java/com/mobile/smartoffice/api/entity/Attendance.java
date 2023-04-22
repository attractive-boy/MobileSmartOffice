package com.mobile.smartoffice.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author attractiveboy
 * @since 2023-04-22
 */
@TableName("attendance")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 考勤日期
     */
    @TableField("date")
    private LocalDate date;

    /**
     * 上班时间
     */
    @TableField("start_work_time")
    private LocalDateTime startWorkTime;

    /**
     * 下班时间
     */
    @TableField("end_work_time")
    private LocalDateTime endWorkTime;

    /**
     * 备注
     */
    @TableField("note")
    private String note;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalDateTime getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(LocalDateTime startWorkTime) {
        this.startWorkTime = startWorkTime;
    }
    public LocalDateTime getEndWorkTime() {
        return endWorkTime;
    }

    public void setEndWorkTime(LocalDateTime endWorkTime) {
        this.endWorkTime = endWorkTime;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Attendance{" +
            "id=" + id +
            ", userId=" + userId +
            ", date=" + date +
            ", startWorkTime=" + startWorkTime +
            ", endWorkTime=" + endWorkTime +
            ", note=" + note +
            ", createTime=" + createTime +
        "}";
    }
}
