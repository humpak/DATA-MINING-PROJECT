/**
 * DBScan.java: This file has the implementation for  the DBSCan algorithm.
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author Ashpak Shaikh
 * @author Richa Singh
 * 
 * A DBSCan algorithm for clustering 2-dimensional points.
 *
 */
public class DBScan extends ClusteringAlgorithm {
	
	int minPts;		// minimum points in one cluster.
	int radius;		// distance of the points to be in one cluster.
	List<Integer> clusters;
	
	/**
	 * 
	 * A constuctor to initialize the variables of the algorithm and setting the input points
	 * 
	 * @param name Name of the algorithm
	 * @param points The data points to be clustered
	 * @param minPts The minimum number of points in one cluster
	 * @param radius The distance between the points to be in one cluster.
	 */
	public DBScan(String name, List<Point> points, int minPts, int radius) {
		super(name,points);
		this.minPts = minPts;
		this.radius = radius;
	}
	
	/**
	 * This function runs the DBScan algorithm on the points.
	 * 
	 */
	public void runAlgorithm() {
		clusters = new ArrayList<Integer>();
		for (Point p : points) {
			if (!p.isVisited()) {
				p.setVisited(true);
				Vector<Point> closestPoints = getclosestNeighbours(p);
				if (closestPoints.size() >= this.minPts)
					createCluster(p, closestPoints, clusters.size());
			}
		}
		displayPoints();
	}
	
	/**
	 * 
	 * Creates the cluster and adds the points in to the cluster to expand the cluster.
	 * 
	 * @param point The point to be added.
	 * @param neighbours The neighbors of the point.
	 * @param cluster The cluster number.
	 */
	public void createCluster(Point point,
			Vector<Point> neighbours, int cluster) {
		clusters.add(cluster);
		point.setCluster(cluster);
		for (int i = 0; i<neighbours.size();i++) {
			Point p = neighbours.get(i);
			if (!p.isVisited()) {
				p.setVisited(true);
				Vector<Point> closestPoints = getclosestNeighbours(p);
				if (closestPoints.size() >= this.minPts) {
					for( Point k : closestPoints )
						neighbours.add(k);
				}
			}
			if (p.getCluster() == -1)
				p.setCluster(cluster);
		}
	}
	
	/**
	 * Returns the closest neighbours within the distance of radius from the given point.
	 * 
	 * @param point The point under consideration.
	 * 
	 * @return Vector<Point> The list of closest neighbors.
	 *
	 */
	public Vector<Point> getclosestNeighbours(Point point) {
		Vector<Point> closestPoints = new Vector<Point>();
		for (Point p : points)
			if (p.distance(point) <= this.radius)
				closestPoints.add(p);
		return closestPoints;
	}
	/**
	 * Displays the Number of clusters and the noise point.
	 * 
	 */
	public void displayPoints() {

		System.out.println(clusters.size() + " density clusters found.");
		int count = 0;
		for (Point p : points) {
			if (p.getCluster() == -1) {
				System.out.println("Point [" + p.getX() + "," + p.getY()
						+ "] not assigned to a density cluster.");
				count++;
			}
		}
		System.out.println("Noise Count " + count);
		colorPoints();
	}
}
