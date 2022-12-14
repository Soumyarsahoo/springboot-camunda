package com.camunda.test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private RuntimeService runtimeService;

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String businessKey = UUID.randomUUID().toString();
		logger.info("Business Key generated: "+businessKey);

		
		Map<String, Object> processOrderData = new HashMap<>();
		processOrderData.put("CustomerName", "Soumya Ranjan Sahoo");
		processOrderData.put("item", "mobile");
		processOrderData.put("giftPackagingNeeded", true);
		processOrderData.put("zip code", "500032");
		
		Map<String, Object> processOrderDataForShipment = new HashMap<>();
		processOrderDataForShipment.put("CustomerName", "Soumya Ranjan Sahoo");
		processOrderDataForShipment.put("item", "mobile");
		processOrderDataForShipment.put("zip code", "500032");
		
		//starting the bpmn by its id
		runtimeService.startProcessInstanceByKey("order_send_task",businessKey,processOrderData);
		
		runtimeService.startProcessInstanceByKey("order_receive_task",businessKey,processOrderDataForShipment);
	}

}