package com.example.beer_app;

import com.example.beer_app.utils.EndpointPrinterUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class BeerAppApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(BeerAppApplicationTests.class);
	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		try {
			this.handleContextRefresh();
		} catch (Exception e) {
			// catch all, we don't want our hack to kill our build/tests
			log.error("Couldn't print endpoints", e);
		}
	}

	private void handleContextRefresh() throws IOException {
		var requestMappingHandlerMapping = context.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		EndpointPrinterUtil.printToFile(map);
	}
}
