import { React, useState } from "react";
import DatePicker from "react-datepicker";
import { registerLocale, setDefaultLocale } from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import es from "date-fns/locale/es";
import "./ReservationBlock.css";
import { useWindowWidth } from "../../hooks/useWindowWidth/useWindowWidth";

setDefaultLocale("es");
registerLocale("es", es);

export const ReservationBlock = () => {
  const { width } = useWindowWidth();
  const [dateRange, setDateRange] = useState([null, null]);
  const [startDate, endDate] = dateRange;

  return (
    <section className="reservation-container">
      <h2>Fechas disponibles</h2>
      <div className="calendar-reservation-container">
        {!(width < 480) && (
          <DatePicker
            className="form-input__check date-picker"
            placeholderText="Check in - Check out"
            dateFormat="dd/MM/yyyy"
            selectsRange={true}
            startDate={startDate}
            endDate={endDate}
            onChange={(update) => {
              setDateRange(update);
            }}
            inline
            monthsShown={2}
          />
        )}
        {width < 480 && (
          <DatePicker
            className="form-input__check date-picker"
            placeholderText="Check in - Check out"
            dateFormat="dd/MM/yyyy"
            selectsRange={true}
            startDate={startDate}
            endDate={endDate}
            onChange={(update) => {
              setDateRange(update);
            }}
            inline
          />
        )}
        <div className="reservation">
          <p className="reservation-text">
            Agrega tus fechas de alquiler para obtener precios exactos
          </p>
          <button className="reservation-button">Iniciar reserva</button>
        </div>
      </div>
    </section>
  );
};
