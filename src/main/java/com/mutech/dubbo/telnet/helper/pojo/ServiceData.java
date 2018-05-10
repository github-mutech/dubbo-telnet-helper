package com.mutech.dubbo.telnet.helper.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ServiceData {
    private String serviceName;
    private List<String> functionNames;
}
