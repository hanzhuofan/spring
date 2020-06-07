package com.bilibili.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hanzhuofan
 * @date 2020/5/23 11:
 */
@Data
@AllArgsConstructor
public class User {
  private Integer id;
  private String name;
  private Integer age;
}
