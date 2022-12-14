package com.camunda.test.service;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sendOrderDelegate")
public class SendOrderDelegate implements JavaDelegate {
	
	private static Logger logger = LoggerFactory.getLogger(SendOrderDelegate.class);

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		MessageCorrelationResult result = runtimeService.createMessageCorrelation("messageShipment")
				.processInstanceBusinessKey(execution.getBusinessKey())
				.setVariable("caller", SendOrderDelegate.class.getName()).correlateWithResult();
		
		logger.info("Business Key in delegate: "+execution.getBusinessKey());
		
		DelegateExecution shipmentExecution = (DelegateExecution)result.getExecution();
		
		logger.info("shipment Execution: "+shipmentExecution);

	}

}
