package a7;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DecimalFormat;

public class PixelInspectorWidget extends JPanel implements MouseListener {
    private Picture _picture;
    private PictureView _picture_view;

    private JLabel x;
    private JLabel y;
    private JLabel r;
    private JLabel g;
    private JLabel b;
    private JLabel brightness;

    DecimalFormat df = new DecimalFormat("0.00");

    public PixelInspectorWidget(Picture picture){
        setLayout(new BorderLayout());
        ObservablePicture obs = picture.createObservable();
        _picture_view = new PictureView(obs);//
        _picture_view.addMouseListener(this);
        add(_picture_view, BorderLayout.CENTER);


        x = new JLabel("X: ");
        y = new JLabel("Y: ");
        r = new JLabel("Red: ");
        g = new JLabel("Green: ");
        b = new JLabel("Blue: ");
        brightness = new JLabel("Brightness: ");

        JPanel left_side = new JPanel();
        left_side.setLayout(new GridLayout(6,1));

        left_side.add(x);
        left_side.add(y);
        left_side.add(r);
        left_side.add(g);
        left_side.add(b);
        left_side.add(brightness);

        add(left_side,BorderLayout.WEST);

        _picture = picture;


    }

    public void mousePressed(MouseEvent e) {
        x.setText("X: " + e.getX());
        y.setText("Y: " + e.getY());

        r.setText("Red: " + df.format(_picture.getPixel(e.getX(), e.getY()).getRed()));
        g.setText("Greed: " + df.format(_picture.getPixel(e.getX(), e.getY()).getGreen()));
        b.setText("Blue: " + df.format(_picture.getPixel(e.getX(), e.getY()).getBlue()));

        brightness.setText("Brightness: "+df.format(_picture.getPixel(e.getX(), e.getY()).getIntensity()));

    }



    @Override
    public void mouseClicked(MouseEvent e) {
        //leave it blank
        //Invoked when the mouse button has been clicked (pressed and released) on a component.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //leave it blank
        //Invoked when a mouse button has been released on a component.

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //leave it blank
        //Invoked when the mouse enters a component.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //leave it blank
        //Invoked when the mouse exits a component.

    }
}
