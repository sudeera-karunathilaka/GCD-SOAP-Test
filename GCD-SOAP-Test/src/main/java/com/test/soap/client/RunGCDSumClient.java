package com.test.soap.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.soap.config.SoapClientConfig;

import io.demo.soap_web_service.GetGCDSumRequest;

/**
 * The Class RunGCDSumClient.
 */
public class RunGCDSumClient
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
    GCDSumClient client = context.getBean(GCDSumClient.class);

    GetGCDSumRequest request = new GetGCDSumRequest();

    client.getGCD(request);
  }
}
