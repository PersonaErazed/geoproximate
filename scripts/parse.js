function print(str) {
  $("#parsed").text(str);
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

function parse(str) {
  $.get("gps-parse", str, function(geojson){
    printJSON(geojson);
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
