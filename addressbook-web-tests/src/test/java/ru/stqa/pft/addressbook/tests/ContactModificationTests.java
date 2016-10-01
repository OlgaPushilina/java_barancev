package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstname("Olga").withLastname("Test1")
              .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08")
              .withMobilephone("+7 888").withWorkphone("(650)11790)").withEmail("olga@test.com")
              .withEmail2("olga1@test.com").withEmail3("olga2@test.com"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData oldContact = before.iterator().next();
    File photo = new File("src/test/resources/Cat1.jpg");
    ContactData newContact = new ContactData().withId(oldContact.getId())
            .withFirstname("Olga").withLastname("TestMod").withTitle("Tester").withCompany("QA")
            .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08")
            .withMobilephone("+7 888").withWorkphone("(650)11790)").withEmail("olga@test.com")
            .withEmail2("olga1@test.com").withEmail3("olga2@test.com").withPhoto(photo);
    app.contact().modify(newContact);
    Contacts after = app.db().contacts();
    assertThat(app.contact().count(),equalTo(before.size()));
    assertThat(after, equalTo(before.withModified(oldContact, newContact)));
    verifyContactListUI();
  }

}
