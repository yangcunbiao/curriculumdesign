package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginFrame extends JFrame {
    //窗口的宽和高
    private final int width=310,height=480;
    public LoginFrame() throws FileNotFoundException {
        //TODO：设置窗口大小
        this.setSize(width,height);
        this.setResizable(false);
        //TODO：设置图标和标题
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("ico.png"));
        this.setTitle("黑白棋");
        //TODO:显示窗口
        this.setVisible(true);
        //TODO：设置窗口关闭程序结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //TODO：居中
        this.center();
        //TODO：设置按钮
        Scanner in =new Scanner(new File("archive.txt"));
        JButton con =new JButton(new ImageIcon("continue1.png"));
        con.setPressedIcon(new ImageIcon("continue2.png"));
        con.setBounds(80,150,140,50);
        con.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new MainFrame(LoginFrame.this,true,con);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                LoginFrame.this.setVisible(false);
            }
        });
        if(in.nextLine().compareTo("false")==0){
            con.setVisible(true);
        }else{
            con.setVisible(false);
        }
        in.close();
        //TODO：按钮透明化
        con.setContentAreaFilled(false);
        //TODO：去边框
        con.setBorderPainted(false);
        this.add(con);
        JButton start = new JButton(new ImageIcon("start1.png"));
        start.setPressedIcon(new ImageIcon("start2.png"));
        start.setBounds(80,210,140,50);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new MainFrame(LoginFrame.this,false,con);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                LoginFrame.this.setVisible(false);
            }
        });
        //TODO：按钮透明化
        start.setContentAreaFilled(false);
        //TODO：去边框
        start.setBorderPainted(false);
        this.add(start);

        JButton rule = new JButton(new ImageIcon("description1.png"));
        rule.setPressedIcon(new ImageIcon("description2.png"));
        rule.setBounds(80,270,140,50);
        rule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new RuleFrame();
            }
        });
        //TODO：按钮透明化
        rule.setContentAreaFilled(false);
        //TODO：去边框
        rule.setBorderPainted(false);
        this.add(rule);

        JButton end = new JButton(new ImageIcon("exit1.png"));
        end.setPressedIcon(new ImageIcon("exit2.png"));
        end.setBounds(80,330,140,50);
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });
        //TODO：按钮透明化
        end.setContentAreaFilled(false);
        //TODO：去边框
        end.setBorderPainted(false);
        this.add(end);
        this.add(new LoginComponent());
}
    //TODO：居中用的函数
    private void center(){
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕的尺寸
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2-width/2,screenHeight/2-height/2);
    }
}
