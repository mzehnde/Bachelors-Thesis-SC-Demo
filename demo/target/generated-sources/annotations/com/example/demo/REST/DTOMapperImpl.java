package com.example.demo.REST;

import com.example.demo.Entities.Partner;
import com.example.demo.Entities.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T17:20:37+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
public class DTOMapperImpl implements DTOMapper {

    @Override
    public Partner convertPartnerPostDTOtoEntity(PartnerPostDTO partnerPostDTO) {
        if ( partnerPostDTO == null ) {
            return null;
        }

        Partner partner = new Partner();

        partner.setId( partnerPostDTO.getId() );

        return partner;
    }

    @Override
    public User convertUserPostDTOtoEntity(UserPostDTO userPostDTO) {
        if ( userPostDTO == null ) {
            return null;
        }

        String emailAddress = null;

        emailAddress = userPostDTO.getEmailAddress();

        Long partner = null;

        User user = new User( emailAddress, partner );

        user.setPartner( userPostDTO.getPartner() );

        return user;
    }
}
