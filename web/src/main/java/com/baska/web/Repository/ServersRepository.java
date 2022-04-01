package com.baska.web.Repository;

import com.baska.web.Models.Servers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ServersRepository extends JpaRepository<Servers,String> {



    @Query("SELECT e FROM Servers as e WHERE e.serverName = :Server")
    Optional<Servers> getServer(@Param("Server") String ServerName);

}
