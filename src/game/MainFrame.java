package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.security.KeyPair;


public class MainFrame extends JFrame implements MouseMotionListener, MouseListener {
    //鼠标坐标
    private int mouseIndexX,mouseIndexY;
    //棋子坐标
    private Point2D[][] chessIndex=new Point2D[8][8];
    //棋格宽度
    private int chessBoardFieldWidth=56;
    //现在下的棋子颜色
    private Color nowColor=Color.BLACK;
    //存放棋子的数组
    private Color[][] chessBoard=new Color[8][8];
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
        //TODO：初始化棋盘
        chessBoardInit();
        //TODO：加入画图组件
        DrawComponent drawComponent= new DrawComponent(chessBoard,chessIndex);
        this.add(drawComponent);

    }
    //TODO：居中用的函数
    private void center(){
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕的尺寸
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2-mainWindowWidth/2,screenHeight/2-mainWondowHeight/2);
    }
    //TODO：初始化棋盘
    private void chessBoardInit(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                chessBoard[i][j] = Color.NULL;
            }
        }
        chessBoard[3][3]=Color.WHITE;
        chessBoard[3][4]=Color.BLACK;
        chessBoard[4][3]=Color.BLACK;
        chessBoard[4][4]=Color.WHITE;

        int X=188,Y=146;
        for (int i=0;i<8;i++){
            X=188;
            for (int j=0;j<8;j++){
                chessIndex[i][j]=new Point2D.Double(X,Y);
                X+=chessBoardFieldWidth;
            }
            Y+=chessBoardFieldWidth;
        }
    }
    private Point getIndex(Point2D mouse){
        int i=0,j=0;
        Point index=new Point(100,100);
        for (;i<8;i++){
            if(mouse.getX()>=chessIndex[i][0].getX()&&mouse.getX()<chessIndex[i][0].getX()+chessBoardFieldWidth) {
                for (; j < 8; j++) {
                    if(mouse.getY()>=chessIndex[i][j].getY()&&mouse.getY()<chessIndex[i][j].getY()+chessBoardFieldWidth){
                        index=new Point(i,j);
                    }
                }
            }
        }
        return index;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
