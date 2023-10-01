package code;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeConsecutiveDays {

    public static void main(String[] args) {
        String csvFile = "employee_records.csv"; // Replace with your CSV file path

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean headerSkipped = false;
            String currentEmployee = null;
            int consecutiveDaysWorked = 0;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip the header row
                }

                String[] parts = line.split(",");
                String name = parts[0].trim();
                String position = parts[1].trim();
                int daysWorked = Integer.parseInt(parts[2].trim());

                if (daysWorked >= 7) {
                    if (!name.equals(currentEmployee)) {
                        currentEmployee = name;
                        consecutiveDaysWorked = 1;
                    } else {
                        consecutiveDaysWorked++;
                        if (consecutiveDaysWorked == 7) {
                            System.out.println("Employee Name: " + name + ", Position: " + position);
                        }
                    }
                } else {
                    // Reset consecutiveDaysWorked if less than 7 days
                    currentEmployee = null;
                    consecutiveDaysWorked = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


