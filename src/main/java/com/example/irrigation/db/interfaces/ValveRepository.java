package com.example.irrigation.db.interfaces;

import com.example.irrigation.db.entities.Plot;
import com.example.irrigation.db.entities.Valve;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ValveRepository extends CrudRepository<Valve, Long> {
    List<Valve> findByPlot(Plot plot);
}
