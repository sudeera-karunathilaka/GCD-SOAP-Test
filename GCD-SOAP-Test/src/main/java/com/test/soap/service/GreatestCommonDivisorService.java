package com.test.soap.service;

import com.test.rest.model.GCDNumber;

/**
 * The Interface GreatestCommonDivisorService.
 */
public interface GreatestCommonDivisorService {

	GCDNumber receiveGCDNumber();
	/**
	 * Find gcd.
	 *
	 * @param number1 the number 1
	 * @param number2 the number 2
	 * @return the int
	 */
	int findGcd(int number1, int number2);
	
	/**
	 * Gcds.
	 *
	 * @return the string
	 */
	String gcds();
	
	/**
	 * Gcd sum.
	 *
	 * @return the int
	 */
	int gcdSum();

	void saveGCD(int gcd);

}
