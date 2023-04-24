package taxicall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import taxicall.FrontApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { FrontApplication.class })
public class CucumberSpingConfiguration {}
