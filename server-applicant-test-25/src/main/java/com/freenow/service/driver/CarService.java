package com.freenow.service.driver;

import java.util.List;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.EngineType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

public interface CarService {

	CarDO find(long carId) throws EntityNotFoundException;

	CarDO create(CarDO carDO) throws ConstraintsViolationException;

	void delete(long carId) throws EntityNotFoundException;

	void updateLicensePlate(long carId, String licensePlate) throws EntityNotFoundException;

	List<CarDO> find(EngineType engineType);

	CarDO findCarByLicensePlate(String licensePlate) throws EntityNotFoundException;;;

	List<CarDO> findByRatings(int rating);
}
