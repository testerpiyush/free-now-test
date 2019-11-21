package com.freenow.apitest.base;

import com.freenow.apitest.constants.ApiConstants;
import com.freenow.apitest.utility.ExtentManager;
import com.freenow.apitest.utility.ReportUtil;
import com.relevantcodes.extentreports.ExtentReports;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;

import static com.freenow.apitest.utility.Utils.readPropsFile;
import static com.freenow.apitest.utility.Utils.readTestProps;

public class TestBase {

    public static Properties testProps;
    private static Logger logger = Logger.getLogger(TestBase.class.getName());


    @BeforeSuite
    protected void setup(ITestContext context) {
        String filePath = "testReport.html";
        ExtentReports extent = ExtentManager.getReporter(filePath);
        extent.loadConfig(new File(System.getProperty("user.dir") + File.separator + "extent-config.xml"));
        setupSuite();
        ReportUtil.initReports(logger);
    }

    private void setupSuite() {
        testProps = readTestProps(ApiConstants.TEST_RESOURCE_DIR + "test.properties");
        String filePath = System.getProperty("user.dir") + File.separator + "log4j.properties";
        Properties log4jProps = readPropsFile(filePath);
        PropertyConfigurator.configure(log4jProps);
        logger.info("Suite Setup Completed");
    }


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        ReportUtil.startTest(method.getName());

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        ReportUtil.endTest();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        logger.info("Suite execution completed.");
        ReportUtil.endReports();
    }


}
