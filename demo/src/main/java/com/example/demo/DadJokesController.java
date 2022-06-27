package com.example.demo;
import com.example.demo.Entities.Partner;
import com.example.demo.Repositories.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/test")
public class DadJokesController {
    @Autowired
    private PartnerRepository partnerRepository;


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        Partner n = new Partner((long) 3, name, email);
        partnerRepository.save(n);
        return "Saved";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/sendMail") // Map ONLY POST Requests
    public @ResponseBody String sendEmail (@RequestBody String email) {

        return email;
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<Partner> getAllUsers() {
        System.out.print("TEST");
        return partnerRepository.findAll();
    }

    @GetMapping("/api/dadjokes")
    public String dadJokes() {
        return "Justice is a dish best served cold, if it were served warm it would be just water.";
    }


}
