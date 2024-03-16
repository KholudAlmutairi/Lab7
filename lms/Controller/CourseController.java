package com.example.lms.Controller;

import com.example.lms.ApiRespones.ApiRespones;
import com.example.lms.Model.Course;
import com.example.lms.Model.Student;
import com.example.lms.Service.CourseService;
import com.example.lms.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor

public class CourseController
{
    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getCourse(){
        ArrayList<Course> courses=courseService.getCourses();
        return ResponseEntity.status(200).body(courses);
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            ResponseEntity.status(400).body(message);
        }
        courseService.addCourses(course);
        return ResponseEntity.status(200).body(new ApiRespones("Course added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable int id, @RequestBody @Valid Course course , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            ResponseEntity.status(400).body(message);
        }
        boolean idupdate =courseService.updateCourse(id,course);
        if(idupdate){
            return ResponseEntity.status(200).body(new ApiRespones("Course updated"));
        }

        return ResponseEntity.status(400).body(new ApiRespones("Course not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable int id){
        boolean isDeleted=courseService.deleteCourse(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiRespones("Course Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiRespones("Course not found!"));
    }

    //1
    @GetMapping("getById/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Course course = courseService.getById(id);
        if (course != null) {
            return ResponseEntity.status(200).body(new ApiRespones("Course id is" + id));
        } else {
            return ResponseEntity.status(400).body(new ApiRespones("Course id not found!"));
        }

    }
    //2
    @GetMapping("/courses/{courseId}/students/count")
    public ResponseEntity getEnrolledStudentsCount(@PathVariable int courseId) {
        int enrolledStudentsCount = courseService.getEnrolledStudentsCount(courseId);
        return ResponseEntity.ok(enrolledStudentsCount);
    }


    //3
    @GetMapping("/{courseId}/checkAvailability")
    public ResponseEntity checkCourseAvailability(@PathVariable int courseId) {
        boolean isAvailable = courseService.isCourseAvailable(courseId);
        return ResponseEntity.status(200).body(isAvailable);
    }

    //4
    @PutMapping("/{courseId}/updateStatus")
    public ResponseEntity<String> updateCourseStatus(@RequestBody Course course) {
        courseService.updateCourseStatus(course);
        return ResponseEntity.ok("Course status updated successfully.");
    }





}











