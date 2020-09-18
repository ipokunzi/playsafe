package com.conversion.test.service;

import org.springframework.stereotype.Service;

@Service
public class TemperatureService {
	double KELVIN_CONSTANT = 273.15;
	
	public double ktoc(double kelvin){
		return kelvin - KELVIN_CONSTANT;	
	}
	
	public double ctok(double celsius){
		return  celsius + KELVIN_CONSTANT;
	}
}
