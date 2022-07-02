package com.example.demo.REST;

import com.example.demo.Entities.Partner;
import com.example.demo.Entities.Reward;
import com.example.demo.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    @Mapping(source = "id", target = "id")
    Partner convertPartnerPostDTOtoEntity(PartnerPostDTO partnerPostDTO);

    @Mapping(source = "emailAddress", target = "emailAddress")
    @Mapping(source = "partner_QR_Code", target = "partner_QR_Code")
    User convertUserPostDTOtoEntity(UserPostDTO userPostDTO);

    @Mapping(source = "sales", target = "sales")
    @Mapping(source = "qrcodereward", target = "qrcodereward")
    Reward convertRewardPutDTOtoEntity(RewardPutDTO rewardPutDTO);

    @Mapping(source = "qrcodereward", target = "qrcodereward")
    Reward convertIsRedeemedGetDTOtoEntity(IsRedeemedGetDTO isRedeemedGetDTO);
}
