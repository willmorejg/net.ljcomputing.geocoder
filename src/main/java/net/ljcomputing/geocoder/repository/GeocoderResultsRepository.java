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
/**
           Copyright 2017, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.geocoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.ljcomputing.geocoder.entity.GeocoderResults;

/**
 * Repository used to obtain geolocation results.
 * 
 * @author James G. Willmore
 *
 */
@Repository
public interface GeocoderResultsRepository extends JpaRepository<GeocoderResults, Long> {
  
  /**
   * Geocode address.
   *
   * @param address the address
   * @return the list
   */
  @Modifying(clearAutomatically = true)
  @Query(value = 
        "SELECT "
      + "ROW_NUMBER() OVER() as id, "
      + "ST_X((g).geomout) as longitude, "
      + "ST_Y((g).geomout) as latitude, "
      + "(g).rating as rating, "
      + "(g).geomout geom, "
      + "to_json(addy) AS address "
      + "FROM "
      + "tiger.geocode(tiger.pagc_normalize_address(?1), 50) AS g"
      , nativeQuery = true)
  public List<GeocoderResults> geocodeAddress(String address);
}
