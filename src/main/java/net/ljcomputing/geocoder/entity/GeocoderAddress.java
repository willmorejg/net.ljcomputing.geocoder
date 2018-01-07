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
package net.ljcomputing.geocoder.entity;

import java.io.Serializable;

/**
 * The address to geolocate.
 *
 * @author James G. Willmore
 */
public class GeocoderAddress implements Serializable {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The street number. */
  private String streetNumber;

  /** The street name. */
  private String streetName;

  /** The city. */
  private String city;

  /** The state. */
  private State state;

  /** The zip code. */
  private String zipCode;

  /**
   * Gets the street number.
   *
   * @return the street number
   */
  public String getStreetNumber() {
    return streetNumber;
  }

  /**
   * Sets the street number.
   *
   * @param streetNumber the new street number
   */
  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  /**
   * Gets the street name.
   *
   * @return the street name
   */
  public String getStreetName() {
    return streetName;
  }

  /**
   * Sets the street name.
   *
   * @param streetName the new street name
   */
  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  /**
   * Gets the city.
   *
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city.
   *
   * @param city the new city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Gets the state.
   *
   * @return the state
   */
  public State getState() {
    return state;
  }

  /**
   * Sets the state.
   *
   * @param state the new state
   */
  public void setState(String state) {
    this.state = State.valueOfAbbreviation(state);
  }

  /**
   * Sets the state.
   *
   * @param state the new state
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * Gets the zip code.
   *
   * @return the zip code
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Sets the zip code.
   *
   * @param zipCode the new zip code
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Gets the address as a comma separate String.
   *
   * @return the address
   */
  public String getAddress() {
    final StringBuilder builder = new StringBuilder();

    builder.append(streetNumber);
    builder.append("," + streetName);
    builder.append("," + city);
    builder.append("," + state.getAbbreviation());
    builder.append("," + zipCode);

    return builder.toString();
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "GeocoderAddress [streetNumber=" + streetNumber
        + ", streetName=" + streetName + ", city=" + city + ", state="
        + state + ", zipCode=" + zipCode + "]";
  }
}
