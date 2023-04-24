package taxicall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import taxicall.TaxiApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { TaxiApplication.class })
public class CucumberSpingConfiguration {}
