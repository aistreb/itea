package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLandingPage;
import page.LinkedinLoginPage;

public class LinkedinLoginTest extends LinkedinBaseTest{
	/*String initialPageTitle;
	String initialPageUrl;

	@BeforeMethod
	public void beforeTest(){
		initialPageTitle = landingPage.getPageTitle();
		initialPageUrl = landingPage.getPageUrl();}*/

	@Test
	public void successfulLoginTest() {
		String initialPageTitle = landingPage.getPageTitle();
		String initialPageUrl = landingPage.getPageUrl();
		Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
				"Login page title is wrong");

		LinkedinHomePage homePage = landingPage.loginAs("a.iastreb1234@gmail.com", "itea2018");
		Assert.assertTrue(homePage.isSignedIn(), "User is not signed in");

		Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle,
				"Page title did not change after login");
		Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl,
				"Page url did not change after login");
	}


	@DataProvider
	public Object[][] negativeTestCredentialsReturnToLogin() {
		return new Object[][]{
				{"xys", "xys", "Укажите действительный адрес эл. почты.", "Пароль должен содержать не менее 6 символов."},
				{"a.iastreb1234@gmail.com", "pass1234"}};
	}

	@Test(dataProvider = "negativeTestCredentialsReturnToLogin")
		public void negativeLoginTest(String email, String password, String emailMessage, String pwdMessage) {
		String initialPageTitle = landingPage.getPageTitle();
		//String initialPageUrl = landingPage.getPageUrl();
		Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
				"Login page title is wrong");

		LinkedinLoginPage loginPage = landingPage.loginAs(email, password);
		Assert.assertTrue(loginPage.isLoginPage(),"Login Page is broken.");

		String actualEmailMessage = loginPage.getEmailMessage();
		String actualPwdMessage = loginPage.getPwdMessage();

		Assert.assertEquals(emailMessage, actualEmailMessage, "Actual and Expected Email messages do not match.");
		Assert.assertEquals(pwdMessage, actualPwdMessage, "Actual and Expected Password messages do not match.");

	}

	@DataProvider
	public Object[][] negativeTestCredentialsReturnToLanding() {
		return new Object[][]{
				{"", "pass1234"},
				{"a.iastreb4567@gmail.com", ""}};
	}

	@Test(dataProvider = "negativeTestCredentialsReturnToLanding")
	public void negativeLandingTest(String email, String password) {
		String initialPageTitle = landingPage.getPageTitle();
		String initialPageUrl = landingPage.getPageUrl();
		Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
				"Login page title is wrong");

		landingPage = landingPage.loginAs(email, password);
		Assert.assertEquals(landingPage.getPageUrl(), initialPageUrl,
				"Page title did not change after login");
	}


}
