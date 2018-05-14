package com.mutech.dubbo.telnet.helper.window.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mutech.dubbo.telnet.helper.window.WindowsApp;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author H
 */
public class FormatButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea textArea = (JTextArea) ((JScrollPane) WindowsApp.getParamsTabbedPane().getComponent(WindowsApp
                .getParamsTabbedPane().getSelectedIndex())).getViewport().getView();
        String josn = StringUtils.trimToNull(textArea.getText());
        if (josn == null) {
            WindowsApp.addConsoleTextAreaText("格式化失败，参数不得为空");
            return;
        }
        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.parseObject(josn);
        } catch (Exception exception) {
            WindowsApp.addConsoleTextAreaText("格式化失败，请检查数据格式");
            return;
        }
        textArea.setText(JSON.toJSONString(jsonObject, true));
    }
}
