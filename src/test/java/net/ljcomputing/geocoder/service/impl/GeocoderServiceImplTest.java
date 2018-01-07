/**
 * Copyright © 2018 James G Willmore (willmorejg@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ljcomputing.geocoder.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.ljcomputing.geocoder.entity.GeocoderAddress;
import net.ljcomputing.geocoder.entity.State;
import net.ljcomputing.geocoder.service.GeocoderService;

/**
 * Geolocate service test
 * 
 * @author James G. Willmore
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeocoderServiceImplTest {
  
  /** The Constant LOGGER. */
  private final static Logger LOGGER = LoggerFactory.getLogger(GeocoderServiceImplTest.class);
  
  /** The geocoder service. */
  @Autowired
  private GeocoderService geocoderService;

  /**
   * Test method for {@link net.ljcomputing.geocoder.service.impl.GeocoderServiceImpl#getResults(net.ljcomputing.geocoder.entity.GeocoderAddress)}.
   */
  @Test
  public void testGetResults() {
    final GeocoderAddress address = new GeocoderAddress();
    address.setStreetNumber("400");
    address.setStreetName("North Street");
    address.setCity("Harrisburg");
    address.setState(State.PENNSYLVANIA);
    address.setZipCode("17120");
    
    LOGGER.debug("{}", geocoderService.getResults(address));
  }

}
