package plane.demo;

import java.awt.Color;
import java.awt.Graphics;

public class shell extends GameObject {
	double degree;
	public shell(){
		x=100;
		y=100;
		width =10;
		height = 10;
		speed = 4;
		degree = Math.random()*Math.PI*2;
	}
	public void draw(Graphics g){
		Color c= g.getColor();
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, width, height);
		
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		if(x<0 || x>Constant.GAME_WIDTH-20){
			degree = Math.PI -degree;
		}
		if(y<30 || y>Constant.GAME_WIDTH-20){
			degree = -degree;
		}
		g.setColor(c);
	}
}
