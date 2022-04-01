package com.baska.web.Repository;


import com.baska.web.Models.ServerScanPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ServerScanPathsRepository extends JpaRepository<ServerScanPath,String> {

    @Query(value="select * from scanpaths x inner join servers as y on x.server_id=y.id where (y.server_name=?1 and y.enabled)", nativeQuery =true)
    List<ServerScanPath> _findByServerName(String serverName);
}
