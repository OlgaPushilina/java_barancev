package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      app.goTo().homePage();
      Set<ContactData> before = app.contact().all();
      ContactData contact = new ContactData()
              .withFirstname("Olga").withLastname("Test1").withTitle("Tester").withCompany("QA")
              .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08").withGroup("test1");
      app.contact().create(contact);
      Set<ContactData> after = app.contact().all();
      Assert.assertEquals(after.size(), before.size() + 1);

      contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
      before.add(contact);
      Assert.assertEquals(before, after);
    }
}
