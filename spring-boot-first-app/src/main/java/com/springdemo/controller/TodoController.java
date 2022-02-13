package com.springdemo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springdemo.model.Todo;
import com.springdemo.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	TodoService service;
	String name;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));		
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		name = (String) model.get("name");
		System.out.println(name);
		model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.GET)
	public String showAddTodosPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, (String) model.get("name"), "", new Date(), false));
		return "add-todos";
	}

	/**
	 * Method to add the description of logged in User
	 * 
	 * @param model
	 * @param todo
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add-todos", method = RequestMethod.POST)
	public String addTodos(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors())
			return "add-todos";

		service.addTodo(name, todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}

	/**
	 *
	 * Method to show the update the description form to logged in User
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update-todos", method = RequestMethod.GET)
	public String showUpdateTodos(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodos(id);
		model.put("todo", todo);
		return "add-todos";
	}

	/**
	 * Method to update the description of logged in User
	 * 
	 * @param model
	 * @param todo
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update-todos", method = RequestMethod.POST)
	public String updateTodos(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors())
			return "add-todos";

		todo.setUser(name);
		service.updateTodos(todo);
		return "redirect:/list-todos";
	}

	/**
	 * Method to delete the todo's of logged in User
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete-todos", method = RequestMethod.GET)
	public String deleteTodos(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
}