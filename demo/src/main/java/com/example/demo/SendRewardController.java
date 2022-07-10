package com.example.demo;

import com.example.demo.Entities.*;
import com.example.demo.REST.*;
import com.example.demo.Repositories.*;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.*;
import java.net.*;
import java.util.List;


@Controller
@RequestMapping(path="/test")
public class SendRewardController {

    @Autowired
    private NormalRewardRepository normalRewardRepository;

    @Autowired
    private NormalRewardGivenOutRepository normalRewardGivenOutRepository;

    @Autowired
    private NFTRewardGivenOutRepository nftRewardGivenOutRepository;

    @Autowired
    private NFTRewardRedeemedRepository nftRewardRedeemedRepository;

    @Autowired
    private NormalRewardRedeemedRepository normalRewardRedeemedRepository;

    private String BearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mb3JtYXRpb24iOnsiaWQiOiJmMzU3YTBlNy1kM2NkLTRjY2MtOGUwZi1iYmJjYTlkZDZkNWUiLCJlbWFpbCI6Im1heC56ZWhuZGVyQGhvdG1haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsInBpbl9wb2xpY3kiOnsicmVnaW9ucyI6W3siaWQiOiJGUkExIiwiZGVzaXJlZFJlcGxpY2F0aW9uQ291bnQiOjF9XSwidmVyc2lvbiI6MX0sIm1mYV9lbmFibGVkIjpmYWxzZSwic3RhdHVzIjoiQUNUSVZFIn0sImF1dGhlbnRpY2F0aW9uVHlwZSI6InNjb3BlZEtleSIsInNjb3BlZEtleUtleSI6IjA2ZGY2NjQzMTE3MThiZDUxMjM4Iiwic2NvcGVkS2V5U2VjcmV0IjoiOTA3ZDNmOTQyMjc3ZWE4NjRjNjdhOWY4YTgzZDBmYjNkMTM3OWY0MGI4ZmZlZDJjNDI4YTJmOWZjYWM2YTY5OCIsImlhdCI6MTY1NzE5MzQ2NX0.uAgBlwk3aYq9-ifBUjXx4aZZC2YUWRT9J_2Mn7MC_0g";

    //REQUESTS:

    //Save a new Partner
    /*@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewPartner (@RequestParam String name
            , @RequestParam Integer QR_Code_Path) {
        Partner n = new Partner(name, QR_Code_Path);
        partnerRepository.save(n);
        return "Saved";
    }*/


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
    @PostMapping(path="/sendNormalEmail") // Map ONLY POST Requests
    public @ResponseBody String sendReward (@RequestBody UserPostDTO userPostDTO){
        User userToReceiveReward = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);


        List<NormalReward> allNormalRewards = normalRewardRepository.findAll();
        NormalReward normalRewardToSend = allNormalRewards.get(0);
        Mail email = new Mail(userToReceiveReward.getEmailAddress(), "claimyourawesomereward@gmail.com", normalRewardToSend.getImage(), normalRewardToSend.getLocation());
        email.sendEmail();
        NormalRewardGivenOut normalRewardGivenOut = new NormalRewardGivenOut(normalRewardToSend.getId(), normalRewardToSend.getName(), normalRewardToSend.getImage(), normalRewardToSend.getLocation(), normalRewardToSend.getPartner());
        normalRewardGivenOutRepository.save(normalRewardGivenOut);
        normalRewardRepository.delete(normalRewardToSend);
        return "NormalRewardRouting";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/sendNFTEmail") // Map ONLY POST Requests
    public @ResponseBody String sendNFTMail (@RequestBody NFTMailPutDTO nftMailPutDTO){
        NFTMail nftMail = DTOMapper.INSTANCE.convertNFTMailPutDTOtoEntity(nftMailPutDTO);
        int NFTId = nftMail.getId();
        String emailAddress = nftMail.getEmail();

        NFTRewardGivenOut nft = nftRewardGivenOutRepository.findById(NFTId);
        Mail mail = new Mail(emailAddress, "claimyourawesomereward@gmail.com", nft.getImage(), nft.getLocation());
        mail.sendEmail();
        return "Email sent";

    }



