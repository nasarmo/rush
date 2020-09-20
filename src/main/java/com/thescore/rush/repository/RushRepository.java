package com.thescore.rush.repository;


import com.thescore.rush.model.Rush;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RushRepository extends PagingAndSortingRepository<Rush, Long> {
    
    Page<Rush> findAllByPlayerLikeIgnoreCase(String player, Pageable pageable);
    List<Rush> findAll();
    List<Rush> findAll(Sort sort);

}
