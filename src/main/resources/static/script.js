let items;

fetch("http://data.snx.ovh/page2.json")
  .then(function (response) {
    return response.json();
  })
  .then(function (items) {});
