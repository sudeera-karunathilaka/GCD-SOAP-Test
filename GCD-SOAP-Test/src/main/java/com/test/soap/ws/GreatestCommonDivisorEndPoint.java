package com.test.soap.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.test.rest.model.GCDNumber;
import com.test.soap.service.GreatestCommonDivisorService;

import io.demo.soap_web_service.GetGCDListRequest;
import io.demo.soap_web_service.GetGCDListResponse;
import io.demo.soap_web_service.GetGCDRequest;
import io.demo.soap_web_service.GetGCDResponse;
import io.demo.soap_web_service.GetGCDSumRequest;
import io.demo.soap_web_service.GetGCDSumResponse;

/**
 * The Class GreatestCommonDivisorEndPoint.
 */
@Endpoint
public class GreatestCommonDivisorEndPoint {

	/** The Constant NAMESPACE_URI. */
	private static final String NAMESPACE_URI = "http://demo.io/soap-web-service";

	/** The greatest common divisor service. */
	@Autowired
	private GreatestCommonDivisorService greatestCommonDivisorService;

	/**
	 * Gets the gcd.
	 *
	 * @param request
	 *            the request
	 * @return the gcd
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGCDRequest")
	@ResponsePayload
	public GetGCDResponse getGcd(@RequestPayload GetGCDRequest request) {
		GCDNumber gcdNumber = greatestCommonDivisorService.receiveGCDNumber();
		GetGCDResponse response = new GetGCDResponse();
		int gcd = greatestCommonDivisorService.findGcd(gcdNumber.getNumberOne(), gcdNumber.getNumberTwo());
		greatestCommonDivisorService.saveGCD(gcd);
		response.setGcd(gcd);

		return response;
	}

	/**
	 * Gets the gcd list.
	 *
	 * @param request
	 *            the request
	 * @return the gcd list
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGCDListRequest")
	@ResponsePayload
	public GetGCDListResponse getGcdList(@RequestPayload GetGCDListRequest request) {
		GetGCDListResponse response = new GetGCDListResponse();
		response.setGcds(greatestCommonDivisorService.gcds());

		return response;
	}

	/**
	 * Gets the gcd sum.
	 *
	 * @param request
	 *            the request
	 * @return the gcd sum
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGCDSumRequest")
	@ResponsePayload
	public GetGCDSumResponse getGcdSum(@RequestPayload GetGCDSumRequest request) {
		GetGCDSumResponse response = new GetGCDSumResponse();
		response.setGcdsum(greatestCommonDivisorService.gcdSum());

		return response;
	}

}
