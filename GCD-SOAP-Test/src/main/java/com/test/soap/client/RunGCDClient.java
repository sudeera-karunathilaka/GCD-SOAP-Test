package com.test.soap.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.soap.config.SoapClientConfig;

import io.demo.soap_web_service.GetGCDRequest;

/**
 * The Class RunGCDClient.
 */
public class RunGCDClient
{

  /**
   * The main method.
   *
   * @param args the arguments
   */
  @SuppressWarnings("resource")
  public static void main(String[] args)
  {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
    GCDClient client = context.getBean(GCDClient.class);

    GetGCDRequest request = new GetGCDRequest();

    client.getGCD(request);
  }
}
