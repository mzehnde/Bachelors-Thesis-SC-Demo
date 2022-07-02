package com.example.demo.REST;

import com.example.demo.Entities.Partner;
import com.example.demo.Entities.Reward;
import com.example.demo.Entities.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-02T16:26:14+0200",
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

    @Override
    public Reward convertRewardPutDTOtoEntity(RewardPutDTO rewardPutDTO) {
        if ( rewardPutDTO == null ) {
            return null;
        }

        Reward reward = new Reward();

        reward.setSales( rewardPutDTO.getSales() );
        reward.setQrcodereward( rewardPutDTO.getQrcodereward() );

        return reward;
    }

    @Override
    public Reward convertIsRedeemedGetDTOtoEntity(IsRedeemedGetDTO isRedeemedGetDTO) {
        if ( isRedeemedGetDTO == null ) {
            return null;
        }

        Reward reward = new Reward();

        reward.setQrcodereward( isRedeemedGetDTO.getQrcodereward() );

        return reward;
    }
}
