package com.mutech.dubbo.telnet.helper.pojo;

import lombok.Data;

import java.util.List;

@Data
public class FunctionData {
    private String functionName;
    private List<String> functionParams;
}
