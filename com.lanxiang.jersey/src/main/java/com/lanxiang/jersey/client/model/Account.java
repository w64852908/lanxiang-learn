package com.lanxiang.jersey.client.model;

import lombok.Data;

/**
 * Created by lanxiang on 2017/3/4.
 */

@Data
public class Account {

    private Long id;

    private Long tenantId;

    private String phone;

    private String name;
}
