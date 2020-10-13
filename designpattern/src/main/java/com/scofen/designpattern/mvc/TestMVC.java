package com.scofen.designpattern.mvc;

/**
 * Create by  GF  in  10:06 2019/3/15
 * Description:
 * Modified  By:
 */
public class TestMVC {
    public static void main(String[] args) {

        //从数据库获取学生记录
        StudentModel model  = retriveStudentFromDatabase();

        //创建一个视图：把学生详细信息输出到控制台
        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);

        controller.updateView();

        //更新模型数据
        model.setName("John");
        //controller.setModel(model);

        controller.updateView();
    }

    private static StudentModel retriveStudentFromDatabase(){
        StudentModel student = new StudentModel();
        student.setName("Robert");
        student.setRollNo("10");
        return student;
    }
}
