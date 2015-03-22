import java.util.Random;


class SeaHorse {
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
	
	SeaHorse(){
		damageInflicting = 1;
		health = 10;
		worth = 46;
		x = 1330;
		y = 200;
		range = 20;
		xSpeed = 5;
		dead = false;
		boss = false;
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
		if(!dead) x += xSpeed;
		else y -= yDeath;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	
	public int inflictDamage(){
		if(x < range)	return damageInflicting;
		return 0;
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
	
}



