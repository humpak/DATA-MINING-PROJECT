/**
 * 
 * AlgorithmFactory.java : A file to generate an instance of 
 * appropriate algorithm. 
 * 
 */
import java.util.Collections;
import java.util.List;

/**
 * A factory class to create an instance of appropriate algorithm.
 * 
 * @author Ashpak Shaikh
 *
 */
public class AlgorithmFactory {
	/**
	 * 
	 * Returns an instance of either KMeans or DBScan based on the input
	 * parameters.
	 * 
	 * @param code : "K" for k means and "D" for DBScan
	 * @param noOfClusterOrRadius : represents noOfClusters for kmenas or the radius for DBScan.
	 * @param points : The points to be clustered.
	 * @param assignTypeOrMinPts : Assignment Strategy for KMeans or the minimum no of points in one cluster.
	 * @return
	 */
	public static ClusteringAlgorithm getAlgorithm(String code, int noOfClusterOrRadius, List<Point> points, int assignTypeOrMinPts){
		if(code.equals("K"))
			return new KMeans("K-Means",noOfClusterOrRadius,points,assignTypeOrMinPts);
		else if(code.equals("D")) 
			return new DBScan("DBScan",Collections.synchronizedList(points),assignTypeOrMinPts,noOfClusterOrRadius);
		else 
			return null;
	}
}
