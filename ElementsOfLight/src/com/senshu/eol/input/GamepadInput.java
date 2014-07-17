package com.senshu.eol.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.senshu.eol.entities.Player;

public class GamepadInput implements ControllerListener, InputProcessor {

	Player player1;
	Player player2;
	Player player3;
	Player player4;
	Vector2 velocity;
	
	public GamepadInput(Player player1, Player player2, Player player3, Player player4) {
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
		this.player4 = player4;
		create();
	}

	public void create(){
	Controllers.addListener(new ControllerListener() {
        public int indexOf(Controller controller) {
            return Controllers.getControllers().indexOf(controller, true);
        }

        @Override
        public void connected(Controller controller) {
            System.out.println("connected " + controller.getName());
            int i = 0;
            for (Controller c : Controllers.getControllers()) {
                System.out.println("#" + i++ + ": " + c.getName());
            }
        }

        @Override
        public void disconnected(Controller controller) {
            System.out.println("disconnected " + controller.getName());
            int i = 0;
            for (Controller c : Controllers.getControllers()) {
                System.out.println("#" + i++ + ": " + c.getName());
            }
            if (Controllers.getControllers().size == 0) {
                System.out.println("No controllers attached");
            }
        }

        @Override
        public boolean buttonDown(Controller controller, int buttonIndex) {
            //System.out.println("#" + indexOf(controller) + ", button " + buttonIndex + " down" + controller.getButton(buttonIndex));
            //Controller 1 config
            if(indexOf(controller) == 0){
	            switch(buttonIndex){
	            	//Button B
	            	case 1:
	            		if(player1.getHealth()>0)player1.swordFireAttack(true);
	        			break;
	        		//Button X
	            	case 2:
	            		if(player1.getHealth()>0)player1.swordWaterAttack(true);
	        			break;
	        		//Button Y
	            	case 3:
	            		if(player1.getHealth()>0)player1.swordLightningAttack(true);
	        			break;
	        		//Button RB
	            	case 5:
	            		player1.setSpeed(300);
	            		break;
	            }
            }
            //Controller 2 config
            if(indexOf(controller) == 1){
	            switch(buttonIndex){
	            	//Button B
	            	case 1:
	            		if(player1.getHealth()>0)player2.swordFireAttack(true);
	        			break;
	        		//Button X
	            	case 2:
	            		if(player1.getHealth()>0)player2.swordWaterAttack(true);
	        			break;
	        		//Button Y
	            	case 3:
	            		if(player1.getHealth()>0)player2.swordLightningAttack(true);
	        			break;
	            }
            }
            //Controller 3 config
            if(indexOf(controller) == 2){
	            switch(buttonIndex){
	            	//Button B
	            	case 1:
	        			player3.swordFireAttack(true);
	        			break;
	        		//Button X
	            	case 2:
	        			player3.swordWaterAttack(true);
	        			break;
	        		//Button Y
	            	case 3:
	        			player3.swordLightningAttack(true);
	        			break;
	            }
            }
            //Controller 4 config
            if(indexOf(controller) == 3){
	            switch(buttonIndex){
	            	//Button B
	            	case 1:
	        			player4.swordFireAttack(true);
	        			break;
	        		//Button X
	            	case 2:
	        			player4.swordWaterAttack(true);
	        			break;
	        		//Button Y
	            	case 3:
	        			player4.swordLightningAttack(true);
	        			break;
	            }
            }
            return false;
        }

        @Override
        public boolean buttonUp(Controller controller, int buttonIndex) {
           // System.out.println("=============================clear=================");
           // System.out.println("#" + indexOf(controller) + ", button " + buttonIndex + " up");
            //Controller 1 config
            if(indexOf(controller) == 0){
	            switch(buttonIndex){
	            	//Button A
	            	case 0:
	            		player1.setHealth(6);
	            		break;
	            	//Button B
	            	case 1:
	            		player1.swordFireAttack(false);
	        			break;
	        		//Button X
	            	case 2:
	            		player1.swordWaterAttack(false);
	        			break;
	        		//Button Y
	            	case 3:
	            		player1.swordLightningAttack(false);
	        			break;
		        	//Button RB
	            	case 5:
	            		player1.setSpeed(180);
	            		break;
	            	
	            }
            }
            //Controller 2 config
            if(indexOf(controller) == 1){
	            switch(buttonIndex){
	            	//Button A
            		case 0:
	            		player2.setHealth(6);
	            		break;
	            	//Button B
	            	case 1:
	        			player2.swordFireAttack(false);
	        			break;
	        		//Button X
	            	case 2:
	        			player2.swordWaterAttack(false);
	        			break;
	        		//Button Y
	            	case 3:
	        			player2.swordLightningAttack(false);
	        			break;
	            }
            }
            //Controller 3 config
            if(indexOf(controller) == 2){
	            switch(buttonIndex){
	            	//Button A
            		case 0:
            			player3.setHealth(6);
            			break;
	            	//Button B
	            	case 1:
	        			player3.swordFireAttack(false);
	        			break;
	        		//Button X
	            	case 2:
	        			player3.swordWaterAttack(false);
	        			break;
	        		//Button Y
	            	case 3:
	        			player3.swordLightningAttack(false);
	        			break;
	            }
            }
            //Controller 4 config
            if(indexOf(controller) == 3){
	            switch(buttonIndex){
	            	//Button A
            		case 0:
            			player4.setHealth(6);
            			break;
	            	//Button B
	            	case 1:
	        			player4.swordFireAttack(false);
	        			break;
	        		//Button X
	            	case 2:
	        			player4.swordWaterAttack(false);
	        			break;
	        		//Button Y
	            	case 3:
	        			player4.swordLightningAttack(false);
	        			break;
	            }
            }
            return false;
        }

        @Override
        public boolean axisMoved(Controller controller, int axisIndex, float value) {
            //System.out.println("#" + indexOf(controller) + ", axis " + axisIndex + ": " + value);
        	//Controller 1 config
        	if(indexOf(controller) == 0){
	            switch (axisIndex){
	            	//Left stick Y direction
	            	case 0:
	            		if(value > 0.3){
	            			//Downward Movement
	            			player1.moveDown(true);
	            		}
	            		if(value < -0.3){
	            			//Upward Movement
	            			player1.moveUp(true);
	            		}
	            		if(value > -0.3 && value < 0.3){
	            			//Stop Movement
	            			player1.moveUp(false);
	            		}
	            		break;
	            	//Left stick x direction
	            	case 1:
	            		if(value > 0.3){
	            			//Right Movement
	            			player1.moveRight(true);
	            		}
	            		if(value < -0.3){
	            			//Left Movement
	            			player1.moveLeft(true);
	            		}
	            		if(value > -0.3 && value < 0.3){
	            			//Stop Movement
	            			player1.moveRight(false);
	            		}
	            		break;
	            }
        	}
        	//Controller 2 config
        	if(indexOf(controller) == 1){
	            switch (axisIndex){
	            	//Left stick Y direction
	            	case 0:
	            		if(value > 0.3){
	            			//Downward Movement
	            			player2.moveDown(true);
	            		}
	            		if(value < -0.3){
	            			//Upward Movement
	            			player2.moveUp(true);
	            		}
	            		if(value > -0.3 && value < 0.3){
	            			//Stop Movement
	            			player2.moveUp(false);
	            		}
	            		break;
	            	//Left stick x direction
	            	case 1:
	            		if(value > 0.3){
	            			//Right Movement
	            			player2.moveRight(true);
	            		}
	            		if(value < -0.3){
	            			//Left Movement
	            			player2.moveLeft(true);
	            		}
	            		if(value > -0.3 && value < 0.3){
	            			//Stop Movement
	            			player2.moveRight(false);
	            		}
	            		break;
	            }
        	}
        	//Controller 3 config
        	if(indexOf(controller) == 2){
	            switch (axisIndex){
	            	//Left stick Y direction
	            	case 0:
	            		if(value > 0.3){
	            			//Downward Movement
	            			player3.moveDown(true);
	            		}
	            		if(value < -0.3){
	            			//Upward Movement
	            			player3.moveUp(true);
	            		}
	            		if(value > -0.3 && value < 0.3){
	            			//Stop Movement
	            			player3.moveUp(false);
	            		}
	            		break;
	            	//Left stick x direction
	            	case 1:
	            		if(value > 0.3){
	            			//Right Movement
	            			player3.moveRight(true);
	            		}
	            		if(value < -0.3){
	            			//Left Movement
	            			player3.moveLeft(true);
	            		}
	            		if(value > -0.3 && value < 0.3){
	            			//Stop Movement
	            			player3.moveRight(false);
	            		}
	            		break;
	            }
        	}
        	//Controller 4 config
        	if(indexOf(controller) == 3){
	            switch (axisIndex){
	            	//Left stick Y direction
	            	case 0:
	            		if(value > 0.3){
	            			//Downward Movement
	            			player4.moveDown(true);
	            		}
	            		if(value < -0.3){
	            			//Upward Movement
	            			player4.moveUp(true);
	            		}
	            		if(value > -0.3 && value < 0.3){
	            			//Stop Movement
	            			player4.moveUp(false);
	            		}
	            		break;
	            	//Left stick x direction
	            	case 1:
	            		if(value > 0.3){
	            			//Right Movement
	            			player4.moveRight(true);
	            		}
	            		if(value < -0.3){
	            			//Left Movement
	            			player4.moveLeft(true);
	            		}
	            		if(value > -0.3 && value < 0.3){
	            			//Stop Movement
	            			player4.moveRight(false);
	            		}
	            		break;
	            }
        	}
        	return false;
        }

        @Override
        public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
            System.out.println("#" + indexOf(controller) + ", pov " + povIndex + ": " + value);
            return false;
        }

        @Override
        public boolean xSliderMoved(Controller controller, int sliderIndex, boolean value) {
            System.out.println("#" + indexOf(controller) + ", x slider " + sliderIndex + ": " + value);
            return false;
        }

        @Override
        public boolean ySliderMoved(Controller controller, int sliderIndex, boolean value) {
            System.out.println("#" + indexOf(controller) + ", y slider " + sliderIndex + ": " + value);
            return false;
        }

        @Override
        public boolean accelerometerMoved(Controller controller, int accelerometerIndex, Vector3 value) {
            // not System.out.printlning this as we get to many values
            return false;
        }
    });

}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Keys.W:
			player1.moveUp(true);
			break;
		case Keys.A:
			player1.moveLeft(true);
			break;
		case Keys.D:
			player1.moveRight(true);
			break;
		case Keys.S:
			player1.moveDown(true);
			break;
		case Keys.R:
			player1.setSpeed(360);
			break;
		case Keys.J:
			player1.swordFireAttack(true);
			break;
		case Keys.K:
			player1.swordLightningAttack(true);
			break;
		case Keys.L:
			player1.swordWaterAttack(true);
			break;
		case Keys.F:
			player1.setHealth(6);
			break;
		case Keys.ESCAPE:
			Gdx.app.exit();
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.W:
		case Keys.S:
			player1.moveDown(false);
			break;
		case Keys.A:
		case Keys.D:
			player1.moveRight(false);
			break;
		case Keys.R:
			player1.setSpeed(120);
			break;
		case Keys.J:
			player1.swordFireAttack(false);
			break;
		case Keys.K:
			player1.swordLightningAttack(false);
			break;
		case Keys.L:
			player1.swordWaterAttack(false);
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller arg0, int arg1, Vector3 arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean axisMoved(Controller arg0, int arg1, float arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buttonDown(Controller arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buttonUp(Controller arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connected(Controller arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(Controller arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean povMoved(Controller arg0, int arg1, PovDirection arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return false;
	}

}
