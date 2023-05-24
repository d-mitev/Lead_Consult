package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {

    public WebDriver driver;

    @FindBy(xpath = "//input[@name='your-name']")
    WebElement name;
    @FindBy(xpath = "//input[@name='your-email']")
    WebElement email;
    @FindBy(xpath = "//input[@name='tel-760']")
    WebElement phoneNumber;
    @FindBy(xpath = "//textarea[@name='your-message']")
    WebElement message;
    @FindBy(xpath = "//div[@id='wpcf7-f5661-p6126-o1']//input[@name='accept-terms[]']")
    WebElement agreeCheckbox;
    @FindBy(xpath = "//input[@type='submit' and @value='Send']")
    WebElement sendButton;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterAllInputFields(String name, String email, String phoneNumber, String message) {
        this.name.sendKeys(name);
        this.email.sendKeys(email);
        this.phoneNumber.sendKeys(phoneNumber);
        this.message.sendKeys(message);
        System.out.printf( "Entered the following:" +
                            "\n Name: %s" +
                            "\n Email: %s" +
                            "\n Phone Number: %s" +
                            "\n Message: %s",
                            name, email, phoneNumber, message);
    }

    public void clickOnCaptcha() {
        new WebDriverWait(driver,
                Duration.ofSeconds(10)).
                until(ExpectedConditions.
                        frameToBeAvailableAndSwitchToIt
                                (By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.
                elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();
        System.out.println("Clicked on Captcha");
        driver.switchTo().defaultContent();
    }

    public void checkAgreeCheckbox() {
        agreeCheckbox.click();
        System.out.println("Clicked on the Agree checkbox");
    }

    public void clickSendButton() {
        sendButton.click();
        System.out.println("Clicked on Send button");
    }

}
