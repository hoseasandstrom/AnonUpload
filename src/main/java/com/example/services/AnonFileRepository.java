package com.example.services;

import com.example.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hoseasandstrom on 6/27/16.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {
    public Iterable<AnonFile> findByForever(Boolean forever);
    public int countByForever(Boolean forever);

}
