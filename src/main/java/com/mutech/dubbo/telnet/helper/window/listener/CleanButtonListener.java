package com.mutech.dubbo.telnet.helper.window.listener;

import com.mutech.dubbo.telnet.helper.window.WindowsApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author H
 */
public class CleanButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ((JTextArea) WindowsApp.getParamsTabbedPane().getComponent(WindowsApp.getParamsTabbedPane().getSelectedIndex()))
                .setText(null);
    }
}
