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
    private Image[] images = new Image[11];
    //存棋盘上的棋子
    private Color[][] chessboard=new Color[8][8];
    //翻转棋子的集合
    private Point[] points;

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public void setchessboard(Color[][] chessboard) {
        this.chessboard = chessboard;
    }
    //棋子翻转
    private Timer timer=new Timer();
    //棋子翻转的图片下标
    private int sub=-1;
    //是否翻转的标志
    private boolean starFlip=false;
    //棋子坐标
    private Point2D[][] chessIndex;

    public  DrawComponent(Color[][] chessboard,Point2D[][] chessIndex){
        //TODO:把鼠标光标变为棋子
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image image = new ImageIcon("black1.png").getImage();
        Cursor cursor = tk.createCustomCursor(image, new Point(20, 20), "norm");
        //setCursor(cursor);
        //TODO：棋子坐标初始化.
        this.chessIndex=chessIndex;
        //TODO：棋盘初始化
        this.chessboard=chessboard;
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
        }catch (IOException e){
            e.printStackTrace();
        }
        TimerTask myTask=new TimerTask() {
            @Override
            public void run() {
                sub++;
                repaint();
                if(sub>9)cancel();
            }
        };
        timer.schedule(myTask,0,150);

    }

    @Override
    public void paintComponent(Graphics g){
        Image chessboardImage=null,blackImage1=null,whiteImage1=null;
        try {
            chessboardImage = ImageIO.read(new File("chessboard.png"));
            blackImage1 = ImageIO.read(new File("black1.png"));
            whiteImage1 = ImageIO.read(new File("white1.png"));
            g.drawImage(chessboardImage,127,105,null);
            if(starFlip) {
                g.drawImage(images[sub], (int) chessIndex[0][0].getX() - 9, (int) chessIndex[0][0].getY() - 31, null);
            }else {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (chessboard[i][j] == Color.BLACK)
                            g.drawImage(blackImage1, (int) chessIndex[i][j].getX() - 9, (int) chessIndex[i][j].getY() - 31, null);
                        if (chessboard[i][j] == Color.WHITE)
                            g.drawImage(whiteImage1, (int) chessIndex[i][j].getX() - 9, (int) chessIndex[i][j].getY() - 31, null);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
