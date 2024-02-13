package assignment1;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    	
    	Scanner myObj = new Scanner(System.in);
    	System.out.println("Option 1: NN Classifier");
    	System.out.println("Option 2: K-NN Classifier");
    	System.out.println("Option 3: Another Classifier");
    	System.out.println("Which option would you like to choose?: ");
    	int userChoice = myObj.nextInt();
    	myObj.nextLine();
    	
    	switch(userChoice) {
    		case 1: // NN Classifier
	    		Scanner myObj1 = new Scanner(System.in);
	        	System.out.println("Please enter the file name you would like to take data from: ");
	        	String inputFilename = myObj1.nextLine();
	        	
	            NNClassifier a = new NNClassifier();
	            a.printNN(inputFilename);
	            break;
            
    		case 2: // KNN Classifier
    			Scanner myObj2 = new Scanner(System.in);
	        	System.out.println("Please enter the training file name you would like to use (trainingData1.txt): ");
	        	String trainingFilename = myObj2.nextLine();
	        	
	        	System.out.println("Please enter the testing file name you would like to use (testingData1.txt): ");
	        	String testingFilename = myObj2.nextLine();
	        	
	        	System.out.println("Please enter the output file name you would like to create (result.txt): ");
	        	String outputFilename = myObj2.nextLine();
	        	
	            KNNClassifier b = new KNNClassifier();
	            b.printKNN(trainingFilename, testingFilename, outputFilename);
	            break;
	            
    		case 3: // Another Classifier
    			AnotherClassifier AC = new AnotherClassifier();
    	    	AC.determinePosition();
    	    	AC.positionResult();
    	    	break;
    	}
    }
}
