package com.test.soap.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import io.demo.soap_web_service.GetGCDListRequest;
import io.demo.soap_web_service.GetGCDListResponse;

/**
 * The Class GCDListClient.
 */
public class GCDListClient extends WebServiceGatewaySupport
{

  /**
   * Gets the gcd list response.
   *
   * @param request the request
   * @return the gcd
   */
  public GetGCDListResponse getGCD(GetGCDListRequest request)
  {
    return (GetGCDListResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }
}
