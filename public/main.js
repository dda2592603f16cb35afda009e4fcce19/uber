(function(){

	//Map element for manipulating the map
	const map = L.map('map').setView([37.75,-122.40], 15);
	
	//Layer for handling the food trucks in the current view
	const mapLayer = L.layerGroup().addTo(map);

	//Layer for handling the current selected search element
	const searchElement = L.layerGroup().addTo(map);

	//Error function to inform the user that something went wrong
	//using toastify 
	const error = (function(){

		const _private = {
			toast : null,
		}

		const _public = {
			//setError takes a message and shows an error with that message
			setError: function (message){

				_public.remove();

				_private.toast = Toastify({
					text: message,
					newWindow: true,
					gravity: "bottom", 
					positionLeft: false, 
					backgroundColor: "red",
					stopOnFocus: true 
				}).showToast();

			},

			//remove removes the message
			remove : function(){
				if(_private.toast !== null){
					_private.toast.hideToast();
					_private.toast = null;
				}
			}
		}
		return _public;
	})()


	//Setup map tiles 
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		maxZoom: 18,
		id: 'mapbox.streets',
		accessToken: 'pk.eyJ1IjoiYmFra2VnYWFyZCIsImEiOiJjangzbzhwZmUwMTJpNDlvMXdvdnNzY2p6In0.B-zTmU8vb6ELJYiO1FvngA'
	}).addTo(map);

	//Ask user for location
	map.locate({setView : true})


	//Fetch data from the server that fits the screen
	function getData(){

		error.remove();

		const positionJSON = map.getBounds();

		requestJSON = {
			northBorder : positionJSON._northEast.lat,
			eastBorder  : positionJSON._northEast.lng,
			southBorder : positionJSON._southWest.lat,
			westBorder  : positionJSON._southWest.lng
		}

		fetch('/api/foodTruckView',{

			method: "POST",

			body:JSON.stringify(requestJSON),

			headers: {

				'Accept': 'application/json',
				'Content-Type': 'application/json'

			}
		})
			.then(function(response) { return response.json(); })

			.catch(function(e) {
				console.log(e.message);
			})

			.then(function(data) {

				mapLayer.clearLayers();

				if(data.status === 0){

					const listOfFoodTrucks = data.listOfFoodTrucks;

					listOfFoodTrucks.forEach(function(element){

						createMarker(element, mapLayer);

					})
				}
				
				else if(data.status === 1) {
					error.setError("No food trucks here, please choose another location");

				}

				else if(data.status === 2){
					error.setError("Too many food trucks please zoom in");

				}
				else if(data.status === 100){
					error.setError("Something went wrong on the server, please try again");

				}

			});
	}

	function createMarker(element, layer){
		const longitude = element.longitude
		const latitude = element.latitude;

		const marker = L.marker([latitude, longitude]).addTo(layer);

		const foodItems = typeof element.foodItems === "undefined" ? "" : element.foodItems;

		marker.bindPopup("<b>"+element.applicant+"</b><br>"+ foodItems)

		return marker;
	}

	//init call
	getData();

	//Update map data when the map is no longer moved
	map.on('moveend',function(){
		getData();
	});

	//Setup jQuery UI autocompletion
	$( "#search input" ).autocomplete({

		source: function( request, response ) {

			$.ajax({

				url: "/api/search",

				data: {
					searchString: request.term
				},

				success: function( data ) {

					//jQuery autocompletion searches for value on the object, 
					//so we need to add it for each object
					let list = data.listOfFoodTrucks;

					list.forEach(function(element){
						element.value = element.applicant;
					})

					response( list );
				}
			});
		},

		minLength: 1,

		select: function( event, ui ) {

			const element = ui.item;
			const marker = createMarker(element, searchElement);	

			map.setView(marker.getLatLng());

			marker.openPopup();
		}
	});
})();
