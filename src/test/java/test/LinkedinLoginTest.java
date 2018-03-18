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

	@DataProvider
	public Object[][] successfulTest() {
		return new Object[][]{
				{"a.iastreb1234@gmail.com","itea2018"},
				{"A.Iastreb1234@gmail.com","itea2018"}};
	}

	@Test (dataProvider = "successfulTest")
	public void successfulLoginTest(String email, String password) {
		String initialPageTitle = landingPage.getPageTitle();
		String initialPageUrl = landingPage.getPageUrl();
		Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
				"Login page title is wrong");

		LinkedinHomePage homePage = landingPage.loginAs(email, password);
		Assert.assertTrue(homePage.isSignedIn(), "User is not signed in");

		Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle,
				"Page title did not change after login");
		Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl,
				"Page url did not change after login");
	}

	@DataProvider
	public Object[][] negativeTestReturnToLogin() {
		return new Object[][]{
				{"xys", "xys", "Please enter a valid email address.", "The password you provided must have at least 6 characters."},
				//{"a.iastreb1234@gmail.com", "pass1234","","Hmm, that's not the right password. Please try again or request a new one."},
				{"a.iastreb1234@gmail.com", "ITEA2018","","Hmm, that's not the right password. Please try again or request a new one."},
				{"a.iastreb4@gmail.com","123456","Hmm, we don't recognize that email. Please try again.",""}};
	}

	@Test(dataProvider = "negativeTestReturnToLogin")
		public void negativeLoginTest(String email, String password, String emailMessage, String pwdMessage) {
		String initialPageTitle = landingPage.getPageTitle();
		//String initialPageUrl = landingPage.getPageUrl();
		Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
				"Login page title is wrong");

		LinkedinLoginPage loginPage = landingPage.loginAs(email, password);
		Assert.assertTrue(loginPage.isLoginPage(),"Login Page is broken.");

		String actualEmailMessage = loginPage.getEmailMessage();
		String actualPwdMessage = loginPage.getPwdMessage();

		/*System.out.println (actualEmailMessage);
		System.out.println (emailMessage);
		System.out.println (actualPwdMessage);
		System.out.println (pwdMessage);*/

		Assert.assertEquals(emailMessage, actualEmailMessage, "Actual and Expected Email messages do not match.");
		Assert.assertEquals(pwdMessage, actualPwdMessage, "Actual and Expected Password messages do not match.");
	}

	@DataProvider
	public Object[][] negativeTestReturnToLanding() {
		return new Object[][]{
				{"", "pass1234"},
				{"a.i4567@gmail.com", ""},
				{" "," "}};
	}

	@Test(dataProvider = "negativeTestReturnToLanding")
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
