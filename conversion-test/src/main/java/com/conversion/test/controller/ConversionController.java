package com.conversion.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.conversion.test.service.DistanceService;
import com.conversion.test.service.TemperatureService;

@Controller
@RequestMapping("/api/v1/conversion")
public class ConversionController {
	private final TemperatureService temperatureService;
	private final DistanceService distanceService;
	
	@Autowired
	public ConversionController(TemperatureService temperatureService, DistanceService distanceService) {
		this.temperatureService = temperatureService;
		this.distanceService = distanceService;
	} 
	
	@GetMapping("/ktoc/{kelvin}")
	public ResponseEntity<Double> getCelsius(@PathVariable("kelvin") Double kelvin) {
		return new ResponseEntity<>(temperatureService.ktoc(kelvin), HttpStatus.OK);
	}
	
	@GetMapping("/ctok/{celsius}")
	public ResponseEntity<Double> getKelvin(@PathVariable("celsius") Double celsius) {
		return new ResponseEntity<>(temperatureService.ctok(celsius), HttpStatus.OK);
	}
	
	@GetMapping("/mtok/{miles}")
	public ResponseEntity<Double> getKilometers(@PathVariable("miles") Double miles) {
		return new ResponseEntity<>(distanceService.mtok(miles), HttpStatus.OK);
	}
	
	@GetMapping("/ktom/{kilometers}")
	public ResponseEntity<Double> getCelcius(@PathVariable("kilometers") Double kilometers) {
		return new ResponseEntity<>(distanceService.ktom(kilometers), HttpStatus.OK);
	}
}
