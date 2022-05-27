import React, { useEffect, useState } from "react";
import "./RentCategories.css";

export const RentCategories = (props) => {

  const [categorySearch, setCategorySearch] = useState("");

  useEffect(() => {
    props.rentCategoriesToHome(categorySearch)
  }, [categorySearch, props])

  return (
    <div className="rentCategories"> 
      <h2 className="main-heading_rentCategories">BUSCAR POR TIPO DE ALQUILER </h2>
      <div className="main-container_rentCategories">
        {props.typesOfRent.map((typeOfRent) => (
          <div key={typeOfRent.id} className="card" onClick={() => {
            setCategorySearch(typeOfRent.name)
          }}>
              <img  src={typeOfRent.imgUrl}  alt={typeOfRent.name} className="card-img__categories"/>
              <div className="text-container">
                <h3 className="category-name">{typeOfRent.name}</h3>
                <p className="category-text">{typeOfRent.description}</p>
              </div>
          </div>
        ))}
      </div>
    </div>
  );
}