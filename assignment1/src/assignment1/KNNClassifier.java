package assignment1;
import java.io.*;
import java.util.*;

public class KNNClassifier {

    // Assuming a simple data point class with features and label
    static class DataPoint {
        double[] features;
        String label;

        DataPoint(double[] features, String label) {
            this.features = features;
            this.label = label;
        }
    }

    // Method to read data from a file and parse into DataPoint objects
    private static List<DataPoint> readDataFromFile(String filename) throws FileNotFoundException {
        List<DataPoint> dataPoints = new ArrayList<>();
        Scanner sc = new Scanner(new File(filename));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // Assuming the data is comma-separated and the last value is the label
            String[] tokens = line.split(",");
            double[] features = new double[tokens.length - 1];
            for (int i = 0; i < tokens.length - 1; i++) {
                features[i] = Double.parseDouble(tokens[i]);
            }
            String label = tokens[tokens.length - 1];
            dataPoints.add(new DataPoint(features, label));
        }

        sc.close();
        return dataPoints;
    }

    // Method to calculate Euclidean distance between two data points
    private static double calculateDistance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }

    // The KNN algorithm method
 // The KNN algorithm method
    public static String classify(List<DataPoint> trainingData, double[] unknownFeatures, int k) {
        PriorityQueue<DataPoint> nearestNeighbors = new PriorityQueue<>(
                k, Comparator.comparingDouble(o -> -calculateDistance(unknownFeatures, o.features))
        );

        // Calculate distance from the unknown point to all others and store in priority queue
        for (DataPoint dataPoint : trainingData) {
            nearestNeighbors.add(dataPoint);
            if (nearestNeighbors.size() > k) {
                nearestNeighbors.poll();
            }
        }

        // Count the frequency of labels among the k-nearest neighbors
        Map<String, Integer> labelCount = new HashMap<>();
        nearestNeighbors.forEach(neighbor -> labelCount.put(neighbor.label, labelCount.getOrDefault(neighbor.label, 0) + 1));

        // Find the label with the highest count
        return Collections.max(labelCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    // Main method to run the KNN classifier
    public static void main(String[] args) throws FileNotFoundException {
        List<DataPoint> trainingData = readDataFromFile("D:\\downloads\\trainingData1.txt");
        List<DataPoint> unknownData = readDataFromFile("D:\\downloads\\testingData1.txt");
        int k = 3; // Assuming k=3 for KNN

        for (DataPoint unknown : unknownData) {
            String predictedLabel = classify(trainingData, unknown.features, k);
            System.out.println("The unknown data point with features " + Arrays.toString(unknown.features) +
                    " is classified as: " + predictedLabel);
        }
    }
}
