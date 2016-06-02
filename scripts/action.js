$(document).on("submit", "#queryForm", function(event) {
  var $form = $(this);
  $.post($form.attr("action"), $form.serialize(), function(responseJson) {
      $("#result").text(responseJson.type);
  });
  event.preventDefault(); 
});
