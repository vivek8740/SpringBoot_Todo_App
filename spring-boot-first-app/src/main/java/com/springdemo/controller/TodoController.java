package com.springdemo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springdemo.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService service;
	String name;
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model){
		 name = (String) model.get("name");
		System.out.println(name);
		model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}
	
	@RequestMapping(value="/add-todos", method = RequestMethod.GET)
	public String showAddTodosPage(ModelMap model){
		return "add-todos";
	}
	
	@RequestMapping(value="/add-todos", method = RequestMethod.POST)
	public String addTodos(ModelMap model ,@RequestParam String description){
		service.addTodo(name, description, new Date(), false);
		model.put(name, name);
		return "redirect:/list-todos";
	}

	@RequestMapping(value="/delete-todos", method = RequestMethod.GET)
	public String deleteTodos(@RequestParam int id){
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
}