package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Olga").withLastname("Test1").withTitle("Tester").withCompany("QA")
              .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData oldContact = before.iterator().next();
    ContactData newContact = new ContactData().withId(oldContact.getId())
            .withFirstname("Olga").withLastname("Test1").withTitle("Tester").withCompany("QA")
            .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08");
    app.contact().modify(newContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withModified(oldContact, newContact)));
  }

}
