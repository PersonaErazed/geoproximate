$(document).on("submit", "#queryForm", function(event) {
  var $form = $(this);
  $.post($form.attr("action"), $form.serialize(), function(responseJson) {

    L.geoJson(responseJson, {
      onEachFeature: function(feature, layer) {
        var popupContent = "<p>The date " +
          feature.properties.timestamp + " is estimated to be located at " +
          feature.geometry.coordinates + ".</p>";
          
        if (feature.properties && feature.properties.popupContent) {
          popupContent += feature.properties.popupContent;
        }
        layer.bindPopup(popupContent);
      }
    }).addTo(map);

    var items = [];
    $.each( responseJson.features, function( index, feature ) {
      items.push("<tr id='feature'>");
      items.push( "<td id='coordinates'>" 
        + feature.geometry.coordinates + "</td>" );
      items.push( "<td id='timestamp'>" 
        + feature.properties.timestamp + "</td>" );
      items.push("</tr>");
    });
   
    $( "<table/>", {
      "class": "table table-striped",
      html: items.join( "" )
    }).appendTo( "#results" );
  });
  event.preventDefault(); 
});
