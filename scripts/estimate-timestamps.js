$(document).ready(
  function() {
    var getEstimate = function() {
      $.get("eventcoordinates", function(responseJSON)) {
          var estimateResultsDiv = document.getElementById("resultContainer");
          estimateReultsDiv.innerHTML = "Result in form of ";
          function onEachFeature(feature, layer) {
            var popupContent = "<p>The date " +
              feature.properties.timestamp + " is estimated to be located at " +
              feature.geometry.coordinates + ".</p>";
          
            if (feature.properties && feature.properties.popupContent) {
              popupContent += feature.properties.popupContent;
            }
            layer.bindPopup(popupContent);
          }
          L.geoJson(responseJSON, {
            style: function(feature) {
              return feature.properties && feature.properties.style;
            },
            onEachFeature: onEachFeature,
            pointToLayer: function (feature, latlng) {
              return L.circleMarker(latlng, {
                radius: 8,
                fillColor:"#ff7800",
                color:"#000",
                weight:1,
                opacity:1,
                fillOpacity:0.8
              });
            }
          }).addTo(map);
      }
    };
    document.getElementById("submitBtn-GetEstimate").addEventListener('click',
	getEstimate);
});
