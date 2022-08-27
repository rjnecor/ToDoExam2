package org.ToDoExam2;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.framework.core.webDriverChrome;
import org.openqa.selenium.WebDriver;
import org.pageObjects.toDoPage;

import static org.junit.Assert.*;


public class StepDefinitions {

    WebDriver driver;
    toDoPage tdp;
    String strEntryTemp;

    @Before
    public void setup(){

        driver =  new webDriverChrome().initialize();
        System.out.println("Setup ran.");
    }

    @Given("the ToDo page is loaded")
    public void the_to_do_page_is_loaded() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://todomvc.com/examples/vue/#/all");
        tdp = new toDoPage(driver);
    }
    @And("prefilled with data")
    public void prefilledWithData() {
        tdp.addEntry("To Edit");
        tdp.addEntry("To Delete");
        tdp.addEntry("To mark as done");
        tdp.addEntry("Marked as done");
        tdp.markAsDone("Marked as done");
        tdp.storeActiveCount();
    }
    @When("an entry with value {string} is added")
    public void an_entry_with_value_is_added(String strentry) {
        // Write code here that turns the phrase above into concrete actions
        tdp.addEntry(strentry);
        strEntryTemp = strentry;
    }
    @When("an entry with value {string} is edited to {string}")
    public void anEntryWithValueIsEditedTo(String searchString, String replaceString) {

        tdp.editEntry(searchString,replaceString);
        strEntryTemp = replaceString;
    }
    @When("an entry with value {string} is deleted")
    public void anEntryWithValueIsDeleted(String searchString) {
        tdp.deleteEntry(searchString);
        strEntryTemp = searchString;
    }
    @When("an entry with value {string} is marked as done")
    public void anEntryWithValueIsMarkedAsDone(String searchString) {
        tdp.storeActiveCount();
        tdp.markAsDone(searchString);
        strEntryTemp = searchString;
    }
    @When("an entry with value {string} is marked as not done")
    public void anEntryWithValueIsMarkedAsNotDone(String searchString) {
        tdp.storeActiveCount();
        tdp.markAsNotDone(searchString);
        strEntryTemp = searchString;
    }
    @When("all entries are marked as done")
    public void allEntriesAreMarkedAsDone() {
        tdp.clickToggleAll();
    }
    @When("all entries are marked as not done")
    public void allEntriesAreMarkedAsNotDone() {
        tdp.clickToggleAll();
    }
    @When("the Active filter is clicked")
    public void theActiveFilterIsClicked() {
        tdp.clickLink("Active");
    }
    @When("the Completed filter is clicked")
    public void theCompletedFilterIsClicked() {
        tdp.clickLink("Completed");
    }
    @When("the Clear completed is clicked")
    public void theClearCompletedIsClicked() {
        tdp.clickButton("Clear completed");
    }
    @Then("the added entry should be found in the list")
    public void the_added_entry_should_be_found_in_the_list() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(tdp.verifyIfEntryExist(strEntryTemp));
    }
    @Then("the edited entry should be found in the list")
    public void theEditedEntryShouldBeFoundInTheList() {
        assertTrue(tdp.verifyIfEntryExist(strEntryTemp));
    }
    @Then("the deleted entry should not be found in the list")
    public void theDeletedEntryShouldNotBeFoundInTheList() {
        assertFalse(tdp.verifyIfEntryExist(strEntryTemp));
    }
    @Then("the entry should be marked as completed")
    public void theEntryShouldBeMarkedAsCompleted() {
        assertTrue(tdp.verifyIfEntryDone(strEntryTemp));
    }
    @Then("the entry should be marked as not completed")
    public void theEntryShouldBeMarkedAsNotCompleted() {
        assertFalse(tdp.verifyIfEntryDone(strEntryTemp));
    }
    @Then("all entries should be marked as completed")
    public void allEntriesShouldBeMarkedAsCompleted() {
        assertTrue(tdp.verifyAllStatus("complete"));
    }
    @Then("all entries should be marked as not completed")
    public void allEntriesShouldBeMarkedAsNotCompleted() {
        assertTrue(tdp.verifyAllStatus("active"));
    }
    @Then("all completed entries should be displayed")
    public void allCompletedEntriesShouldBeDisplayed() {
        assertTrue(tdp.verifyAllStatus("complete"));
    }
    @Then("all active entries should be displayed")
    public void allActiveEntriesShouldBeDisplayed() {
        assertTrue(tdp.verifyAllStatus("active"));
    }
    @And("the item count is {string}")
    public void theItemCountIs(String textSwitch) {
        assertTrue(tdp.verifyToDoCount(textSwitch));
    }

    @After
    public void afterTest(){
        if(null != driver){
            driver.close();
            //driver.quit();
        }
    }



}
