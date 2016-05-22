package com.github.personaerazed.util;

import java.util.regex.*;
import java.util.*;

class GSPFormat {
  
  static final GSPFormat ISO6709 = new GSPFormat(
      "\u00B1DD(MMSS)(.XXXX)\u00B1DDD(MMSS)(.XXXX)"
    , "[+-]\\d{2}(\\d{2}){0,2}(\\.\\d+)?"+"[+-]\\d{3}(\\d{2}){0,2}(\\.\\d+)?"
    , "(?<=\\d)(?=[+-])|/"
    , "[+-]?\\d{2}(\\.\\d+)?"
    , "([+-]\\d{3}(\\.\\d+)?)|(^\\d{3}(\\.\\d+)?)|(\\d{2}(\\.\\d+)?)"
  );
  static final GSPFormat ISO6709_RELAXED = new GSPFormat(
      "\u00B1DD(MMSS)(.XXXX), \u00B1DDD(MMSS)(.XXXX)"
    , "[+-]?\\d{2}(\\d{2}){0,2}(\\.\\d+)?"+"[\\s,;]+"+"[+-]?\\d{3}(\\d{2}){0,2}(\\.\\d+)?"
    , "[\\s,;/]+"
    , "[+-]?\\d{2}(\\.\\d+)?"
    , "([+-]\\d{3}(\\.\\d+)?)|(^\\d{3}(\\.\\d+)?)|(\\d{2}(\\.\\d+)?)"
  );
  private String regex;
  private String displayName;
  private String delimiter;
  private String latPattern;
  private String lonPattern;
  GSPFormat(String name, String pattern, String delimiter, String latPattern, String lonPattern ) {
    this.displayName = name;
    this.regex = pattern;
    this.delimiter = delimiter;
    this.latPattern = latPattern;
    this.lonPattern = lonPattern;
  }
  String getPattern() { return regex; }
  String getLatPattern() { return latPattern; }
  String getLonPattern() { return lonPattern; }
  String getName() { return displayName; }
  String getDelimiter() { return delimiter; }
}

public class GSPFormatter {
  
  private ArrayList<GSPFormat> validFormats;
  public GSPFormatter() {
    validFormats = new ArrayList<GSPFormat>();
    validFormats.add( GSPFormat.ISO6709 );
    validFormats.add( GSPFormat.ISO6709_RELAXED );
  }
  public GlobalSurfacePosition parse(String str) {
    int i=0;
    Matcher m;
    while ( i < validFormats.size() ) {
      m = Pattern.compile(validFormats.get(i).getPattern()).matcher(str);
      if(m.find()) { break; }
      i++;
    }
    if (i==validFormats.size()) {
      System.out.println("Error: Position String "+str+" is not in one of formates this program is designed to parse.");
      return null;
    } else {
      String[] coordinate = str.split( validFormats.get(i).getDelimiter() );
      double lat = convertToDecimalDegrees(
          coordinate[0]
        , validFormats.get(i).getLatPattern());
      double lon = convertToDecimalDegrees(
          coordinate[1]
        , validFormats.get(i).getLonPattern());
      return new GlobalSurfacePosition(lat,lon,'d');
    }
  }
  private double convertToDecimalDegrees(String str, String pattern) {
    int sign = (str.contains("-")) ? -1 : 1;
    Matcher m = Pattern.compile(pattern).matcher(str);
    double result=0;
    int i=1;
    if (m.find()) {
      result += Double.parseDouble(m.group());
    }
    while (m.find()) {
      result += sign * Double.parseDouble(m.group()) / Math.pow(60,i);
      i++;
    }
    return result;
  }
}
