package com.tired7.controller;

import com.tired7.model.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToDoController {

    private List<Todo> todos = new ArrayList<>();

    @GetMapping("/")
    public String showTasks(Model model) {
        model.addAttribute("todos", todos);
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Todo todo) {
        todo.setId(todos.size() + 1);
        todos.add(todo);
        return "redirect:/";
    }

    // Mark a task as completed
    @PostMapping("/complete/{id}")
    public String completeTask(@PathVariable int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setCompleted(!todo.isCompleted());
                break;
            }
        }
        return "redirect:/"; // Redirect back to task list
    }

    // Delete a task
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        todos.removeIf(todo -> todo.getId() == id);
        return "redirect:/"; // Redirect back to task list
    }
}
