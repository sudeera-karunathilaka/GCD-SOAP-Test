package com.test.soap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.test.rest.model.GCDNumber;
import com.test.soap.model.GreatestCommonDivisor;
import com.test.soap.repository.GreatestCommonDivisorRepository;
import com.test.soap.service.GreatestCommonDivisorService;

/**
 * The Class GreatestCommonDivisorServiceImpl.
 */
@Service
public class GreatestCommonDivisorServiceImpl implements GreatestCommonDivisorService {

	@Autowired
	private GreatestCommonDivisorRepository greatestCommonDivisorRepository;

	@Autowired
	JmsTemplate jmsTemplate;

	@Value("${inbound.endpoint}")
	String destinationQueue;

	@Override
	public GCDNumber receiveGCDNumber() {
		return (GCDNumber) jmsTemplate.receiveAndConvert(destinationQueue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.soap.demo.service.api.GreatestCommonDivisorService#findGcd(int,
	 * int)
	 */
	@Override
	public int findGcd(int number1, int number2) {
		while (number1 != 0 && number2 != 0) // until either one of them is 0
		{
			int tempVal = number2;
			number2 = number1 % number2;
			number1 = tempVal;
		}
		int gcd = number1 + number2;

		saveGcd(gcd);

		return gcd;
	}

	/**
	 * Save gcd.
	 *
	 * @param gcd
	 *            the gcd
	 */
	private void saveGcd(int gcd) {
		GreatestCommonDivisor gcdObj = new GreatestCommonDivisor();
		gcdObj.setValue(gcd);

		greatestCommonDivisorRepository.save(gcdObj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.soap.demo.service.api.GreatestCommonDivisorService#gcds()
	 */
	@Override
	public String gcds() {
		List<Integer> gcds = greatestCommonDivisorRepository.findAllGcds();
		StringBuilder strBuilder = new StringBuilder();
		if (gcds != null && gcds.size() > 0) {

			for (Integer i : gcds) {
				if (strBuilder.toString().isEmpty())
					strBuilder.append("" + i);
				else
					strBuilder.append(" , " + i);
			}
		}

		return strBuilder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.soap.demo.service.api.GreatestCommonDivisorService#gcdSum()
	 */
	@Override
	public int gcdSum() {
		return greatestCommonDivisorRepository.findSumOfGcds();
	}

	@Override
	public void saveGCD(int gcd) {
		GreatestCommonDivisor gcdObj = new GreatestCommonDivisor();
		gcdObj.setValue(gcd);
		greatestCommonDivisorRepository.save(gcdObj);
	}
}
