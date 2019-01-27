package plane.demo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class plane extends GameObject {

	boolean left,up,right,down;
	boolean live = true;
	public void drawSelf(Graphics g){
		
		if(live){
			
			g.drawImage(img, (int)x, (int)y, null);
			if(left && x>10){
				x-=speed;
			}
			if(right && x<470){
				x+=speed;
			}
			if(up && y >30){
				y-=speed;
			}
			if(down && y <470){
				y+=speed;
			}else{}
		}
		
	}
	public plane(Image img,double x,double y){
		this.img = img;
		this.x = (int)x;
		this.y = (int)y;
		this.speed = 5;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	//按下某个键增加相应的方向
	public void addDirection(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
	}
	//按下某个键取消相应的方向
	public void minusDirection(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}	
}

}
