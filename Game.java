package com.example.dspritzman.myapplication;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Shane McDonald on 3/21/2015.
 */
public class Game {
    WeaponInventory wInventory;
    LevelManager lManager;
    ArrayList<Bullet> Bullets = new ArrayList<Bullet>();

    Game(){
        wInventory = new WeaponInventory();
        wInventory.addWeapon('a', true, true);
        wInventory.addWeapon('b', false, false);
        wInventory.addWeapon('c', false, false);
        wInventory.addWeapon('d', false, false);
        wInventory.addWeapon('e', false, false);

        lManager = new LevelManager();

        Health = 2000;
        Money = 0;
        ActiveW = 0;
    }

    public void tick()
    {
        lManager.tick();
    }


    public void addBullet(int xOrigin, int yOrigin, int xTouch, int yTouch)
    {
        BulletPhysics physics = new BulletPhysics(xOrigin, yOrigin, xTouch, yTouch);
        Bullet b = new Bullet(xOrigin, yOrigin, physics.getRise(), physics.getRun());

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

    public SeaHorse seaIterator(int i)
    {
        return lManager.seaIterator(i);
    }

    private int ActiveW;
    private int Money;
    private int Health;
}
