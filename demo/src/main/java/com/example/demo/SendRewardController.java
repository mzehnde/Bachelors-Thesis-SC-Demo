package com.example.demo;

import com.example.demo.Entities.NormalReward;
import com.example.demo.Entities.NormalRewardGivenOut;
import com.example.demo.Entities.User;
import com.example.demo.REST.*;
import com.example.demo.Repositories.NormalRewardGivenOutRepository;
import com.example.demo.Repositories.NormalRewardRepository;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping(path="/test")
public class SendRewardController {

    @Autowired
    private NormalRewardRepository normalRewardRepository;

    @Autowired
    private NormalRewardGivenOutRepository normalRewardGivenOutRepository;

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

    public static String decode(String url)
    {
        try {
            String prevURL="";
            String decodeURL=url;
            while(!prevURL.equals(decodeURL))
            {
                prevURL=decodeURL;
                decodeURL=URLDecoder.decode( decodeURL, "UTF-8" );
            }
            return decodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while decoding" +e.getMessage();
        }
    }

    //API CALl that fetches metadata and returns its URL (body:partner from URL (key=name))
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path="/metadata") // Map ONLY POST Requests
    public @ResponseBody
    Object getMetadata (@RequestParam String partnerWhereRewardReceived) throws IOException, JSONException {
        String queryParam = "?metadata[keyvalues][Partner]{\"value:\"" + partnerWhereRewardReceived + ", \"op\":\"ne\"}";
        String nameParam = "?metadata[name]=PartnerHonigReward1.json";
        String testParam = "?metadata[keyvalues]={Partner:{value:" + partnerWhereRewardReceived + ",op:ne}}";
        URL url = new URL("https://api.pinata.cloud/data/pinList" + queryParam);

        //HttpGet httpGet = new HttpGet("https://example.com");
        /*try {
            URI uri = new URIBuilder(httpGet.getURI())
                    .addParameter("param1", "value1")
                    .addParameter("param2", "value2")
                    .build();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/

        //HttpURLConnection con = (HttpURLConnection) url.openConnection();

        URL url1 = UriComponentsBuilder.fromUriString(String.valueOf(url)).queryParam("status", "pinned").build().toUri().toURL();
        String url2 = url1.toString();
        String decodeUrl = decode(url2);
        /*try (InputStream in = url1.openStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }*/
        return decodeUrl;



        //CORRECT
        /*HttpURLConnection con = (HttpURLConnection) url1.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mb3JtYXRpb24iOnsiaWQiOiJmMzU3YTBlNy1kM2NkLTRjY2MtOGUwZi1iYmJjYTlkZDZkNWUiLCJlbWFpbCI6Im1heC56ZWhuZGVyQGhvdG1haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsInBpbl9wb2xpY3kiOnsicmVnaW9ucyI6W3siaWQiOiJGUkExIiwiZGVzaXJlZFJlcGxpY2F0aW9uQ291bnQiOjF9XSwidmVyc2lvbiI6MX0sIm1mYV9lbmFibGVkIjpmYWxzZSwic3RhdHVzIjoiQUNUSVZFIn0sImF1dGhlbnRpY2F0aW9uVHlwZSI6InNjb3BlZEtleSIsInNjb3BlZEtleUtleSI6IjA2ZGY2NjQzMTE3MThiZDUxMjM4Iiwic2NvcGVkS2V5U2VjcmV0IjoiOTA3ZDNmOTQyMjc3ZWE4NjRjNjdhOWY4YTgzZDBmYjNkMTM3OWY0MGI4ZmZlZDJjNDI4YTJmOWZjYWM2YTY5OCIsImlhdCI6MTY1NzE5MzQ2NX0.uAgBlwk3aYq9-ifBUjXx4aZZC2YUWRT9J_2Mn7MC_0g") ;
        //CORRECT*/


        /*OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();*/


        //CORRECT
        /*int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content;*/
        //CORRECT
        //fetch metadatas and check if .partner=partnerReward...
        //return if condition is correct: .image
        /*JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(String.valueOf(content));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject files = (JSONObject) jsonObject.get("content");
        return files.get("name");*/
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
