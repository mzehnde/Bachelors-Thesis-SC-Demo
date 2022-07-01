package com.example.demo.REST;

import com.example.demo.Entities.Partner;
import com.example.demo.Entities.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-01T19:30:55+0200",
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

        String partner = null;

        User user = new User( emailAddress, partner );

        user.setPartner_QR_Code( userPostDTO.getPartner_QR_Code() );

        return user;
    }
}
