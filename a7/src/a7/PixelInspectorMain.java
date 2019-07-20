package a7;

import java.awt.*;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PixelInspectorMain {
    public static void main(String[] args) throws IOException {

        //This Movie is awesome!!
        Picture three_billborad = A7Helper.readFromURL("https://plymouthartscentre.org/wp-content/uploads/2018/02/three-billboards-outside-ebbing-missouri-2696_8-1024x576.jpg");

        JFrame main_frame = new JFrame();
        main_frame.setTitle("Three Billboard Outside Ebbing Missouri");
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO Auto-generated method stub
        PixelInspectorWidget widget = new PixelInspectorWidget(three_billborad);



        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(widget, BorderLayout.CENTER);
        main_frame.setContentPane(panel);

        main_frame.pack();
        main_frame.setVisible(true);

    }
}
