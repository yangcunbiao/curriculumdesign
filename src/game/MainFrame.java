package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


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
    //双方棋子数
    private JLabel black;
    private JLabel white;
    //当前回合
    private JLabel turn;
    //再来一局的按钮
    JButton rmrematch;
    //继续游戏按钮
    JButton conButton;



    public MainFrame(JFrame frontFrame,boolean con,JButton conButton) throws FileNotFoundException {
        this.conButton=conButton;
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
        //TODO：双方棋子数
        black = new JLabel("黑棋数："+counter.getPlayerNum1());
        black.setFont(new Font("宋体", Font.BOLD, 20));
        black.setBounds(695,420,120,20);
        this.add(black);
        white = new JLabel("白棋数："+counter.getPlayerNum2());
        white.setFont(new Font("宋体", Font.BOLD, 20));
        white.setBounds(10,420,120,20);
        this.add(white);
        //TODO：当前回合
        turn = new JLabel("黑棋回合");
        turn.setFont(new Font("宋体", Font.BOLD, 40));
        turn.setBounds(315,45,200,40);
        this.add(turn);
        //TODO：初始化棋盘
        if(con==false){
            chessboardInit();
        }else{
            chessboardInit1();
        }
        //TODO:加入再来一局按钮
        rmrematch=new JButton();
        rmrematch.setBounds(300,300,140,50);
        rmrematch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.chessboardInit();
                MainFrame.this.rmrematch.setVisible(false);
            }
        });
        //this.add(rmrematch);
        rmrematch.setVisible(false);
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
    private void chessboardInit1() throws FileNotFoundException {
        Scanner in=new Scanner(new File("archive.txt"));
        in.nextLine();
        String color=in.nextLine();
        if(color.compareTo("NULL")==0) {
            nowColor = Color.NULL;
        }else if(color.compareTo("BLACK")==0){
            nowColor = Color.BLACK;
        }else if(color.compareTo("WHITE")==0){
            nowColor = Color.WHITE;
        }
        for(int i=0;i<8;i++){
            String line=in.nextLine();
            Scanner lineScanner=new Scanner(line);
            lineScanner.useDelimiter(" ");
            for(int j=0;j<8;j++){
                String chess=lineScanner.next();
                if(chess.compareTo("NULL")==0) {
                    chessboard[i][j] = Color.NULL;
                }else if(chess.compareTo("BLACK")==0){
                    chessboard[i][j] = Color.BLACK;
                }else if(chess.compareTo("WHITE")==0){
                    chessboard[i][j] = Color.WHITE;
                }
            }
        }
        in.close();
        int X=160,Y=160;
        for (int i=0;i<8;i++){
            X=160;
            for (int j=0;j<8;j++){
                chessIndex[i][j]=new Point2D.Double(X,Y);
                X+=chessboardFieldWidth;
            }
            Y+=chessboardFieldWidth;
        }
        counter.count(chessboard);
        if(nowColor == Color.BLACK){
            turn.setText("黑棋回合");
        }else {
            turn.setText("白棋回合");
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
//            for(int i=0;i<8;i++){
//                for (int j=0;j<8;j++){
//                    chessboard[i][j]=Color.endFlip(chessboard[i][j]);
//                }
//            }
            counter.count(chessboard);
            if(Judge.isStalemate(filpColor(nowColor),chessboard)) {
                nowColor = filpColor(nowColor);
            }
            if(Judge.judgeIsOver(chessboard)){
                counter.count(chessboard);
                if(counter.getPlayerNum1()>counter.getPlayerNum2()){
                    turn.setText("黑棋胜");
                }else if(counter.getPlayerNum1()<counter.getPlayerNum2()){
                    turn.setText("白棋胜");
                }else {
                    turn.setText("平局");
                }
                rmrematch.setVisible(true);
            }
            black.setText("黑棋数："+counter.getPlayerNum1());
            white.setText("白棋数："+counter.getPlayerNum2());
            if(nowColor == Color.BLACK){
                turn.setText("黑棋回合");
            }else {
                turn.setText("白棋回合");
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
        try {
            PrintWriter out=new PrintWriter("archive.txt");
            if(Judge.judgeIsOver(chessboard)){
                out.println("true");
                conButton.setVisible(false);
            }else{
                out.println("false");
                conButton.setVisible(true);
            }
            out.println(nowColor);
            for (Color[] a:chessboard){
                for(Color color:a){
                    out.print(color+" ");
                }
                out.println();
            }
            out.close();
            this.dispose();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

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
