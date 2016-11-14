package warehouse;
/**
 * 
 * @author Anani
 *
 */

public class Bin {
	Order order;
	boolean finished;
	public Bin(){
		order = null; finished = false;}
	public boolean isFinished(){
		return finished;}
	public void setFinished(){
		finished = true;}
	public Order getOrder(){
		return order;}
	public void setOrder(Order o){
		order = o;}		
	}
