package com.mutech.dubbo.telnet.helper;


import com.mutech.dubbo.telnet.helper.window.WindowsApp;

import java.awt.*;

/**
 * @author H
 */
public class DubboTelnetHelperApplication {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new WindowsApp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
