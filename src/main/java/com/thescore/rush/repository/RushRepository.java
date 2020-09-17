package com.thescore.rush.repository;


import com.thescore.rush.model.Rush;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RushRepository extends JpaRepository<Rush, Long> {

    List<Rush> findByPlayerIgnoreCase(String player);
    List<Rush> findAll();
    List<Rush> findAll(Sort sort);

}
