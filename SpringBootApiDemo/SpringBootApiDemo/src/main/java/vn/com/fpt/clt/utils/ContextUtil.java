package vn.com.fpt.clt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextUtil {

	/** The application context */
    private static ApplicationContext applicationContext;

    /**
     * Constructors
     * @param applicationContext - the application context
     */
    @Autowired
    protected ContextUtil(ApplicationContext applicationContext) {
        ContextUtil.applicationContext = applicationContext;
    }

    /**
     * Get a bean by Class from application context
     * @param clazz - class type of bean
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * Get a bean by bean name from application context
     * @param name - the name of Bean
     * @return bean
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * Get message from resources
     * @param code - the message code
     * @return message value
     */
    public static String getMessage(String code) {
        return ContextUtil.getMessage(code, new String[] {});
    }

    /**
     * Get message from resources
     * @param code - the message code
     * @param args - the variable of message
     * @return message value
     */
    public static String getMessage(String code, String... args) {
        return applicationContext.getBean(MessageSource.class).getMessage(code, args,
                LocaleContextHolder.getLocale());
    }
}
