package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeShifts {
    public static void main(String[] args) {
        String csvFile = "employee_shifts.csv"; // Replace with your CSV file path

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean headerSkipped = false;
            String previousEmployee = null;
            Date previousEndTime = null;

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

                if (previousEmployee != null && name.equals(previousEmployee)) {
                    long timeBetweenShiftsMillis = startTime.getTime() - previousEndTime.getTime();
                    long hoursBetweenShifts = timeBetweenShiftsMillis / (60 * 60 * 1000); // Convert to hours

                    if (hoursBetweenShifts > 1 && hoursBetweenShifts < 10) {
                        System.out.println("Employee Name: " + name + ", Position: " + position);
                    }
                }

                previousEmployee = name;
                previousEndTime = endTime;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

