package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

public class DrawComponent extends JComponent {
    private Color[][] chessboard=new Color[8][8];

    public void setchessboard(Color[][] chessboard) {
        this.chessboard = chessboard;
    }
    //棋子坐标
    private Point2D[][] chessIndex=new Point2D[8][8];

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
    }
//    private void chessIndexInit(){
//        int X=188,Y=146;
//        for (int i=0;i<8;i++){
//            X=188;
//            for (int j=0;j<8;j++){
//                chessIndex[i][j]=new Point2D.Double(X,Y);
//                X+=56;
//            }
//            Y+=56;
//        }
//    }
    @Override
    public void paintComponent(Graphics g){
        Image chessboardImage=null,blackImage1=null,whiteImage1=null;

        try {
            chessboardImage = ImageIO.read(new File("chessboard.png"));
            blackImage1 = ImageIO.read(new File("black1.png"));
            whiteImage1 = ImageIO.read(new File("white1.png"));
            g.drawImage(chessboardImage,167,125,490,495,null);
            for (int i=0;i<8;i++){
                for (int j=0;j<8;j++){
                    if(chessboard[i][j]==Color.BLACK)
                        g.drawImage(blackImage1,(int)chessIndex[i][j].getX(),(int)chessIndex[i][j].getY(),56,56,null);
                    if(chessboard[i][j]==Color.WHITE)
                        g.drawImage(whiteImage1,(int)chessIndex[i][j].getX(),(int)chessIndex[i][j].getY(),56,56,null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawCanDrap(Point2D point,Graphics g){
        int x = (int) point.getX()+28;
        int y = (int) point.getY()+28;
        g.setColor(java.awt.Color.RED);
        //TODO：画十字
        g.drawLine(x-5,y,x+5,y);
        g.drawLine(x,y-5,x,y+5);
        //TODO：画左上
        g.drawLine(x-26,y-26,x-21,y-26);
        g.drawLine(x-26,y-26,x-26,y-21);
        //TODO：画右上
        g.drawLine(x+26,y-26,x+21,y-26);
        g.drawLine(x+26,y-26,x+26,y-21);
        //TODO：画左下
        g.drawLine(x-26,y+26,x-21,y+26);
        g.drawLine(x-26,y+26,x-26,y+21);
        //TODO：画右下
        g.drawLine(x+26,y+26,x+21,y+26);
        g.drawLine(x+26,y+26,x+26,y+21);
    }
}
