package com.example.lms.Service;

import com.example.lms.Model.Course;
import com.example.lms.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(int id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }
     //Endpoint 1
    public Student getById(int id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
    //Endpoint 2
    public ArrayList<Course> getRegisteredCourses(int studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student.getRegisteredCourses();
            }
        }
        return new ArrayList<>();
    }


    //Endpoint 3

    public void enrollCourse(Student student, Course course) {
        ArrayList<Course> registeredCourses = student.getRegisteredCourses();
        if (registeredCourses == null) {
            registeredCourses = new ArrayList<>();
            student.setRegisteredCourses(registeredCourses);
        }
        registeredCourses.add(course);
    }


    //Endpoint 4
    public void updateStudentStatus(int studentId,int courseId, boolean attended, boolean passed) {
        ArrayList<Student> registeredStudents=new ArrayList<>();
        if (registeredStudents == null) {
            registeredStudents = new ArrayList<>();
        }
        for (Student student : registeredStudents) {
            if (student.getId() == student.getId()) {
                student.setAttended(attended);
                student.setPassed(passed);
                break;
            }
        }
    }


}
