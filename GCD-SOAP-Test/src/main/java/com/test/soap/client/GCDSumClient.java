package com.test.soap.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import io.demo.soap_web_service.GetGCDSumRequest;
import io.demo.soap_web_service.GetGCDSumResponse;

/**
 * The Class GCDSumClient.
 */
public class GCDSumClient extends WebServiceGatewaySupport
{

  /**
   * Gets the gcd.
   *
   * @param request the request
   * @return the gcd
   */
  public GetGCDSumResponse getGCD(GetGCDSumRequest request)
  {
    return (GetGCDSumResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }
}
