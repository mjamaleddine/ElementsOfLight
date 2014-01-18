package com.senshu.eol.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.senshu.eol.entities.Player;

public class GamepadInput implements ControllerListener, InputProcessor {

	Player player;
	Vector2 velocity;
	
	public GamepadInput(Player player) {
		this.player = player;
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
            System.out.println("#" + indexOf(controller) + ", button " + buttonIndex + " down" + controller.getButton(buttonIndex));
            switch(buttonIndex){
        	case 1:
    			velocity = player.getVelocity();
    			velocity.y = player.getSpeed();
    			player.setVelocity(velocity);
    			break;
        }
            return false;
        }

        @Override
        public boolean buttonUp(Controller controller, int buttonIndex) {
            System.out.println("=============================clear=================");
            System.out.println("#" + indexOf(controller) + ", button " + buttonIndex + " up");
            switch(buttonIndex){
            	case 1:
        			velocity = player.getVelocity();
        			velocity.y = 0;
        			player.setVelocity(velocity);
        			break;
            }
            return false;
        }

        @Override
        public boolean axisMoved(Controller controller, int axisIndex, float value) {
            //System.out.println("#" + indexOf(controller) + ", axis " + axisIndex + ": " + value);
            switch (axisIndex){
            	//Left stick Y direction
            	case 0:
            		if(value > 0.2){
            			//Upward Movement
            			velocity = player.getVelocity();
            			velocity.y = -player.getSpeed();
            			player.setVelocity(velocity);
            		}
            		if(value < -0.2){
            			//Downward Movement
            			velocity = player.getVelocity();
            			velocity.y = player.getSpeed();
            			player.setVelocity(velocity);
            		}
            		if(value > -0.2 && value < 0.2){
            			//Stop Movement
            			velocity = player.getVelocity();
            			velocity.y = 0;
            			player.setVelocity(velocity);
            		}
            		break;
            	//Left stick x direction
            	case 1:
            		if(value > 0.2){
            			//Right Movement
            			velocity = player.getVelocity();
            			velocity.x = player.getSpeed();
            			player.setVelocity(velocity);
            		}
            		if(value < -0.2){
            			//Left Movement
            			velocity = player.getVelocity();
            			velocity.x = -player.getSpeed();
            			player.setVelocity(velocity);
            		}
            		if(value > -0.2 && value < 0.2){
            			//Stop Movement
            			velocity = player.getVelocity();
            			velocity.x = 0;
            			player.setVelocity(velocity);
            		}
            		break;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
