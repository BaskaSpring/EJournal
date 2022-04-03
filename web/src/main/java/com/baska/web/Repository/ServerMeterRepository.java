package com.baska.web.Repository;

import com.baska.web.Models.ServerMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


@Repository
public interface ServerMeterRepository extends JpaRepository<ServerMeter,String> {

    @Query(value = "select * from meters as v where not (v.id in(select y.id from meters as y right join (select x.server_id,Max(x.timestamp) as ttt,x.disc_name from meters as x group by x.server_id, x.disc_name) as z on y.server_id=z.server_id and y.disc_name=z.disc_name and y.timestamp=z.ttt))", nativeQuery=true)
    List<ServerMeter> getLastMeters();


}
