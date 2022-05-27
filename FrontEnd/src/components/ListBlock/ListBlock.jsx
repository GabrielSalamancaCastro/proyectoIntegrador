import React from "react";
import { Link } from "react-router-dom";
//import carsData from "../../assets/data/cars.json"
import "./ListBlock.css"

export const ListBlock = (props) => {

  return (
    <section className="card-car-container">
      <h2 className="card-car-container__title">RECOMENDACIONES</h2>
      <div className="card-car-list">
        { props.cars.map(car => (
        <Link to={"product/"+ car.id} className={"card-car-link"}><div key={car.id} className="card-car">
          <div className="card-image-container">
            <img className="image-list" src={car.images[0].url} alt={car.images[0].title}/>
          </div>
          <div className="info-container">
            <p>{car.category.name}</p>
            <h4>{car.name}</h4>
            <p>{`${car.city.name}, ${car.city.country}`}</p>
          </div>
        </div></Link>
        )) }
      </div>
    </section>
  )
}