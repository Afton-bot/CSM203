import java.io.*;
import java.util.*;

public class CarParkingSimple {

    private static final String EMPTY = "--";
    private static final int CAPACITY = 10;

    public static List<String> readGarage(String path) {
        List<String> garage = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) garage.add(line.trim());
        } catch (Exception e) { System.out.println("Error reading file: " + e.getMessage()); }
        while (garage.size() < CAPACITY) garage.add(EMPTY);
        return garage;
    }

    public static void writeGarage(String path, List<String> garage) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, false))) {
            for (String s : garage) pw.println(s);
        } catch (Exception e) { System.out.println("Error writing file: " + e.getMessage()); }
    }

    public static boolean parkCar(List<String> garage, String plate) {
        for (int i = CAPACITY - 1; i >= 0; i--) {
            if (garage.get(i).equals(EMPTY)) {
                garage.set(i, plate);
                return true;
            }
        }
        return false;
    }

    public static boolean removeCar(List<String> garage, String plate) {
        for (int i = 0; i < CAPACITY; i++) {
            if (garage.get(i).equals(plate)) {
                garage.set(i, EMPTY);
                return true;
            }
        }
        return false;
    }

    public static void displayGarage(List<String> garage) {
        for (int i = 0; i < CAPACITY; i++) System.out.println((i+1) + ": " + garage.get(i));
        System.out.println("-----");
    }

    public static String chooseFile(String defaultPath) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        return input.isEmpty() ? defaultPath : input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filePath = chooseFile("car.txt");
        List<String> garage = readGarage(filePath);

        while (true) {
            displayGarage(garage);
            String cmd = sc.next();
            if (cmd.equalsIgnoreCase("exit")) break;
            String plate = sc.next();
            if (cmd.equalsIgnoreCase("A")) System.out.println(parkCar(garage, plate) ? "Car " + plate + " parked." : "Garage full.");
            else if (cmd.equalsIgnoreCase("D")) System.out.println(removeCar(garage, plate) ? "Car " + plate + " removed." : "Car " + plate + " not in garage.");
            writeGarage(filePath, garage);
        }
        sc.close();
    }
}
