package com.baska.web.Repository;

import com.baska.web.Models.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FilesRepository extends JpaRepository<Files,String> {

    @Query(value="select * from files",nativeQuery=true)
    List<Files> getAll();


    @Query(value="select * from files inner join servers s on files.server_id = s.id where (files.enable and s.enabled)",nativeQuery=true)
    List<Files> getEnableFiles();


}
