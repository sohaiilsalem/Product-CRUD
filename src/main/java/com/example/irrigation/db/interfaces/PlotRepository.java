package com.example.irrigation.db.interfaces;

import com.example.irrigation.db.entities.Plot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PlotRepository extends CrudRepository<Plot, Long> {
     Plot save(Plot plot);
     List<Plot> findAll();
}
