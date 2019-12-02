package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.security.KeyPair;
import java.util.ArrayList;


public class MainFrame extends JFrame implements MouseMotionListener, MouseListener, WindowListener {
    //计数器
    Counter counter=new Counter(2,2);
    //画图组件
    DrawComponent drawComponent=null;
    //鼠标坐标
    private int mouseIndexX,mouseIndexY;
    //棋子坐标
    private Point2D[][] chessIndex=new Point2D[8][8];
    //棋格宽度
    private int chessboardFieldWidth=62;
    //现在下的棋子颜色
    private Color nowColor=Color.BLACK;
    //存放棋子的数组
    private Color[][] chessboard=new Color[8][8];
    //窗口的宽和高
    private final int mainWindowWidth=824,mainWondowHeight=738;
    //上一个窗口
    JFrame frontFrame;

    public MainFrame(JFrame frontFrame){
        this.frontFrame=frontFrame;
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
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        chessboardInit();
        //TODO：加入画图组件
        drawComponent= new DrawComponent(chessboard,chessIndex);
        this.add(drawComponent);
        //TODO:加入鼠标事件和监听器
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        //TODO:加入窗口监听器
        this.addWindowListener(this);

    }
    //TODO：居中用的函数
    private void center(){
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕的尺寸
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2-mainWindowWidth/2,screenHeight/2-mainWondowHeight/2);
    }
    //TODO：初始化棋盘
    private void chessboardInit(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                chessboard[i][j] = Color.NULL;
            }
        }
        chessboard[3][3]=Color.WHITE;
        chessboard[3][4]=Color.BLACK;
        chessboard[4][3]=Color.BLACK;
        chessboard[4][4]=Color.WHITE;



        int X=160,Y=160;
        for (int i=0;i<8;i++){
            X=160;
            for (int j=0;j<8;j++){
                chessIndex[i][j]=new Point2D.Double(X,Y);
                X+=chessboardFieldWidth;
            }
            Y+=chessboardFieldWidth;
        }
    }
    //TODO:得到鼠标的棋盘坐标
    private Point getIndex(Point2D mouse){
        int i=0,j=0;
        Point index=new Point(100,100);
        for (;j<8;j++){
            if(mouse.getX()>=chessIndex[0][j].getX()&&mouse.getX()<chessIndex[0][j].getX()+chessboardFieldWidth) {
                for (; i < 8; i++) {
                    if(mouse.getY()>=chessIndex[i][j].getY()&&mouse.getY()<chessIndex[i][j].getY()+chessboardFieldWidth){
                        index=new Point(i,j);
                        return index;
                    }
                }
            }
        }
        return index;
    }

    //TODO:翻转棋子
    private void flipChess(int x,int y){
        for (int offsetX=-1;offsetX<=1;offsetX++){
            for (int offsetY=-1;offsetY<=1;offsetY++){
                if(offsetX==0&&offsetY==0)continue;
                int indexX=offsetX+x,indexY=offsetY+y;
                if(Judge.isInside(indexX,indexY)&&chessboard[indexX][indexY].isSame(nowColor)==false&&chessboard[indexX][indexY]!=Color.NULL){
                    int flipNum=1;
                    for (int i=indexX+offsetX,j=indexY+offsetY;Judge.isInside(i,j);i+=offsetX,j+=offsetY){
                        if(chessboard[i][j]==Color.NULL){
                            break;
                        }else if(chessboard[i][j]==nowColor){
                            for (int k=1;k<=flipNum;k++){
                                if(nowColor==Color.BLACK) {
                                    chessboard[x + k * offsetX][y + k * offsetY] = Color.WHITETOBLACK;
                                }else{
                                    chessboard[x + k * offsetX][y + k * offsetY] = Color.BLACKTOWHITE;
                                }
                            }
                            break;
                        }else {
                            flipNum++;
                        }
                    }
                }
            }
        }

    }
    //TODO:转换棋子颜色
    private Color filpColor(Color color){
        if (color.isSame(Color.WHITE)){
            return Color.BLACK;
        }else{
            return Color.WHITE;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX()+","+e.getY());
        Point index=getIndex(new Point2D.Double(e.getX(),e.getY()));
        mouseIndexX=(int)index.getX();
        mouseIndexY=(int)index.getY();
        if (Judge.judgeDrap(mouseIndexX, mouseIndexY, nowColor, chessboard)) {
            chessboard[mouseIndexX][mouseIndexY] = nowColor;
            System.out.println(1);
            flipChess(mouseIndexX, mouseIndexY);
            drawComponent.setchessboard(chessboard);
            drawComponent.flipChess(nowColor);
            if(Judge.isStalemate(filpColor(nowColor),chessboard)) {
                nowColor = filpColor(nowColor);
            }
            if(Judge.judgeIsOver(chessboard)){
                counter.count(chessboard);
                if(counter.getPlayerNum1()>counter.getPlayerNum2()){
                    System.out.println("黑棋胜");
                }else if(counter.getPlayerNum1()<counter.getPlayerNum2()){
                    System.out.println("白棋胜");
                }else {
                    System.out.println("平局");
                }
            }
        }
        System.out.println(nowColor);
        drawComponent.repaint();
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
        Point index=getIndex(new Point2D.Double(e.getX(),e.getY()));
        mouseIndexX=(int)index.getX();
        mouseIndexY=(int)index.getY();
        if (Judge.judgeDrap(mouseIndexX, mouseIndexY, nowColor, chessboard)){
            drawComponent.setCrossIndex(index);
            drawComponent.repaint();
        }else{
            drawComponent.setCrossIndex(null);
            drawComponent.repaint();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        frontFrame.setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
