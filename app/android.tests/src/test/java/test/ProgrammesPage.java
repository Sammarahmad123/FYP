package test;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProgrammesPage {
	//constructor to initialize ProgrammesPage Class with IOSDriver with pageFactory design pattern
	AppiumDriver<MobileElement> driver;
	 public ProgrammesPage(AppiumDriver<MobileElement> driver) {
	       this.driver = driver;
	      // PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	       PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	   }
		
		
		//This method verifies program code on list of program page and program code after clicking a particular program
//		public boolean verifyProgramCode()
//		{
//			boolean verify=false;
//			String code_text=program_code.getText();
//			String title_text=programme_title.getText();
//			
//			System.out.println("mjha click kar do");
//			program_code.click();
//			
//			if(code_text.equals(program_code_match.getText()))
//			{
//				System.out.println("dfhdfhhd");
//				if(title_text.equals(programme_title_match.getText()))
//				{
//					System.out.println("Both Matches");
//					verify=true;
//				}
//
//			}
//			else
//			{
//				System.out.println("Both not Matches");
//				verify=false;
//			}
//			return verify;
//		}
		
		
		//this method verifies that continue button is disabled for the first time 
		public boolean verifyContinueBtnDisable()
		{
			System.out.println("verify methed starts");
			
			MobileElement el1 = (MobileElement) driver.findElementById("uk.org.humanfocus.hfi:id/textView_select_outcome");
			el1.click();

			//driver.findElementById("uk.org.humanfocus.hfi:id/textView_select_code").click();
			//program_code.click();
			
			
			System.out.println("Clicked");
			boolean verify=continue_btn_disable.isEnabled();
			
			if(verify==false)
			{
				verify=true;
			}
			else
			{
				verify=false;
			}
			return verify;
			
		}
		
		//@SuppressWarnings("deprecation")
		public boolean verifyVideoNextBtnDIsable()
		{
			
			
			
			program_start_btn.click();
			boolean isElementPresent;
			String bottom_sheet_value=bottom_sheet.getText();
			System.out.println(bottom_sheet_value);
			isElementPresent = next_btn_disable.isEnabled();
			
			if(isElementPresent==false && bottom_sheet_value.contains("Section (1 of 3)"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		public boolean verifyWatchView_ClickonNextBtn() throws InterruptedException 
		{
			boolean isElementPresent;
			
			
			video_click_btn.click();
			//WebDriverWait wait = new WebDriverWait(driver,20);
			//wait.until(ExpectedConditions.elementToBeClickable(video_next_btn));
			
				
				Thread.sleep(50000);
				isElementPresent = next_btn_enable.isEnabled();
				if(isElementPresent==true)
				{
					return true;
				}
				else
				{
					System.out.println("Button cannot be clicked");
					return false;
				}
				
				
			
		}
		
		public boolean verifyDocumentPageNextButton() throws InterruptedException
		{
			next_btn_enable.click();
			String bottom_sheet_value=bottom_sheet.getText();
			System.out.println(bottom_sheet_value);
			
			
			boolean isElementPresent;
			
			isElementPresent=next_btn_disable.isEnabled();
			
			if(isElementPresent==false && bottom_sheet_value.contains("Section (2 of 3)"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		
		public boolean verifyViewDocument_NextBtn() throws InterruptedException
		{
			boolean isElementPresent;
			
			Thread.sleep(3000);
			document_view.click();
			
			isElementPresent=next_btn_enable.isEnabled();
			
			if(isElementPresent==true)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		
		public boolean verifyLastSectionName()
		{
			String text=section_name.getText();
			System.out.println(text);
			driver.navigate().back();
			exit_yes_btn.click();
			program_code.click();
			boolean isElementPresent=false;
			
			if(text.contains(last_section_name.getText()))
			{
				if(continue_btn_enable.isEnabled())
				{
					isElementPresent=true;
				}
			}
			else
			{
				isElementPresent=false;
			}
			return isElementPresent;
			
			
		}
		
//		public boolean verifyContinueBtnEnable()
//		{
//			boolean isElementFound=false;
//			isElementFound=continue_btn.isEnabled();
//			if(isElementFound==true)
//			{
//				return true;
//			}
//			else
//			{
//				return false;
//			}
//		}
		
		public boolean verifyGoBackToSection() throws InterruptedException
		{
			String text=last_section_name.getText();
			continue_btn_enable.click();
			boolean isElementFound;
			Thread.sleep(2000);
			if(text.contains(section_name.getText()))
			{
				isElementFound=true;
			}
			else
			{
				isElementFound=false;
			}
			return isElementFound;
		}
		
		public boolean verifyLastSectionNextBtnDisable() throws InterruptedException
		{
			document_view.click();
			next_btn_enable.click();
			

			
			boolean isElementFound;
			Thread.sleep(2000);
			isElementFound=next_btn_disable.isEnabled();
			if(isElementFound==false)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		
		
		public boolean verifyThirdSectionName()
		{
			String text=section_name.getText();
			driver.navigate().back();
			exit_yes_btn.click();
			program_code.click();
			
			String bottom_sheet_value=bottom_sheet.getText();
			System.out.println(bottom_sheet_value);
			
			
			boolean isElementPresent=false;
			
			if(text.contains(last_section_name.getText()))
			{
				if(continue_btn_enable.isEnabled() && bottom_sheet_value.contains("Section (3 of 3)"))
				{
					isElementPresent=true;
				}
			}
			else
			{
				isElementPresent=false;
			}
			return isElementPresent;
			
		}
		
		
		
		public boolean verifyFailWith0Score()
		{
			
			continue_btn_enable.click();
			date_and_time_btn.click();
			okBtnDatenTime.click();
			okBtnDatenTime.click();
			first_question_option_2.click();
			input_text.sendKeys("Hello World");
			//second_question_option_2.click();
			
			
			MobileElement listItem=(MobileElement)driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				      + ".scrollable(true)).scrollIntoView("
				      + "new UiSelector().text(\"next day\"))"));
			
			
			third_question_option_4.click();
			
			
			next_btn_enable.click();
			int totalQ=Integer.parseInt(total_questions.getText());
			
			int correctQ=Integer.parseInt(correct_questions.getText());
			
			int score=correctQ/totalQ ;
			score=score*100;
			String text=training_state.getText();
			
			
			
			
			if(score==0 && text.contains("Fail"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
		
		public boolean verifyFailWith50Score() throws InterruptedException
		{
			retake_training_btn.click();
			program_code.click();
			program_start_btn.click();
			video_click_btn.click();
			Thread.sleep(40000);
			next_btn_enable.click();
			Thread.sleep(2000);
			
			document_view.click();
			next_btn_enable.click();
			date_and_time_btn.click();
			okBtnDatenTime.click();
			okBtnDatenTime.click();
			first_question_option_1.click();
			input_text.sendKeys("Hello World");
			
			MobileElement listItem=(MobileElement)driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				      + ".scrollable(true)).scrollIntoView("
				      + "new UiSelector().text(\"next day\"))"));
			third_question_option_4.click();
			
			
			next_btn_enable.click();
			double totalQ=Double.parseDouble(total_questions.getText());
			//System.out.println("total questions:"+totalQ);
			double correctQ=Double.parseDouble(correct_questions.getText());
			//System.out.println("correct questions:"+correctQ);
			double score=correctQ/totalQ ;
			
			score=score*100;
			//System.out.println("score:"+score);
			String text=training_state.getText();
			
			if(score==50 && text.contains("Fail"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
		
		/*public boolean verifyFailWith66Score() throws InterruptedException
		{
			retake_training_btn.click();
			program_code.click();
			program_start_btn.click();
			video_click_btn.click();
			Thread.sleep(40000);
			next_btn_enable.click();
			Thread.sleep(2000);
			
			document_view.click();
			next_btn_enable.click();
			first_question_option_1.click();
			second_question_option_1.click();
			
			third_question_option_1.click();
			next_btn_enable.click();
			double totalQ=Double.parseDouble(total_questions.getText());
			System.out.println("total questions:"+totalQ);
			double correctQ=Double.parseDouble(correct_questions.getText());
			System.out.println("coorect questions:"+correctQ);
			double score=correctQ/totalQ ;
			
			
			score=score*100;
			System.out.println("score:"+score);
			String text=training_state.getText();
			
			if(score==66.66666666666666 && text.contains("Fail"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}*/
		
//		public boolean verifyFailWith75Score() throws InterruptedException
//		{
//			retake_training_btn.click();
//			video_click_btn.click();
//			Thread.sleep(30000);
//			video_next_btn.click();
//			Thread.sleep(2000);
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("direction", "down");
//			js.executeScript("mobile: scroll", scrollObject);
//			document_next_btn.click();
//			
//			first_question_option_1.click();
//			second_question_option_1.click();
//			js.executeScript("mobile: scroll", scrollObject);
//			third_question_option_3.click();
//			third_question_option_4.click();
//			fourth_question_option_1.click();
//			scrollObject.put("direction", "down");
//			js.executeScript("mobile: scroll", scrollObject);
//			fourth_question_option_4.click();
//			document_next_btn.click();
//			double totalQ=Double.parseDouble(total_questions.getText());
//			
//			double correctQ=Double.parseDouble(correct_questions_3.getText());
//			
//			double score=correctQ/totalQ ;
//			
//			score=score*100;
//			String text=fail.getText();
//			System.out.println(text);
//			if(score==75 && text.contains("Fail"))
//			{
//				return true;
//			}
//			else
//			{
//				return false;
//			}
//			
//			
//		}
		
		public boolean verifyPasswith100Score() throws InterruptedException
		{
			
			retake_training_btn.click();
			program_code.click();
			program_start_btn.click();
			video_click_btn.click();
			Thread.sleep(40000);
			next_btn_enable.click();
			Thread.sleep(2000);
			
			document_view.click();
			next_btn_enable.click();
			date_and_time_btn.click();
			okBtnDatenTime.click();
			okBtnDatenTime.click();
			first_question_option_1.click();
			input_text.sendKeys("Hello World");
			
			MobileElement listItem=(MobileElement)driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				      + ".scrollable(true)).scrollIntoView("
				      + "new UiSelector().text(\"next day\"))"));
			
			//third_question_option_1.click();
			
		//	third_question_option_1.click();
			
			MobileElement el1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.CheckBox[1]");
			el1.click();
			
			
			MobileElement el2 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.CheckBox[2]");
			el2.click();
			
			
			//third_question_option_2.click();
			
			next_btn_enable.click();
			double totalQ=Double.parseDouble(total_questions.getText());
			System.out.println(totalQ);
			double correctQ=Double.parseDouble(correct_questions.getText());
			System.out.println(correctQ);
			double score=correctQ/totalQ ;
			System.out.println(score);
			score=score*100;
			String text=training_state.getText();
			System.out.println(text);
			if(score==100 && text.contains("Pass"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
		
		
		
		
		
			
			
		
			
			
			
		
		
		
		
		 /**
		    * All elements of this Page are identified below with their respective element locators 
		    * element locator like-> by.name , by,xpath, by.id etc
		    */
           
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/textView_select_code") 
		    public MobileElement program_code;
            
			@FindBy(id = "uk.org.humanfocus.hfi:id/btn_continue")
		    public MobileElement continue_btn_disable;
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/btn_continue_enable")
		    public MobileElement continue_btn_enable;
			
		
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/textView_select_title") 
		    public MobileElement programme_title;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v7.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView[2]")
		    public MobileElement program_code_match;
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/tv_programme_title")
		    public MobileElement programme_title_match;
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/btn_start")
		    public MobileElement program_start_btn;
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/start_video")
		    public MobileElement video_click_btn;
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/btn_next")
		    public MobileElement next_btn_disable;
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/btn_next_enable")
		    public MobileElement next_btn_enable;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[6]")
		    public MobileElement document_view;
			
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/tv_item_title")
		    public MobileElement section_name;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v7.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.TextView[2]")
		    public MobileElement last_section_name;
			
			
			
			

			
			

			
			
			
			
			
			
			
			@FindBy(id = "android:id/button1")
		    public MobileElement exit_yes_btn;
			
			@FindBy(name = "Next")
		    public MobileElement question_next_btn;
			
			
			
			
			//question1
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[1]")
		    public MobileElement first_question_option_1;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[2]")
		    public MobileElement first_question_option_2;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[3]")
		    public MobileElement first_question_option_3;
			
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/edt_comment")
		    public MobileElement input_text;
			

			
			
			//questions2 
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[1]")
		    public MobileElement second_question_option_1;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[2]")
		    public MobileElement second_question_option_2;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[3]")
		    public MobileElement second_question_option_3;
			
			
			//question3
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.CheckBox[1]")
		    public MobileElement third_question_option_1;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.CheckBox[2]")
		    public MobileElement third_question_option_2;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.CheckBox[3]")
		    public MobileElement third_question_option_3;
			
			@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.CheckBox[4]")
		    public MobileElement third_question_option_4;
			
			
			
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/tv_total_questions")
		    public MobileElement total_questions;
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/tv_correct_questions")
		    public MobileElement correct_questions;
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/tv_incorrect_questions")
		    public MobileElement incorrect_questions;
			
			
			
			
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/tv_state")
		    public MobileElement training_state;
			
			
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/btn_print_certificate")
		    public MobileElement retake_training_btn;
			
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/tv_sections")
		    public MobileElement bottom_sheet;
			
			
			@FindBy(id = "uk.org.humanfocus.hfi:id/tv_dateTime")
		    public MobileElement date_and_time_btn;
			
			@FindBy(id = "android:id/button1")
		    public MobileElement okBtnDatenTime;
			

			
			

			
			

			
			
			
			
			
			
			
			
			
			

}
