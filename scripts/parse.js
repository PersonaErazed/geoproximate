function print(str) {
  $("#parsed").append(str);
}
function printJSON(json) {
  print(JSON.stringify(json));
}
function mapPoint(point) {
  // leaflet
  L.geoJson(point, {
    pointToLayer: function (feature, latlng) {
      return L.circleMarker(latlng, {
        radius: 6,
        fillColor: "red",
        color: "#000",
        weight: 3,
        opacity: 1,
        fillOpacity: 0.8
      });
    }
  }).addTo(map);
}

function truncate(number) {
  return number > 0 
    ? Math.floor(number)
    : Math.ceil(number);
}
// geojson coordinate [longitude,latitude]
// common order is latitude then longitude
function coordinateFormat(point) {
  var strArray = [];
  var deg, min, sec;
  var compassDir;
  $.each( point.coordinates, function(index, coordinate) {
    switch(index) {
      case 0: compassDir = coordinate > 0 ? "W" : "E"; break;
      case 1: compassDir = coordinate > 0 ? "N" : "S"; break;
    }
    coordinate = Math.abs(coordinate);
    deg = truncate(coordinate);
    min = truncate(
      (coordinate - deg) * 60
    );
    sec = Math.abs(
      (((coordinate - deg) * 60) - min) * 60
    ).toFixed(2);
    strArray.push( 
        deg.toString() + "\u00B0"
      + min.toString() + "\'"
      + sec.toString() + "\""
      + compassDir
    );
  });
  return strArray[1] + " and " + strArray[0];
}

function parse(str) {
  $.get("gps-parse", str, function(geojson){
//    printJSON(geojson);
    print(coordinateFormat(geojson.geometry));
    print("\n");
    mapPoint(geojson.geometry)
  }, "json");
}

$(document).ready( function() {
  $("#textArea1").keypress( function() {
    var input = $(this).val().split("\n");
    $.each(input, function(i, line) {
      parse(line);
    });
  });
});
