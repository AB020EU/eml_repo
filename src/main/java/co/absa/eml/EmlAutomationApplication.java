package co.absa.eml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class EmlAutomationApplication {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {

		SpringApplication.run(EmlAutomationApplication.class, args);


//		SOAPInteractionPoint soapInteractionPoint= new SOAPInteractionPoint();
//		data data = new data();
//		soapInteractionPoint.send("https://eomlsit.absa.africa/homeloans/services/HomeLoanFulfilmentSOAP",data.test(),"\'\'");
	}

}
