package com.scofen.designpattern.mvc;

import lombok.Data;

/**
 * Create by  GF  in  10:04 2019/3/15
 * Description:
 * Modified  By:
 */
@Data
public class StudentView {

    public void printStudentDetails(String studentName, String studentRollNo){
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("Roll No: " + studentRollNo);
    }

}
