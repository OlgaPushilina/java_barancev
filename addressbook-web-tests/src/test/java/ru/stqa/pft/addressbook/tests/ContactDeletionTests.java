package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by t_aleksandr on 8/23/16.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstname("Olga").withLastname("Test1")
              .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08").withGroup("[none]")
              .withMobilephone("+7 888").withWorkphone("(650)11790)").withEmail("olga@test.com")
              .withEmail2("olga1@test.com").withEmail3("olga2@test.com"));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertThat(app.contact().count(),equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
