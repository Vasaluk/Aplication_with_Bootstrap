package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {
	private UserServiceImp userService;

	private RoleServiceImp roleService;

	@Autowired
	public AdminsController(UserServiceImp userService, RoleServiceImp roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping()
	public String printUsers(ModelMap model, @ModelAttribute("user") User user) {
		model.addAttribute("users", userService.listAll());
		model.addAttribute("messages", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		model.addAttribute("roles", roleService.listAll());

		return "admin/printUsers";
	}

	@PostMapping()
	public String createUser(@ModelAttribute("user") User user, @RequestParam("idRoles") List<Long> idRoles) {
		user.setRoles(roleService.getRole(idRoles));
		userService.saveUser(user);
		return "redirect:/admin";
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/admin";
	}

	@PatchMapping("/update/{id}")
	public String updateUser( @ModelAttribute("user") User user, ModelMap model, @RequestParam("idRoles") List<Long> idRoles) {
		model.addAttribute("roles", roleService.listAll());
		user.setRoles(roleService.getRole(idRoles));
		userService.saveUser(user);
		return "redirect:/admin";
	}
}
