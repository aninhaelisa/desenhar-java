import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Desenhar extends JFrame {

    boolean pressed = false;

    public Desenhar() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                pressed = false;
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                pressed = true;
                draw.clear();
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
            }
        });
        new Tempo().start();
        setSize(1200, 990);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    ArrayList<Draw> draw = new ArrayList<>();

    public class Draw {
        int x, y;

        public Draw(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void paint(Graphics g) {
        for (int i = 1; i < draw.size(); i++) {
            int x = draw.get(i).x;
            int y = draw.get(i).y;
            int x2 = draw.get(i - 1).x;
            int y2 = draw.get(i - 1).y;
            g.drawLine(x2, y2, x, y);
        }

    }

    public static void main(String[] args) {
        new Desenhar();
    }

    public class Tempo extends Thread {
        public void run() {
            while (true) {
                if (pressed) {
                    try {
                        Point ponto = getMousePosition();
                        draw.add(new Draw(ponto.x, ponto.y));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                repaint();

            }
        }
    }

}