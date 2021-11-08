$(document).ready(function() {
    function markUserLocations(breed) {
        let request = $.ajax({
            "url": "/users.json",
            "data": {
                "breed": breed
            }
        });

        request.done(function(users) {
            markers.forEach(marker => marker.remove());

            users.forEach(user => {
                let dog = {
                    id: user.dogs[0].id,
                    name: user.dogs[0].name,
                    breed: user.dogs[0].breed,
                    gender: user.dogs[0].gender == 'F' ? 'Female' : 'Male',
                    age: user.dogs[0].details.age,
                    photo: user.dogs[0].photos.length ? user.dogs[0].photos[0].url : '/images/dog_silhouette.png'
                };

                if (breed != null) {
                    for (let i = 0; i < user.dogs.length; i++) {
                        let searchTerm = breed.toLowerCase();
                        let dogBreed = user.dogs[i].breed.toLowerCase();

                        if (dogBreed.includes(searchTerm)) {
                            let thisDog = user.dogs[i];

                            dog = {
                                id: thisDog.id,
                                name: thisDog.name,
                                breed: thisDog.breed,
                                gender: thisDog.gender == 'F' ? 'Female' : 'Male',
                                age: thisDog.details.age,
                                photo: thisDog.photos.length ? thisDog.photos[0].url : '/images/dog_silhouette.png'
                            };

                            break;
                        }
                    }
                }

                let zip = user.details.zipcode.toString();
                if (zip.length < 5) {
                    zip = '0' + zip;
                }

                geocode(zip, mapboxKey)
                    .then(function (coordinates) {
                        // console.log(zip, coordinates);

                        let paw = document.createElement('div');
                        paw.style.width = '23px';
                        paw.style.height = '24px';
                        paw.style.backgroundImage = 'url(/images/dog_paw_print.png)';
                        paw.style.backgroundSize = '100%';
                        paw.className = 'marker';

                        // let link = document.createElement('a');
                        // link.href = `/profile/${user.id}`;

                        // link.append(paw);

                        marker = new mapboxgl.Marker(paw)
                            .setLngLat(coordinates)
                            .addTo(map);

                        popup = new mapboxgl.Popup()
                            .setHTML(`
                                <img src="${dog.photo}" class="img-dog-map img-thumbnail" alt="dog profile picture">
                                <p>
                                    <span class="font-weight-bold">${dog.name}</span>
                                    <br>
                                    ${dog.breed}
                                    <br>
                                    ${dog.gender}, ${dog.age} years old 
                                    <br>
                                    <a href="/dog/${dog.id}">Dog Profile</a>
                                    <br>
                                    <a href="/profile/${user.id}">Owner Profile</a>
                                </p>
                            `).addTo(map);

                        marker.setPopup(popup);
                        marker.togglePopup();

                        markers.push(marker);
                    });
            });
        });
    }

    const markers = [];

    mapboxgl.accessToken = mapboxKey;
    const map = new mapboxgl.Map({
        container: 'map',
        // style: 'mapbox://styles/mapbox/streets-v11',
        // style: 'mapbox://styles/michaeldcoyle/ckvl9wd2x3uu514uivwg7txz2',
        // style: 'mapbox://styles/michaeldcoyle/ckvlcexic0apm15qol8wts88y',
        // style: 'mapbox://styles/michaeldcoyle/ckvlb73dx3o0914mwejkurn5b',
        style: 'mapbox://styles/michaeldcoyle/ckvlcqix23pii14mwm6oq27q8',
        zoom: 3.5,
        center: [-95.7129, 37.0902]
    });

    markUserLocations(null);

    // Add zoom and rotation controls to the map.
    map.addControl(new mapboxgl.NavigationControl(), 'bottom-right');

    // Add geolocate control to the map.
    const geolocate = new mapboxgl.GeolocateControl({
        showUserHeading: true
    });
    map.addControl(geolocate);

    // Set center and zoom in
    geolocate.on('geolocate', function(event) {
        map.flyTo({
            center: [event.coords.longitude, event.coords.latitude],
            zoom: 8
        });
    });

    $('#breed-submit').click(function() {
        markUserLocations($('#breed').val());
    });
});
