import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BadCodeExample {
    public static void main (String args[]) throws InterruptedException {
        //System.out.println("Hello world");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com/");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Selenium");
        element.submit();

        Thread.sleep(5000);
        driver.close();
    }
}
