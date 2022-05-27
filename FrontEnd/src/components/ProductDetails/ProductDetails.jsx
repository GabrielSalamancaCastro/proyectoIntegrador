import React, { useState } from 'react';
import { useWindowWidth } from "../../hooks/useWindowWidth/useWindowWidth";
import { Link } from "react-router-dom";
import { Modal } from 'react-responsive-modal';
import ImageGallery from 'react-image-gallery';
import iconHomePath from "../../assets/img/icon-arrowHomePath.svg";
import iconLocation from "../../assets/img/icon-location.svg";
import sharePhoto from "../../assets/img/icon-sharing.svg";
import favorite from "../../assets/img/icon-favorite.svg";
import "react-image-gallery/styles/css/image-gallery.css";
import "./ProductDetails.css";

export const ProductDetails = (props) => {

    const { width } = useWindowWidth();
	const [open, setOpen] = useState(false);

	const onOpenModal = () => setOpen(true);
	const onCloseModal = () => setOpen(false);

    const carImages = props.car.images.map(image => {
        return {
            id: image.id,
            original: image.url,
            thumbnail: image.url
        }
    })
    
    return (
        <section>
            <div className="heading-section">
                <div className="heading-section_texts">
                    <h3 className="heading-section_category">{props.car.category.name}</h3>
                    <h2 className="heading-section_title">{props.car.name}</h2>
                </div>
                <Link to="/"><img className="heading-section__icon" src={iconHomePath} alt="Regresar al Home"/></Link>
            </div>
            <div className="location-section">
                <div className="location-section__left">
                    <img className="location-section__left-icon" src={iconLocation} alt="Ícono de localización del producto"/>
                    <p className="location-section__left-text">{`${props.car.city.name}, ${props.car.city.country}`}</p>  
                </div>
                <small className="location-section__right">8</small> 
            </div>
            <div className="gallery-block">
                { (width < 1199) && (<img className="icons-photo-gallery gallery-icon1" src={sharePhoto} alt="Compartir foto"/>) }
                { (width < 1199) && (<img className="icons-photo-gallery gallery-icon2" src={favorite} alt="Agregar foto a favoritos"/>) }
				{ (width > 1199) && 
				(<div className="icons-photo-gallery-desktop">
					<img className="gallery-icon1-desktop" src={sharePhoto} alt="Compartir foto"/>
					<img className="gallery-icon2-desktop" src={favorite} alt="Agregar foto a favoritos"/>
					<Modal open={open} onClose={onCloseModal} center>
						<ImageGallery 
							items={carImages} 
							showIndex={true}
							showNav={true}
							showFullscreenButton={false}
							showPlayButton={false}
							autoPlay={true}
							additionalClass={"gallery-desktop"}
						/>
					</Modal>
				</div>)}
                {(width < 1199) && (                    
                    <ImageGallery 
                        items={carImages} 
                        showIndex={true}
                        showNav={false}
                        showFullscreenButton={false}
                        showPlayButton={false}
                        autoPlay={true}
						showThumbnails={false}
                    />
                )}
                { (width > 1199) && (
                    <div className="gallery-block-desktop">
                        {carImages.slice(0, 5).map((image) => (
							<img src={image.original}  alt="images" className={`card-img ${image.id}`}/>
						))}
						<small onClick={onOpenModal}>Ver más</small>
                    </div>
                )}
            </div>
            <div className="description-product">
                <h2 className="description-product__title">{props.car.name}</h2>
                <p className="description-product__details">{props.car.description}</p>
            </div>
            <div className="product-services__block">
                <h2 className="product-services__title">¿Qué ofrece?</h2>
                <div className="product-services__container">
                    {props.car.characteristics.map((carServices) => (
                        <div key={carServices.id} className="product-services__section">
                            <img className="product-services__icon" src={carServices.icon} alt={carServices.name}/>
                            <p className="product-services__name">{carServices.name}</p>
                        </div>
                    ))}
                </div>
            </div>
        </section>
    );
}