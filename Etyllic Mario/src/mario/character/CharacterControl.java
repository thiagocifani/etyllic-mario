package mario.character;

import br.com.etyllica.core.event.KeyEvent;

public class CharacterControl {

	private Character character;
	
	public CharacterControl(Character character){
		this.character = character;
	}
	
	
	public void keyboardEvents(KeyEvent event){
		if(!character.walking){

			//UP Arrow
			if(event.isKeyDown(KeyEvent.TSK_UP_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_UP)){
				character.lookUp();				
			}else if(event.isKeyUp(KeyEvent.TSK_UP_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_CENTER_Y)){
				character.stand();
			}

			//DOWN Arrow
			if(event.isKeyDown(KeyEvent.TSK_DOWN_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_DOWN)){
				character.standDown();
			}else if(event.isKeyUp(KeyEvent.TSK_DOWN_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_CENTER_Y)){
				character.stand();
			}
		}

		if(!character.looking){

			//RIGHT Arrow
			if(event.isKeyDown(KeyEvent.TSK_RIGHT_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_RIGHT)){
				character.turnRight();
				character.startWalking();

			}else if(event.isKeyUp(KeyEvent.TSK_RIGHT_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_CENTER_X)){
				character.stopWalk();
			}

			//LEFT Arrow
			if(event.isKeyDown(KeyEvent.TSK_LEFT_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_LEFT)){
				character.turnLeft();
				character.startWalking();

			}else if(event.isKeyUp(KeyEvent.TSK_LEFT_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_CENTER_X)){
				character.stopWalk();
			}

		//If Looking up or down
		}else{
			
			//RIGHT ARROW
			if(event.isKeyDown(KeyEvent.TSK_RIGHT_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_RIGHT)){
				character.turnRight();
			}
			
			//LEFT ARROW
			else if(event.isKeyDown(KeyEvent.TSK_LEFT_ARROW)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_LEFT)){
				character.turnLeft();
			}
		}

		if(event.isKeyDown(KeyEvent.TSK_ESPACO)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_BUTTON_1)){
			character.startJump();
		}
		
		if(event.isKeyDown(KeyEvent.TSK_SHIFT_LEFT)||event.isKeyDown(KeyEvent.TSK_JOYSTICK_BUTTON_3)){
			character.walkSpeed = 5;
		}else if(event.isKeyUp(KeyEvent.TSK_SHIFT_LEFT)||event.isKeyUp(KeyEvent.TSK_JOYSTICK_BUTTON_3)){
			character.walkSpeed = 3;
		}

	}
}
