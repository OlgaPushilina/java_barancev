package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      app.goTo().homePage();
      Contacts before = app.contact().all();
      ContactData contact = new ContactData()
              .withFirstname("Olga").withLastname("Test1").withTitle("Tester").withCompany("QA")
              .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08").withGroup("test1");
      app.contact().create(contact);
      Contacts after = app.contact().all();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(
              before.withAdded( contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
