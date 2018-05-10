package com.mutech.dubbo.telnet.helper.test;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;

public class Test {

    public static void main(String[] args) throws IOException {

        TelnetClient telnetClient = new TelnetClient("VT220");
        telnetClient.connect("localhost", 9032);
        // 输入命令流

        // 读取命令的流
        InputStream out = telnetClient.getInputStream();
        while (true){
//            (char)out.read();
        }

    }

}
