import React, { useState, useRef, useEffect } from "react";
/* import { Link } from "react-router-dom"; */
import DatePicker from "react-datepicker";
import { registerLocale, setDefaultLocale } from  "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import es from 'date-fns/locale/es';
import "./SearchingBlock.css";
import { useWindowWidth } from "../../hooks/useWindowWidth/useWindowWidth";
setDefaultLocale('es');
registerLocale('es', es)

export const SearchingBlock = (props) => {

    const {width} = useWindowWidth();

    const [dateRange, setDateRange] = useState([null, null]);
    const [startDate, endDate] = dateRange;
    
    const [searchCityOpen, setSearchCityOpen] = useState(false);
    const [citySelection, setCitySelection] = useState("");
    const cityInputRef = useRef();

    const [citySearch, setCitySearch] = useState("");

    
    const handleToggle = () => {
        setSearchCityOpen(!searchCityOpen);
    }
    
    function submitHandler(event) {
        event.preventDefault();
        setCitySearch(cityInputRef.current.value.split(",", 1))
    }
    
    useEffect(() => {
        props.searchingBlockToHome(citySearch)
    }, [citySearch, props])

    return (

        <div className="main-box">
            <h1>Busca ofertas en todo tipo de carros</h1>
            <form>
                <input className="form-input__map" type="text" placeholder="¿A dónde vamos?" onClick={handleToggle} ref={cityInputRef} defaultValue={citySelection}/>
                { searchCityOpen && (<div className="search-city">
                    { props.cities.map(city => (
                        <option 
                            key={city.id} 
                            onClick={ () => {
                                setCitySelection(city.name + ", " + city.country)
                                cityInputRef.current.value = city.name + ", " + city.country;
                                handleToggle();
                            }} 
                            >{city.name + ", " + city.country}
                        </option>
                    )) }
                </div>) }
                {/* <input className="form-input__check" type="text" placeholder="Check in - Check out"/> */}
                {!(width < 480) && (<DatePicker 
                    className="form-input__check date-picker"
                    placeholderText="Check in - Check out"
                    dateFormat="dd/MM/yyyy"
                    selectsRange={true}
                    startDate={startDate}
                    endDate={endDate}
                    onChange={(update) => {
                        setDateRange(update);
                    }}
                    monthsShown={2}
                />)}
                {(width < 480) && (<DatePicker 
                    className="form-input__check date-picker"
                    placeholderText="Check in - Check out"
                    dateFormat="dd/MM/yyyy"
                    selectsRange={true}
                    startDate={startDate}
                    endDate={endDate}
                    onChange={(update) => {
                        setDateRange(update);
                    }}
                />) }
                <button className="search-button"  onClick={submitHandler}>Buscar</button>
            </form>
        </div>
    )
}