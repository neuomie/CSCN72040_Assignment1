package assignment1; //package name
import java.io.*;
import java.util.*;

public class KNNClassifier //class KNNClassifier.
{
    // Datapoint class with features and label
    static class DataPoint //nested class.
    {
        double[] features;//array of double to store features
        String label;//string variable to store labels.

        DataPoint(double[] features, String label) //constructor for datapoint class.
        {
            this.features = features;   //assigning features array passed as parameter
            this.label = label;  //assigning label passed as parameter
        }
    }

    // Method to read data from a file and parse into DataPoint objects
    private static List<DataPoint> readDataFromFile(String filename) throws FileNotFoundException //method declaration to read data from file and return list of Datapoints.
    {
        List<DataPoint> dataPoints = new ArrayList<>(); //creating a new ArrayList to store Dtapoints
        Scanner scanner = new Scanner(new File(filename));//Scanner object to read file.

        //adding a loop to read each and every line of the file.
        while (scanner.hasNextLine()) //will check if there is another line
        {
            String line = scanner.nextLine(); //read the next line from file.
            String[] tokens = line.split(",");  //splitting the line by comma and storing tokens in array.
            double[] features = extractFeatures(tokens); //extracting features fromthe token.
            String label = extractLabel(tokens);//extract labels
            dataPoints.add(new DataPoint(features, label));//create a new datapoint object and add it to the list.
        }

        scanner.close();//we close the scanner.
        return dataPoints;//return the list of datapoints.
    }

    //method to take features from array of tokens.
    private static double[] extractFeatures(String[] tokens) 
    {
        int numFeatures = tokens.length - 1;//calculate number of features.
        double[] features = new double[numFeatures]; //new array to store features.
        
        for (int i = 0; i < numFeatures; i++) //loop to extract features from tokens
        {
            features[i] = Double.parseDouble(tokens[i]);//parsing each token to double
        }
        return features;
    }

    //extract label from array of token
    private static String extractLabel(String[] tokens) 
    {
        return tokens[tokens.length - 1];//return last token as label.
    }

    //calculate Euclidean distance between the two vectors
    private static double calculateDistance(double[] a, double[] b) 
    {
        double sum = 0;
        for (int i = 0; i < a.length; i++) 
        {
            double difference = a[i] - b[i];
            sum += difference * difference;
        }
        return Math.sqrt(sum);
    }

    //classify unknown data points using KNN
    public static String classify(List<DataPoint> trainingData, double[] unknownFeatures, int k) 
    {
    	 PriorityQueue<DataPoint> nearestNeighbors = new PriorityQueue<>(
                 k, Comparator.comparingDouble(o -> -calculateDistance(unknownFeatures, o.features))
         ); //priority queue to store nearest neighbors.

    	  // Calculate distance from the unknown point to all others and store in priority queue
         for (DataPoint dataPoint : trainingData) {
             nearestNeighbors.add(dataPoint);
             if (nearestNeighbors.size() > k) {
                 nearestNeighbors.poll();
             }
         }

     // Create a map to store label counts
         Map<String, Integer> labelCount = new HashMap<>();
         nearestNeighbors.forEach(neighbor -> labelCount.put(neighbor.label, labelCount.getOrDefault(neighbor.label, 0) + 1));


         return Collections.max(labelCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
         

    //Main method to run the KNN classifier
    public void printKNN(String trainingfilename, String testingFilename) throws FileNotFoundException 
    {
        List<DataPoint> trainingData = readDataFromFile(trainingfilename);
        List<DataPoint> unknownData = readDataFromFile(testingFilename);
        int k = 3;//Set the value of k for KNN algorithm

        for (DataPoint unknown : unknownData) // Iterate through the unknown data points
        {
            String predictedLabel = classify(trainingData, unknown.features, k);// Classify the unknown data point
            System.out.println("The unknown data point with features " + Arrays.toString(unknown.features) +
                    " is classified as: " + predictedLabel);// Print the predicted label
        }
        }
    }