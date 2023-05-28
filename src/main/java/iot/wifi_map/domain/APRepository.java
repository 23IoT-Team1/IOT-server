package iot.wifi_map.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface APRepository extends JpaRepository<AP,Long> {

    @Query("select count(a) from AP a")
    Integer getNumberOfAp();
}
