import React from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import L from 'leaflet';
import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';
import "leaflet/dist/leaflet.css";
import "./MapBlock.css";

let DefaultIcon = L.icon({
    iconUrl: icon,
    shadowUrl: iconShadow
});

L.Marker.prototype.options.icon = DefaultIcon;

export const MapBlock = (props) => {

  const position = [Number(props.car.city.latitude), Number(props.car.city.longitude)]
  return (

    <section className="map-block-container">
      <h2>¿Dónde vas a estar?</h2>
      <p>{`${props.car.city.name}, ${props.car.city.country}`}</p>
      <MapContainer center={position} zoom={12} scrollWheelZoom={false}>
        <TileLayer
          attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Marker position={position}>
          <Popup>
            {props.car.name}
          </Popup>
        </Marker>
      </MapContainer>
    </section>
  );
};
