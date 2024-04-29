import javax.swing.*;
public class SnakeGame extends JFrame{
//    default const
    SnakeGame(){
//        super keyword for the Title of frame
        super("Snake Game");
        add (new Board());
//        pack fn used for refreshing the frame while it is still running
        pack();


//        setting dimensions of frame
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    public static void main (String[] args){
        new SnakeGame();
    }
}
