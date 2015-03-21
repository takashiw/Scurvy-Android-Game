package com.example.shanemcdonald.hackfsu1;

import android.graphics.Bitmap;

/**
 * Created by Shane McDonald on 3/21/2015.
 */
public class Bullet {

    public int getBullet_speed() {
        return bullet_speed;
    }

    public int getX_coordinate() { return x_coordinate; }
    public int getY_coordinate() { return y_coordinate; }

    private int x_coordinate;
    private int y_coordinate;
    private Bitmap bullet;
    private int bullet_speed;
}
