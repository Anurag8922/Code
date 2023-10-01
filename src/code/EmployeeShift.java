package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeShift {
    public static void main(String[] args) {
        String csvFile = "employee_shifts.csv"; // Replace with your CSV file path

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean headerSkipped = false;

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm"); // Time format

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip the header row
                }

                String[] parts = line.split(",");
                String name = parts[0].trim();
                String position = parts[1].trim();
                Date startTime = dateFormat.parse(parts[2].trim());
                Date endTime = dateFormat.parse(parts[3].trim());

                long shiftDurationMillis = endTime.getTime() - startTime.getTime();
                long shiftDurationHours = shiftDurationMillis / (60 * 60 * 1000); // Convert to hours

                if (shiftDurationHours > 14) {
                    System.out.println("Employee Name: " + name + ", Position: " + position);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
