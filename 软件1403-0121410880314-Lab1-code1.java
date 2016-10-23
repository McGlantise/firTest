package Lifegame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Gamemap extends JPanel{
	private final char CELL_ALIVE='A';
	private final char CELL_DEAD='D';
	//设置细胞方格大小
	private final int width = 22;  
    private final int height = 22;  
   

	private  char[][] gamemap =new char[26][26];
	public Gamemap(){
	 this.startgame();
	}
	
	
	
	private final char[][] nextstatus = new char[26][26];
	private final char[][] tempstatus = new char[26][26];
	private Timer timer;
	
	private final int DELAY_TIME=1200;
	
	//开始游戏并初始化
	public void startgame(){
		 for(int i=0;i<gamemap.length;i++){
			 for (int j=0;j<gamemap[i].length;j++){
				 double sum=Math.random();
				 
				 if(sum<0.5)  
					 gamemap[i][j]=CELL_ALIVE;
				 else 
					 gamemap[i][j]=CELL_DEAD;
				 nextstatus[i][j]=gamemap[i][j];
				 tempstatus[i][j]=gamemap[i][j];
			 }
		 }
		 timer = new Timer(DELAY_TIME, new ActionListener() {  
			 
	            public void actionPerformed(ActionEvent e) {  
	                changecellstatus();  
	                repaint();  
	            }  
	        });  
		 timer.start();
	}
	
	//查询细胞附近活细胞的数量
	public int countneighborcell(int row,int col){
		int count = 0 , r=0,c=0;
		for(r=row-1;r<=row+1;r++){
			for(c=col-1;c<=col+1;c++){
				if (r < 0 || r >= tempstatus.length || c < 0  
                        || c >= tempstatus[1].length) {  
                    continue;  
                }  
				if(tempstatus[r][c]==CELL_ALIVE)  count++;
			}
		}
		if(tempstatus[row][col]==CELL_ALIVE) count--;
		return count;
	}
	
	//改变细胞的状态
	private void changecellstatus(){
		for(int row=0;row<gamemap.length;row++){
			for(int col=0;col<gamemap[row].length;col++){
				switch(countneighborcell(row, col)){
				case 0:  
                case 1:  
                case 4:  
                case 5:  
                case 6:  
                case 7:  
                case 8:  
                    nextstatus[row][col] = CELL_DEAD;  
                    break;  
                case 2:  
                    nextstatus[row][col] = tempstatus[row][col];  
                    break;  
                case 3:  
                    nextstatus[row][col] = CELL_ALIVE;  
                    break;  
                }
			}
		}
		copyWorldMap(); 
	}
	
	
	//画图形界面
	protected void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        int i,j;
        for (i=0; i < nextstatus.length; i++) {  
            for (j=0; j < nextstatus[i].length; j++) {  
                if (nextstatus[i][j] == CELL_ALIVE) {  
                    g.fillRect( j * width, i * height, width, height);  
                   
                } else {  
                	 g.drawRect( j * width, i * height, width, height); 
                }  
            }  
        }  
    }  
	
	//复制地图
	private void copyWorldMap() {  
        for (int row = 0; row < nextstatus.length; row++) {  
            for (int col = 0; col < nextstatus[row].length; col++) {  
                tempstatus[row][col] = nextstatus[row][col];  
            }  
        }  
    }  
	
}
	
	



		

