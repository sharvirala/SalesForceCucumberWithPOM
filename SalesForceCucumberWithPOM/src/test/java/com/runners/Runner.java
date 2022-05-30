package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin= {"pretty", "html:target/cucumber-reports/cucumber.html",
"json:target/cucumber-reports/cucumber.json" },features={"src/test/resources/features/featurefileTC1andTc2.feature"}
,glue={"com.steps"},dryRun=false,monochrome = true,tags="@smoke or @regression")
public class Runner  extends AbstractTestNGCucumberTests{

}
