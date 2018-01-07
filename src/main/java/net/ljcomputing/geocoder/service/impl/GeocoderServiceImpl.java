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

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ljcomputing.geocoder.entity.GeocoderAddress;
import net.ljcomputing.geocoder.entity.GeocoderResults;
import net.ljcomputing.geocoder.repository.GeocoderResultsRepository;
import net.ljcomputing.geocoder.service.GeocoderService;

/**
 * Implementation of a geocoder service.
 *
 * @author James G. Willmore
 */
@Service
@Transactional
public class GeocoderServiceImpl implements GeocoderService {

  /** The geocoder results repository. */
  @Autowired
  private GeocoderResultsRepository geocoderResultsRepository;

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.ljcomputing.geocoder.service.GeocoderService#getResults(net.ljcomputing
   * .geocoder.entity.GeocoderAddress)
   */
  @Override
  public String getResults(GeocoderAddress address) {
    final List<GeocoderResults> results = geocoderResultsRepository
        .geocodeAddress(address.getAddress());

    final StringBuilder builder = new StringBuilder();

    for (GeocoderResults result : results) {
      builder.append(result.getGeoJson());
    }

    return builder.toString();
  }

}
