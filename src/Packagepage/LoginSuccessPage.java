package Packagepage;



	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	import basepkg.MainClass;

	public class LoginSuccessPage extends MainClass {
		
		@FindBy(xpath="//table[@width='492']/tbody/tr[1]/td/h3")
		WebElement loginSuccessMsg;
		
		public LoginSuccessPage(){
			PageFactory.initElements(driver, this);
		}
		
		public String captureLoginSuccessMsg() {
			String msg = loginSuccessMsg.getText().trim();
			return msg;
		}

	}


