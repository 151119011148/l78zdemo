package com.scofen.designpattern.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by  GF  in  10:05 2019/3/15
 * Description:
 * Modified  By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentController {

    private StudentModel model;
    private StudentView view;

    public void updateView(){
        view.printStudentDetails(model.getName(), model.getRollNo());
    }
}
