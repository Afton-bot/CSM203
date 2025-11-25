import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class ReadFileExample {
    public static void main(String[] args) {
        File file = new File("/home/aft/Desktop/dt/Assignment/car.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading file.");
            }
        } else {
            System.out.println("File not found.");
        }
    }
}