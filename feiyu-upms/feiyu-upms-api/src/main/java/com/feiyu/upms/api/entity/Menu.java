package com.feiyu.upms.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author shudongping
 * @since 2019-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String menuName;

    private String menuDesc;

    private Long parentId;

    private Integer menuType;

    private String permission;

    private String path;

    private Integer sort;

    private Integer keepAlive;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer state;


}
