package com.example.shanemcdonald.hackfsu1;

import android.graphics.Bitmap;

/**
 * Created by Shane McDonald on 3/21/2015.
 */
public class Game {
    WeaponInventory wInventory;

    Game(){
        wInventory.addWeapon('a', true, true);
        wInventory.addWeapon('b', false, false);
        wInventory.addWeapon('c', false, false);
        wInventory.addWeapon('d', false, false);
        wInventory.addWeapon('e', false, false);
        Health = 100;
        Money = 0;
        ActiveW = 0;
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

    private int ActiveW;
    private int Money;
    private int Health;
}
