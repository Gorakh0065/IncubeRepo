package com.incubyte.testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) 
@CucumberOptions( 
		features = "src/test/resources/features", 
		glue = "com.incubyte.stepdefinitions" ,
		publish = true
		)

public class TestRunner {

}
