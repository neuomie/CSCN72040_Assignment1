package assignment1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class NNClassifier {
    @SuppressWarnings("unused")
    public  void printNN(String filename) {
        
        // Declaration and assignment
        float x;
        float y;
        float z;
        
        int Orientation;
        
        String Orientation1 = "Face up";
        String Orientation2 = "Face down";
        String Orientation3 = "Portrait";
        String Orientation4 = "Portrait upside down";
        String Orientation5 = "Landscape left";
        String Orientation6 = "Landscape Right";


        // opening the training file
       // String filePath = "D:\\downloads\\trainingData1.txt"; // this will have to vary person running the code

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            // Loop until the end of the file
            while ((line = reader.readLine()) != null) { 

                // Separates values by commas
                String[] values = line.split(","); 
                
                // makes sure each line has 4 values
                if (values.length >= 4) {

                    // reads the values and assigns them to a variable
                    x = Float.parseFloat(values[0]);
                    y = Float.parseFloat(values[1]);
                    z = Float.parseFloat(values[2]);
                    Orientation = Integer.parseInt(values[3]);
                    
                    switch(Orientation) {
                    case 1:
                        System.out.println("Orientation: " + Orientation1);
                        break;
                    case 2:
                        System.out.println("Orientation: " + Orientation2);
                        break;
                    case 3:
                        System.out.println("Orientation: " + Orientation3);
                        break;
                    case 4:
                        System.out.println("Orientation: " + Orientation4);
                        break;
                    case 5:
                        System.out.println("Orientation: " + Orientation5);
                        break;
                    case 6:
                        System.out.println("Orientation: " + Orientation6);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}