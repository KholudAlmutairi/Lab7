package com.example.lms.Controller;

import com.example.lms.ApiRespones.ApiRespones;
import com.example.lms.Model.Course;
import com.example.lms.Model.Student;
import com.example.lms.Model.Teacher;
import com.example.lms.Service.StudentService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudents(){
        ArrayList<Student>students=studentService.getStudents();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            ResponseEntity.status(400).body(message);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiRespones("Student added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable int id,@RequestBody @Valid Student student ,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            ResponseEntity.status(400).body(message);
        }
        boolean idupdate =studentService.updateStudent(id,student);
        if(idupdate){
            return ResponseEntity.status(200).body(new ApiRespones("Student updated"));
        }

        return ResponseEntity.status(400).body(new ApiRespones("Student not found"));

    }

   @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id){
        boolean isDeleted=studentService.deleteStudent(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiRespones("Student Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiRespones("Student not found!"));
    }
   //1
    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Student student = studentService.getById(id);
        if (student != null) {
            return  ResponseEntity.status(200).body(new ApiRespones("Student is "+student));
        } else {
            return ResponseEntity.status(400).body(new ApiRespones("Student id not found"));
        }
    }


    //2
    @GetMapping("/{studentId}/courses")
    public ResponseEntity getRegisteredCourses(@PathVariable int studentId) {
        ArrayList<Course> registeredCourses = studentService.getRegisteredCourses(studentId);
        if (!registeredCourses.isEmpty()) {
            return ResponseEntity.status(200).body(registeredCourses);
        } else {
            return ResponseEntity.status(400).body(new ApiRespones("Student id not found"));
        }
    }
     //3
    @PostMapping("/{studentId}/enroll")
    public ResponseEntity enrollCourse(@PathVariable Student student, @RequestBody @Valid Course course) {
         studentService.enrollCourse(student, course);
         return ResponseEntity.status(200).body(new ApiRespones("Student enrolled successfully"));
        }


    //4
    @PutMapping("/{courseId}/students/{studentId}")
    public ResponseEntity updateStudentStatus(@PathVariable int courseId, @PathVariable int studentId, @RequestParam boolean attended, @RequestParam boolean passed) {
       studentService.updateStudentStatus(courseId, studentId, attended, passed);
        return ResponseEntity.status(200).body("Student status updated successfully.");
    }









}

