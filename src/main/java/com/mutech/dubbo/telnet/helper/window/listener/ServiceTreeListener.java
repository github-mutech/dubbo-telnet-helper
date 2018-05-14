package com.mutech.dubbo.telnet.helper.window.listener;

import com.mutech.dubbo.telnet.helper.window.WindowsApp;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * @author H
 */
public class ServiceTreeListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        String path = e.getPath().toString();
        path = path.substring(1, path.length() - 1);
        String[] nodePath = path.split(", ");
        if (nodePath.length == 3) {
            WindowsApp.setCommand(nodePath[1] + "." + nodePath[2]);
            // 设置参数
            WindowsApp.setInputPanel(nodePath[1], nodePath[2]);
        } else {
            WindowsApp.setCommand(null);
        }
    }
}
