package com.emp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.emp.entity.Employee;
import com.emp.service.EmpService;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {

    @Autowired
    private EmpService service;

    @GetMapping("/")
    public String home(Model model) {
        List<Employee> emp = service.getAllEmp();
        model.addAttribute("emp", emp);
        return "index"; 
    }

    @GetMapping("/addemp")
    public String addEmpForm() {
        return "add_emp"; 
    }

    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee e, HttpSession session) {
        service.addEmp(e);
        session.setAttribute("msg", "Employee Added successfully..");
        return "redirect:/";
    } 

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Employee e= service.getEmpById(id);
        model.addAttribute("emp", e);
        return "edit";
    }

    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
        service.addEmp(emp); // Assuming you have an updateEmp method in EmpService
        session.setAttribute("msg", "Employee Data Updated Successfully..");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id, HttpSession session) {
        service.deleteEmp(id);
        session.setAttribute("msg", "Employee Data Deleted Successfully..");
        return "redirect:/";
    }
}
