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

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.json.JSONException;
import org.json.JSONObject;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

import com.vividsolutions.jts.geom.Point;

/**
 * Entity class representing the geocoded results.
 *
 * @author James G. Willmore
 */
@Entity
public class GeocoderResults implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -8178109154084137618L;

  /** The id. */
  @Id
  private Long id;

  /** The rating. */
  private Integer rating;

  /** The longitude. */
  private Double longitude;

  /** The latitude. */
  private Double latitude;

  /** The address. */
  private String address;

  /** The geom. */
  private Point geom;

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id
   *          the new id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the rating.
   *
   * @return the rating
   */
  public Integer getRating() {
    return rating;
  }

  /**
   * Sets the rating.
   *
   * @param rating
   *          the new rating
   */
  public void setRating(Integer rating) {
    this.rating = rating;
  }

  /**
   * Gets the longitude.
   *
   * @return the longitude
   */
  public Double getLongitude() {
    return longitude;
  }

  /**
   * Sets the longitude.
   *
   * @param longitude
   *          the new longitude
   */
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  /**
   * Gets the latitude.
   *
   * @return the latitude
   */
  public Double getLatitude() {
    return latitude;
  }

  /**
   * Sets the latitude.
   *
   * @param latitude
   *          the new latitude
   */
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   * Gets the address.
   *
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the address.
   *
   * @param address
   *          the new address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets the JSON representation of the located address.
   *
   * @return the address json
   * @throws JSONException
   *           the JSON exception
   */
  public JSONObject getAddressJson() throws JSONException {
    return new JSONObject(address);
  }

  /**
   * Gets the geom.
   *
   * @return the geom
   */
  public Point getGeom() {
    return geom;
  }

  /**
   * Sets the geom.
   *
   * @param geom
   *          the new geom
   */
  public void setGeom(Point geom) {
    this.geom = geom;
  }

  /**
   * Gets a GeoJSON representation of the located address. Returns null if the
   * located address cannot be represented as GeoJSON.
   *
   * @return the GeoJSON
   */
  public String getGeoJson() {
    try {
      SimpleFeatureBuilder fBuild = new SimpleFeatureBuilder(
          createFeatureType());
      fBuild.add(geom);
      fBuild.set("rating", rating);
      fBuild.set("latitude", latitude);
      fBuild.set("longitude", longitude);

      @SuppressWarnings("unchecked")
      Iterator<String> it = getAddressJson().keys();

      while (it.hasNext()) {
        String key = it.next().toString();
        fBuild.set(key, getAddressJson().get(key));
      }

      SimpleFeature feature = fBuild.buildFeature(String.valueOf(id));
      FeatureJSON fjson = new FeatureJSON();
      StringWriter writer = new StringWriter();
      fjson.writeFeature(feature, writer);
      return writer.toString();
    } catch (IOException | FactoryException | JSONException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Creates a SimpleFeatureType of the located address.
   *
   * @return the simple feature type
   * @throws NoSuchAuthorityCodeException
   *           the no such authority code exception
   * @throws FactoryException
   *           the factory exception
   */
  private SimpleFeatureType createFeatureType()
      throws NoSuchAuthorityCodeException, FactoryException {
    SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
    builder.setName("Location");

    builder.add("geom", Point.class);
    builder.add("rating", Integer.class);
    builder.add("latitude", Double.class);
    builder.add("longitude", Double.class);

    try {
      @SuppressWarnings("unchecked")
      Iterator<String> it = getAddressJson().keys();

      while (it.hasNext()) {
        builder.add(it.next().toString(), String.class);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return builder.buildFeatureType();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "GeocoderResults [id=" + id + ", rating=" + rating
        + ", longitude=" + longitude + ", latitude=" + latitude
        + ", geom=" + geom + ", address=" + address + ", geoJson="
        + getGeoJson() + "]";
  }
}
