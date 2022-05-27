import { React, useState } from "react";
import { Link } from "react-router-dom";
import logoWeb from "../../assets/img/logo.svg";
import iconMenu from "../../assets/img/iconMenu-Mobile.svg";
import iconFacebook from "../../assets/img/iconFacebook.svg"
import iconLinkedin from "../../assets/img/iconLinkedin.svg"
import iconTwitter from "../../assets/img/iconTwitter.svg"
import iconInstagram from "../../assets/img/iconInstagram.svg"
import iconX from "../../assets/img/iconX.svg"
import "../../general.css";
import "./Header.css";

export const Header = (props) => {

    /* window.addEventListener('scroll', function() {
        let header = document.querySelector('header');
        header.classList.toggle('header-down', window.scrollY > 0);
    }) */

    const [navBarOpen, setNavBarOpen] = useState(false);

    const handleToggle = () => {
        setNavBarOpen(!navBarOpen);
    }

    const closeMenu = () => {
        setNavBarOpen(false);
    }

    const closeSession = () => {
        localStorage.setItem("userAuth", false);
        closeMenu()
    }

    const closeSessionUser = () => {
        localStorage.setItem("userAuth", false);
    }

    const userData = JSON.parse(localStorage.getItem("userData"));

    return (
        <header>
            <div className="header-links">
                <div className="header-links__logo">
                    <Link to="/"><img alt="Logo de Digital Booking" src={logoWeb}/></Link>
                </div>
                <div className="header-links__slogan">
                    <Link to="/"><p>Los mejores carros a tu disposición</p></Link>
                </div>
            </div>
            <img className="menu-icon" src={iconMenu} alt="Ícono menú" onClick={handleToggle}/>
            <nav className="nav-bar">
                <ul className={`menu-nav ${navBarOpen ? " show-menu" : ""}`}>
                    <div className="menu-nav-box">
                        { JSON.parse(localStorage.getItem("userAuth")) && (<div className="user-logo"><small>{userData.name.charAt(0).toUpperCase() + userData.lastName.charAt(0).toUpperCase()}</small></div>) }
                        { JSON.parse(localStorage.getItem("userAuth")) && (<p className="greeting">Hola,</p>) }
                        { JSON.parse(localStorage.getItem("userAuth")) && (<p>{userData.name} {userData.lastName}</p>) }
                        { !(JSON.parse(localStorage.getItem("userAuth"))) && (<p>Menú</p>) }
                    </div>
                    <li>
                        { !(props.register) && !(JSON.parse(localStorage.getItem("userAuth"))) && (<Link to="/register" className="menu-nav-link register" onClick={closeMenu}>Crear cuenta</Link>) }
                    </li>
                    <li>
                        { !(props.login) && !(JSON.parse(localStorage.getItem("userAuth"))) && (<Link to="/login" className="menu-nav-link login" onClick={closeMenu}>Iniciar sesión</Link>) }
                    </li>
                    <li>
                        { JSON.parse(localStorage.getItem("userAuth")) && (<Link to="/" className="menu-nav-link close-session" onClick={closeSession}>¿Deseas cerrar sesión?</Link>) }
                    </li>
                    <div className="hamburguer-icons">
                        <img src={iconFacebook} alt="Logo de Facebook"></img>
                        <img src={iconLinkedin} alt="Logo de Linkedin"></img>
                        <img src={iconTwitter} alt="Logo de Twitter"></img>
                        <img src={iconInstagram} alt="Logo de Instagram"></img>
                    </div>
                </ul>
            </nav>
            <nav>
                <ul className="header-buttons">
                    <li>
                        { !(props.register) && !(JSON.parse(localStorage.getItem("userAuth"))) && (<Link to="/register" className="header-button__signup">Crear cuenta</Link>) }
                    </li>
                    <li>
                        { !(props.login) && !(JSON.parse(localStorage.getItem("userAuth"))) && (<Link to="/login" className="header-button__login">Iniciar sesión</Link>) }
                    </li>
                    { JSON.parse(localStorage.getItem("userAuth")) && (<div className="header-user-info">
                        <div className="header-user-logo">
                            <small>{userData.name.charAt(0).toUpperCase() + userData.lastName.charAt(0).toUpperCase()}</small>
                        </div>
                        <div className="header-user-info-name">
                            <p className="header-greeting">Hola,</p>
                            <p className="header-user-name">{userData.name} {userData.lastName}</p>
                        </div>
                        <Link to="/" className="close-link"><img className="header-user-close" src={iconX} alt="Logo de cerrar" onClick={closeSessionUser}></img></Link>
                    </div>) }
                </ul>
            </nav>
        </header>
    )

}