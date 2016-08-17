package com.nejokers.main.testrails;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.gurock.main.testrail.APIClient;
import com.gurock.main.testrail.APIException;

public class CRITestRails {

    // status_id 1 = Passed
    // 2 = Blocked
    // 4 = Retest
    // 5 = Failed
    // 6 = KnownIssue

    APIClient client;
    private String testCase;
    private String testRun;

    public void InitTestRail(String url, String userName, String password) {
        client = new APIClient(url);
        client.setUser(userName);
        client.setPassword(password);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void Passed(String TestRun, String TestCase, String Comment) throws MalformedURLException, IOException, APIException {
        Map data = new HashMap();
        data.put("status_id", new Integer(1));
        data.put("comment", Comment);
        @SuppressWarnings("unused")
        JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + TestRun + "/" + TestCase, data);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void Blocked(String TestRun, String TestCase, String Comment) throws MalformedURLException, IOException, APIException {
        Map data = new HashMap();
        data.put("status_id", new Integer(2));
        data.put("comment", Comment);
        @SuppressWarnings("unused")
        JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + TestRun + "/" + TestCase, data);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void Retest(String TestRun, String TestCase, String Comment) throws MalformedURLException, IOException, APIException {
        Map data = new HashMap();
        data.put("status_id", new Integer(4));
        data.put("comment", Comment);
        @SuppressWarnings("unused")
        JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + TestRun + "/" + TestCase, data);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void Failed(String TestRun, String TestCase, String Comment) throws MalformedURLException, IOException, APIException {
        Map data = new HashMap();
        data.put("status_id", new Integer(5));
        data.put("comment", Comment);
        @SuppressWarnings("unused")
        JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + TestRun + "/" + TestCase, data);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void KnowIssue(String TestRun, String TestCase, String Comment) throws MalformedURLException, IOException, APIException {
        Map data = new HashMap();
        data.put("status_id", new Integer(6));
        data.put("comment", Comment);
        @SuppressWarnings("unused")
        JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + TestRun + "/" + TestCase, data);
    }

    public JSONArray getProjectId(String projectId) throws MalformedURLException, IOException, APIException {
        JSONArray arr = (JSONArray) client.sendGet("get_sections/" + projectId);
        return arr;
    }

    public JSONArray getSectionsByProjectId(String projectId, String suiteId,
            Object section_id) throws MalformedURLException, IOException, APIException {
        JSONArray casesArray =
            (JSONArray) client.sendGet("get_cases/" + projectId + "&suite_id=" + suiteId + "&section_id=" + section_id);
        return casesArray;
    }

    public JSONObject getSection(Object section_id) throws MalformedURLException, IOException, APIException {
        JSONObject sectionObject = (JSONObject) client.sendGet("get_section/" + section_id);
        return sectionObject;
    }

    public void updateTestRails(WebDriver driver, String browse,
            String TestCase, String TestRun, ITestResult result) {

        if (TestCase.length() > 0) {
            if (result.getStatus() == ITestResult.SUCCESS) {

                try {
                    Passed(TestRun, TestCase, "Automation Passed  = " + browse);
                } catch (IOException | APIException e) {
                    e.printStackTrace();
                }
            } else {
                try {

                    Failed(TestRun, TestCase, "Failed on Browser = " + browse);

                } catch (IOException | APIException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateTestRails(WebDriver driver, String browse,
            String TestCase, String TestRun, ITestResult result, int KnownIssue) {

        if (TestCase.length() > 0) {
            if (result.getStatus() == ITestResult.SUCCESS) {

                try {
                    Passed(TestRun, TestCase, "Automation Passed on Browser = " + browse);
                } catch (IOException | APIException e) {
                    e.printStackTrace();
                }
            } else {
                try {

                    KnowIssue(TestRun, TestCase, "Failed on Browser = " + browse);

                } catch (IOException | APIException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateTestRails(String TestCase, String TestRun,
            ITestResult result) {
        //System.out.println("Test Case = [" + TestCase + "]");
        if (TestCase.length() > 0) {
            if (result.getStatus() == ITestResult.SUCCESS) {

                try {
                    Passed(TestRun, TestCase, "Automation Passed (no browser test)");
                } catch (IOException | APIException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Failed(TestRun, TestCase, "Failed (no browser test)");
                } catch (IOException | APIException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String returnTestRun(ITestContext context) {

        switch (context.getCurrentXmlTest().getParameter("browse")) {
            case "CHROME":
                return context.getCurrentXmlTest().getParameter("chromeTestRun");
            case "FIREFOX":
                return context.getCurrentXmlTest().getParameter("firefoxTestRun");
            case "IE":
                return context.getCurrentXmlTest().getParameter("ieTestRun");
            case "ANDROID_WEB":
                return context.getCurrentXmlTest().getParameter("androidWebTestRun");
            case "ANDROID_APP":
                return context.getCurrentXmlTest().getParameter("androidAppTestRun");
            case "IOS_APP":
                return context.getCurrentXmlTest().getParameter("IOSTestRun");
            default:
                return context.getCurrentXmlTest().getParameter("firefoxTestRun");
        }
    }


    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public String getTestRun() {
        return testRun;
    }

    public void setTestRun(String testRun) {
        this.testRun = testRun;
    }

}
