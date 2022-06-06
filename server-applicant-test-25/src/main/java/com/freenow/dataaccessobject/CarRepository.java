package com.freenow.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.EngineType;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long> {

	List<CarDO> findByEngineType(EngineType engineType);

	CarDO findByLicensePlate(String licensePlate);

	List<CarDO> findByRating(int rating);

}
