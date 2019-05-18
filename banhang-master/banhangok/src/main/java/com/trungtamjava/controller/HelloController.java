package com.trungtamjava.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.trungtamjava.model.UserDTO;
import com.trungtamjava.validator.UserValidator;

@Controller
//@RequestMapping("/admin")
public class HelloController {
	
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping("/say-hello")
	public ModelAndView sayhello(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(name="user", required=true)String username,
			@RequestHeader(name="content-type", required=false) String contentType) {		
		request.setAttribute("msg","trung tam java");
		return new ModelAndView("hello");
		
	}
//	path variable, doc gia tri gui len tu dg dan
	@RequestMapping("/hello/{name}/{id}")
	public String hello(HttpServletRequest request,
			@PathVariable(name="name")String name,
			@PathVariable(name="id") int userId){
		request.setAttribute("msg", userId);
		return "hello";	
		
	}
	
	@RequestMapping(value="/them-user", method=RequestMethod.GET)
	public String addUser(HttpServletRequest request){
		UserDTO user= new UserDTO();
		user.setName("spring");
		request.setAttribute("user", user);
		return "addUser";
	}
	
	@RequestMapping(value="/them-user", method=RequestMethod.POST)
	// @Valid canh User
	public String addUser(HttpServletRequest request, 
			@ModelAttribute("user")  UserDTO user, BindingResult bindingResult){
			userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			return "addUser";
		}
		
		MultipartFile file = user.getAvata();
		try {
			File newfile= new File("D:/upload/" + file.getOriginalFilename());
			FileOutputStream fileOutputStream;
			
				fileOutputStream = new FileOutputStream(newfile);
				fileOutputStream.write(file.getBytes());
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		request.setAttribute("u", user);
		return "viewUser";
	}
	
	@RequestMapping(value="/upload-file", method = RequestMethod.GET)
	public String upload(HttpServletRequest request){
		return "upload";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
		public String upload(HttpServletRequest request, 
			@RequestParam(name="file") List<MultipartFile>  files){
		for(MultipartFile file:files){	
			// luu file vao o cung
		try {
			File newfile= new File("D:/upload/" + file.getOriginalFilename());
			FileOutputStream fileOutputStream;
			
				fileOutputStream = new FileOutputStream(newfile);
				fileOutputStream.write(file.getBytes());
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
			request.setAttribute("files", files);
			return "viewUpload";
		}
	
	@RequestMapping(value="/dowload-file", method = RequestMethod.GET)
	public void dowload(HttpServletRequest request, HttpServletResponse response){
		String dataDirectory = "D:/upload/";
		Path file= Paths.get(dataDirectory,"1.png");
		if(Files.exists(file)){
			response.setContentType("image/png");
			response.addHeader("Content-Disposition", "attachment; filename"+"anh.png");
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
