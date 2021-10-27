package com.yh.mybatisplus.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户实体对应表 user
 */
@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 租户 ID
     */
    private Long tenantId;
    private String name;

    @TableField(exist = false)
    private String addrName;

}
/*
    DROP TABLE IF EXISTS user;

        CREATE TABLE user
        (
        id BIGINT(20) NOT NULL COMMENT '主键ID',
        tenant_id BIGINT(20) NOT NULL COMMENT '租户ID',
        name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
        PRIMARY KEY (id)
        );

        DROP TABLE IF EXISTS user_addr;

        CREATE TABLE USER_ADDR
        (
        id BIGINT(20) NOT NULL COMMENT '主键ID',
        user_id BIGINT(20) NOT NULL COMMENT 'user.id',
        name VARCHAR(30) NULL DEFAULT NULL COMMENT '地址名称',
        PRIMARY KEY (id)
        );

        */
