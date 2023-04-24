package taxicall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import taxicall.PointApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { PointApplication.class })
public class CucumberSpingConfiguration {}
