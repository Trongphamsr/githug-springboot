package com.trungtamjava.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.trungtamjava.controller.UserController;



@Configuration
@EnableWebMvc
// scan tat ca cac class com.trungtamjava
@ComponentScan(basePackages = {"com.trungtamjava"})
@PropertySource(value={"classpath:db.properties"})


// cau hinh transaction
@EnableTransactionManagement


public class SpringConfiguration extends WebMvcConfigurerAdapter{    
	
	
	
	@Autowired
	Environment environment;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
	@Bean    
	public ViewResolver viewResolver() {  
		
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();        
//		viewResolver.setViewClass(JstlView.class);        
//		
//		viewResolver.setPrefix("/WEB-INF/views/");        
//		viewResolver.setSuffix(".jsp");         

				TilesViewResolver viewResolver = new TilesViewResolver();
				return viewResolver;    
		} 

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSerder= new JavaMailSenderImpl();
		
		// using mail
		mailSerder.setHost("smtp.gmail.com");
		mailSerder.setPort(587);
		mailSerder.setUsername("anhhop200193@gmail.com");
		mailSerder.setPassword("HUYENVK100595");
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");
		
		mailSerder.setJavaMailProperties(javaMailProperties);
		return mailSerder;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/files/**").addResourceLocations("file:/D:/upload/");
	}
	
//	tao bean de them hien thi loi
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setBasename("classpath:messages");
		bundleMessageSource.setDefaultEncoding("utf-8");
		return bundleMessageSource;
// end hien thi loi		
	}
//	@Bean
//	public UserValidator userValidator(){
//		return new UserValidator();
//	}
	
	
	@Bean(name="multipartResolver")
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		
		commonsMultipartResolver.setMaxUploadSize(-1);
		return commonsMultipartResolver;
		
	}
	
	@Bean
	public DataSource dataSource(){
		
			
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(environment.getProperty("driver"));
//		dataSource.setUrl(environment.getProperty("url"));
//		dataSource.setUsername(environment.getProperty("username"));
//		dataSource.setPassword(environment.getProperty("password"));
//		return dataSource;
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://LATITUDE:49895; databaseName=banhang");
		dataSource.setUsername("sa");
		dataSource.setPassword("huyenvk100595");
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean(){
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		
		bean.setDataSource(dataSource());
		// packet nay chua cac class de map truong trinh java vs csdl
		bean.setPackagesToScan("com.trungtamjava.entity");
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		hibernateProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		
		bean.setHibernateProperties(hibernateProperties);
		return bean;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer configurer = new TilesConfigurer(); 
		configurer.setDefinitions("classpath:tiles.xml");
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	
	@Bean(name="transactionManager")
	@Autowired
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		return hibernateTransactionManager;
	}
	
	
//	@Bean
//	public JdbcTemplate jdbcTemplate(){
//		return new JdbcTemplate(dataSource());
//	}
	
	
	
	
//		
//	// tao bean transaction
//	@Bean(name="transactionManager")
//	public DataSourceTransactionManager dataSourceTransactionManager(){
//		return new DataSourceTransactionManager(dataSource());
//	}
	
	
}