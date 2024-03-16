package com.example.lms.Service;

import com.example.lms.Model.Course;
import com.example.lms.Model.Student;
import com.example.lms.Service.StudentService;
import  com.example.lms.Controller.StudentController;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses=new ArrayList<>();



    public ArrayList<Course> getCourses(){
        return courses;
    }


    public void addCourses(Course course){
        courses.add(course);
    }


    public boolean updateCourse(int id,Course course){
        for(int i=0; i<courses.size();i++){
            if(courses.get(i).getId()==id){
                courses.set(i,course);
                return true;
            }
        }
        return false;
    }


    public boolean deleteCourse(int id){
        for (int i=0;i<courses.size();i++){
            if(courses.get(i).getId()==id){
                courses.remove(i);
                return true;
            }

        }
        return  false;
    }

     //Endpoint 1
    public Course getById(int id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

    //Endpoint 2
    public int getEnrolledStudentsCount(int courseId) {
        Course course = getById(courseId);
        if (course != null) {
            return course.getRegisteredStudents().size();
        } else {
            return 0;
        }
    }


    //Endpoint 3
        public boolean isCourseAvailable(int courseId) {
            Course course = getById(courseId);
            return course != null && course.isAvailable();
        }

    //Endpoint 4
    public void updateCourseStatus(Course course) {
        if (course != null) {
            int numberOfRegisteredStudents = course.getRegisteredStudents().size();
            if (numberOfRegisteredStudents >= 20) {
                course.setStatus("Full");
            } else {
                course.setStatus("Available");
            }
        }
    }











        }

