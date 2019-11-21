package com.freenow.apitest.utility;

import com.freenow.apitest.constants.ApiConstants;
import com.jayway.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

public class Utils {

    public static Properties readPropsFile(String filePath) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static Properties readTestProps(String filePath) {
        Properties testProps = readPropsFile(filePath);
        Iterator<Object> iter = testProps.keySet().iterator();

        while (iter.hasNext()) {
            String key = iter.next().toString();
            String value = System.getProperty(key);
            if (value != null)
                testProps.setProperty(key, value);
        }
        return testProps;
    }

    public static int statusCodeValidation(Response response) {
        if (ApiConstants.STATUS_CODE_200 == response.getStatusCode() || response.asString().contains("Error")) {
            ReportUtil.log("Verify Status code ", "Status code is 200", "info");
            return 200;
        } else if (ApiConstants.STATUS_CODE_400 == response.getStatusCode()) {
            ReportUtil.log("Verify Status code ", "Status code is 400" + response.asString(), "fail");
            return 400;
        } else if (ApiConstants.STATUS_CODE_500 == response.getStatusCode()) {
            ReportUtil.log("Verify Status code ", "Status code is 500" + response.asString(), "fail");
            return 500;
        } else if (ApiConstants.STATUS_CODE_504 == response.getStatusCode()) {
            ReportUtil.log("Verify Status code ", "Status code is 504" + response.asString(), "fail");
            return 504;
        } else if (ApiConstants.STATUS_CODE_502 == response.getStatusCode()) {
            ReportUtil.log("Verify Status code ", "Status code is 502" + response.asString(), "fail");
            return 502;
        }
        return 0;
    }


}
