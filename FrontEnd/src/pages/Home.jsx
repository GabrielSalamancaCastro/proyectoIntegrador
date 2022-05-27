import React, { useEffect, useState } from "react";
import Axios from "axios";
import { Header } from "../components/Header/Header";
import { SearchingBlock } from "../components/SearchingBlock/SearchingBlock";
import { RentCategories } from "../components/RentCategories/RentCategories";
import { ListBlock } from "../components/ListBlock/ListBlock";
import { Loader } from "../components/Loader/Loader";
import { Footer } from "../components/Footer/Footer";

export const Home = () => {
  const [categoryList, setCategoryList] = useState([]);
  const [citiesList, setCitiesList] = useState([]);
  const [carsList, setCarsList] = useState([]);

  const [categorySearchData, setCategorySearchData] = useState('');
  const [citySearchData, setCitySearchData] = useState('');

  const [isLoading, setLoading] = useState(true);

  const rentCategoriesToHome = (categorySearch) => {
    setCategorySearchData(categorySearch)
  }

  const searchingBlockToHome = (citySearch) => {
    setCitySearchData(citySearch)
  }
  
  useEffect(() => {
    Axios({
      url: "http://localhost:8080/category"
    })
    .then((response) => {
      setCategoryList(response.data);
    })
    .catch((error) => {
      console.log(error);
    })
  }, [setCategoryList])
  
  useEffect(() => {
    Axios({
      url: "http://localhost:8080/city"
    })
    .then((response) => {
      setCitiesList(response.data);
    })
    .catch((error) => {
      console.log(error);
    })
  }, [setCitiesList])
  
  useEffect(() => {
    Axios({
      url: "http://localhost:8080/product"
    })
    .then((response) => {
      setCarsList(response.data);
      setLoading(false);
    })
    .catch((error) => {
      console.log(error);
    })
  }, [setCarsList])

  useEffect(() => {
    Axios({
      url: `http://localhost:8080/product/category/${categorySearchData}`
    })
    .then((response) => {
      setCarsList(response.data);
    })
    .catch((error) => {
      console.log(error);
    })
  }, [setCarsList, categorySearchData])

  useEffect(() => {
    Axios({
      url: `http://localhost:8080/product/city/${citySearchData}`
    })
    .then((response) => {
      setCarsList(response.data);
    })
    .catch((error) => {
      console.log(error);
    })
  }, [setCarsList, citySearchData])

  if (isLoading) {
    return (
      <>
      <Header/>
      <main>
        <SearchingBlock 
          cities={citiesList}
          searchingBlockToHome = {searchingBlockToHome}
        />
        <RentCategories 
          typesOfRent={categoryList}
          rentCategoriesToHome = {rentCategoriesToHome}
        />
      <Loader/>
      </main>
      <Footer/>
    </>
    )
  }

  return (
    <>
      <Header/>
      <main>
        <SearchingBlock 
          cities={citiesList}
          searchingBlockToHome = {searchingBlockToHome}
        />
        <RentCategories 
          typesOfRent={categoryList}
          rentCategoriesToHome = {rentCategoriesToHome}
        />
        <ListBlock cars={carsList}/>
      </main>
      <Footer/>
    </>
  )
}