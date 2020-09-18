package com.conversion.test.service;

import org.springframework.stereotype.Service;

@Service
public class DistanceService {
	double MILES_CONSTANT = 0.621371;
	
	public double mtok(double miles){
		return miles / MILES_CONSTANT;	
	}
	
	public double ktom(double kilometers){	
		return kilometers * MILES_CONSTANT;
	}
}
