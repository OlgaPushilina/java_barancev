package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      app.goTo().gotoHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      ContactData contact = new ContactData(
              "Olga", "Test1", "Tester", "QA", "100 Main Street San Francisco, CA", "516-29-08", "test1");
      app.getContactHelper().createContact(contact);
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() + 1);

      before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
