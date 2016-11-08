package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import org.testng.annotations.Test;
import net.webservicex.GeoIPService;

import static org.testng.Assert.assertEquals;

/**
 * Created by Olga on 11/8/16.
 */
public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("73.158.253.83");
    assertEquals(geoIP.getCountryCode(), "USA");
  }

  @Test
  public void testInvalidIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("73.158.253.xx");
    assertEquals(geoIP.getCountryCode(), "USA");
  }
}
