package a7;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
// implements keyboard(KeyListener) and mouse(MouseListener);

public class FramePuzzleWidget extends JPanel implements  KeyListener, MouseListener{

    private Picture _picture;
    private Picture moving_pic;

    private Picture[][] picture_puzzle;

    private int xIndex;
    private int yIndex;
    private int block_x;
    private int block_y;
    private int tile_width;
    private int tile_height;

    private PictureView[][] _picture_view = new PictureView[5][5];



    public FramePuzzleWidget(Picture picture){

        // 5x5 grid of PictureView objects that each display a portion of the original picture
        setLayout(new GridLayout(5,5));

        block_x =4;
        block_y =4;
        _picture=picture;
        picture_puzzle = new Picture[5][5];


        tile_width = picture.getWidth() / 5;
        tile_height = picture.getHeight() / 5;

        //  pictureView.addMouseListener(this);
        //  setLayout(new GridLayout(5,5));
        //  for(int i=0;i<5;i++) {
        //	for(int j=0;j<5;j++) {
        //		add(pictureViews[i][j]);
        //	}
        //}
        Pixel[][]tile_array = new Pixel[tile_width][tile_height];

        for(int i = 0; i< tile_width; i++) {
            for(int j = 0; j< tile_height; j++) {
               // tile_array[i][j]=new GrayPixel(1);
                //make the empty square Carolina Blue!
                tile_array[i][j] = new ColorPixel(0.6,0.729,0.867);
            }
        }
        //make the empty square Carolina Blue!
        moving_pic = new MutablePixelArrayPicture(tile_array,"Carolina Blue");
        setFocusable(true);
        grabFocus();


        for(int x = 0; x < 5; x++) {
            for(int y = 0; y < 5; y++) {
                if(x != 4 || y != 4){
                    SubPicture subpic = picture.extract(tile_width * y, tile_height * x, tile_width,tile_height);

                    ObservablePicture obs = subpic.createObservable();
                    _picture_view [y][x] = new PictureView(obs);

                }else {

                    ObservablePicture obs = moving_pic.createObservable();
                    _picture_view[y][x]= new PictureView(obs);
                }

    //        picture_view.addMouseListener(this);
    //        picture_view.addKeyListener(this);
    //        picture_view.setFocusable(true);
    //        add(picture_view);

                add(_picture_view[y][x]);
                 _picture_view[y][x].addMouseListener(this);
                 _picture_view[y][x].addKeyListener(this);
            }
        }

        this.addKeyListener(this);

    }


    //swaps on direction,
    // left (click left, swap that direction)
    public void mousePressed(MouseEvent e) {
//        int x=e.getX()/tile_width;
//        int y=e.getY()/tile_height;
//
//        //System.out.println(e.getX()+"	"+e.getY());

        for(int bx=0; bx<5; bx++) {
        for(int by=0; by<5; by++) {
           if ((e.getSource() == _picture_view[by][bx])&&(bx == block_y || by == block_x)){
           if(by == block_x){
           if(block_y >bx) {
               for(int i = block_y; i > bx; i--) {
                   ObservablePicture obs = _picture_view[by][i-1].getPicture();
                   _picture_view[by][i].setPicture(obs);
               }
                   ObservablePicture obs_mov = moving_pic.createObservable();
                   _picture_view[by][bx].setPicture(obs_mov);
           } else {
               for(int j = block_y; j < bx; j++) {
                   ObservablePicture obs = _picture_view[by][j+1].getPicture();
                   _picture_view[by][j].setPicture(obs);
               }
                   ObservablePicture obs_mov = moving_pic.createObservable();
                   _picture_view[by][bx].setPicture(obs_mov);
           }
           } else {
               if(block_x >by) {
                   for(int k = block_x; k > by; k--) {
                       ObservablePicture obs = _picture_view[k-1][bx].getPicture();
                       _picture_view[k][bx].setPicture(obs);
                   }
                       ObservablePicture obs_mov = moving_pic.createObservable();
                       _picture_view[by][bx].setPicture(obs_mov);
               } else {
                   for(int l = block_x; l < by; l++) {
                       ObservablePicture obs = _picture_view[l+1][bx].getPicture();
                       _picture_view[l][bx].setPicture(obs);
                   }
                       ObservablePicture obs_mov = moving_pic.createObservable();
                       _picture_view[by][bx].setPicture(obs_mov);
               }}   block_x = by; block_y = bx;

           }
        }}
        }


    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        //key pressed, it will trigger different situation
        if(e.getKeyCode() == KeyEvent.VK_UP){
           keyMoveUp();
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN ){
           keyMoveDown();
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT ){
           keyMoveLeft();
        }else{
           keyMoveRight();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

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
    public void keyMoveUp(){
    if(block_x == 0){
        return;
    }else{
        if(block_y >= 0) {
            ObservablePicture obs = _picture_view[block_x][block_y-1].getPicture();
            _picture_view[block_x][block_y].setPicture(obs);
            ObservablePicture mov_obs = moving_pic.createObservable();
            _picture_view[block_x][block_y-1].setPicture(mov_obs);
            block_y--;
        }
    }
    }

    public void keyMoveDown() {
    if (block_y == 5) {
        return;
    } else {
        ObservablePicture obs = _picture_view[block_x][block_y+1].getPicture();
        _picture_view[block_x][block_y].setPicture(obs);
        ObservablePicture mov_obs = moving_pic.createObservable();
        _picture_view[block_x][block_y+1].setPicture(mov_obs);

        block_y++;
    }
}
    public void keyMoveLeft(){
        if(block_x == 0) {
            return;
        }else{
            ObservablePicture obs = _picture_view[block_x-1][block_y].getPicture();
            _picture_view[block_x][block_y].setPicture(obs);
            ObservablePicture mov_obs = moving_pic.createObservable();
            _picture_view[block_x-1][block_y].setPicture(mov_obs);
            block_x--;
        }
    }
    public void keyMoveRight(){
        if(block_x == 5) {
            return;
        }else {
            ObservablePicture obs = _picture_view[block_x+1][block_y].getPicture();
            _picture_view[block_x][block_y].setPicture(obs);
            ObservablePicture mov_obs = moving_pic.createObservable();
            _picture_view[block_x+1][block_y].setPicture(mov_obs);
            block_x++;
        }
    }

    //from piazza.
//    public void mousePressed(MouseEvent e) {
//        for (int y = 0; y < _frames[0].length; y++) {
//            for (int x = 0; x < _frames.length; x++) {
//                if (_frames[x][y] == e.getSource()) {
//                    moveTile(x, y);
//                }
//            }
//        }
//    }


}
