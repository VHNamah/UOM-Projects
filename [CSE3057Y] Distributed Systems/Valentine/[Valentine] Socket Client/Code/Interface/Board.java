package Interface;

/* Created by Vidush Namah on 2/15/2017 */

import Packet.Type;
import Services.Sender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Board extends JPanel implements MouseListener, MouseMotionListener {

    private static volatile Graphics2D GDraw;
    public static Color Ink = Color.BLACK;
    public static boolean Status = false;

    public static Type Choice = null;
    public static ArrayList<Point2D> Points = new ArrayList<>();

    //LIST OF SHAPES
    private Shape Drawing = null;
    public static volatile ArrayList<Shape> Shapes = new ArrayList<>();

    public Board() {
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public synchronized void Draw(Shape S, Color C) {
        Ink = Color.BLACK;
        Shapes.add(S);
        repaint();
    }

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        GDraw = (Graphics2D) G;
        GDraw.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GDraw.setColor(Ink);
        for (Shape S : Shapes) GDraw.draw(S);
        if (Drawing != null) GDraw.draw(Drawing);

        Ink = Color.BLACK;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent E) {
        Status = true;
        if (Choice != null)
            Points.add(E.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent E) {
        if (Status) {
            Status = false;
            Points.clear();

            new Sender(Choice, Drawing, Ink);
            Drawing = null;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent E) {
        if (Status) {
            Point2D End = E.getPoint();
            Point2D Start = Points.get(0);

            switch (Choice) {
                default:
                    break;

                case LINE:
                    Drawing = new Line2D.Double(Start, End);
                    break;

                case CIRCLE:
                    double W1 = Start.getX() - End.getX();
                    double L1 = Start.getY() - End.getY();

                    W1 = W1 > 0 ? W1 : -1 * W1;
                    L1 = L1 > 0 ? L1 : -1 * L1;

                    Drawing = new Ellipse2D.Double(End.getX() < Start.getX() ? End.getX() : Start.getX(),
                            End.getY() < Start.getY() ? End.getY() : Start.getY(), W1, L1);
                    break;

                case SQUARE:
                    double W2 = Start.getX() - End.getX();
                    double L2 = Start.getY() - End.getY();

                    W2 = W2 > 0 ? W2 : -1 * W2;
                    L2 = L2 > 0 ? L2 : -1 * L2;

                    Drawing = new Rectangle2D.Double(Start.getX(), Start.getY(), W2, L2);
                    break;

                case FREE:
                    break;
            }

            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent E) {

    }
}