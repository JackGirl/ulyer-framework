package com.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:31 2021/3/20
 */
@Data
@Accessors(chain = true)
public class User {

    private String id;

    private String name;

}
