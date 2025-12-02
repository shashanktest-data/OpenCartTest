package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseTest.BaseClass;

public class extentreportmanager implements ITestListener {

	public ExtentSparkReporter sparkReporter; //ui of the report
	public ExtentReports extent;//populate common info
	public ExtentTest test;//creating test case entries
	String repname;
	
	@Override
	public void onTestStart(ITestResult result) {
		  String className = result.getTestClass().getName();
	        String methodName = result.getMethod().getMethodName();
	     test=   extent.createTest(methodName + " (" + className + ")");
	      
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//test=extent.createTest(result.getTestClass().getName());//create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.PASS,"test case passed"+result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
	//test=extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());//to display groups in report
	//test.log(Status.FAIL, "test case failed is"+result.getName());
	//test.log(Status.INFO, "test case failed reason is"+result.getThrowable().getMessage());
	test.log(Status.FAIL, "Test case failed: " + result.getName());
	test.log(Status.FAIL, "Reason: " + result.getThrowable());

	try {
		BaseClass base = (BaseClass) result.getInstance();  // get running test instance
		String imgpath= base.captureScreen(result.getName());
	test.addScreenCaptureFromPath(imgpath);
			}catch(Exception e)
	{
		e.printStackTrace();
	}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.SKIP, "test case skipped is"+result.getName());
		test.log(Status.INFO, "test case skipped reason is"+result.getThrowable().getMessage());
	}

	@Override
	public void onStart(ITestContext context) {
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentDatetimestamp=df.format(dt);
		 String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		 
		//String reportpath=System.getProperty("user.dir")+"/reports/"+timestamp+"_"+ "myReport.html";
		 repname="Test-Report-"+timestamp+".html";
		 sparkReporter = new ExtentSparkReporter(".\\reports\\"+repname);
		sparkReporter.config().setDocumentTitle("automation report");
		sparkReporter.config().setReportName("functional");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("application", "open cart");
		extent.setSystemInfo("env", "qa");
		extent.setSystemInfo("browser", "chrome");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("User", System.getProperty("user.name"));
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
	    String env = context.getCurrentXmlTest().getParameter("env");
	    extent.setSystemInfo("Environment", env);
	    System.out.println("Browser: " + browser + ", Environment: " + env);
	    List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
	    if(!includedGroups.isEmpty())
	    {
	    	extent.setSystemInfo("Groups", includedGroups.toString());
	    }
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repname;
		File extentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	/*	try {
			URL url =new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repname);
			ImageHtmlEmail email=new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("SHASHANKSC50@GMAIL.COM", "PASSSWORD"));
            email.setSSLOnConnect(true);
			try {
				email.setFrom("shashanksc50@gmail.com");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			email.setSubject("Test Results");
			try {
				email.setMsg("Please find attached report!");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				email.addTo("ershashank@gmail.com");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				email.attach(url,"extent report","please check report..");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				email.send();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //send the email)
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();*/
		//}
		
	}

	

}
