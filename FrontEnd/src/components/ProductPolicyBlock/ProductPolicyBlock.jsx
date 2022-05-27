import React from "react";
import "./ProductPolicyBlock.css";

export const ProductPolicyBlock = () => {

    return (
        <section className="policy-container">
            <h2>¿Qué tienes que saber?</h2>
            <div className="flex-container__tablet-big">
               <div className="rules-container">
                    <h3>Normas Generales</h3>
                    <p>Recoger y entregar el vehículo en la fecha, hora y lugar indicados.</p>
                    <p>Dejar depósito de seguridad reembolsable de $200 dólares.</p>
                    <p>Tener documento de identificación y licencia de conducción</p>
                    <p>Ser mayor de 18 años.</p>
                    <p>El vehículo se entrega con medio tanque cargado y debe ser regresado con la misma cantidad.</p>
                    <p>El alquiler incluye kilómetros gratis ilimitados.</p>
                </div>
                <div className="flex-container__tablet-mini">
                    <div className="security-container">
                        <h3>Seguridad</h3>
                        <p>Debe usarse siempre el cinturón de seguridad.</p>
                        <p>No conducir el vehículo en estado de embriaguez. En caso de requerirlo, se puede solicitar servicio de conductor elegido. Aplican costos adicionales.</p>
                        <p>Tanquear con el vehículo apagado.</p>
                    </div>
                    <div className="cancellation-container">
                        <h3>Política de Cancelación</h3>
                        <p>El plazo máximo para hacer uso del servicio de cancelación sin recargo es de 48 horas previas a la entrega del vehículo.</p>
                    </div> 
                </div>
            </div>
        </section>
    )
}