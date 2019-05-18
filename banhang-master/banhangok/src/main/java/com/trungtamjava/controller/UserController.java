package com.trungtamjava.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.trungtamjava.model.UserDTO;
import com.trungtamjava.service.UserService;
import com.trungtamjava.validator.UserValidator;

@Controller
@RequestMapping("/admin")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	UserService userService;

	@Autowired
	MailSender mailSender;
////	public static void main(String[] args){
////		new UserController();
////	}
//	
//	@RequestMapping(value=("/list-user"), method=RequestMethod.GET)
//	public @ResponseBody List<UserDTO> listUser(HttpServletRequest request){ 
//		List<UserDTO> users = userService.getAllUsers();
//		return users;
//	}
//	
//	@RequestMapping(value=("/user/{userId}"), method=RequestMethod.GET)
//	public @ResponseBody UserDTO view(
//			@PathVariable(name="userId") int userId){
//		return userService.getUserById(userId);
//		
//	}
//
//	@RequestMapping(value="/add-user", method=RequestMethod.POST)
//	@ResponseStatus(code=HttpStatus.CREATED)
//	// @Valid canh User
//	public @ResponseBody void add(@RequestBody UserDTO user){ 
//			
//		userService.addUser(user);
//	}
	
		
	@RequestMapping(value=("/danh-sach-khach-hang"), method=RequestMethod.GET)
	public String getAllUser(HttpServletRequest request, HttpSession session){
		List<UserDTO> users = userService.getAllUsers();
		request.setAttribute("users", users);
		session.setAttribute("users", users);
		return"listUsers";
		//return "user/listUsers";
		
	}
	
	@RequestMapping(value=("/chi-tiet-khach-hang/{userId}"), method=RequestMethod.GET)
	public String viewUser(HttpServletRequest request, @PathVariable(name="userId") int userId){
		request.setAttribute("user", userService.getUserById(userId));
		return "viewUser";
		
	}
	
	// dg dan den trang them khach hang dung get
	@RequestMapping(value=("/them-khach-hang"), method=RequestMethod.GET)
	public String addUser(HttpServletRequest request){
		request.setAttribute("user", new UserDTO());
		return "addUser";
		
	}
	
	// them ng dung
	@RequestMapping(value="/them-khach-hang", method=RequestMethod.POST)
	// @Valid canh User
	public String addUser(HttpServletRequest request, 
			@ModelAttribute("user")  UserDTO user, BindingResult bindingResult){
			userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			return "addUser";
		}
		userService.addUser(user);
		return "redirect:/admin/danh-sach-khach-hang";
	}
	
	// phuong thuc xoa
	
	@RequestMapping(value=("/xoa-khach-hang/{userId}"), method=RequestMethod.GET)
	public String deleteUser(HttpServletRequest request, @PathVariable(name="userId") int userId){
		userService.deleteUser(userId);
		return "redirect:/admin/danh-sach-khach-hang";
		
	}
	
	// lay danh sach hien thi ra de sua khach hang, bang get
	
	@RequestMapping(value=("/sua-khach-hang/{userId}"), method=RequestMethod.GET)
	public String editUser(HttpServletRequest request, @PathVariable(name="userId") int userId){
		request.setAttribute("user", userService.getUserById(userId));
		return "editUser";
	}
	
	// sau khi lay dk thong tin hien thi ra roi ta update lai vao csdl, bang pt post
	
	
	@RequestMapping(value="/sua-khach-hang", method=RequestMethod.POST)
	// @Valid canh User
	public String editUser(HttpServletRequest request, 
			@ModelAttribute("user")  UserDTO user, BindingResult bindingResult){
			userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			return "editUser";
		}
		userService.updateUser(user);
		
		sendEmail("anhhop200193@gmail.com","namstyle29@gmail.com", "hello", "xin chao");
		return "redirect:/admin/danh-sach-khach-hang";
	}
	
	
	public void sendEmail(String from,String to, String subject, String content) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);
		mailSender.send(mailMessage);
		
	}
	
	
}

//package com.trungtamjava.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.trungtamjava.model.UserDTO;
//import com.trungtamjava.service.UserService;
//
//@RestController
//public class RestUserController {
//
//	@Autowired
//	UserService userService;
//	
////	public static void main(String[] args){
////		new UserController();
////	}
//	
//	@RequestMapping(value=("/list-user"), method=RequestMethod.GET)
//	public List<UserDTO> listUser(HttpServletRequest request){ 
//		List<UserDTO> users = userService.getAllUsers();
//		return users;
//	}
//	
//	@RequestMapping(value=("/user/{userId}"), method=RequestMethod.GET)
//	public  UserDTO view(
//			@PathVariable(name="userId") int userId){
//		return userService.getUserById(userId);
//		
//	}
//
//	@RequestMapping(value="/add-user", method=RequestMethod.POST)
//	@ResponseStatus(code=HttpStatus.CREATED)
//	// @Valid canh User
//	public void add(@RequestBody UserDTO user){ 
//			
//		userService.addUser(user);
//	}
//}

