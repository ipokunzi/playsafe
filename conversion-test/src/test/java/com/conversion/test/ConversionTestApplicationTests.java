package com.conversion.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest
class ConversionTestApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;

	@LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
	
	@Test
	void contextLoads() {
		System.out.print("Tests Working");
	}
	
	@Test
    public void test_ktoc() {
        double celsius = restTemplate.getForObject(getRootUrl() + "/api/v1/conversion/ktoc/0", Double.class);
        assertEquals(-273,15, celsius);
    }

	@Test
    public void test_ctok() {
        double kelvin = restTemplate.getForObject(getRootUrl() + "/api/v1/conversion/ctok/0", Double.class);
        assertEquals(273,15, kelvin);
    }
	
	@Test
    public void test_mtok() {
        double kilometers = restTemplate.getForObject(getRootUrl() + "/api/v1/conversion/mtok/1", Double.class);
        assertEquals(1.60934, kilometers);
    }
	
	@Test
    public void test_ktom() {
        double miles = restTemplate.getForObject(getRootUrl() + "/api/v1/conversion/ktom/1", Double.class);
        assertEquals(0.621371, miles);
    }
	
}
