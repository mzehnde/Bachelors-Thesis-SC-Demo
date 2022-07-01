package com.example.demo.Repositories;

import com.example.demo.Entities.Reward;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RewardRepository extends CrudRepository<Reward, Long> {


    public List<Reward> findByPartner(Integer partnerId);

    List<Reward> findByQrcodepartner(String partner_qr_code);
}

