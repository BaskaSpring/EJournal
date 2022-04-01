package com.baska.web.Repository;

import com.baska.web.Models.Bases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasesRepository extends JpaRepository<Bases,String> {


    @Query("select x from bases as x")
    List<Bases> getAll();
}
