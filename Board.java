import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Board extends JPanel implements ActionListener {
    private int dots;
    private Image apple;
    private boolean left=false,right=true,up=false,down=false,inGame=true;
    private Timer t1;
    private Image dot;
    private Image head;
    private final int Dot_Size=10;
    private final int Area=900,rand=29;
    private final int x[]=new int[Area],y[]=new int[Area];
    private int apple_x,apple_y;
    Board(){
//        changing bg color
        addKeyListener(new Tadapter());
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
    }
    locateApple();
    }
    public void locateApple(){
        apple_x = (int)(Math.random() * rand) * Dot_Size;
        apple_y = (int)(Math.random() * rand) * Dot_Size;
        t1=new Timer(140,this);
        t1.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        }
    public void draw(Graphics g) {
        if(inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
            for (int i = 0; i < dots; i++) {
                if (i == 0)
                    g.drawImage(head, x[i], y[i], this);
                else
                    g.drawImage(dot, x[i], y[i], this);
            }
            Toolkit.getDefaultToolkit().sync();
        }
        else{
            gameOver(g);
        }
    }
    public void gameOver(Graphics g){
        String msg = "Game Over!";
        Font font = new Font("SAN_SERIF", Font.BOLD, 14);
        FontMetrics metrices = getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg, (300 - metrices.stringWidth(msg)) / 2, 300/2);
    }

    public void move(){
        for(int i=dots;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if(left)
            x[0]-=Dot_Size;
        if(right)
            x[0]+=Dot_Size;
        if(up)
            y[0]-=Dot_Size;
        if(down)
            y[0]+=Dot_Size;
//        x[0]+=Dot_Size;
//        y[0]+=Dot_Size;
    }
    public void checkApple(){
        if(x[0]==apple_x && y[0]==apple_y){
            dots++;
            locateApple();
        }
    }
    public void checkCollision(){
        for(int i=dots;i>0;i--){
            if((i>4) && ((x[0]==x[i]) && y[0]==y[i]))
                inGame=false;}
            if(y[0]<0 || x[0]<0)
                inGame=false;
            if(y[0]>=300 || x[0]>=300)
                inGame=false;
        if(!inGame)
            t1.stop();

    }
    public void actionPerformed(ActionEvent e){
        if(inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }
    public class Tadapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
            if(key==KeyEvent.VK_LEFT && !right)
            {
                left = true;up=down=false;
            }
            if(key==KeyEvent.VK_RIGHT && !left)
            {
                right = true;up=down=false;
            }
            if(key==KeyEvent.VK_UP && !down)
            {
                up = true;left=right=false;
            }
            if(key==KeyEvent.VK_DOWN && !up)
            {
                down = true;left=right=false;
            }

        }
    }


}
