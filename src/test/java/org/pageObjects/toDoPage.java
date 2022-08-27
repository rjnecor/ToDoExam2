package org.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class toDoPage {
    private final WebDriver driver;
    int activeCount;

    @FindBy(className = "new-todo")
     WebElement newToDo;

    By toggleAllId = By.xpath("//section/label[@for = 'toggle-all']");

    By toggleId = By.xpath("//section/ul/li/div/input[@class = 'toggle']");
    By liId = By.xpath("//section/ul/li");
    By todoId = By.xpath("//section/ul/li[@class = 'todo']");
    By todoCompleteId = By.xpath("//section/ul/li[@class = 'todo completed']");
    By editId = By.xpath("//section/ul/li/input[@class = 'edit']");
    By deleteId = By.xpath("//section/ul/li/div/button[@class = 'destroy']");
    By countId = By.xpath("//footer/span/strong");
    By labelId = By.xpath("//section/ul/li/div/label");

    public toDoPage(WebDriver driver) {

        this.driver = driver;
       initializeElements();
    }

    private void initializeElements(){
        PageFactory.initElements(driver, this);
    }

    public void addEntry(String strEntry) {
        newToDo.sendKeys(strEntry);
        newToDo.sendKeys(Keys.ENTER);
    }

    public void storeActiveCount(){
        try {
            activeCount = Integer.parseInt(
                    driver.findElement(countId).getText()
            );
        }
        catch (Exception e){
            activeCount = 0;
        }
    }

    private WebElement loopElements(String searchString, List<WebElement> searchList){
        for (WebElement webElement : searchList) {
            if (webElement.getText().equals(searchString)) {
                return webElement;
            }
        }
        throw new NotFoundException("Element with "+searchString+" not found");
    }

    private WebElement loopElementGetAttached(String searchString,List<WebElement> searchList
            ,List<WebElement> attachList){
        for (int i = 0; i < searchList.size(); i++) {
            if(searchList.get(i).getText().equals(searchString)) {
                return attachList.get(i);
            }
        }
        throw new NotFoundException("Element with "+searchString+" not found");
    }

    private void doubleClick(WebElement elementToAccess){
        Actions act = new Actions(driver);
        act.doubleClick(elementToAccess).perform();
    }


    private void hoverElement(WebElement elementToAccess){
        Actions act = new Actions(driver);
        act.moveToElement(elementToAccess);
        act.perform();
    }
    public void markAsDone(String searchString) {
        List<WebElement> todolist =  driver.findElements(todoId);
        List<WebElement> toggleList = driver.findElements(toggleId);
        loopElementGetAttached(searchString,todolist,toggleList).click();
    }
    public void markAsNotDone(String searchString) {
        List<WebElement> todolist = driver.findElements(liId);
        List<WebElement> toggleList =  driver.findElements(toggleId);
        loopElementGetAttached(searchString,todolist,toggleList).click();
    }

    public void editEntry(String searchString, String replaceString) {
        List<WebElement>  todolist = driver.findElements(todoId);
        List<WebElement>  labelList = driver.findElements(labelId);
        doubleClick(loopElementGetAttached(searchString,todolist,labelList));
        driver.findElement(editId).sendKeys(Keys.chord(Keys.CONTROL,"a"),replaceString,Keys.ENTER);
    }

    public void deleteEntry(String searchString) {
        List<WebElement>  todolist = driver.findElements(todoId);
        List<WebElement>  labellist = driver.findElements(labelId);
        List<WebElement>  dellist = driver.findElements(deleteId);
        hoverElement(loopElements(searchString,labellist));
        loopElementGetAttached(searchString,todolist,dellist).click();
    }

    public void clickToggleAll() {
        driver.findElement(toggleAllId).click();
    }

    public void clickLink(String searchString) {
        driver.findElement(By.xpath("//a[text() = '"+searchString+"']")).click();
    }


    public void clickButton(String buttonName) {
        driver.findElement(By.xpath("//button[contains(text(), '"+buttonName+"')]")).click();
    }
    public boolean verifyIfEntryExist(String strEntryTemp) {
        List<WebElement> todolist = driver.findElements(todoId);

        for (WebElement todoEntry:todolist) {
            if(todoEntry.getText().equals(strEntryTemp)) {
                return true;
            }
        }
        return false;
    }
    public boolean verifyIfEntryDone(String strEntryTemp) {
        List<WebElement> todoCompleteList = driver.findElements(todoCompleteId);

        for (WebElement todoEntry:todoCompleteList) {
            if(todoEntry.getText().equals(strEntryTemp)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyAllStatus(String statusText) {
        By targetByTemp,otherByTemp;
        switch (statusText){
            case "complete":
                targetByTemp = todoCompleteId;
                otherByTemp = todoId;
                break;
            case "active":
                targetByTemp = todoId;
                otherByTemp = todoCompleteId;
                break;
            default:
                throw new UnsupportedOperationException("status not yet supported by code");
        }

        List<WebElement> todoTargetList = driver.findElements(targetByTemp);
        List<WebElement> todoOtherList = driver.findElements(otherByTemp);
        List<WebElement> liList = driver.findElements(liId);

        if(todoOtherList.size()>0)
            return false;
        return todoTargetList.size() == liList.size();
    }


    public boolean verifyToDoCount(String textSwitch) {
        int currentCount = Integer.parseInt(driver.findElement(countId).getText());
        switch (textSwitch){
            case "incremented":
                return currentCount>activeCount;
            case "decremented":
                return currentCount<activeCount;
            default:
                throw new UnsupportedOperationException();
        }

    }
}
