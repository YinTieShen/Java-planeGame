package plane.demo;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

	

public class MyGameFrame extends Frame {
	
	Image planeImg = GameUtil.getImage("Images/plane5.png");
	Image bg = GameUtil.getImage("Images/background.png");
	
	plane plane1 = new plane(planeImg,200,300);
	shell[] shells = new shell[30];
	Explode bao;
	Date startTime = new Date();
	Date endTime;
	int period;
	
		   @Override
		public void paint(Graphics g) {//自动被调用，g相当于画笔

			   
			   g.drawImage(bg, 0, 0, null);
			   plane1.drawSelf(g);
			   for(int i=0;i<30;i++){
				   shells[i].draw(g);
				   boolean peng = shells[i].getRect().intersects(plane1.getRect());
				   if(peng){
					  plane1.live = false;
					  if(bao == null){
						  bao = new Explode(plane1.x,plane1.y);
						
						  endTime = new Date();
						  period = (int)((endTime.getTime() - startTime.getTime())/1000);
					  }
					  
					  
					  bao.draw(g);
				   }
				   if(plane1.live == false){
					   g.setColor(Color.red);
					   
						g.drawString("时间"+period+"秒", (int)plane1.x, (int)plane1.y);
				   }
			   }

			 
		}
	
//线程启动
		   class PaintThread extends Thread{
			   @Override
			public void run() {
				while(true){
				
					repaint();//重画
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		   }
	
//按键的按下抬起   键盘监听的内部类
		   class KeyMonitor extends KeyAdapter{
			   @Override
				public void keyPressed(KeyEvent e) {
					plane1.addDirection(e);
				}
				   
				   @Override
				public void keyReleased(KeyEvent e) {
					   plane1.minusDirection(e);
				}
		   }
		   
		   
		   
		   
	/**
	 * 初始化窗口
	 */
	public void launchFrame(){
		this.setTitle("一个游戏小测试");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(30,30);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		} );
		new PaintThread().start();//启动重画动画的线程
		addKeyListener(new KeyMonitor());//增加键盘的监听
		//初始化炮弹
		for(int i=0;i<30;i++){
			shells[i] = new shell();		}
		
	}
	
	public static void main(String[] args){
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	}
	
	//解决双反冲问题
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_HEIGHT,Constant.GAME_WIDTH);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
	
}
