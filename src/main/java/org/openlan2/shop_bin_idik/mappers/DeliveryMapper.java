package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openlan2.shop_bin_idik.dto.DeliveryDto;
import org.openlan2.shop_bin_idik.entities.Delivery;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    
    @Mapping(source = "livreur.nomComplet", target = "nomComplet")
    @Mapping(source = "livreur.photoConducteur", target = "photo")
    @Mapping(source = "livreur.typeVehicule", target = "typeVehicule")
    @Mapping(source = "livreur.numImmatriculation", target = "numImmatriculation")
    @Mapping(source = "livreur.photoVehiculeRecto", target = "photoVehiculeRecto")
    @Mapping(source = "livreur.photoVehiculeVerso", target = "photoVehiculeVerso")
    DeliveryDto toDto(Delivery delivery);
    
    Delivery toEntity(DeliveryDto deliveryDto);
}
