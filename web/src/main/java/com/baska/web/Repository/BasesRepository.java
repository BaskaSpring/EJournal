package com.baska.web.Repository;

import com.baska.web.Models.Bases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasesRepository extends JpaRepository<Bases,String> {


    @Query(value= "select * from bases as x",nativeQuery=true)
    List<Bases> getAll();
}
