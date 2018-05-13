package com.mutech.dubbo.telnet.helper.window;

import com.mutech.dubbo.telnet.helper.pojo.ServiceData;
import com.mutech.dubbo.telnet.helper.util.DubboTelnetUtil;
import com.mutech.dubbo.telnet.helper.window.listener.ConnectButtonListener;
import com.mutech.dubbo.telnet.helper.window.listener.ServiceTreeListener;
import com.mutech.dubbo.telnet.helper.window.listener.TestButtonListener;


import javax.swing.*;
import java.awt.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

/**
 * @author H
 */
public class WindowsApp {

    private static final Font LABEL_FONT = new Font("微软雅黑", Font.PLAIN, 20);
    private static final Font TEXT_FIELD_FONT = new Font("微软雅黑", Font.PLAIN, 20);
    private static final Font BUTTON_FONT = new Font("微软雅黑", Font.PLAIN, 20);
    private static final Font TABBED_PANE_FONT = new Font("微软雅黑", Font.PLAIN, 12);

    private static String command;
    private static JTextField ipTextField;
    private static JTextField portTextField;
    private static JTree serviceTree;
    private static JTextArea inputTextArea;
    private static JTextArea consoleTextArea;
    public static DubboTelnetUtil dubboTelnetUtil = new DubboTelnetUtil();

    /**
     * Create the application.
     */
    public WindowsApp() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        JFrame frmDubbo = new JFrame();
        frmDubbo.setResizable(false);
        frmDubbo.setType(Window.Type.UTILITY);
        frmDubbo.setTitle("DUBBO服务测试工具");
        frmDubbo.setBounds(100, 100, 900, 800);
        frmDubbo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        frmDubbo.getContentPane().add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new GridLayout(1, 0, 0, 0));

        /* ********************  ipLabel ******************** */
        JLabel ipLabel = new JLabel("IP");
        ipLabel.setFont(LABEL_FONT);
        ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(ipLabel);

        /* ********************  ipTextField ******************** */
        ipTextField = new JTextField();
        ipTextField.setFont(TEXT_FIELD_FONT);
        topPanel.add(ipTextField);
        ipTextField.setColumns(10);

        /* ********************  protLabel ******************** */
        JLabel protLabel = new JLabel("PORT");
        protLabel.setFont(LABEL_FONT);
        protLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(protLabel);

        /* ********************  portTextField ******************** */
        portTextField = new JTextField();
        portTextField.setFont(TEXT_FIELD_FONT);
        topPanel.add(portTextField);
        portTextField.setColumns(10);

        /* ********************  connectButton ******************** */
        JButton connectButton = new JButton("连接");
        connectButton.setFont(BUTTON_FONT);
        connectButton.setBackground(Color.GRAY);
        connectButton.addActionListener(new ConnectButtonListener());
        topPanel.add(connectButton);

        /* ********************  testButton ******************** */
        JButton testButton = new JButton("测试");
        testButton.setFont(BUTTON_FONT);
        testButton.setBackground(Color.GRAY);
        testButton.addActionListener(new TestButtonListener());
        topPanel.add(testButton);

        /* ********************  centerPanel ******************** */
        JPanel centerPanel = new JPanel();
        frmDubbo.getContentPane().add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(3, 1, 0, 0));

        /* ********************  servicesTabbedPane ******************** */
        JTabbedPane servicesTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        servicesTabbedPane.setFont(TABBED_PANE_FONT);
        centerPanel.add(servicesTabbedPane);
        serviceTree = new JTree();
        serviceTree.setModel(null);
        serviceTree.setForeground(Color.GRAY);
        serviceTree.addTreeSelectionListener(new ServiceTreeListener());
        servicesTabbedPane.addTab("服务", null, serviceTree, null);

        /* ********************  inputTabbedPane ******************** */
        JTabbedPane inputTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        inputTabbedPane.setFont(TABBED_PANE_FONT);
        centerPanel.add(inputTabbedPane);
        inputTextArea = new JTextArea();
        inputTextArea.setBackground(Color.LIGHT_GRAY);
        inputTabbedPane.addTab("请求参数", null, inputTextArea, null);

        /* ********************  consoleTabbedPane ******************** */
        JTabbedPane consoleTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        consoleTabbedPane.setFont(TABBED_PANE_FONT);
        centerPanel.add(consoleTabbedPane);
        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        consoleTextArea.setBackground(Color.GRAY);
        consoleTabbedPane.addTab("控制台", null, consoleTextArea, null);

        frmDubbo.setVisible(true);
    }

    public static String getIp() {
        return ipTextField.getText().trim();
    }

    public static int getPort() {
        return Integer.parseInt(portTextField.getText().trim());
    }

    public static void setConsoleTextAreaText(String consoleTextAreaText) {
        System.out.println(consoleTextAreaText);
        WindowsApp.consoleTextArea.setText(consoleTextAreaText);
    }

    public static void addConsoleTextAreaText(String consoleTextAreaText) {
        System.out.println(consoleTextAreaText);
        WindowsApp.consoleTextArea.setText(WindowsApp.consoleTextArea.getText() + "\n" + consoleTextAreaText);
    }

    public static void setServiceTreeData(List<ServiceData> serviceDatas) {


        DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(getIp() + ":" + getPort());
        DefaultTreeModel defaultTreeModel = new DefaultTreeModel(defaultMutableTreeNode);
        for (ServiceData serviceData : serviceDatas) {
            DefaultMutableTreeNode serviceTreeNode = new DefaultMutableTreeNode(serviceData.getServiceName());
            for (String functionName : serviceData.getFunctionNames()) {
                DefaultMutableTreeNode functionTreeNode = new DefaultMutableTreeNode(functionName);
                serviceTreeNode.add(functionTreeNode);
            }
            defaultMutableTreeNode.add(serviceTreeNode);
        }
        serviceTree.setModel(defaultTreeModel);
    }

    public static String getCommand() {
        return command;
    }

    public static void setCommand(String command) {
        WindowsApp.command = command;
    }

    public static String getInputTextAreaText() {
        return inputTextArea.getText().trim();
    }

    public static void setInputTextArea(JTextArea inputTextArea) {
        WindowsApp.inputTextArea = inputTextArea;
    }

}
