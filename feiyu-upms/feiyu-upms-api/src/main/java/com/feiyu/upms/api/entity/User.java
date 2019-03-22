package com.feiyu.upms.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author shudongping
 * @since 2019-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;

    private Integer errorCount;

    private String mobile;

    private Long deptId;

    private String imageUrl;

    private String userDesc;

    private Integer state;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}
