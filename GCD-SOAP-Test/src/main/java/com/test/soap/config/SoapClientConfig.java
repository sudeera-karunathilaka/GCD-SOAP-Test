package com.test.soap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import com.test.soap.client.GCDClient;
import com.test.soap.client.GCDListClient;
import com.test.soap.client.GCDSumClient;

/**
 * The Class SoapClientConfig.
 */
@Configuration
public class SoapClientConfig {

	/** The jaxb 2 marshaller. */
	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	/**
	 * Security interceptor.
	 *
	 * @return the wss 4 j security interceptor
	 */
	@Bean
	public Wss4jSecurityInterceptor securityInterceptor() {
		Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
		wss4jSecurityInterceptor.setSecurementActions("Timestamp UsernameToken");
		wss4jSecurityInterceptor.setSecurementUsername("admin");
		wss4jSecurityInterceptor.setSecurementPassword("secret");
		wss4jSecurityInterceptor.setValidateResponse(false);
		return wss4jSecurityInterceptor;
	}

	/**
	 * Gets the GCD client.
	 *
	 * @return the GCD client
	 */
	@Bean
	public GCDClient getGCDClient() {
		GCDClient client = new GCDClient();
		setClientProperties(client);
		return client;
	}

	/**
	 * Gets the GCD list client.
	 *
	 * @return the GCD list client
	 */
	@Bean
	public GCDListClient getGCDListClient() {
		GCDListClient client = new GCDListClient();
		setClientProperties(client);
		return client;
	}

	/**
	 * Gets the GCD sum client.
	 *
	 * @return the GCD sum client
	 */
	@Bean
	public GCDSumClient getGCDSumClient() {
		GCDSumClient client = new GCDSumClient();
		setClientProperties(client);
		return client;
	}

	/**
	 * Sets the client properties.
	 *
	 * @param client
	 *            the new client properties
	 */
	private void setClientProperties(WebServiceGatewaySupport client) {
		client.setMarshaller(jaxb2Marshaller);
		client.setUnmarshaller(jaxb2Marshaller);
		ClientInterceptor[] interceptors = new ClientInterceptor[] { securityInterceptor() };
		client.setInterceptors(interceptors);
		client.setDefaultUri("http://localhost:8080/ws");
	}

}
