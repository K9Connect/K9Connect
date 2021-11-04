$(document).ready(function() {
    mapboxgl.accessToken = MAPBOX_API_KEY;
    const map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        zoom: 3,
        center: [-95.7129, 37.0902]
    });

    let request = $.ajax({'url': '/users.json'});
    request.done(function(users) {
        users.forEach(user => {
            geocode(user.details.zipcode, MAPBOX_API_KEY)
                .then(function (coordinates) {
                    // console.log(coordinates);

                    marker = new mapboxgl.Marker()
                        .setLngLat(coordinates)
                        .addTo(map);

                    popup = new mapboxgl.Popup()
                        .setHTML(`
                        <a href="/profile/${user.id}">${user.username}</a>
                    `)
                        .addTo(map);

                    marker.setPopup(popup);
                    // marker.togglePopup();
                });
        });
    });
});

