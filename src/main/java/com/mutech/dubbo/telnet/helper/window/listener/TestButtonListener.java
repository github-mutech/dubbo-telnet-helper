package com.mutech.dubbo.telnet.helper.window.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mutech.dubbo.telnet.helper.window.WindowsApp;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author H
 */
public class TestButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = WindowsApp.getCommand();
        if (command == null) {
            WindowsApp.addConsoleTextAreaText("傻逼，先选服务！");
            return;
        }

        String commandParams = "";
        String commandParam;
        Component[] components = WindowsApp.getParamsTabbedPane().getComponents();
        for (int i = 0, size = components.length; i < size; i++) {


            commandParam = StringUtils.trimToNull(((JTextArea) ((JScrollPane) WindowsApp.getParamsTabbedPane()
                    .getComponents()[i]).getViewport().getView()).getText());
            if (commandParam == null) {
                WindowsApp.addConsoleTextAreaText("傻逼，填参数！");
                return;
            }
            if (i == size - 1) {
                commandParams = commandParams.concat(JSONObject.parseObject(commandParam).toJSONString());
                break;
            }
            try {
                commandParams = commandParams.concat(JSONObject.parseObject(commandParam).toJSONString()).concat(",");
            } catch (Exception exception) {
                WindowsApp.addConsoleTextAreaText("参数格式化错误!!!");
                return;
            }
        }

        command = "invoke ".concat(command).concat("(").concat(commandParams).concat(")");
        WindowsApp.dubboTelnetUtil.writeIn(command);
        try {
            WindowsApp.addConsoleTextAreaText(WindowsApp.dubboTelnetUtil.readOut());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
