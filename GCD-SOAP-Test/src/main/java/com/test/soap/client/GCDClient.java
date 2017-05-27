package com.test.soap.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import io.demo.soap_web_service.GetGCDRequest;
import io.demo.soap_web_service.GetGCDResponse;

/**
 * The Class GCDClient.
 */
public class GCDClient extends WebServiceGatewaySupport
{

  /**
   * Gets the gcd response.
   *
   * @param request the request
   * @return the gcd
   */
  public GetGCDResponse getGCD(GetGCDRequest request)
  {
    return (GetGCDResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }
}