    //API CALl that fetches metadata and returns its URL (body:partner from URL (key=name))
    //make jsonObject only with first index of "row"
    //convert it to NFTRewardGivenOut class and save in db
    //send Email with image of this class
    //return ifps link to metadata for minting in frontend
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path="/metadata/{partnerWhereRewardReceived}") // Map ONLY POST Requests
    public @ResponseBody
    Object getMetadata (@PathVariable String partnerWhereRewardReceived) throws IOException, JSONException, URISyntaxException, ParseException {
        String baseURL = "https://api.pinata.cloud/data/pinList?";
        String pinnedParam = "&status=pinned";
        String metadataParam = "metadata[keyvalues]=";
        String metadataValues = "{\"Partner\":{\"value\":\""+partnerWhereRewardReceived+"\",\"op\":\"ne\"}}";
        String completeURL = baseURL + metadataParam + java.net.URLEncoder.encode(metadataValues, "UTF-8") + pinnedParam;
        //CORRECT
        URL url = new URL(completeURL);
        //CORRECT
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mb3JtYXRpb24iOnsiaWQiOiJmMzU3YTBlNy1kM2NkLTRjY2MtOGUwZi1iYmJjYTlkZDZkNWUiLCJlbWFpbCI6Im1heC56ZWhuZGVyQGhvdG1haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsInBpbl9wb2xpY3kiOnsicmVnaW9ucyI6W3siaWQiOiJGUkExIiwiZGVzaXJlZFJlcGxpY2F0aW9uQ291bnQiOjF9XSwidmVyc2lvbiI6MX0sIm1mYV9lbmFibGVkIjpmYWxzZSwic3RhdHVzIjoiQUNUSVZFIn0sImF1dGhlbnRpY2F0aW9uVHlwZSI6InNjb3BlZEtleSIsInNjb3BlZEtleUtleSI6IjA2ZGY2NjQzMTE3MThiZDUxMjM4Iiwic2NvcGVkS2V5U2VjcmV0IjoiOTA3ZDNmOTQyMjc3ZWE4NjRjNjdhOWY4YTgzZDBmYjNkMTM3OWY0MGI4ZmZlZDJjNDI4YTJmOWZjYWM2YTY5OCIsImlhdCI6MTY1NzE5MzQ2NX0.uAgBlwk3aYq9-ifBUjXx4aZZC2YUWRT9J_2Mn7MC_0g") ;
        //CORRECT
        //CORRECT
        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        String data = content.toString();


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray files = jsonObject.getJSONArray("rows");
        JSONObject metadataToMint = files.getJSONObject(0);
        String ipfsHash =  metadataToMint.getString("ipfs_pin_hash");
        JSONObject metadata = metadataToMint.getJSONObject("metadata");



        String name = metadata.getString("name");
        JSONObject keyvalues = metadata.getJSONObject("keyvalues");
        int id = keyvalues.getInt("Id");
        String image = keyvalues.getString("Image");
        String location = keyvalues.getString("Location");

        NFTRewardGivenOut nftRewardGivenOut = new NFTRewardGivenOut(id, name, image, ipfsHash, location);
        nftRewardGivenOutRepository.save(nftRewardGivenOut);

        NFTGetDTO nftGetDTO=DTOMapper.INSTANCE.convertEntityToNFTGetDTO(nftRewardGivenOut);

        return nftGetDTO;



        //TODO: Claiming Reward:
        //create entity and repository for NFTRewardGivenOUt:
        // --> fields: Name, id, ifpsHash, image link (QR code that routes to /NFT/redeem/{id})
        // add key value pairs to pinata (the ones that are in metadata --> namely: id, imageLink, Partner)
        // create instance of that entity with the metadata values fetched from pinata
        // save the file in NFTRewardsClaimed Repo
        // send back ifpsHash and id
        //new api call (body:cid):
        // delete this file from pinata
        // new api call --> send Email with image (body: id


        //TODO: Redeeming Reward (NFT):
        //create a test route with a correct id that also is in pinata
        //make request after submitting sales form (Body: id) & make page invalid:
        //find reward in NFTGivenOut repo
        //make a new NFTRewardClaimed repo and entity
        //create an instance and construct via field os NFTGIvenOUt
        //add sales field and save in NFTRedeemed Repo
        // --> same with normalReward Route (only other repos)
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/metadata/delete") // Map ONLY POST Requests
    public @ResponseBody int deleteMetadata(@RequestBody MetadataPinataPutDTO metadataPinataPutDTO) throws IOException {
        MetadataPinata metadataPinata = DTOMapper.INSTANCE.convertMetadataPinataPutDTOtoEntity(metadataPinataPutDTO);
        String stringURL = "https://api.pinata.cloud/pinning/unpin/"+metadataPinata.getIpfsHash();
        URL url = new URL(stringURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mb3JtYXRpb24iOnsiaWQiOiJmMzU3YTBlNy1kM2NkLTRjY2MtOGUwZi1iYmJjYTlkZDZkNWUiLCJlbWFpbCI6Im1heC56ZWhuZGVyQGhvdG1haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsInBpbl9wb2xpY3kiOnsicmVnaW9ucyI6W3siaWQiOiJGUkExIiwiZGVzaXJlZFJlcGxpY2F0aW9uQ291bnQiOjF9XSwidmVyc2lvbiI6MX0sIm1mYV9lbmFibGVkIjpmYWxzZSwic3RhdHVzIjoiQUNUSVZFIn0sImF1dGhlbnRpY2F0aW9uVHlwZSI6InNjb3BlZEtleSIsInNjb3BlZEtleUtleSI6IjA2ZGY2NjQzMTE3MThiZDUxMjM4Iiwic2NvcGVkS2V5U2VjcmV0IjoiOTA3ZDNmOTQyMjc3ZWE4NjRjNjdhOWY4YTgzZDBmYjNkMTM3OWY0MGI4ZmZlZDJjNDI4YTJmOWZjYWM2YTY5OCIsImlhdCI6MTY1NzE5MzQ2NX0.uAgBlwk3aYq9-ifBUjXx4aZZC2YUWRT9J_2Mn7MC_0g") ;
        //CORRECT
        //CORRECT
        return con.getResponseCode();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/isNFTRedeemed") // Map ONLY POST Requests
    public @ResponseBody
    boolean isNFTRedeemed(@RequestBody NFTisRedeemedPutDTO nfTisRedeemedPutDTO){
        NFTRewardGivenOut idToFind = DTOMapper.INSTANCE.convertNFTisRedeemedPutDTOtoEntity(nfTisRedeemedPutDTO);
        NFTRewardGivenOut rewardToCheck = nftRewardGivenOutRepository.findById(idToFind.getId());
        if (rewardToCheck==null){
            return true;
        }
        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/redeemNFT") // Map ONLY POST Requests
    public @ResponseBody
    String redeemNFT(@RequestBody NFTRedeemPutDTO nftRedeemPutDTO){
    NFTRewardRedeemed nft = DTOMapper.INSTANCE.convertNFTRedeemPutDTOtoEntity(nftRedeemPutDTO);
    NFTRewardGivenOut nftToRedeem = nftRewardGivenOutRepository.findById(nft.getId());
    int id = nftToRedeem.getId();
    String name = nftToRedeem.getName();
    String image = nftToRedeem.getImage();
    String ipfsHash = nftToRedeem.getImage();
    String location = nftToRedeem.getLocation();
    NFTRewardRedeemed nftRewardRedeemed = new NFTRewardRedeemed(id, name, image, ipfsHash, location, nft.getSales());
    nftRewardRedeemedRepository.save(nftRewardRedeemed);
    nftRewardGivenOutRepository.delete(nftToRedeem);
    return "saved Sales";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/isNormalRedeemed") // Map ONLY POST Requests
    public @ResponseBody
    boolean isNormalRedeemed(@RequestBody NFTisRedeemedPutDTO nfTisRedeemedPutDTO){
        NFTRewardGivenOut idToFind = DTOMapper.INSTANCE.convertNFTisRedeemedPutDTOtoEntity(nfTisRedeemedPutDTO);
        NormalRewardGivenOut rewardToCheck = normalRewardGivenOutRepository.findById(idToFind.getId());
        if (rewardToCheck==null){
            return true;
        }
        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/redeemNormal") // Map ONLY POST Requests
    public @ResponseBody
    String redeemNormal(@RequestBody NFTRedeemPutDTO nftRedeemPutDTO){
        NFTRewardRedeemed nftRewardRedeemed = DTOMapper.INSTANCE.convertNFTRedeemPutDTOtoEntity(nftRedeemPutDTO);
        NormalRewardGivenOut rewardToRedeem = normalRewardGivenOutRepository.findById(nftRewardRedeemed.getId());
        int id= rewardToRedeem.getId();
        String name= rewardToRedeem.getName();
        String image = rewardToRedeem.getImage();
        String location = rewardToRedeem.getLocation();
        String partner = rewardToRedeem.getPartner();
        int sales = nftRewardRedeemed.getSales();

        NormalRewardRedeemed normalRewardRedeemed = new NormalRewardRedeemed(id, name, image, location, partner, sales);
        normalRewardRedeemedRepository.save(normalRewardRedeemed);
        normalRewardGivenOutRepository.delete(rewardToRedeem);
        return "added sales";
    }

    //API CALl that fetches metadata and returns its URL (body:partner from URL)



    //API CALL that makes new NFTRewardGivenOutENtity with metadata infos (body: metadata URL) and returns id
    //API CALL 3 that sendsEmail from image to emailAdress (body: email, id --> to get image URL for Mail)






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
    /*@CrossOrigin(origins = "http://localhost:3000")
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
    }*/
}
