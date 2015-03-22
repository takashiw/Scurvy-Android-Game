package com.example.dspritzman.myapplication;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Shane McDonald on 3/21/2015.
 */
public class Game {
    WeaponInventory wInventory;
    LevelManager lManager;
    ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
    int score;

    Game(){
        wInventory = new WeaponInventory();
        wInventory.addWeapon('a', true, true);
        wInventory.addWeapon('b', false, false);
        wInventory.addWeapon('c', false, false);
        wInventory.addWeapon('d', false, false);
        wInventory.addWeapon('e', false, false);
        score = 0;

        lManager = new LevelManager();

        Health = 2000;
        Money = 0;
        ActiveW = 0;
    }

    public void tick()
    {
        lManager.tick();
        Collision_Detect();
        updateBullets();
    }

    public int getScore()
    {
        return lManager.getScore();
    }


    public void addBullet(int xOrigin, int yOrigin, int xTouch, int yTouch)
    {
        BulletPhysics physics = new BulletPhysics(xOrigin, yOrigin, xTouch, yTouch);
        Bullet b = new Bullet(xOrigin, yOrigin, physics.getRise(), physics.getRun());

        Bullets.add(b);
        Log.e("add", "add: " + Bullets.get(0).getX());
    }


    public void gainMoney(int amount) {
        Money = Money + amount;
    }

    public void loseMoney(int amount) {
        if(Money > 0 && amount <= Money) {
            Money = Money - amount;
        }
        else {
            // not enough money error message
        }
    }

    public void SelectWeapon(int index) {
        wInventory.getIndex(ActiveW).ToggleEquipped();

        wInventory.getIndex(index).ToggleEquipped();
        ActiveW = index;
    }


    public int getMoney() {
        return Money;
    }

    public int getHealth() {
        return Health;
    }

    public void loseHealth(int amount) {
        if(Health > 0 && amount < Health) {
            Health = Health - amount;
        }
        else {
            GameOver();
        }
    }

    public void GameOver(){
        // Game Over message
    }

    public int getPuffSize()
    {
        return lManager.getPuffSize();
    }

    public int getNarwhalSize()
    {
        return lManager.getNarwhalize();
    }

    public int getManSize()
    {
        return lManager.getManSize();
    }

    public int getFlyingSize()
    {
        return lManager.getFlyingSize();
    }
    public int getSeaSize()
    {
        return lManager.getFlyingSize();
    }


    public Pufferfish puffIterator(int i)
    {
        return lManager.puffIterator(i);
    }

    public Narwhal narIterator(int i)
    {
        return lManager.narIterator(i);
    }

    public Manatee manIterator(int i)
    {
        return lManager.manIterator(i);
    }

    public FlyingFish flyIterator(int i)
    {
        return lManager.flyIterator(i);
    }

    //public SeaHorse seaIterator(int i)
    {
        //return lManager.seaIterator(i);
    }

    private double Distance(int xa, int ya, int xb, int yb)
    {
        int x1 = xa;
        int x2 = xb;
        int xSqr = (x2 - x1) * (x2 - x1);
        int y1 = ya;
        int y2 = yb;
        int ySqr = (y2 - y1) * (y2 - y1);
        double distance = Math.sqrt(xSqr + ySqr);

        return distance;
    }

    public void updateBullets()
    {
        for (int i = 0; i < Bullets.size(); ++i)
        {
            Bullets.get(i).setX(Bullets.get(i).getX() + Bullets.get(i).getRun());
            Bullets.get(i).setY(Bullets.get(i).getY() + Bullets.get(i).getRise());
        }
    }

    public void Collision_Detect(){

        //Log.e("manatee", "1: " + Bullets.size() + "2: " );

        for(int i = 0; i < Bullets.size() && Bullets.size() != 0; i++) {

            boolean stop = false;
            if (Bullets.get(i).getY() > 1280 || Bullets.get(i).getX() > 720 || Bullets.get(i).getX() < 0)
            {
                Bullets.remove(i);
            }

            for (int f = 0; f < getFlyingSize() && getFlyingSize() > 0 && !stop && Bullets.size() > 0; f++)
            {
                if (Bullets.get(i) != null && flyIterator(f) != null) {
                    if (Distance(Bullets.get(i).getX(), Bullets.get(i).getY(), flyIterator(f).getX(), flyIterator(f).getY()) < flyIterator(f).getRadius()) {
                        flyIterator(f).takeDamage(50);
                        Bullets.remove(i);
                        stop = true;
                    }
                }
            }


            for (int p = 0; p < getPuffSize()  && getPuffSize() > 0 && !stop && Bullets.size() > 0; p++)
            {
                if (Bullets.get(i) != null && puffIterator(p) != null) {
                    if (Distance(Bullets.get(i).getX(), Bullets.get(i).getY(), puffIterator(p).getX(), puffIterator(p).getY()) < puffIterator(p).getRadius()) {
                        puffIterator(p).takeDamage(50);
                        Bullets.remove(i);
                        stop = true;
                    }
                }
            }
/*

            for (int s = 0; s < getSeaSize() && getSeaSize() > 0; s++)
            {
                if(Distance(Bullets.get(s).getX(), Bullets.get(s).getY(), seaIterator(s).getX(), seaIterator(s).getY()) < seaIterator(s).getRadius()){
                    seaIterator(s).takeDamage(50);
                    Bullets.remove(s);
                    break;
                }
            }*/

            for (int m = 0; m < getManSize() && getManSize() > 0 && !stop && Bullets.size() > 0; m++)
            {
                if (Bullets.get(i) != null && manIterator(m) != null) {
                    if (Distance(Bullets.get(i).getX(), Bullets.get(i).getY(), manIterator(m).getX(), manIterator(m).getY()) < manIterator(m).getRadius()) {
                        manIterator(m).takeDamage(50);
                        Bullets.remove(i);
                        Log.e("manatee", "hit dat");
                        stop = true;
                    }
                }
            }

            for (int n = 0; n < getNarwhalSize() && getNarwhalSize() > 0 && !stop && Bullets.size() > 0; n++)
            {
                if (Bullets.get(i) != null && narIterator(n) != null) {
                    if (Distance(Bullets.get(i).getX(), Bullets.get(i).getY(), narIterator(n).getX(), narIterator(n).getY()) < narIterator(n).getRadius()) {
                        narIterator(n).takeDamage(50);
                        Bullets.remove(i);
                        stop = true;
                    }
                }
            }

        }
    }



    private int ActiveW;
    private int Money;
    private int Health;
}
