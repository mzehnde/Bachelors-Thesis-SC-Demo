package com.example.demo;

import com.example.demo.Entities.Partner;
import com.example.demo.Entities.Reward;
import com.example.demo.Entities.User;
import com.example.demo.REST.DTOMapper;
import com.example.demo.REST.PartnerPostDTO;
import com.example.demo.REST.UserPostDTO;
import com.example.demo.Repositories.PartnerRepository;
import com.example.demo.Repositories.RewardRepository;
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
    private RewardRepository rewardRepository;

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
        Reward n = new Reward(name, PartnerId);
        rewardRepository.save(n);
        return "Saved";
    }





    //Send Email with Reward
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/sendMail") // Map ONLY POST Requests
    public @ResponseBody String sendMail (@RequestBody UserPostDTO userPostDTO){
        // searched for correct reward
        // now send Email with Name of that Reward as message to user.email
        User user = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);
        Long PartnerId = user.getPartner();
        List<Reward> rewardToGiveOut =rewardRepository.findByPartnerId(PartnerId.intValue());
        Reward reward = rewardToGiveOut.get(0);
        System.out.println(reward.getName());


        Mail email = new Mail(user.getEmailAddress(), "claimyourawesomereward@gmail.com");
        email.sendEmail();
        return "Email sent";
    }







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
