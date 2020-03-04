package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/projectFeatures", glue="com/ait/network/rest/selenium/customer/test")
public class CustomerRunner {

}