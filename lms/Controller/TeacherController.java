package com.example.lms.Controller;

import com.example.lms.ApiRespones.ApiRespones;
import com.example.lms.Model.Student;
import com.example.lms.Model.Teacher;
import com.example.lms.Service.StudentService;
import com.example.lms.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {



    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity getTeacher(){
        ArrayList<Teacher> teachers=teacherService.getTeachers();
        return ResponseEntity.status(200).body(teachers);
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            ResponseEntity.status(400).body(message);
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiRespones("Teacher added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable int id, @RequestBody @Valid Teacher teacher , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            ResponseEntity.status(400).body(message);
        }
        boolean idupdate = teacherService.updateTeacher(id,teacher);
        if(idupdate){
            return ResponseEntity.status(200).body(new ApiRespones("Teacher updated"));
        }

        return ResponseEntity.status(400).body(new ApiRespones("Teacher not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable int id){
        boolean isDeleted= teacherService.deleteTeacher(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiRespones("Teacher Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiRespones("Teacher not found!"));
    }



    //Endpint 1
    @GetMapping("/getById{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            return  ResponseEntity.status(200).body(new ApiRespones("Teacher id is"+id));
        } else {
            return ResponseEntity.status(400).body("Teacher id not found");
        }
    }

    //Endpint 2
    @GetMapping("/salary/{salary}")
    public ResponseEntity<?> getTeachersBySalary(@PathVariable double salary) {
        ArrayList<Teacher> teachers = teacherService.getTeachersBySalary(salary);
        if (teachers.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiRespones("No teachers found with salary greater than or equal to " + salary));
        }
        return ResponseEntity.status(200).body(teachers);
    }


}



