$(function () {
  $('form').submit(function () {
    if (isValid()) {
      var submitButton = $('input[name=submit]');
      submitButton.attr('value', 'Loading...');
      $('#ajax-spinner').appendTo("body").css({
        position: "fixed",
        left: "40%",
        top: "50%"
      }).show();
      submitButton.attr("disabled", "true");
      return true;
    }
    else {
      return false;
    }
  });
});

//function isValid() {
//	if ($('input[name=term]').attr('value') == "") {
//		alert('Field keyword must be filled in.');
//		return false;
//	}
//	if (!(($('input[name=numberOfResults]').attr('value') > 0) && ($('input[name=numberOfResults]').attr('value') <= 1500))) {
//		alert('Field number of items must contains value between 1 and 1500.');
//		return false;
//	}
//	return true;
//}

$(function () {
  $('.results img').load(function () {
    $(this).fadeTo(2000, 1);
  });
  $('.results a').lightBox()
});

function setGeo(lat, lng) {
  $('#longitude').val(lng.toString());
  $('#latitude').val(lat.toString());
}

$(function () {
  var latDef;
  var lonDef;

  if ($('#latitude').val() == 0 && $('#longitude').val() == 0) {
    latDef = 50.104838;
    lonDef = 14.389772;
  } else {
    latDef = $('#latitude').val();
    lonDef = $('#longitude').val();
    console.log($('#latitude').val());
    console.log(latDef);
    console.log($('#longitude').val());
  }



  var map = L.map('map').setView([latDef, lonDef], 13);

  setGeo(latDef, lonDef);

  L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6IjZjNmRjNzk3ZmE2MTcwOTEwMGY0MzU3YjUzOWFmNWZhIn0.Y8bhBaUMqFiPrDRW9hieoQ',
      {
        maxZoom: 18,
        id: 'mapbox.streets'
      }).addTo(map);

  var marker = new L.marker([latDef, lonDef]);
  map.addLayer(marker);

  function onMapClick(e) {
    setGeo(e.latlng.lat, e.latlng.lng);
    marker.setLatLng(e.latlng).update();
    map.addLayer(marker);
  };

  map.on('click', onMapClick);
});


$(document).ready(function () {
  $('[data-toggle="tooltip"]').tooltip()
});
