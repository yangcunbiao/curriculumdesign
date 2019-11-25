package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class DrawComponent extends JComponent implements MouseListener, MouseMotionListener {
    private int mouseIndexX,mouseIndexY;
    public  DrawComponent(){
        //TODO:把鼠标光标变为棋子
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image image = new ImageIcon("D:\\curriculumdesign\\src\\picture\\black1.png").getImage();
        Cursor cursor = tk.createCustomCursor(image, new Point(16, 16), "norm");
        setCursor(cursor);
    }
    @Override
    public void paintComponent(Graphics g){

        Image image=null;
        try {
            image= ImageIO.read(new File("D:\\curriculumdesign\\src\\picture\\chessboard.png"));
            //image= ImageIO.read(new File("D:\\curriculumdesign\\src\\picture\\background.jpg"));
            g.drawImage(image,167,125,490,495,null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
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
        mouseIndexX=e.getX();
        mouseIndexY=e.getY();
        repaint();
    }
}
