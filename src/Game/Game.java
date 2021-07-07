package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable, MouseListener{
                                                   
    public static final int WIDTH = 600;                                  
    public static final int HEIGHT = WIDTH; 
    public static int LEVEL  = 5; // Medium
    public static final int menuWIDTH = 200;      
    public static int algoSpeed = 25; // Medium
    public static final String TITLE = "The Maze Game";                                            

    private boolean running = false;                                                           
   	private Thread thread;                                                          

    //Variables Declration
    public BufferedImage background;
    public static GridSystem MazeSet;
    private Starter startGame;
    
    public static enum STATE{                                                                   
        GAME,                                                                                  
        STARTMENU,
        WINNER,
    };
    public static STATE state =  STATE.STARTMENU;                                                 

    public void init(){                                                                        
        requestFocus();                        
        try{
            background = ImageIO.read(getClass().getResource("/MazeRunnerBackground.png"));
        }
        catch (IOException e){                             
            e.printStackTrace();
        }
        startGame= new Starter();
        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(this);
        
    }


    public synchronized void start(){                                                         
        if(running){                                                                          
            return;
        }
        running = true;                                                                        
        thread = new Thread(this);                                                             
        thread.start();                                                                       
    }

    private synchronized void stop(){                                                         
        if(!running) {                                                                         
            return;
        }

        running = false;
        try {
            thread.join();                                                                    
        }
        catch (InterruptedException e){                                                         
            e.printStackTrace();
        }
        System.exit(1);                                                                        
    }



    public void run(){                                                                        
        init();                                                                                
        long lastTime = System.nanoTime();                                                     
        final double amountOfTicks = 60.0;                                                     
        double ns = 1000000000 / amountOfTicks;                                                
        double delta = 0;                                                                      
        int updates = 0;                                                                        
        int frames = 0;
        long timer = System.currentTimeMillis();                                                


        while(running){                                                                        
            long now = System.nanoTime();                                                       
            delta += (now - lastTime) / ns;                                                     
            lastTime = now;                                                                    
            if(delta >=1){                                                                    
            	gameOverCheck();                                                                        
                updates++;                                                                     
                delta--;                                                                       
            }
            render();  
            //System.out.println("Here");//Rita ut alla objekten
            frames++;                                                                          

           
            if(System.currentTimeMillis() - timer > 1000){                                     
                timer += 1000;                                                                  
                //System.out.println(updates + " Ticks, Fps " + frames);                       
                updates = 0;                                                                   
                frames = 0;
            }
        }
        stop();                                                                                 
    }

    private void gameOverCheck(){                                                                       
        if(state==STATE.GAME) { 
        	if(GridSystem.grid[GridSystem.currentX][GridSystem.currentY].getEnding())
        	{
        		try {
					background = ImageIO.read(getClass().getResource("/winner.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		Game.state = STATE.WINNER;
        	}
        }
    }

    private void render(){                                                                     
        BufferStrategy bs = this.getBufferStrategy();                                           
        if (bs == null){
            createBufferStrategy(3);                                                            
            return;
        }

        Graphics g = bs.getDrawGraphics();                                                     
        g.drawImage(background, 0, 0, Game.WIDTH + Game.menuWIDTH, Game.HEIGHT, null);                                                   

        if(state == STATE.GAME) {
        	MazeSet.renderGrid(g);
        	Menu.render(g);
        }
        if(state == STATE.STARTMENU){
        	startGame.render(g);
        }
        if(state == STATE.WINNER)
        {
        	WinnerScreen.render(g);
        }

        g.dispose();                                                                            
        bs.show();                                                                              
    }

// Keys Events
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();                                                              

        if(state == STATE.GAME) {                                                               
            if (key == KeyEvent.VK_RIGHT) 
            {
            	MazeSet.moveRIGHT();
            } 
            else if (key == KeyEvent.VK_LEFT) {
            	MazeSet.moveLEFT();
            } 
            else if (key == KeyEvent.VK_DOWN) {
            	MazeSet.moveBOTTOM();
            } 
            else if (key == KeyEvent.VK_UP) {
            	MazeSet.moveUP();
            }
        }
        else if (state == STATE.STARTMENU){                                                       
            if(key == KeyEvent.VK_ENTER){                                                      
                MazeSet = new GridSystem(Game.LEVEL);
    			MazeSet.makeGrid();
    			state = STATE.GAME;
            }

        }
        else if(state == STATE.WINNER)
        {
        	if(key == KeyEvent.VK_ENTER){     
		    	try {
					background = ImageIO.read(getClass().getResource("/MazeRunnerBackground.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	state = STATE.STARTMENU;
        	}
        }
    }

    public void keyReleased(KeyEvent e){                                                        
        int key = e.getKeyCode();                                                               

        if (key == KeyEvent.VK_RIGHT) {
        } else if (key == KeyEvent.VK_LEFT) { 
        } else if (key == KeyEvent.VK_DOWN) {
        } else if (key == KeyEvent.VK_UP) {
        }
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int key = e.getButton();
		
		if(state == Game.STATE.GAME)
		{
			switch(key)
			{
			case MouseEvent.BUTTON1:
				Menu.StartFunction(e.getX(), e.getY());
			}
		}
		if(state == Game.STATE.STARTMENU)
		{
			switch(key)
			{
			case MouseEvent.BUTTON1:
				Starter.StartFunction(e.getX(), e.getY());
			}
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
