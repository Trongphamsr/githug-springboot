package com.fsoft.project.service;

import java.util.Map;

import com.fsoft.project.entity.Member;

public interface LoginService {
//  Member getMember(String email, String password);
  String loginPage(Map<String, Object> session, int index);
}
