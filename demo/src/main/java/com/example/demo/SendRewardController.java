package com.example.demo;

import com.example.demo.Entities.NormalReward;
import com.example.demo.Entities.NormalRewardGivenOut;
import com.example.demo.Entities.Partner;
import com.example.demo.Entities.User;
import com.example.demo.REST.*;
import com.example.demo.Repositories.NormalRewardGivenOutRepository;
import com.example.demo.Repositories.PartnerRepository;
import com.example.demo.Repositories.NormalRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;




@Controller
@RequestMapping(path="/test")
public class SendRewardController {
    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private NormalRewardRepository normalRewardRepository;

    @Autowired
    private NormalRewardGivenOutRepository normalRewardGivenOutRepository;

    //REQUESTS:

    //Save a new Partner
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewPartner (@RequestParam String name
            , @RequestParam Integer QR_Code_Path) {
        Partner n = new Partner(name, QR_Code_Path);
        partnerRepository.save(n);
        return "Saved";
    }


    //Save new Reward
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/addReward") // Map ONLY POST Requests
    public @ResponseBody String addNewReward (@RequestParam String name
            , @RequestParam Integer PartnerId) {
        NormalReward n = new NormalReward(name);
        normalRewardRepository.save(n);
        return "Saved";
    }

    //check normal or NFT Reward
    // --> do different things accordingly in service
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/sendReward") // Map ONLY POST Requests
    public @ResponseBody String sendReward (@RequestBody UserPostDTO userPostDTO){
        User userToReceiveReward = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);

        //create repo NormalRewardsGivenOut here and in DB
        //Get all Rewards
        //check if there are rewards left
        //send one per Email
        //delete reward from NormalRewards and insert in table NormalRewardsGivenOut
        //populate db to test -> check which image link
        if (userToReceiveReward.getKindOfReward().equals("/normalReward")) {
            List<NormalReward> allNormalRewards = normalRewardRepository.findAll();
            NormalReward normalRewardToSend = allNormalRewards.get(0);
            Mail email = new Mail(userToReceiveReward.getEmailAddress(), "claimyourawesomereward@gmail.com", normalRewardToSend.getImage(), normalRewardToSend.getLocation());
            email.sendEmail();
            NormalRewardGivenOut normalRewardGivenOut = new NormalRewardGivenOut(normalRewardToSend.getId(), normalRewardToSend.getName(), normalRewardToSend.getImage(), normalRewardToSend.getLocation());
            normalRewardGivenOutRepository.save(normalRewardGivenOut);
            normalRewardRepository.delete(normalRewardToSend);
            return "NormalRewardRouting";
        }
        //MintNFT
        // --> get metadatas from pinata
        // --> get first json file
        // --> delete from pinata
        // --> call mintNFT(link)
        //send image of it via mail (image from pinata)
        if (userToReceiveReward.getKindOfReward().equals("/blockchainReward")) {

            return "BlockchainrewardRouting";
        }
        else{
            return "Didnt Work";

        }

    }






    //Send Email with Reward
    /*@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/sendMail") // Map ONLY POST Requests
    public @ResponseBody String sendMail (@RequestBody UserPostDTO userPostDTO){
        // searched for correct reward
        // now send Email with Name of that Reward as message to user.email
        User user = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);
        String partner_QR_Code = user.getPartner_QR_Code();
        //Long Partnerid = Long.parseLong(PartnerId);
        List<NormalReward> normalRewardToGiveOut =rewardRepository.findByQrcodepartner(partner_QR_Code);
        NormalReward normalReward = normalRewardToGiveOut.get(0);
        Mail email = new Mail(user.getEmailAddress(), "claimyourawesomereward@gmail.com", normalReward.getImage(), normalReward.getLocation());
        email.sendEmail();
        return normalReward.getLocation();
        //return "Email sent";
    }*/

    //checks if reward was redeemed --> tested
   /* @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/isRedeemed") // Map ONLY POST Requests
    public @ResponseBody Boolean isRedeemed (@RequestBody IsRedeemedGetDTO isRedeemedGetDTO) {
        Boolean isRedeemed = true;
        NormalReward normalReward = DTOMapper.INSTANCE.convertIsRedeemedGetDTOtoEntity(isRedeemedGetDTO);
        NormalReward normalRewardToCheck = rewardRepository.findByQrcodereward(normalReward.getQrcodereward());
        if (normalRewardToCheck.getIsredeemed() == null){
            return !isRedeemed;
        }
        if (normalRewardToCheck.getIsredeemed()){
            return isRedeemed;
        }
        else{
            return !isRedeemed;
        }
    }

    //adds sales and marks as redeemed --> tested
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/addSales") // Map ONLY POST Requests
    public @ResponseBody
    Boolean addSales (@RequestBody RewardPutDTO rewardPutDTO) {
        NormalReward normalReward = DTOMapper.INSTANCE.convertRewardPutDTOtoEntity(rewardPutDTO);
        NormalReward normalRewardToAddSales = rewardRepository.findByQrcodereward(normalReward.getQrcodereward());

        normalRewardToAddSales.setSales(normalReward.getSales());
        normalRewardToAddSales.setIsredeemed(true);
        rewardRepository.save(normalRewardToAddSales);
        return normalRewardToAddSales.getIsredeemed();
    }*/





    //Delete Partner by Id
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/delete") // Map ONLY POST Requests
    public @ResponseBody String deletePartner (@RequestBody PartnerPostDTO partnerPostDTO){
        Partner partner = DTOMapper.INSTANCE.convertPartnerPostDTOtoEntity(partnerPostDTO);
        Optional<Partner> partnerToDelete = partnerRepository.findById(partner.getId());
        Partner p2 = partnerToDelete.get();
        partnerRepository.delete(p2);
        return "Partner deleted";
    }


    //Return all Partners in DB
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Partner> getAllUsers() {
        System.out.print("TEST");
        return partnerRepository.findAll();
    }
}
