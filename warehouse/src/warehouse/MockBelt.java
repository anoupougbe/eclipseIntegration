package warehouse;

import java.util.*;
import java.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * 
 * @author Anani
 *
 */

public class MockBelt extends JPanel implements ActionListener, Belt, Tickable{
	Floor F;
	List<Point>beltarea;
	int x, y;
	Timer timer;
	
public MockBelt(Floor F){
	this.F=F;
	beltarea=F.getBeltArea();
}

public void tick(int count){//initialize the timer
	
	x = picker.x;			//for moving Bin or Parcel
	y = picker.y;			//cell by cell
	timer = new Timer(count, this);	
}

public void actionPerformed(ActionEvent e) {
if (isMovable()){	//Test if Bin is ready
	if(y==packer.y){//Test if Bin arrives on Packer location
		try {
			Thread.sleep(4000);//Stop Belt moving
			doPacker();	//Make a Parcel		
			x=0;		//Send Parcel to Shipping Duck
			y -= 1;
			repaint();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	else{
		x=0; //Send Bin to packer
		y -= 1;
		repaint();
	}
}
}
public void paintComponent(Graphics g) {//Simulate Bin or 
	super.paintComponent(g);			//Parcel
	g.fillOval(x, y, 10, 10);
}
private boolean isMovable(){
	for (Piont p: beltarea){
		Cell c= F.getCell(p);
		Object o  = c.getContents();
		if (o==null);
		if ((o instanceof Bin) && !((Bin)o).isFinished())return false;
		if ((o instanceof Parcel) && !((Parcel)o).isFinished())return false;
	}
	return true;
}

private void doPacker(){
	Cell c = F.getCell(F.getPacker());
	Object o = c.getContents();
	assert o instanceof Bin;
	Bin b = (Bin)o;
	Order v = b.getOrder();
	Parcel n = new Parcel(v.getAddress(), v.getOrderItems());
	c.setContents(n);
}

public boolean binAvailable(){
	return false;
}
public Bin getBin(){
	return null;
}
	

}
