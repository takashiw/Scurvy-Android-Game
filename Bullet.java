package com.example.shanemcdonald.hackfsu1;

import android.graphics.Bitmap;

/**
 * Created by Shane McDonald on 3/21/2015.
 */
public class Bullet {

    Bullet(int xorig, int yorig, int temprise, int temprun) {
        x = xorig;
        y = yorig;
        rise = temprise;
        run = temprun;
    }

    public int getBullet_speed() {
        return bullet_speed;
    }

    public void setX(int temp) { x = temp; }
    public void setY(int temp) { y = temp; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getXdestination() { return xdestination; }
    public int getYdestination() { return ydestination; }
    public int getRise() { return rise; }
    public int getRun() { return run; }



    private int x;
    private int y;
    private int xdestination;
    private int ydestination;
    private int rise;
    private int run;
    private Bitmap bullet;
    private int bullet_speed;
}


