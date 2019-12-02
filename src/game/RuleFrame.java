package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class RuleFrame extends JFrame {
    //窗口的宽和高
    private final int width=700,height=500;
    public RuleFrame(){
        //TODO：设置窗口大小
        this.setSize(width,height);
        this.setResizable(false);
        //TODO：设置图标和标题
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("ico.png"));
        this.setTitle("规则说明");
        //TODO:显示窗口
        this.setVisible(true);
        //TODO：居中
        this.setLocationRelativeTo(null);
        //TODO:设置背景图片
        ImageIcon img=new ImageIcon("background.jpg");
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        Image temp= img.getImage().getScaledInstance(screenSize.width,screenSize.height,img.getImage().SCALE_DEFAULT);
        img=new ImageIcon(temp);
        JLabel jLabelBackGround=new JLabel(img);
        jLabelBackGround.setBounds(0,0,screenSize.width,screenSize.height);
        this.getRootPane().add(jLabelBackGround);
        ((JPanel)this.getContentPane()).setOpaque(false);
        //TODO：设置文本
        JTextArea rule = new JTextArea();
        rule.setFont(new java.awt.Font("楷体", 1, 20));
        rule.setLineWrap(true);
        byte[] b = new byte[2048];
        int n = 0;
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("规则.txt"));
            n = in.read(b);
        }catch (IOException e){
            e.printStackTrace();
        }
        rule.append(new String(b,0,n));
        //TODO：透明化
        rule.setOpaque(false);
        this.add(rule);
    }

}
