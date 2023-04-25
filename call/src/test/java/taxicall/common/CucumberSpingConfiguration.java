package taxicall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import taxicall.CallApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { CallApplication.class })
public class CucumberSpingConfiguration {}
