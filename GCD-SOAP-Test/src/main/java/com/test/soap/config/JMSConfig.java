package com.test.soap.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * The Class JMSConfig.
 */
@Configuration
@EnableJms
@PropertySource(value = { "classpath:application.properties" })
public class JMSConfig {

	/**
	 * Creates the marshalling message converter.
	 *
	 * @param jaxb2Marshaller the jaxb 2 marshaller
	 * @return the marshalling message converter
	 */
	@Bean
	public MarshallingMessageConverter createMarshallingMessageConverter(final Jaxb2Marshaller jaxb2Marshaller) {
		return new MarshallingMessageConverter(jaxb2Marshaller);
	}

	/**
	 * Creates the jaxb 2 marshaller.
	 *
	 * @param contextPath the context path
	 * @param schemaResource the schema resource
	 * @return the jaxb 2 marshaller
	 */
	@Bean
	public Jaxb2Marshaller createJaxb2Marshaller(@Value("${context.path}") final String contextPath,
			@Value("${schema.location}") final Resource schemaResource) {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath(contextPath);

		Resource schema = new ClassPathResource("greatest-common-divisor.xsd");
		jaxb2Marshaller.setSchema(schema);

		Map<String, Object> properties = new HashMap<>();
		properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxb2Marshaller.setMarshallerProperties(properties);
		return jaxb2Marshaller;
	}
}
