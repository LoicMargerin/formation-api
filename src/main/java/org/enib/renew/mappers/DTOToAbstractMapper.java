package org.enib.renew.mappers;

import org.enib.renew.business.model.Devise;
import org.enib.renew.business.model.Solde;
import org.enib.renew.dto.DeviseDTO;
import org.enib.renew.dto.SoldeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper
 */
@Mapper
public abstract class DTOToAbstractMapper {

    public static final DTOToAbstractMapper INSTANCE = Mappers.getMapper(DTOToAbstractMapper.class);

    @Mapping(target = "Devise", ignore = true)
    public abstract Solde getFromSoldeDTO(final SoldeDTO pToConvert);

    public abstract Devise getFromDeviseDTO(final DeviseDTO pToConvert);

}
