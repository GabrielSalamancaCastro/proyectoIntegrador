import React, { useState, useLayoutEffect } from "react";
import Axios from "axios";
import { Header } from "../components/Header/Header";
import { ProductDetails } from "../components/ProductDetails/ProductDetails";
import { ReservationBlock } from "../components/ReservationBlock/ReservationBlock";
import { ProductPolicyBlock } from "../components/ProductPolicyBlock/ProductPolicyBlock";
import { MapBlock } from "../components/MapView/MapBlock";
import { Loader } from "../components/Loader/Loader";
import { Footer } from "../components/Footer/Footer";

export const ProductPage = (props) => {

  const [productDetails, setProductDetails] = useState([]);
  const [isLoading, setLoading] = useState(true);

  const idProduct = props.match.params.id;
  console.log(props.match)

  useLayoutEffect(() => {
    Axios({
      url: `http://localhost:8080/product/id/${idProduct}`
    })
    .then((response) => {
      setProductDetails(response.data);
      setLoading(false);
    })
    .catch((error) => {
      console.log(error);
    })
  }, [setProductDetails, idProduct])

  if (isLoading) {
    return (
      <div className="loader-container">
        <Header />
        <Loader />
      </div>
    )
  }

  return (
    <>
      <Header/>
        <main>
          < ProductDetails car={productDetails}/>
          < ReservationBlock />
          < MapBlock car={productDetails}/>
          < ProductPolicyBlock />  {/* ESTE ES EL PLOQUE CON INFORMACION DEBAJO DEL MAPA DE GOOGLE MAPS */}
        </main>
      <Footer/>
    </>
  )
}