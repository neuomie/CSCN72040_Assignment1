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
    	
    	switch(userChoice) {
    		case 1: // NN Classifier
	    		Scanner myObj1 = new Scanner(System.in);
	        	System.out.println("Please in the file name would like take data from: ");
	        	
	        	String inputFilename = myObj1.nextLine();
	        	
	            NNClassifier a = new NNClassifier();
	            a.printNN(inputFilename);
            
    		case 2:
    			Scanner myObj2 = new Scanner(System.in);
	        	System.out.println("Please in the training file name would like to train: ");
	        	
	        	String trainingFilename = myObj2.nextLine();
	        	
	        	System.out.println("Please in the testing file name would like to train: ");
	        	
	        	String testingFilename = myObj2.nextLine();
	        	
	        	
	            KNNClassifier b = new KNNClassifier();
	            b.printKNN(trainingFilename, testingFilename);
    	
    		case 3:
    			AnotherClassifier AC = new AnotherClassifier();
    	    	AC.determinePosition();
    	    	AC.positionResult();
    	}
    }
}