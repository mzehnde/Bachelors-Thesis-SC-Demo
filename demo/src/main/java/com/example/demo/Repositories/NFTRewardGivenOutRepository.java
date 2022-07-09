package com.example.demo.Repositories;

import com.example.demo.Entities.NFTRewardGivenOut;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NFTRewardGivenOutRepository extends CrudRepository<NFTRewardGivenOut, Long> {

    Optional<NFTRewardGivenOut> findById(Long id);
}
