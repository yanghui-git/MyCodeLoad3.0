package com.yh.mybatisplus.demouser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@TableName("demo_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoUser {

    @TableId(type = IdType.AUTO)
    private long id;

    private String name;

    private Integer age;

    private Date createTime;

    private Date updateTime;

}
