package game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {
    //存放棋子的数组
    private Color[][] chessBoard;
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
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("ico.png"));
        this.setTitle("黑白棋");
        //TODO：设置窗口关闭程序结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //TODO:显示窗口
        this.setVisible(true);
        //TODO:设置背景图片
        ImageIcon img=new ImageIcon("background.jpg");
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        Image temp= img.getImage().getScaledInstance(screenSize.width,screenSize.height,img.getImage().SCALE_DEFAULT);
        img=new ImageIcon(temp);
        JLabel jLabelBackGround=new JLabel(img);
        jLabelBackGround.setBounds(0,0,screenSize.width,screenSize.height);
        this.getRootPane().add(jLabelBackGround);
        ((JPanel)this.getContentPane()).setOpaque(false);
        //TODO：加入画图组件
        DrawComponent drawComponent=new DrawComponent();
        this.add(drawComponent);
        //TODO：初始化棋盘
        this.chessBoard = chessBoardInit();
    }
    //TODO：居中用的函数
    private void center(){
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕的尺寸
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2-mainWindowWidth/2,screenHeight/2-mainWondowHeight/2);
    }
    //TODO：初始化棋盘
    private Color[][] chessBoardInit(){
        Color[][] chessBoard = new Color[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                chessBoard[i][j] = Color.NULL;
            }
        }
        chessBoard[4][4]=Color.WHITE;
        chessBoard[4][5]=Color.BLACK;
        chessBoard[5][4]=Color.BLACK;
        chessBoard[5][5]=Color.WHITE;
        return chessBoard;
    }
    //TODO：判断能否落子
    private boolean judge(int x,int y,int color){
        Chess[][] chesses = new Chess[8][8];
        int drapFlag = 0;
        if(chesses[x][y] != null) {
            //TODO：判断左
            if(chesses[x][y-1].getColor() != color && drapFlag !=0){
                for(int i=y-2;i>=0;i--){
                    if(chesses[x][i].getColor() == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断右
            if(chesses[x][y+1].getColor() != color && drapFlag !=0){
                for(int i=y+2;i<8;i++){
                    if(chesses[x][i].getColor() == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断上
            if(chesses[x-1][y].getColor() != color && drapFlag !=0){
                for(int i=x-2;i>=0;i--){
                    if(chesses[i][y].getColor() == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断下
            if(chesses[x+1][y].getColor() != color && drapFlag !=0){
                for(int i=x+2;i<8;i++){
                    if(chesses[i][y].getColor() == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断左上
            if(chesses[x-1][y-1].getColor() != color && drapFlag !=0){
                for(int i=2;x-i>=0&&y-i>=0;i++){
                    if(chesses[x-i][y-i].getColor() == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断右上
            if(chesses[x-1][y+1].getColor() != color && drapFlag !=0){
                for(int i=2;x-i>=0&&y+i<8;i++){
                    if(chesses[x-i][y+i].getColor() == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断左下
            if(chesses[x+1][y-1].getColor() != color && drapFlag !=0){
                for(int i=2;x+i<8&&y-i>=0;i++){
                    if(chesses[x+i][y-i].getColor() == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断右下
            if(chesses[x+1][y+1].getColor() != color && drapFlag !=0){
                for(int i=2;x+i<8&&y+i<8;i++){
                    if(chesses[x+i][y+i].getColor() == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
        }
        if(drapFlag == 1){
            return true;
        } else{
            return false;
        }
    }
    //TODO：判断输赢
    private void judgeIsWin(){

    }
}
