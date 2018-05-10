package com.mutech.dubbo.telnet.helper.window.listener;

import com.mutech.dubbo.telnet.helper.window.WindowsApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TestButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = WindowsApp.getCommand();
        if (command == null) {
            WindowsApp.addConsoleTextAreaText("傻逼，先选服务！");
            return;
        }
        String inputTextAreaText = WindowsApp.getInputTextAreaText();
        if ("".equals(inputTextAreaText)) {
            WindowsApp.addConsoleTextAreaText("傻逼，填参数！");
            return;
        }
        command = "invoke ".concat(command).concat("(").concat(inputTextAreaText).concat(")");
        WindowsApp.dubboTelnetUtil.writeIn(command);
        try {
            WindowsApp.addConsoleTextAreaText(WindowsApp.dubboTelnetUtil.readOut());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
