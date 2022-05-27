import React from "react";
import Lottie from "react-lottie";

export const Loader = () => {
  
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: require("./../../assets/data/carLoader.json"),
    rendererSettings: {
      preserveAspectRatio: "xMidYMid slice",
    },
  };
  const style = {
    margin: `20vh auto`,
  };

  return (
    <>
      <Lottie className={"loader"} options={defaultOptions} height={300} width={300} style={style}/>
    </>
  )
}