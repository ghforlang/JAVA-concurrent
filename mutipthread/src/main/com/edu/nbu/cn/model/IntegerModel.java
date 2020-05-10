package com.edu.nbu.cn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class IntegerModel {
    /**
     * 值
     */
    volatile Integer value;
    /**
     * 描述
     */
    String desc;
}
