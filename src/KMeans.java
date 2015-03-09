/**
 * 
 * KMeans.java : This file has the implementation for the KMeans algorithm.
 * 
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the class which implements KMeans algorithm.
 * 
 * @author Ashpak Shaikh
 * @author Richa Singh
 *
 */
public class KMeans extends ClusteringAlgorithm {

	int noOfClusters;
	int assignType;
	Point[] centroids;
	
	/**
	 *
	 * Constructor which initializes the variables for the KMeans class.
	 *
	 * @param name : The name of the algorithm.
	 * @param noOfClusters : The no Of clusters for kmeans
	 * @param points : The data points.
	 * @param assignType : The assignment strategy
	 * 
	 */
	public KMeans( String name,int noOfClusters, List<Point> points,
			int assignType) {
		super (name, points);
		this.noOfClusters = noOfClusters;
		this.assignType = assignType;
		centroids = new Point[noOfClusters];
		assignInitialClusters();
	}
	
	/**
	 * Calculates the centroids from the points for each cluster.
	 * 
	 */
	private void calculateCentroids() {
		for (int k = 0; k < noOfClusters; k++) {
			long sumX = 0, sumY = 0;
			int count = 0;
			for (Point p : points) {
				if (p.getCluster() == k) {
					sumX += p.getX();
					sumY += p.getY();
					count++;
				}
			}
			int x = (count != 0) ? (int) (sumX / count) : centroids[k].getX();
			int y = (count != 0) ? (int) (sumY / count) : centroids[k].getY();
			centroids[k] = new Point(x, y, k);
		}
	}
	
	/**
	 * Assigns the initial clusters based on the appropriate assignment strategy.
	 * 
	 */
	private void assignInitialClusters() {
		if (assignType == 1){
			assignRoundR();
			calculateCentroids();
		}
		if(assignType == 3)
			assignPillar();
		if( assignType == 2)
		{
			assignRandom();
			calculateCentroids();
		}

	}
	
	/**
	 * 
	 * Assigns the initial points to random clusters. (Strategy 2)
	 * 
	 */
	private void assignRandom() {

		for (Point p: points ){
			p.setCluster((int)(Math.random()*1000)%noOfClusters);
		}
	}
	
	/**
	 * 
	 * Assigns the initial clusters based on the pillaring approach. (Strategy 3)
	 * 
	 */
	private void assignPillar() {
		int meanX = 0;
		int meanY = 0;
		for ( Point p : points ){
			meanX += p.getX();
			meanY += p.getY();
		}
		meanX /= points.size();
		meanY /= points.size();
		Point mean = new Point(meanX,meanY);
		List<Point> cent = new ArrayList<Point>();
		for( int i = 0;i<centroids.length; i++ ){
			centroids[i] = maxDistance(mean,cent);
			cent.add(centroids[i]);
			mean = centroids[i];
			centroids[i].setCluster(i);
		}
	}
	/**
	 * Returns the point with the maximum distance from the given point.
	 * 
	 * @param point : The given point.
	 * @param cent : List of centroids.
	 * 
	 * @return : The point with the maximum distance from the given point.
	 */
	private Point maxDistance(Point point,List<Point> cent){
		double maxDis = 0;
		Point maxPoint = null;
		for(Point p : points){
			double dist = p.distance(point);
			if( dist > maxDis && !cent.contains(p) ){
				maxDis = dist;
				maxPoint = p;
			}
		}
		return maxPoint;
	}
	/**
	 * 
	 * Assigns initial clusters according to the Round Robin Strategy.
	 * 
	 */
	private void assignRoundR() {
		int listNo = 0;
		for (Point p : points)
			p.setCluster((listNo++) % noOfClusters);
	}
	/**
	 * 
	 * Runs the K means Algorithm.
	 * 
	 */
	public void runAlgorithm() {
		boolean didChange = true;
		int iterations = 0;
		do {
			didChange = false;
			for (Point p : points) {
				double minDistance = Double.MAX_VALUE;
				Point closestCentroid = centroids[0];
				for (Point c : centroids) {
					double dist = p.distance(c);
					if (dist < minDistance) {
						minDistance = dist;
						closestCentroid = c;
					}
				}
				if (p.getCluster() != closestCentroid.getCluster()) {
					p.setCluster(closestCentroid.getCluster());
					didChange = true;
				}

			}
			calculateCentroids();
			iterations++;
		} while (didChange);
		System.out.println("RMS error ="+getRMSError());
		System.out.println("Iterations ="+iterations);
		colorPoints();
	}
	/**
	 * 
	 * Print the RMS error for each cluster and return the total 
	 * rms error for K means.
	 * 
	 * @return
	 */
	public double getRMSError(){
		long tlSum = 0;
		for (int k = 0; k < noOfClusters; k++) {
			long sum = 0;
			int count = 0;
			for (Point p : points) {
				if (p.getCluster() == k) {
					sum += Math.pow(p.distance(centroids[k]),2);
					count++;
				}
			}
			System.out.println("Rms error for cluster k = "+k+" is "+Math.sqrt(sum/count));
			tlSum += sum;
		}
		return Math.sqrt(tlSum/points.size());
	}
	
	/**
	 * 
	 * Displays all the points with the corresponding clusters.
	 * 
	 */
	public void displayPoints() {

		for (int k = 0; k < noOfClusters; k++) {
			System.out.println("Centroid = " + centroids[k]);
			for (Point p : points) {
				if (p.getCluster() == k) {
					System.out.println(p);
				}
			}
		}
		colorPoints();
	}

}
