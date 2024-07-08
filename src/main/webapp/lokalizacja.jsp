<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>


<div class="container" style="text-align: center">
    <div id="map" style="text-align:center; margin-top:100px !important">

        <iframe width="100%" height="550" id="iframe-map"
                src="https://www.openstreetmap.org/export/embed.html?bbox=16.180586814880375%2C54.186184231960716%2C16.18766784667969%2C54.18889624465653&amp;layer=mapnik"
                style="border: 1px solid black; margin:auto; display:block;"></iframe>

    </div>
    <small >
        <a href="https://www.openstreetmap.org/#map=18/54.18754/16.18413">Wyświetl większą mapę</a>
    </small>
</div>
<script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        document.getElementById("iframe-map").onload = function () {
            // Creating map options
            var mapOptions = {
                center: [54.187325480508136, 16.18531226028367],
                zoom: 17
            }

            // Creating a map object
            var map = new L.map(`map`, mapOptions);

            // Creating a Layer object
            var layer = new L.TileLayer(`http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png`);

            // Adding layer to the map
            map.addLayer(layer);
        };
    });
</script>

<%@ include file="parts/footer.jsp" %>

