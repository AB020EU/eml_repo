package co.absa.eml.config;

import co.absa.eml.restinteractionpoints.RestInteractionPoints;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import co.absa.eml.environmentalvariables.EnvironmentalVariables;
import co.absa.eml.restinteractionpoints.RestInteractionPoints;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebDriverConfig {
    @Autowired

    RestInteractionPoints restInteractionPoints;
    public WebDriver webDriver(EnvironmentalVariables environmentVariablesDto) {

        WebDriver driver;
        String envStatus = environmentVariablesDto.getStatus();
        ChromeOptions chromeOptions = new ChromeOptions();

        if(envStatus.equalsIgnoreCase("Run")) {
            chromeOptions.setCapability("browserName", environmentVariablesDto.getChrome());
            chromeOptions.setCapability("platformName", "Windows XP");
            chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");
            chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//            chromeOptions.addArguments("--incognito");
            // chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
            try {
                driver = new RemoteWebDriver(new URL(environmentVariablesDto.getGridUrl()), chromeOptions);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver.manage().window().maximize();
        } else {
            chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            chromeOptions.addArguments("--incognito");
            // chromeOptions.addArguments("--headless");
            System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");

            driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public String nucleusUrl(String envId){
        Response env=restInteractionPoints.get("/get/application/variables/byid/"+envId);
        return env.getBody().path("nucleusUrl");

    }

    public EnvironmentalVariables getEnvironments(String envId) {
        Response env=restInteractionPoints.get("/get/application/variables/byid/"+envId);
        EnvironmentalVariables environmentVariablesDto = new EnvironmentalVariables();
        environmentVariablesDto.setEnvironment(env.getBody().path("environment"));
        environmentVariablesDto.setChrome(env.getBody().path("chrome"));
        environmentVariablesDto.setStatus(env.getBody().path("status"));
        environmentVariablesDto.setGridUrl(env.getBody().path("gridUrl"));
        environmentVariablesDto.setNucleusUrl(env.getBody().path("nucleusUrl"));
        environmentVariablesDto.setId(env.getBody().path("id"));
        return environmentVariablesDto;

    }
}
