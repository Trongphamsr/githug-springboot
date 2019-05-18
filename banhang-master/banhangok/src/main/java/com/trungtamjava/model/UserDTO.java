package com.trungtamjava.model;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public class UserDTO {
	//@NotEmpty
	private String name;
	//@Length(min=6,max=12)
	private String password;
	//@Min(0)
	private int id;
	private int phone;
	private List<String> favouries;
	private String gender;
	private String about;
	private boolean acceptAgreement;
	private MultipartFile avata;
	
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getFavouries() {
		return favouries;
	}
	public void setFavouries(List<String> favouries) {
		this.favouries = favouries;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public boolean isAcceptAgreement() {
		return acceptAgreement;
	}
	public void setAcceptAgreement(boolean acceptAgreement) {
		this.acceptAgreement = acceptAgreement;
	}
	public MultipartFile getAvata() {
		return avata;
	}
	public void setAvata(MultipartFile avata) {
		this.avata = avata;
	}
}
