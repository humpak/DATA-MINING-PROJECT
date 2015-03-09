/**
 * Cluster.java : The file which runs the main program.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * @author Ashpak Shaikh
 * @author Richa Singh
 * 
 * Represents the class which runs the clustering algorithms.
 *
 */
public class Cluster {
	/**
	 * Takes the commandline arguments as input and runs either DBScan or
	 * KMeans algorithm with the spcified parameters.
	 * 
	 * @param args Command Line arguments.
	 * 
	 * @throws FileNotFoundException When the file is not found.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		if (args.length != 4) {
			System.out.println("Please enter the proper arguments");
			return;
		}

		String algorithm = args[0];
		String inputFile = args[1];
		
		int assignTypeOrMinPoints = Integer.parseInt(args[2]);
		int noOfClustersOrRadius = Integer.parseInt(args[3]);
		
		List<Point> points = new ArrayList<Point>();
		Scanner src = new Scanner(new File(inputFile));
		while (src.hasNextLine()) {
			if (src.hasNextInt()) {
				int x = src.nextInt();
				int y = src.nextInt();
				points.add(new Point(x, y));
			}
			else{
				src.nextLine();
			}
		}

		ClusteringAlgorithm ca = AlgorithmFactory.getAlgorithm(algorithm,
				noOfClustersOrRadius, points, assignTypeOrMinPoints);
		ca.runAlgorithm();

	}

}
