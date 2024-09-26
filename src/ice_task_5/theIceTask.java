/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ice_task_5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class theIceTask 
{
    
    private Scanner myScanner = new Scanner(System.in);
    static final String FilePath = "Employees.txt";
    private static final int RECORD_SIZE = 52;
    
    public theIceTask()
    {
        
    }
    
    
    public void theProcess()
    {
        int iChoice;
        
        do
        {
        System.out.println("\nMenu:");
        System.out.println("1. Add new Employee");
        System.out.println("2. Update Employee Salary");
        System.out.println("3. Retrieve Employee Details");
        System.out.println("4. Exit");
        System.out.println("");
        System.out.print("Enter your Choice: ");
        iChoice = myScanner.nextInt();
            
        switch (iChoice)
                {
                case 1:
                    AddEmployee(myScanner);
                    break;
                case 2:
                    UpdateEmployee(myScanner);
                    break;
                case 3:
                    RetrieveEmployee(myScanner);
                    break;
                case 4:
                    System.out.println("Quiting Application");
                    break;
                default:
                    System.out.println("Invalid Response \nTry Again");
                    break;
                }
        }
        while (iChoice != 4);
        
            System.exit(0);
        
    }
    
    private static void AddEmployee(Scanner sc) {
        try (RandomAccessFile file = new RandomAccessFile(FilePath, "rw")) {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Employee Salary: ");
            double salary = sc.nextDouble();

            file.seek((id - 1) * RECORD_SIZE);  // Position based on ID
            EmployeeClass emp = new EmployeeClass(name, id, salary);
            emp.writeToFile(file);

            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void UpdateEmployee(Scanner sc) {
        try (RandomAccessFile file = new RandomAccessFile(FilePath, "rw")) {
            System.out.print("Enter Employee ID to update: ");
            int id = sc.nextInt();
            System.out.print("Enter new Salary: ");
            double newSalary = sc.nextDouble();

            file.seek((id - 1) * RECORD_SIZE);
            EmployeeClass emp = EmployeeClass.readFromFile(file);

            EmployeeClass updatedEmp = new EmployeeClass(emp.getEmployeeName(), emp.getEmployeeID(), newSalary);
            file.seek((id - 1) * RECORD_SIZE);
            updatedEmp.writeToFile(file);

            System.out.println("Salary updated successfully!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void RetrieveEmployee(Scanner sc) {
        try (RandomAccessFile file = new RandomAccessFile(FilePath, "r")) {
            System.out.print("Enter Employee ID to retrieve: ");
            int id = sc.nextInt();

            file.seek((id - 1) * RECORD_SIZE);
            EmployeeClass emp = EmployeeClass.readFromFile(file);

            System.out.println("Employee Details: ID = " + emp.getEmployeeID() +
                               ", Name = " + emp.getEmployeeName() +
                               ", Salary = " + emp.getEmployeeSalary());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
