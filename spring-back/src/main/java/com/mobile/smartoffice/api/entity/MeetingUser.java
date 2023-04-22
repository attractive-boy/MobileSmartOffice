package com.mobile.smartoffice.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author attractiveboy
 * @since 2023-04-22
 */
@TableName("meeting_user")
public class MeetingUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会议ID
     */
    @TableField("meeting_id")
    private Integer meetingId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 人脸认证结果:0失败,1成功
     */
    @TableField("face_auth")
    private Boolean faceAuth;

    /**
     * 加入会议时间
     */
    @TableField("join_time")
    private LocalDateTime joinTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Boolean getFaceAuth() {
        return faceAuth;
    }

    public void setFaceAuth(Boolean faceAuth) {
        this.faceAuth = faceAuth;
    }
    public LocalDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public String toString() {
        return "MeetingUser{" +
            "id=" + id +
            ", meetingId=" + meetingId +
            ", userId=" + userId +
            ", faceAuth=" + faceAuth +
            ", joinTime=" + joinTime +
        "}";
    }
}
