package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginComponent extends JComponent {
    @Override
    public void paintComponent(Graphics g) {
        try{
            Image background = ImageIO.read(new File("background.jpg"));
            g.drawImage(background,0,0,300,450,null);
            Image title = ImageIO.read(new File("标题.png"));
            g.drawImage(title,40,50,220,80,null);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
