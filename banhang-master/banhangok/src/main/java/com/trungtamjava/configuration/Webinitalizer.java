package com.trungtamjava.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class Webinitalizer implements WebApplicationInitializer {    
	public void onStartup(ServletContext container) throws ServletException {        
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();        
		ctx.register(SpringConfiguration.class);        
		ctx.setServletContext(container);  
//		cau hinh xu ly loi
		DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//		end xu ly loi
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcherServlet);        
		servlet.setLoadOnStartup(1);        
		servlet.addMapping("/");
		
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8",true);
		container.addFilter("encodingFilter", characterEncodingFilter).addMappingForUrlPatterns(null, false,"/*");
	}
	}

//public class Webinitalizer extends AbstractAnnotationConfigDispatcherServletInitializer {    
//	@Override    protected Class<?>[] getRootConfigClasses() {        
//		return new Class[] { SpringConfiguration.class };    }      
//	@Override    protected Class<?>[] getServletConfigClasses() {        
//		return null;    }     @Override    protected String[] getServletMappings() {        
//			return new String[] { "/" };    
//			}
//	}