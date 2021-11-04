$(document).ready(function() {
    mapboxgl.accessToken = MAPBOX_API_KEY;
    const map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        zoom: 3.5,
        center: [-95.7129, 37.0902]
    });

    let request = $.ajax({'url': '/users.json'});
    request.done(function(users) {
        users.forEach(user => {
            let zip = user.details.zipcode.toString();
            if (zip.length < 5) {
                zip = '0' + zip;
            }

            geocode(zip, MAPBOX_API_KEY)
                .then(function (coordinates) {
                    // console.log(zip, coordinates);

                    let paw = document.createElement('div');
                    paw.style.width = '23px';
                    paw.style.height = '24px';
                    paw.style.backgroundImage = 'url(https://upload.wikimedia.org/wikipedia/commons/thumb/c/c0/Dog_Paw_Print.png/228px-Dog_Paw_Print.png)';
                    paw.style.backgroundSize = '100%';
                    paw.className = 'marker';

                    let link = document.createElement('a');
                    link.href = `/profile/${user.id}`;

                    link.append(paw);

                    marker = new mapboxgl.Marker(link)
                        .setLngLat(coordinates)
                        .addTo(map);

                    // popup = new mapboxgl.Popup()
                    //     .setHTML(`
                    //     <a href="/profile/${user.id}">${user.username}</a>
                    // `)
                    //     .addTo(map);

                    marker.setPopup(popup);
                    // marker.togglePopup();
                });
        });
    });
});
