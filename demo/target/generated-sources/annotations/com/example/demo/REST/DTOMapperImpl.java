package com.example.demo.REST;

import com.example.demo.Entities.Partner;
import com.example.demo.Entities.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-08T17:16:43+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
public class DTOMapperImpl implements DTOMapper {

    @Override
    public User convertUserPostDTOtoEntity(UserPostDTO userPostDTO) {
        if ( userPostDTO == null ) {
            return null;
        }

        String emailAddress = null;

        emailAddress = userPostDTO.getEmailAddress();

        String kindOfReward = null;

        User user = new User( emailAddress, kindOfReward );

        return user;
    }

    @Override
    public Partner convertPartnerGetDTOtoEntity(PartnerGetDTO partnerGetDTO) {
        if ( partnerGetDTO == null ) {
            return null;
        }

        String name = null;

        name = partnerGetDTO.getName();

        Partner partner = new Partner( name );

        return partner;
    }
}
