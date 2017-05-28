package com.test.soap.config;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * The Class WebServiceConfig.
 */
@EnableWs
@Configuration
@ComponentScan(basePackages = { "com.test.soap.repository", "com.test.soap.service" })
@PropertySource(value = { "classpath:application.properties" })
public class WebServiceConfig extends WsConfigurerAdapter {
	
	/** The user name. */
	@Value("${security.username}")
	private String userName;
	
	/** The password. */
	@Value("${security.password}")
	private String password;

	/**
	 * Security callback handler.
	 *
	 * @return the simple password validation callback handler
	 */
	@Bean
	public SimplePasswordValidationCallbackHandler securityCallbackHandler() {
		SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
		Properties users = new Properties();
		// users.setProperty("admin", "secret");
		users.setProperty(userName, password);
		callbackHandler.setUsers(users);
		return callbackHandler;
	}

	/**
	 * Security interceptor.
	 *
	 * @return the wss 4 j security interceptor
	 */
	@Bean
	public Wss4jSecurityInterceptor securityInterceptor() {
		Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
		securityInterceptor.setValidationActions("Timestamp UsernameToken");
		securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());
		return securityInterceptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.ws.config.annotation.WsConfigurerAdapter#
	 * addInterceptors(java.util.List)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInterceptors(List interceptors) {
		interceptors.add(securityInterceptor());
	}

	/**
	 * Message dispatcher servlet.
	 *
	 * @param applicationContext
	 *            the application context
	 * @return the servlet registration bean
	 */
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	/**
	 * Default wsdl 11 definition.
	 *
	 * @param gcdSchema
	 *            the gcd schema
	 * @return the default wsdl 11 definition
	 */
	@Bean(name = "gcd")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema gcdSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("GcdPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://demo.io/soap-web-service");
		wsdl11Definition.setSchema(gcdSchema);
		return wsdl11Definition;
	}

	/**
	 * Gcd schema.
	 *
	 * @return the xsd schema
	 */
	@Bean
	public XsdSchema gcdSchema() {
		return new SimpleXsdSchema(new ClassPathResource("greatest-common-divisor.xsd"));
	}
}
