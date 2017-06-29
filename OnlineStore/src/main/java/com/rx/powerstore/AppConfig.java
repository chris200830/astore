package com.rx.powerstore;

//import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
@Configuration 
@EnableWebMvc
@ComponentScan(
		  basePackages ={ "com.rx.powerstore" }
		)
public class AppConfig extends WebMvcConfigurerAdapter  {
	   @Override
	    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		   registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	    }
    //start Thymeleaf specific configuration
    @Bean(name ="templateResolver")	
    public TemplateResolver getTemplateResolver() {
            TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("templates/");
            templateResolver.setCacheable(false);
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML5");
            return templateResolver;
        }
    @Bean(name ="templateEngine")	    
    public SpringTemplateEngine getTemplateEngine() {
    	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    	templateEngine.setTemplateResolver(getTemplateResolver());
    	templateEngine.addDialect(new SpringSecurityDialect());
	return templateEngine;
    }
    @Bean(name="viewResolver")
    public ThymeleafViewResolver getViewResolver(){
    	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver(); 
    	viewResolver.setTemplateEngine(getTemplateEngine());
	return viewResolver;
    }
    //end Thymeleaf specific configuration
/*    @Bean(name ="messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("resources/validation");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }*/
} 