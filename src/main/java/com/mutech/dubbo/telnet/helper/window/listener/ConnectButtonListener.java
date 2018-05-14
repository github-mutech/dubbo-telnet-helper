package com.mutech.dubbo.telnet.helper.window.listener;


import com.mutech.dubbo.telnet.helper.window.WindowsApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author H
 */
public class ConnectButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            WindowsApp.setServiceDatas(WindowsApp.dubboTelnetUtil.initData(WindowsApp.getIp(), WindowsApp.getPort()));
        } catch (Exception exception) {
            WindowsApp.addConsoleTextAreaText("傻逼连不上,自检");
            exception.printStackTrace();
        }
        if (null != WindowsApp.getServiceDatas()) {
            WindowsApp.addConsoleTextAreaText("dubbo服务连接成功。。。");
            WindowsApp.setServiceTree(WindowsApp.getServiceDatas());
        }
    }
}
