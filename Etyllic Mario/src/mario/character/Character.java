package mario.character;

import sound.model.Sound;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.StaticLayer;
import br.com.etyllica.core.video.Graphic;

public class Character {

	public Character(int x, int y, int xTile, int yTile, String name){
		this.xPosition = x;
		this.yPosition = y;
		this.xTile = xTile;
		this.yTile = yTile;
		this.groundPosition = y;
		this.name = name;
		
	}
	
	protected boolean right = true;
	protected boolean walking = false;
	protected boolean jumping = false;
	protected boolean fallen = false;
	protected boolean looking = false;

	
	private String name;
	private Sound jump;


	protected int walkSpeed = 3;
	protected int jumpSpeed = 5;
	protected int jumpSize = 32;
	private int groundPosition;
	
	
	private StaticLayer characterLeft;
	private StaticLayer characterRight;
	private AnimatedLayer characterLayer;
	private int xPosition;
	private int yPosition;
	private int xTile;
	private int yTile;
	
	public void load(){
		
		jump = new Sound("jump.wav");
		jumpSize = groundPosition-32;//groundPosition - 100 pixels 
		characterRight = new StaticLayer(name + ".png");
		characterLeft = new StaticLayer(name + "inv.png");
		
		characterLayer = new AnimatedLayer(xPosition,yPosition,xTile,yTile,characterRight.getPath());
		characterLayer.setSpeed(250);
		characterLayer.setFrames(2);	
	}
	
	public void preAnima(){
		characterLayer.preAnima();
		jumping();
		walking();
	}
	
	public void draw(Graphic g){
		characterLayer.draw(g);
	}
	
	private void jumping(){
		if(walking){
			//if walking to Right
			if(right){
				characterLayer.setX(characterLayer.getX()+walkSpeed);
				//if walking to Left
			}else{
				characterLayer.setX(characterLayer.getX()-walkSpeed);
			}
		}

		
	}
	
	private void walking(){
		if(jumping){

			if(!fallen){
				
				if(characterLayer.getY()>jumpSize){
					characterLayer.setY(characterLayer.getY()-jumpSpeed);	
				}else{
					startFallen();
				}

			}else{

				if(characterLayer.getY()<groundPosition){
					characterLayer.setY(characterLayer.getY()+jumpSpeed);	
				}else{
					stopJump();
				}

			}
		}
	}
	
	protected void stand(){
		characterLayer.setYImage(0);
		characterLayer.setXImage(0);

		looking = false;
	}

	protected void lookUp(){
		characterLayer.setYImage(characterLayer.getYTile());
		characterLayer.setXImage(0);

		looking = true;
	}

	protected void standDown(){
		characterLayer.setXImage(characterLayer.getXTile());
		characterLayer.setYImage(characterLayer.getYTile());

		looking = true;
	}

	protected void startJump(){
		if(!jumping){
			jump.play();
			jumping = true;

			characterLayer.setYImage(characterLayer.getYTile()*2);
			characterLayer.setXImage(0);
		}
	}

	protected void turnRight(){
		characterLayer.cloneLayer(characterRight);
		right = true;
	}

	protected void turnLeft(){
		characterLayer.cloneLayer(characterLeft);
		right = false;
	}

	protected void startWalking(){
		characterLayer.setFrames(2);
		characterLayer.setStopped(false);
		walking = true;
	}	

	protected void stopJump(){

		jumping = false;
		fallen = false;

		characterLayer.setYImage(0);
		characterLayer.setXImage(0);

	}

	protected void startFallen(){
		fallen = true;

		characterLayer.setYImage(characterLayer.getYTile()*2);
		characterLayer.setXImage(characterLayer.getXTile());
	}

	protected void stopWalk(){
		characterLayer.setFrames(1);
		characterLayer.setStopped(true);
		walking = false;

		characterLayer.setXImage(0);
	}
}
