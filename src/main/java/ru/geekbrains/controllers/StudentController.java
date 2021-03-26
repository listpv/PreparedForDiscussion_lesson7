package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.Student;
import ru.geekbrains.repo.StudentRepo;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepo studentRepo;

    @GetMapping
    public String main(Model model){
        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

    @GetMapping("/edit/{id}")
    public String changeStudent(
            @PathVariable("id") Long id,
            Model model){
        model.addAttribute("student", studentRepo.getOne(id));
        return "student_change";
    }

    @PostMapping("/edit")
    public String changeStudent(@ModelAttribute Student student){
        System.out.println(student);
        studentRepo.save(student);
//        studentRepo.update(student.getId(), student.getName(), student.getAge());
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addStudent(
            Model model
    ) {
        model.addAttribute("student", new Student());
        return "student_add_form";
    }

    @PostMapping("/add")
    public String addStudent(
            @Valid @ModelAttribute Student student,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "student_add_form";
        }
       studentRepo.save(student);

        return "redirect:";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(
            @PathVariable("id") Long id,
            Model model){
        studentRepo.deleteById(id);
        return "redirect:/";
    }


}
