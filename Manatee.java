package com.example.dspritzman.myapplication;


import java.util.Random;

class Manatee {
    private int damageInflicting;
    private int health;
    private int worth;
    private int x;
    private int y;
    private int range;
    private int xSpeed;
    private boolean dead;
    private int yDeath;
    private boolean boss;
    private int radius;


    Manatee(){
        damageInflicting = 80;
        health = 350;
        worth = 75;
        x = 150;
        y = 1325;
        range = 30;
        xSpeed = 1;
        dead = false;
        boss = false;
        radius = 65;
        yDeath = 1;
    }
	
	/*
	Enemy(int damageInflicting, int health, String name, int worth, int x, int y, int range, int xSpeed){
		this.damageInflicting = damageInflicting;
		this.health = health;
		this.name = name;
		this.worth = worth;
		this.x = x;
		this.y = y;
		this.range = range;
	}
	*/
    //

    public int getRadius()
    {
        return radius;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setWorth(int worth){
        this.worth = worth;
    }

    public void setRange(int range){
        this.range = range;
    }

    public void move(){
        if(!dead) y -= xSpeed;
        else x -= yDeath;
    }

    public void setX(int x){
        this.x = x;
    }
    public void attack()
    {
        y += 110;
    }


    public int inflictDamage(){
        return damageInflicting;
    }

    public void takeDamage(int hitDamage){
        if(!dead){
            health = health - hitDamage;
            if(health <= 0){
                dead = true; } }
    }

    public boolean getDead(){
        return dead;
    }

    public int getHealth(){
        return health;
    }

    public int getWorth(){
        Random x = new Random();
        return x.nextInt(10) + worth;
    }

    public void setBoss(boolean input) {
        boss = input;
    }

    public boolean getBoss() {
        return boss;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRange() {
        return range;
    }

    public int getxSpeed() {
        return xSpeed;
    }

}
