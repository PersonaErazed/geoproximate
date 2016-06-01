$(document).ready(
  function() {
      $.get("eventcoordinates", function(responseJSON)) {
          var div = document.getElementById("result");
          div.innerHTML = "Result in form of ";
      }
    };
});
