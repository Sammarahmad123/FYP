package test;


import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.core.annotation.Order;

import io.appium.java_client.MobileElement;

import org.junit.*;
import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.runners.MethodSorters;
import org.junit.Rule;


/**
 * Unit test for simple App.
 */

public class AppTest{
	public static String accessKey = "GVNJi4iYqsjcLUF4Mzdn";
	  public static String userName = "sammarahmad1";
	String appiumPort ="4723";
    String serverIp ="0.0.0.0";
    LoginPage lPage;
	 //static IOSDriver<IOSElement> driver;
   // public  AppiumDriver<MobileElement> driver;
    ProgrammesPage p_page=null;

	 public static URL url;
	 private static EnhancedAndroidDriver<MobileElement> driver;
	
	   @Rule
	    public TestWatcher watcher = Factory.createWatcher();

	
	 
	
		
	 	@Test
	 	@Order(1)
		public void verify() throws MalformedURLException,SocketException  {
			 
		 DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", "sALMAN");
			cap.setCapability("device", "4dbde386ded266a2");	
			cap.setCapability("platformName", "Android");	
			cap.setCapability("automationName","UiAutomator2");
			cap.setCapability("platformVersion", "6.0");
			cap.setCapability("appPackage", "uk.org.humanfocus.hfi");	
			cap.setCapability("appActivity", "uk.org.humanfocus.hfi.Home.MainActivity");
			cap.setCapability("app", "/Users/ahmsam/Downloads/Android Builds/Dev Builds/date n time Questions/humanFocus-debug (14).apk");
			
			cap.setCapability("autoGrantPermissions", "true");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");

			driver = Factory.createAndroidDriver(url,cap);
			
			

		    
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
				
			 lPage=new LoginPage(driver);
			 lPage.validateLoginpage();
			  boolean check= lPage.validateLoginpage();

			     // = lPage.loginTestCase();
			     
			     Assert.assertTrue(check); 
			   
		}
	 
	 
	 @Test
	 @Order(2)
	 public void verify2()throws SocketException
	 {
		  lPage=new LoginPage(driver);
			
			boolean check1=lPage.TestdoLoginWIthValues();
			
			Assert.assertTrue(check1==true);
	 }
	 
	 @Test
	 @Order(3)
	 public void test3() throws SocketException
		{
		 	lPage=new LoginPage(driver);
			
			boolean check1=lPage.loginTestCase();
			
			Assert.assertTrue(check1==true);
			
		}

	 @Test
	 @Order(4)
	 public void test4() throws SocketException
		{
		 	lPage=new LoginPage(driver);
			
			boolean check1=lPage.TestGotoHomePage();
		
			Assert.assertTrue(check1==true);
			
		}
	 
	 @Test
	 @Order(5)
		 public void test5() throws InterruptedException, SocketException 
			{
		 		lPage=new LoginPage(driver);
			 	
				
				boolean check1=lPage.goToTrainings();
				
				Assert.assertTrue(check1);
				
			}
	 

//	 @Test(priority=6)
//	 public void test6() 
//		{
//		 	p_page=new ProgrammesPage(driver);
//		 	
//			
//			boolean check1=p_page.verifyProgramCode();
//			
//			Assert.assertTrue(check1);
//			
//		}
//	 
	 

	 @Test
	 @Order(6)
	// @Order(7)
	// @DisplayName("Verify that the continue button is not clickable at the first time to start a training")
	 public void test7() 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyContinueBtnDisable();
			
			Assert.assertTrue(check1);
			
		}

	 @Test@Order(7)
	// @Order(8)
	// @DisplayName("Verify that after starting a training the Next button is by default disable in the first section")
	 public void test8() 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyVideoNextBtnDIsable();
			Assert.assertTrue(check1);
			
		}

	 @Test
	 @Order(8)
	 public void test9() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyWatchView_ClickonNextBtn();
			
			Assert.assertTrue(check1);
			
		}

	 @Test@Order(9)
	 //@Order(10)
	// @DisplayName("Verify that the Next button on the document is Not Clickable without viewing the document")
	 public void test10() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyDocumentPageNextButton();
			
			Assert.assertTrue(check1);
			
		}
	 @Test@Order(10)
	 //@Order(10)
	// @DisplayName("Verify that the Next button on the document is Not Clickable without viewing the document")
	 public void test11() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyViewDocument_NextBtn();
			
			Assert.assertTrue(check1);
			
		}
	 @Test@Order(11)
	 //@Order(10)
	// @DisplayName("Verify that the Next button on the document is Not Clickable without viewing the document")
	 public void test12() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyLastSectionName();
			
			Assert.assertTrue(check1);
			
		}
	 
	 @Test@Order(12)
	 //@Order(10)
	// @DisplayName("Verify that the Next button on the document is Not Clickable without viewing the document")
	 public void test13() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyGoBackToSection();
			
			Assert.assertTrue(check1);
			
		}
	 @Test@Order(13)
	 //@Order(10)
	// @DisplayName("Verify that the Next button on the document is Not Clickable without viewing the document")
	 public void test14() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyLastSectionNextBtnDisable();
			
			Assert.assertTrue(check1);
			
		}
	 @Test@Order(14)
	 //@Order(10)
	// @DisplayName("Verify that the Next button on the document is Not Clickable without viewing the document")
	 public void test15() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyThirdSectionName();
			
			Assert.assertTrue(check1);
			
		}
	 
	 @Test@Order(15)
	 //@Order(10)
	// @DisplayName("Verify that the Next button on the document is Not Clickable without viewing the document")
	 public void test16() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyFailWith0Score();
			
			Assert.assertTrue(check1);
			
		}
	 
	 @Test@Order(16)
	 //@Order(10)
	// @DisplayName("Verify that the Next button on the document is Not Clickable without viewing the document")
	 public void test17() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyFailWith50Score();
			
			Assert.assertTrue(check1);
			
		}
	 
	 @Test@Order(17)
	 //@Order(10)
	// @DisplayName("Verify that the Next button on the document is Not Clickable without viewing the document")
	 public void test18() throws InterruptedException 
		{
		 	p_page=new ProgrammesPage(driver);
			boolean check1=p_page.verifyPasswith100Score();
			
			Assert.assertTrue(check1);
			
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

    
}
