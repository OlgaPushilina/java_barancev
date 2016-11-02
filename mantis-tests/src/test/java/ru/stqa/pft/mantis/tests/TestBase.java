package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php" );
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m) {
    logger.info ("Start test " + m.getName());
  }

  @BeforeMethod (alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info ("Stop test " + m.getName());
  }

}
