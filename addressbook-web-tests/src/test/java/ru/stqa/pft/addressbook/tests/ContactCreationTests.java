package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().initContactCreation();
      app.getContactHelper().fillContactCreation(new ContactData("Olga", "Test", "Tester", "QA", "100 Main Street San Francisco, CA", "516-29-08"));
      app.getContactHelper().submitContactCreation();
      app.getContactHelper().returnToHomePage();
    }
}
