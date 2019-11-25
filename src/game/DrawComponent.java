package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawComponent extends JComponent implements MouseListener, MouseMotionListener {
    private int mouseIndexX,mouseIndexY;
    private Point2D[][] chessIndex=new Point2D[8][8];
    public  DrawComponent(){
        //TODO:加入鼠标事件和监听器
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        //TODO:把鼠标光标变为棋子
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image image = new ImageIcon("D:\\curriculumdesign\\src\\picture\\black1.png").getImage();
        Cursor cursor = tk.createCustomCursor(image, new Point(20, 20), "norm");
        //setCursor(cursor);
        //TODO：棋子坐标初始化
        chessIndexInit();

    }
    private void chessIndexInit(){
        int X=188,Y=146;
        for (int i=0;i<8;i++){
            X=188;
            for (int j=0;j<8;j++){
                chessIndex[i][j]=new Point2D.Double(X,Y);
                X+=56;
            }
            Y+=56;
        }
    }
    @Override
    public void paintComponent(Graphics g){
        Image chessBoardImage=null,blackImage1=null,whiteImage1=null;

        try {
            chessBoardImage = ImageIO.read(new File("D:\\curriculumdesign\\src\\picture\\chessboard.png"));
            blackImage1 = ImageIO.read(new File("D:\\curriculumdesign\\src\\picture\\black1.png"));
            whiteImage1 = ImageIO.read(new File("D:\\curriculumdesign\\src\\picture\\white1.png"));
            g.drawImage(chessBoardImage,167,125,490,495,null);
            for (int i=0;i<8;i++){
                for (int j=0;j<8;j++){
                    g.drawImage(blackImage1,(int)chessIndex[i][j].getX(),(int)chessIndex[i][j].getY(),56,56,null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX()+","+e.getY());
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
