package pageobject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MainPage extends AbstractPage {
    protected static final By CASEMENU_LOCATOR = By.id("menuItem-10000-0-main");
    protected static final By CREATECASE_LOCATOR = By.xpath("//*[@id=\"menuItem-10047-0-sub\"]");
    protected static final By CASE_SEARCH_LOCATOR = By.id("menuItem-10053-0-sub");
    private static final By FIELD_LOCATOR = By.id("select2-chosen-1");
    private static final By TYPEAHEAD_LOCATOR = By.id("s2id_autogen1_search");
    protected static final By INVISIBLE_LOCATOR = By.className("select2-searching");
    protected static final By CLASSDROPDOWN_LOCATOR = By.xpath("//*[@id=\"select2-results-1\"]");
    protected static final By SAVEBTN_LOCATOR = By.name("save");
    public MainPage (WebDriver driver){super(driver);}

    public MainPage openCreateCase(){
        waitElementClickable(CASEMENU_LOCATOR);
        driver.findElement(CASEMENU_LOCATOR).click();
        driver.findElement(CREATECASE_LOCATOR).click();
        return this;
    }

    public MainPage fillInCaseClassification(String classification){
        driver.findElement(FIELD_LOCATOR).click();
        driver.findElement(TYPEAHEAD_LOCATOR).sendKeys(classification);
        waitElementInvisible(INVISIBLE_LOCATOR);
        return this;
    }

    public MainPage chooseCaseClassification(String classification){
        WebElement classificationDropdown = driver.findElement(CLASSDROPDOWN_LOCATOR);
        List<WebElement> options = classificationDropdown.findElements(By.tagName("li"));
        for (WebElement option: options){
            if(option.getText().equals(classification)){
                new WebDriverWait(driver,10)
                    .until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                break;
            }
        }

        WebElement saveBtn = driver.findElement(SAVEBTN_LOCATOR);
        customWait(saveBtn);
        return this;
    }
}


