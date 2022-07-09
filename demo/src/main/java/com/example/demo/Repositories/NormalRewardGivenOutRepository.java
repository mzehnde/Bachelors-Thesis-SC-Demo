package com.example.demo.Repositories;


import com.example.demo.Entities.NormalReward;
import com.example.demo.Entities.NormalRewardGivenOut;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NormalRewardGivenOutRepository extends CrudRepository<NormalRewardGivenOut, Long> {


    List<NormalRewardGivenOut> findAll();


}