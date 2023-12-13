package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
    @Autowired
	private EmpService empservice;
    
	@GetMapping("/")
	public String index(Model m) {
		List<Employee> list = empservice.getAllEmp();
		m.addAttribute("empList",list);
		return"index";
	}
	
	@GetMapping("/loadEmpSave")
	public String loadEmpSave() {
		return"emp_save";
	}
	
	@GetMapping("/EditEmp/{id}")
	public String EditEmp(@PathVariable int id,Model m){
		//System.out.println(id);
		 Employee emp = empservice.getEmpById(id);
		 m.addAttribute("emp",emp);
		return"edit";
	}
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp,HttpSession session) {
		//System.out.println(emp);
		Employee newEmp=empservice.saveEmp(emp);
		if(newEmp!=null) {
			session.setAttribute("msg","Register successfully");
		}
		else {
			session.setAttribute("msg","something wrong on server");
		}
		//return"emp_save";
		return"redirect:/loadEmpSave";
	}
	@PostMapping("/updateEmpDetails")
	public String updateEmp(@ModelAttribute Employee emp,HttpSession session) {
		//System.out.println(emp);
		Employee updateEmp=empservice.saveEmp(emp);
		if(updateEmp!=null) {
			session.setAttribute("msg","Updated successfully");
		}
		else {
			session.setAttribute("msg","something wrong on server");
		}
		//return"emp_save";
		return "redirect:/";
	}
	@GetMapping("/deleteEmp/{id}")
	public String deleteEmp(@PathVariable int id,HttpSession session) {
		boolean f=empservice.deleteEmp(id);
		if(f) {
			session.setAttribute("msg","Deleted successfully");
		}
		else {
			session.setAttribute("msg","something wrong on server");
		}
		
		return"redirect:/";
	}
	
	
}
