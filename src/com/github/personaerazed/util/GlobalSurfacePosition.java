package com.github.personaerazed.util;

import static java.lang.Math.*;

public class GlobalSurfacePosition {
  private static double EARTH_MEAN_RADIUS_M=6356752; //units: meters
  private double lat; // radians
  private double lon; // radians

  public GlobalSurfacePosition(double lat, double lon, char units) {
    switch (units) {
      case 'd': 
        this.lat = toRadians(lat);
        this.lon = toRadians(lon);
        break;
      case 'r': 
        this.lat = lat;
        this.lon = lon;
        break;
    }
  }
  public double getLatitude() {
    return toDegrees(lat);
  }
  public double getLongitude() {
    return toDegrees(lon);
  }
  public String toString() {
    return "lat: "+toDegrees(lat)+"\u00B0; lon:  "+toDegrees(lon)+"\u00B0 ";
  }
}
