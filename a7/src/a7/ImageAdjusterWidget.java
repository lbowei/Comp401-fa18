package a7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class ImageAdjusterWidget extends JPanel implements ChangeListener {
    private Picture _picture;
    private PictureView _picture_view;


    private JPanel sliders;

    private int blur_start = 0;
    private int saturation_start = 0;
    private int brightness_start = 0;

    private JSlider _blur_slider;
    private JSlider _saturation_slider;
    private JSlider _brightness_slider;


    private JLabel blur_label;
    private JLabel saturation_label;
    private JLabel brightness_label;



    public ImageAdjusterWidget (Picture picture) {
        setLayout(new BorderLayout());
        ObservablePicture obs = picture.createObservable();
        _picture_view = new PictureView(obs);//
        add(_picture_view,BorderLayout.CENTER);

       _picture=picture;

        sliders =new JPanel();
        sliders.setLayout(new GridLayout(3,1));

        _blur_slider = new JSlider(0,5,0);
        _saturation_slider = new JSlider(-100,100,0);
        _brightness_slider = new JSlider(-100,100,0);


        _blur_slider.setMajorTickSpacing(1);
        _blur_slider.setPaintTicks(true);
        _blur_slider.setPaintLabels(true);
        _blur_slider.setSnapToTicks(true);
        _blur_slider.addChangeListener(this);

        _saturation_slider.setMajorTickSpacing(25);
        _saturation_slider.setPaintTicks(true);
        _saturation_slider.setPaintLabels(true);
        _saturation_slider.setSnapToTicks(true);
        _saturation_slider.addChangeListener(this);

        _brightness_slider.setMajorTickSpacing(25);
        _brightness_slider.setPaintTicks(true);
        _brightness_slider.setPaintLabels(true);
        _brightness_slider.setSnapToTicks(true);
        _brightness_slider.addChangeListener(this);



        blur_label = new JLabel("Blur: ");
        saturation_label = new JLabel("Saturation: ");
        brightness_label = new JLabel("Brightness: ");

        sliders.add(blur_label);
        sliders.add(_blur_slider);
        sliders.add(saturation_label);
        sliders.add(_saturation_slider);
        sliders.add(brightness_label);
        sliders.add(_brightness_slider);
        add(sliders, BorderLayout.SOUTH);


    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if (!_blur_slider.getValueIsAdjusting() && !_saturation_slider.getValueIsAdjusting() && !_brightness_slider.getValueIsAdjusting()) {

            Picture af_blur = blurMethod(_picture, _blur_slider.getValue());
            Picture af_saturate = saturateMethod( af_blur,_saturation_slider.getValue());
            Picture af_bright = brightnessMethod(af_saturate,_brightness_slider.getValue());

            ObservablePicture obsPic = af_bright.createObservable();
            _picture_view.setPicture(obsPic);

        }
    }


        public Picture saturateMethod(Picture picture, int factor){
        Pixel [][] saturate_picture = pixHelp(picture);
        double red;
        double blue;
        double green;

        for(int i = 0; i< picture.getWidth(); i++){
            for(int j = 0 ;j< picture.getHeight();j++){

                if(factor == 0){
                    saturate_picture[i][j] = saturate_picture[i][j];

                }
                //For saturation factors from -100 to 0
                else if(factor<0){
                    //intensity = saturate_picture[i][j].getIntensity();

                    double sa_factor = (1.0 + (factor / 100.0)) - (saturate_picture[i][j].getIntensity() * factor / 100.0);

                    red = saturate_picture[i][j].getRed()*sa_factor;
                    green = saturate_picture[i][j].getGreen()*sa_factor;
                    blue = saturate_picture[i][j].getBlue() *sa_factor;

                    saturate_picture[i][j] = new ColorPixel(red,green,blue);

                }
                //For satruation facgtors >0
                else {
                    // make flag the maximum of the three.
                    double flag;

                    if(saturate_picture[i][j].getRed() > saturate_picture[i][j].getBlue()){
                        flag = saturate_picture[i][j].getRed();
                    }else{
                        flag = saturate_picture[i][j].getBlue();
                    }
                    if(flag > saturate_picture[i][j].getGreen()){
                        flag = flag;
                    }else{
                        flag = saturate_picture[i][j].getGreen();
                    }

                    double sa_factor =((flag + ((1.0 - flag) * (factor / 100.0))) / flag);
                    red= saturate_picture[i][j].getRed()* sa_factor;
                    blue= saturate_picture[i][j].getRed()* sa_factor;
                    green= saturate_picture[i][j].getRed()* sa_factor;

                    saturate_picture[i][j] = new ColorPixel(red,green,blue);

                }
            }
        }
        return toPicture(saturate_picture);

    }


        public Picture brightnessMethod(Picture picture, double factor){
        Pixel [][] bright_picture = pixHelp(picture);

        for(int i = 0;i<picture.getWidth();i++){
            for(int j=0;j<picture.getHeight();j++){

                if(factor >=0){
                    bright_picture[i][j] = bright_picture[i][j].lighten(factor/100.0);

                }else {
                    bright_picture[i][j] = bright_picture[i][j].darken(Math.abs(factor/100.0));
                }
            }
        }

        return toPicture(bright_picture);
    }
    public Picture blurMethod(Picture picture, int factor) {
        //change pic to pixel and change it back at last.
        Pixel[][] blurred_picture= pixHelp(picture);

        for(int i=0;i<picture.getWidth();i++) {
            for(int j=0;j<picture.getHeight();j++) {

                double red=0;
                double blue=0;
                double green=0;

                int left = i-factor;
                int right = i+factor;
                int top = j-factor;
                int bottom = j+factor;

                int divider=0;


                for(int x=left;x<=right;x++) {
                    for(int y=top;y<=bottom;y++) {
                        try{
                            red = red + blurred_picture[x][y].getRed();
                            blue = blue + blurred_picture[x][y].getBlue();
                            green = green +blurred_picture[x][y].getGreen();
                            divider++;
                        }catch(RuntimeException e) {
                            //
                        }
                    }
                }

                red = red / divider;
                green = green / divider;
                blue = blue / divider;

                blurred_picture[i][j] = new ColorPixel(red,green,blue);
            }
        }

        return toPicture(blurred_picture);

    }



    //picture to pixel, help method
    public Pixel[][] pixHelp(Picture picture) {
        Pixel[][] return_pix = new Pixel[picture.getWidth()][picture.getHeight()];

        for (int i = 0; i < picture.getWidth(); i++) {
            for (int j = 0; j < picture.getHeight(); j++) {

                return_pix[i][j] = picture.getPixel(i, j);
            }
        }
        return return_pix;
    }


    //pixel to picture, help method
    public Picture toPicture(Pixel[][] pixel) {

        Picture return_pic = new MutablePixelArrayPicture(pixel,_picture.getCaption());

        return return_pic;
    }

//    public void makeClone(Picture p1, Picture p2) {
//        Pixel[][] a = pixHelp(p1);
//        Pixel[][] b = toPicture(p2);
//
//        for (int y = 0; y < _picture.getHeight(); y++) {
//            for (int x = 0; x < _picture.getWidth(); x++) {
//                p1[x][y] = p2[x][y];
//            }
//        }
//    }


}

