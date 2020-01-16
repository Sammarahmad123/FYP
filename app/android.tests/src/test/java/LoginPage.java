


import java.net.SocketException;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;

public class LoginPage {
	EnhancedAndroidDriver<MobileElement> driver;
	
	
	
	
	//constructor to initialize LoginPage Class with IOSDriver with pageFactory design pattern
	
	   public LoginPage(EnhancedAndroidDriver<MobileElement> driver) {
	       this.driver = driver;
	      // PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	       PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	   }
	   
	    @FindBy(id= "uk.org.humanfocus.hfi:id/editText_org")
		public MobileElement organizationIdentifier;
	    
	    @FindBy(id= "uk.org.humanfocus.hfi:id/editText_user")
		public MobileElement personIdentifier;
	    
	    @FindBy(id= "uk.org.humanfocus.hfi:id/editText_password")
		public MobileElement password;
	    
	    @FindBy(id= "uk.org.humanfocus.hfi:id/button_submit")
		public MobileElement loginButton;
	    
	    @FindBy(xpath= "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]")
		public MobileElement logoutDoneButtonWheel;
	    
	    @FindBy(id =  "uk.org.humanfocus.hfi:id/btn_positive")
        public MobileElement acceptPopupBtn;
	    
	    
	    @FindBy(id =  "uk.org.humanfocus.hfi:id/btn_positive")
        public MobileElement OKPopupBtn;
	    
	    @FindBy(id =  "uk.org.humanfocus.hfi:id/btn_setting")
        public MobileElement settingsBtn;
	    
	    @FindBy(xpath =  "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]")
        public MobileElement Training_Guide_Btn;
	    
	    @FindBy(xpath =  "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.GridView/android.widget.LinearLayout[7]/android.widget.TextView")
        public MobileElement program_click;
	    
	    
	    @FindBy(id =  "uk.org.humanfocus.hfi:id/textView_select_code")
        public MobileElement program_code;

	    @FindBy(id =  "uk.org.humanfocus.hfi:id/button_UdertakeTraining")
        public MobileElement button_UdertakeTraining;
	    
	        
	    
	    public boolean validateLoginpage()throws SocketException{
	        boolean elements = false;
	        if(organizationIdentifier.isDisplayed()){
	            if(personIdentifier.isDisplayed()){
	                if(password.isDisplayed()){
	                    if(loginButton.isDisplayed()){
	                        elements = true;
	                    }
	                }
	            }
	        }
	        else{
	            elements = false;
	        }
	        return elements;


	    }
	    
	    
	    public boolean TestdoLoginWIthValues()throws SocketException
		{
			organizationIdentifier.sendKeys("testauto");
			personIdentifier.sendKeys("manager");
			password.sendKeys("123456");
			boolean element=false;
			
			loginButton.click();
			
			
			if(logoutDoneButtonWheel.isDisplayed())
			{
				element=true;
			}
			
			return element;
		}
	    
	    public boolean loginTestCase()throws SocketException
		{
			logoutDoneButtonWheel.click();
			boolean check=false;
			if(acceptPopupBtn.isDisplayed())
			{
				check=true;
				
			}
			else
			{
				check=false;
				
				
			}
			return check;
		}
	    public boolean TestGotoHomePage()
		{
			boolean element=false;
			acceptPopupBtn.click();
			OKPopupBtn.click();
			
			
			if(settingsBtn.isDisplayed())
			{
				element=true;
			}
			else
			{
				element=false;
			}
			
			return element;
			
			
		}
	    
	    public boolean goToTrainings() throws InterruptedException, SocketException
		{
			boolean element=true;
			
			Training_Guide_Btn.click();
			button_UdertakeTraining.click();
			
			//Thread.sleep(10000);
		
			//AndroidElement list=(AndroidElement)driver.findElementById("uk.org.humanfocus.hfi:id/gridview_toolkits");
			//System.out.println("after list");
			
			
			
			MobileElement listItem=(MobileElement)driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				      + ".scrollable(true)).scrollIntoView("
				      + "new UiSelector().text(\"IOS Test ToolKit OBRU213\"))"));
			
			listItem.click();

			


			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			
			//scrollObject.put("xpath", prog_page.program_code());
			scrollObject.put("direction", "down");
			js.executeScript("mobile: swipe", scrollObject);
			//js.executeScript("mobile: scroll", scrollObject);
			//js.executeScript("mobile: scroll", scrollObject);*/
			
			//program_click.click();
			
			
			
			if(program_code.isDisplayed())
			{
				element=true;
			}
			else
			{
				element=false;
			}
			return element;
			
			
		}
	   
	    

	

}
