package com.example.dspritzman.myapplication;

import android.graphics.Bitmap;

/**
 * Created by Shane McDonald on 3/21/2015.
 */
public class Weapons {

    public Weapons() {
        base_damage = 1;
        upgrade_damage = 1;
        base_cooldown = 1;
        upgrade_cooldown = 1;
        type = 'a';
        equipped = true;
        owned = true;
        price = 0;
    }

    public Weapons(char t, boolean e, boolean o) {
        setType(t);
        upgrade_damage = 1;
        upgrade_cooldown = 1;
        equipped = e;
        owned = o;
    }


    public void setType(char t) {
        type = t;

        if (type == 'a') {
            base_damage = 1;
            base_cooldown = 1;
            price = 0;
        }
        else if (type == 'b'){
            base_damage = 2;
            base_cooldown = 2;
            price = 100;
        }
        else if (type == 'c') {
            base_damage = 5;
            base_cooldown = 5;
            price = 200;
        }
        else if (type == 'd') {
            base_damage = 8;
            base_cooldown = 8;
            price = 400;
        }
        else if (type == 'e') {
            base_damage = 10;
            base_cooldown = 10;
            price = 600;
        }
        else {
            base_damage = 1;
            base_cooldown = 1;
        }
    }

    public void UpgradeDamage(){
        upgrade_damage = upgrade_damage * 2;
    }

    public int TotalDamage() {
        return base_damage * upgrade_damage;
    }


    public char getType(){
        return type;
    }

    public int getBase_damage() {
        return base_damage;
    }

    public int getUpgrade_damage() {
        return upgrade_damage;
    }

    public int getBase_cooldown() {
        return base_cooldown;
    }

    public int getUpgrade_cooldown() {
        return upgrade_cooldown;
    }

    public boolean getEquipped() {
        return equipped;
    }

    public void ToggleEquipped(){
        equipped = !equipped;
    }

    public void ToggleOwned() { owned = !owned; }

    public Bitmap getImage() { return image; }



    private Bitmap image;
    private char type;
    private int base_damage;
    private int upgrade_damage;
    private int base_cooldown;
    private int upgrade_cooldown;
    private int price;
    private boolean equipped;
    private boolean owned;
}
