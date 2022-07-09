package com.example.demo.REST;

import com.example.demo.Entities.NFTRewardGivenOut;
import com.example.demo.Entities.Partner;
import com.example.demo.Entities.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-09T15:31:53+0200",
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

    @Override
    public NFTGetDTO convertEntityToNFTGetDTO(NFTRewardGivenOut nftRewardGivenOut) {
        if ( nftRewardGivenOut == null ) {
            return null;
        }

        int id = 0;
        String ifpsHash = null;

        id = nftRewardGivenOut.getId();
        ifpsHash = nftRewardGivenOut.getIfpsHash();

        NFTGetDTO nFTGetDTO = new NFTGetDTO( id, ifpsHash );

        return nFTGetDTO;
    }
}
