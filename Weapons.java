package com.example.shanemcdonald.hackfsu1;

import android.graphics.Bitmap;

/**
 * Created by Shane McDonald on 3/21/2015.
 */
public class Weapons {

    public Weapons() {
        base_damage = 1;
        upgrade_damage = 0;
        base_cooldown = 1;
        upgrade_damage = 0;
        type = 'a';
    }

    public Weapons(char t) {
        setType(t);
        upgrade_damage = 0;
        upgrade_cooldown = 0;
    }


    public void setType(char t) {
        type = t;

        if (type == 'a') {
            base_damage = 1;
            base_cooldown = 1;
        }
        else if (type == 'b'){
            base_damage = 2;
            base_cooldown = 2;
        }
        else if (type == 'c') {
            base_damage = 5;
            base_cooldown = 5;
        }
        else if (type == 'd') {
            base_damage = 8;
            base_cooldown = 8;
        }
        else if (type == 'e') {
            base_damage = 10;
            base_cooldown = 10;
        }
        else {
            base_damage = 1;
            base_cooldown = 1;
        }
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



    private char type;
    private int base_damage;
    private int upgrade_damage;
    private int base_cooldown;
    private int upgrade_cooldown;
    private boolean equipped;
}
