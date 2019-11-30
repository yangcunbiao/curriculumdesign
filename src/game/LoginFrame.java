package game;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    //窗口的宽和高
    private final int width=250,height=450;
    public LoginFrame(){
        //TODO：设置窗口大小
        this.setSize(width,height);
        //TODO：设置图标和标题
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("ico.png"));
        this.setTitle("黑白棋");
        //TODO:显示窗口
        this.setVisible(true);
        //TODO：设置窗口关闭程序结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //TODO：居中
        this.center();
        //TODO：设置背景

        //TODO：
    }
    //TODO：居中用的函数
    private void center(){
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕的尺寸
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2-width/2,screenHeight/2-height/2);
    }
}
