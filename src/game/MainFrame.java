package game;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    //窗口的宽和高
    private final int mainWindowWidth=824,mainWondowHeight=738;
    public MainFrame(){
        //TODO:设置窗口的各种信息.
        //TODO：窗口大小
        this.setSize(mainWindowWidth,mainWondowHeight);
        this.setResizable(false);
        //TODO：窗口位置居中
        this.center();
        //TODO：设置图标和标题
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\curriculumdesign\\src\\picture\\ico.png"));
        this.setTitle("黑白棋");
        //TODO：设置窗口关闭程序结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
    //TODO：居中用的函数
    private void center(){
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕的尺寸
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2-mainWindowWidth/2,screenHeight/2-mainWondowHeight/2);
    }
}
