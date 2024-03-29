package pageobject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static pageobject.page.MainPage.*;

public class CaseEvent extends AbstractPage {
    private static final By CASEEVENTFIELD_LOCATOR = By.id("select2-chosen-1");
    private static final By CASEEVENTTYPEAHEAD_LOCATOR = By.id("s2id_autogen1_search");
    private static final By CASEEVENTCLASS_LOCATOR = By.id("docketEntryName");
    protected static final By CASEEVENT_LOCATOR = By.id("menuItem-10046-1-main");
    protected static final By CASEEVENTFOUND_LOCATOR = By.id("select2-result-label-771");
    public CaseEvent(WebDriver driver){super(driver);}

    public CaseEvent chooseCaseEventClass(String caseEvent){
       // waitElementVisible(CASEEVENT_LOCATOR);
        driver.findElement(CASEEVENT_LOCATOR).click();
        waitElementClickable(CASEEVENTFIELD_LOCATOR);
        driver.findElement(CASEEVENTFIELD_LOCATOR).click();
        waitElementClickable(CASEEVENTTYPEAHEAD_LOCATOR);
        driver.findElement(CASEEVENTTYPEAHEAD_LOCATOR).sendKeys(caseEvent);
        waitElementInvisible(INVISIBLE_LOCATOR);

        WebElement classificationDropdown = driver.findElement(CLASSDROPDOWN_LOCATOR);
        List<WebElement> options = classificationDropdown.findElements(By.tagName("li"));
        for (WebElement option: options){
            if(option.getText().contains(caseEvent)){
                new WebDriverWait(driver,20)
                        .until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                break;
            }
        }

        WebElement saveBtn = driver.findElement(SAVEBTN_LOCATOR);
        customWait(saveBtn);
        return this;
    }
     public CaseEvent saveCaseEvent(){
        waitElementVisible(CASEEVENTCLASS_LOCATOR);
        driver.findElement(SAVEBTN_LOCATOR).click();
        return this;
     }
}
