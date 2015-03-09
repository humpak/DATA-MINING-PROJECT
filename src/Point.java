/**
 * Point.java Represents a 2 dimensional point.
 */
import java.awt.Color;

/**
 * Represents a 2 dimensional point with x and y co-ordinate. It also 
 * stores the cluster to which this point belongs.
 * 
 * @author Ashpak Shaikh
 * @author Richa Singh
 *
 */
public class Point {

	private int x;
	private int y;
	private int cluster;
	private Color color;
	private boolean visited;
	
	/**
	 * Constructor to initialize the points.
	 * 
	 * @param x X co-ordinate of the point
	 * @param y Y co-ordinate of the point
	 */
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		this.setCluster(-1);
		color = null;
		visited = false;
	}
	
	/**
	 * Constructor to initialize a point with a initial cluster.
	 * 
	 * @param x X co-ordinate of the point
	 * @param y Y co-ordinate of the point
	 * @param k The initial cluster.
	 */
	public Point(int x, int y,int k){
		this.x = x;
		this.y = y;
		this.setCluster(k);
		color = null;
	}
	
	/**
	 * Returns the x co-ordinate of the point.
	 * 
	 * @return x The X co ordinate of the point.
	 */
	public int getX() {
		return x;
	}
	/**
	 * Sets the x co-ordinates of the point
	 * 
	 * @param x The new x co-ordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Returns the y co-ordinate of the point.
	 * 
	 * @return y The Y co ordinate of the point.
	 */
	public int getY() {
		return y;
	}
	/**
	 * Sets the y co-ordinate of the point.
	 * 
	 * @param y The Y co ordinate of the point.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Returns the color of the points cluster.
	 * 
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Sets the color of the point according to the cluster.
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Returns the distance of this point with the given point.
	 * 
	 * @param p The point to which the distance is to be calculated.
	 * 
	 * @return The distance between this point and the given point. 
	 */
	public double distance(Point p){
		double xdel = this.x - p.x;
		double ydel = this.y - p.y;
		return Math.sqrt(xdel*xdel+ydel*ydel);
	}
	/**
	 * Returns the cluster no of this point.
	 * 
	 * @return int cluster no
	 */
	public int getCluster() {
		return cluster;
	}
	
	/**
	 * Sets the cluster No of this point
	 * 
	 * @param cluster
	 */
	public void setCluster(int cluster) {
		this.cluster = cluster;
	}
	
	/**
	 * The overWritten toString method to display the point.
	 * 
	 */
	public String toString(){
		return "["+this.x+","+this.y+"] --> Cluster "+cluster;
		
	}
	/**
	 * Returns if the point is visited or not
	 * 
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}
	/**
	 * Sets if the point is visited or not.
	 * 
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
