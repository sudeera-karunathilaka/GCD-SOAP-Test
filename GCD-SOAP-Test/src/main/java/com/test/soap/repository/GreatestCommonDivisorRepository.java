package com.test.soap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.soap.model.GreatestCommonDivisor;


/**
 * The Interface GreatestCommonDivisorRepository.
 */
@Repository
public interface GreatestCommonDivisorRepository extends JpaRepository<GreatestCommonDivisor, Integer> {

	/**
	 * Find sum of gcds.
	 *
	 * @return the int
	 */
	@Query("select sum(gcd.value) FROM GreatestCommonDivisor gcd")
	int findSumOfGcds();

	/**
	 * Find all gcds.
	 *
	 * @return the list
	 */
	@Query("select gcd.value FROM GreatestCommonDivisor gcd")
	List<Integer> findAllGcds();

}

