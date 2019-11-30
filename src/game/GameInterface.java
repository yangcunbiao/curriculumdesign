package game;

import javax.swing.*;
import java.awt.*;

public class GameInterface {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new LoginFrame();
            }
        });
    }
}
