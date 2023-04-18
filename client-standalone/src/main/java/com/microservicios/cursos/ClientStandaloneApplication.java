package com.microservicios.cursos;

import com.microservicios.cursos.clients.DragonBallCharacterClient;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ClientStandaloneApplication  implements ApplicationRunner {

	@Autowired
	private DragonBallCharacterClient dragonBallCharacterClient;

	private static final Logger logger = LoggerFactory.getLogger(ClientStandaloneApplication.class);


	@Autowired
	private EurekaClient eurekaClient;

	public static void main(String[] args) {
		SpringApplication.run(ClientStandaloneApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		for (int i = 0; i < 1000; i++) {
			ResponseEntity<String> responseEntity = dragonBallCharacterClient.getApplicationName();
			logger.info("Status {} ",responseEntity.getStatusCode());
			logger.info("Body {} ",responseEntity.getBody());
		}

	}

	/**
	 * implementacion de cliente eureka
	 * @param args
	 * @throws Exception
	 */
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//
//		Application application = eurekaClient.getApplication("dragon-ball");
//
//		logger.info("Application name {} ",application.getName());
//
//		List<InstanceInfo> infoList = application.getInstances();
//		for (InstanceInfo instance:infoList
//			 ) {
//			logger.info("Ip address {} ",instance.getIPAddr());
//		}
//
//	}
}
