package SeleniumTests;

import Pages.ContactUsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContactUsPageTests extends BaseTest{

    @Test
    public void checkContactForm() throws InterruptedException {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        driver.get("https://www.leadconsult.eu/contact-us/");

        String name = "John Travolta";
        String email = "john.travolta@abv.bg";
        String phoneNumber = "0898123321";
        String message = "THIS IS A TEST MESSAGE!";
        contactUsPage.enterAllInputFields(name, email, phoneNumber, message);

        contactUsPage.clickOnCaptcha();
        //Not a good practice using Thread.sleep, but trying to bypass the captcha.
        //You have to manually pass the captcha if required.
        Thread.sleep(20000);
        contactUsPage.checkAgreeCheckbox();
        contactUsPage.clickSendButton();

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(
                                        By.xpath("//div[text()='Thank you for your message. It has been sent.']")));
        Assert.assertTrue(driver.findElement(
                By.xpath("//div[text()='Thank you for your message. It has been sent.']")).isDisplayed(),
                "Success message is not displayed!");
    }
}
