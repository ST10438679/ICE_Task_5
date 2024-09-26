/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ice_task_5;

import java.io.IOException;
import java.io.RandomAccessFile;


/**
 *
 * @author lab_services_student
 */
public class EmployeeClass 
{

    public EmployeeClass(String employeeName, int employeeID, double employeeSalary) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
    
    private String employeeName;
    private int employeeID;
    private double employeeSalary;
    
    
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeInt(employeeID);
        StringBuilder nameBuffer = new StringBuilder(employeeName);
        nameBuffer.setLength(20);
        file.writeChars(nameBuffer.toString());
        file.writeDouble(employeeSalary);
    }

    public static EmployeeClass readFromFile(RandomAccessFile file) throws IOException {
        int employeeID = file.readInt();
        char[] nameChars = new char[20];
        for (int i = 0; i < 20; i++) {
            nameChars[i] = file.readChar();
        }
        String employeeName = new String(nameChars).trim();
        double employeeSalary = file.readDouble();
        return new EmployeeClass(employeeName, employeeID, employeeSalary);
    }
    
}
