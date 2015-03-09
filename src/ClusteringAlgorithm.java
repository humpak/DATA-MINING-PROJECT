/**
 * 
 * ClusteringAlgorithm : An Abstract class for Clustering Algorithm.
 * 
 */
import java.awt.Color;
import java.util.List;
/**
 * 
 * Represents an Abstract Class for Clustering Algorithm.
 * 
 * @author Ashpak Shaikh
 * @author Richa Singh
 *
 */
public abstract class ClusteringAlgorithm {
	
	String name = "";
	List<Point> points;
	
	/**
	 * Constructor to set intialize the variables.
	 * 
	 * @param name
	 * @param points
	 */
	public ClusteringAlgorithm( String name, List<Point> points) {
		this.name = name;
		this.points = points;
	}
	// Represents the colour which is used to color the points that belong to the
	// same cluster.
	Color colors[] = { Color.RED, new Color(0, 102, 0), new Color(0, 0, 102),
			new Color(153, 0, 153), new Color(0, 153, 153),
			new Color(153, 153, 0), new Color(102, 51, 0), Color.BLUE,
			Color.black, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.cyan,
			Color.PINK };
	/**
	 * Abstract method to display the points
	 */
	abstract void displayPoints();

	/**
	 * Abstract method which runs the algorithm.
	 */
	abstract void runAlgorithm();
	
	/**
	 * Assigns various colors to the points based on the cluster number.
	 * 
	 */
	public void colorPoints() {
		for (Point p : points)
			if (p.getCluster() > -1)
				p.setColor(colors[p.getCluster() % colors.length]);

		Visualize v = new Visualize(name);
		v.showPoints(points);
	}

}
