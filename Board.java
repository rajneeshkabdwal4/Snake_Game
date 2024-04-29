import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private int dots;
    private Image apple;
    private Image dot;
    private Image head;
    private final int Dot_Size=10;
    private final int Area=900;
    private final int x[]=new int[Area],y[]=new int[Area];
    Board(){
//        changing bg color
        setBackground(Color.black);
        setFocusable(true);
        loadImages();
        initGame();
    }
    public void loadImages(){
        try {
            // Adjust the path to match your project structure
            apple = new ImageIcon(getClass().getResource("/icons/apple.png")).getImage();
            dot = new ImageIcon(getClass().getResource("/icons/dot.png")).getImage();
            head = new ImageIcon(getClass().getResource("/icons/head.png")).getImage();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("Failed to load images. Make sure the resource paths are correct.");
        }
    }

    public void initGame(){
//    initial length of snake
    dots=3;
    for(int i=0;i<dots;i++){
        y[i]=50;
        x[i]=50 - i*Dot_Size;
    }}
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        }
    public void draw(Graphics g) {
        for (int i = 0; i < dots; i++) {
            if (i == 0)
                g.drawImage(head, x[i], y[i], this);
            else
                g.drawImage(dot, x[i], y[i], this);
        }
        Toolkit.getDefaultToolkit().sync();
    }


}
