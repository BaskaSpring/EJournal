package com.baska.web.Repository;

import com.baska.web.Models.ServerRefreshTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ServerRefreshTimeRepository extends JpaRepository<ServerRefreshTime,String> {

    @Query(value="select * from refreshtime as x right join servers as y on x.server_id=y.id where (y.server_name=?1 and x.service=?2 and y.enabled)",nativeQuery=true)
    Optional<ServerRefreshTime> _findByServerName(String serverName, String serviceName);


}
