package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

public class DrawComponent extends JComponent {
    private Color[][] chessBoard=new Color[8][8];

    public void setChessBoard(Color[][] chessBoard) {
        this.chessBoard = chessBoard;
    }
    //棋子坐标
    private Point2D[][] chessIndex=new Point2D[8][8];

    public  DrawComponent(Color[][] chessBoard,Point2D[][] chessIndex){
        //TODO:把鼠标光标变为棋子
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image image = new ImageIcon("black1.png").getImage();
        Cursor cursor = tk.createCustomCursor(image, new Point(20, 20), "norm");
        //setCursor(cursor);
        //TODO：棋子坐标初始化.
        this.chessIndex=chessIndex;
        //TODO：棋盘初始化
        this.chessBoard=chessBoard;
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
        Image chessBoardImage=null,blackImage1=null,whiteImage1=null;

        try {
            chessBoardImage = ImageIO.read(new File("chessboard.png"));
            blackImage1 = ImageIO.read(new File("black1.png"));
            whiteImage1 = ImageIO.read(new File("white1.png"));
            g.drawImage(chessBoardImage,167,125,490,495,null);
            for (int i=0;i<8;i++){
                for (int j=0;j<8;j++){
                    if(chessBoard[i][j]==Color.BLACK)
                        g.drawImage(blackImage1,(int)chessIndex[i][j].getX(),(int)chessIndex[i][j].getY(),56,56,null);
                    if(chessBoard[i][j]==Color.WHITE)
                        g.drawImage(whiteImage1,(int)chessIndex[i][j].getX(),(int)chessIndex[i][j].getY(),56,56,null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
