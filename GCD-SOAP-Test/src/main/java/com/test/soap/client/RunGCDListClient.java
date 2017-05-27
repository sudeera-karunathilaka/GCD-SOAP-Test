package com.test.soap.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.soap.config.SoapClientConfig;

import io.demo.soap_web_service.GetGCDListRequest;

/**
 * The Class RunGCDListClient.
 */
public class RunGCDListClient
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
    GCDListClient client = context.getBean(GCDListClient.class);

    GetGCDListRequest request = new GetGCDListRequest();

    client.getGCD(request);
  }
}
