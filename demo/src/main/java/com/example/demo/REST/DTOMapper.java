package com.example.demo.REST;

import com.example.demo.Entities.NormalReward;
import com.example.demo.Entities.Partner;
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
    @Mapping(source = "kindOfReward", target = "kindOfReward")
    User convertUserPostDTOtoEntity(UserPostDTO userPostDTO);

    /*@Mapping(source = "sales", target = "sales")
    @Mapping(source = "qrcodereward", target = "qrcodereward")
    NormalReward convertRewardPutDTOtoEntity(RewardPutDTO rewardPutDTO);

    @Mapping(source = "qrcodereward", target = "qrcodereward")
    NormalReward convertIsRedeemedGetDTOtoEntity(IsRedeemedGetDTO isRedeemedGetDTO);*/
}
