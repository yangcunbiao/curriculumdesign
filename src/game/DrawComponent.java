package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class DrawComponent extends JComponent {
    //翻转图片集
    private Image[] images = new Image[22];
    //存棋盘上的棋子
    private Color[][] chessboard=new Color[8][8];

    //十字光标坐标
    private Point crossIndex=null;
    //修改棋盘内容
    public void setchessboard(Color[][] chessboard) {
        for(int i=0;i<8;i++){
            for (int j=0;j<8;j++){
               this.chessboard[i][j]=chessboard[i][j];
            }
        }
    }
    //棋子翻转
    private Timer timer=new Timer();
    //棋子翻转的图片下标
    private int sub=-1;

    public void setCrossIndex(Point crossIndex) {
        this.crossIndex = crossIndex;
    }

    //棋子坐标
    private Point2D[][] chessIndex;


    public  DrawComponent(Color[][] chessboard, Point2D[][] chessIndex){
        //TODO:把鼠标光标变为棋子
//        Toolkit tk = Toolkit.getDefaultToolkit();
//        Image image = new ImageIcon("black1.png").getImage();
//        Cursor cursor = tk.createCustomCursor(image, new Point(20, 20), "norm");
//        setCursor(cursor);
        //TODO：棋子坐标初始化.
        this.chessIndex=chessIndex;
        //TODO：棋盘初始化
        for(int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                this.chessboard[i][j]=chessboard[i][j];
            }
        }
        //TODO：图片集初始化
        try {
            images[0] = ImageIO.read(new File("black1.png"));
            images[1] = ImageIO.read(new File("black2.png"));
            images[2] = ImageIO.read(new File("black3.png"));
            images[3] = ImageIO.read(new File("black4.png"));
            images[4] = ImageIO.read(new File("black5.png"));
            images[5] = ImageIO.read(new File("black6.png"));
            images[6] = ImageIO.read(new File("black7.png"));
            images[7] = ImageIO.read(new File("black8.png"));
            images[8] = ImageIO.read(new File("black9.png"));
            images[9] = ImageIO.read(new File("black10.png"));
            images[10] = ImageIO.read(new File("black11.png"));
            images[11] = ImageIO.read(new File("white1.png"));
            images[12] = ImageIO.read(new File("white2.png"));
            images[13] = ImageIO.read(new File("white3.png"));
            images[14] = ImageIO.read(new File("white4.png"));
            images[15] = ImageIO.read(new File("white5.png"));
            images[16] = ImageIO.read(new File("white6.png"));
            images[17] = ImageIO.read(new File("white7.png"));
            images[18] = ImageIO.read(new File("white8.png"));
            images[19] = ImageIO.read(new File("white9.png"));
            images[20] = ImageIO.read(new File("white10.png"));
            images[21] = ImageIO.read(new File("white11.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void flipChess(Color color){
        TimerTask myTask=new TimerTask() {
            @Override
            public void run() {
                sub++;
                repaint();
                if(sub>9){
                    cancel();
                    for (int i=0;i<8;i++){
                        for (int j=0;j<8;j++){
                            chessboard[i][j]=Color.endFlip(chessboard[i][j]);
                        }
                    }
                }
            }
        };
        TimerTask myTask1=new TimerTask() {
            @Override
            public void run() {
                sub++;
                repaint();
                if(sub>20) {
                    cancel();
                    for (int i=0;i<8;i++){
                        for (int j=0;j<8;j++){
                            chessboard[i][j]=Color.endFlip(chessboard[i][j]);
                        }
                    }
                }
            }
        };
        if(color==Color.BLACK){
            sub=10;
            timer.schedule(myTask1,0,50);
        }else{
            sub=-1;
            timer.schedule(myTask,0,50);
        }

    }
    @Override
    public void paintComponent(Graphics g){
        Image chessboardImage=null,blackImage1=null,whiteImage1=null,peopleImage1=null,peopleImage2=null;
        try {
            peopleImage1=ImageIO.read(new File("people1.png"));
            peopleImage2=ImageIO.read(new File("people2.png"));
            chessboardImage = ImageIO.read(new File("chessboard.png"));
            blackImage1 = ImageIO.read(new File("black1.png"));
            whiteImage1 = ImageIO.read(new File("white1.png"));
            g.drawImage(chessboardImage,127,105,null);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (chessboard[i][j] == Color.BLACK)
                        g.drawImage(blackImage1, (int) chessIndex[i][j].getX() - 9, (int) chessIndex[i][j].getY() - 31, null);
                    if (chessboard[i][j] == Color.WHITE)
                        g.drawImage(whiteImage1, (int) chessIndex[i][j].getX() - 9, (int) chessIndex[i][j].getY() - 31, null);
                    if(chessboard[i][j] == Color.BLACKTOWHITE || chessboard[i][j] == Color.WHITETOBLACK){
                        g.drawImage(images[sub], (int) chessIndex[i][j].getX() - 9, (int) chessIndex[i][j].getY() - 31, null);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(crossIndex!=null)drawCanDrap(chessIndex[crossIndex.x][crossIndex.y],g);
        g.drawImage(peopleImage1,0,222,null);
        g.drawImage(peopleImage2,678,222,null);
    }

    public void drawCanDrap(Point2D point,Graphics g){
        int x = (int) point.getX()+22;
        int y = (int) point.getY();
        g.setColor(java.awt.Color.RED);
        //TODO：画十字
        g.drawLine(x-5,y,x+5,y);
        g.drawLine(x,y-5,x,y+5);
        //TODO：画左上
        g.drawLine(x-29,y-29,x-22,y-29);
        g.drawLine(x-29,y-29,x-29,y-22);
        //TODO：画右上
        g.drawLine(x+29,y-29,x+22,y-29);
        g.drawLine(x+29,y-29,x+29,y-22);
        //TODO：画左下
        g.drawLine(x-29,y+29,x-22,y+29);
        g.drawLine(x-29,y+29,x-29,y+22);
        //TODO：画右下
        g.drawLine(x+29,y+29,x+22,y+29);
        g.drawLine(x+29,y+29,x+29,y+22);
    }
}
