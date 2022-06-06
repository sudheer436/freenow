package com.freenow.service.driver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.EngineType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for some car specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

	private final CarRepository carRepository;

	public DefaultCarService(final CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public CarDO find(long carId) throws EntityNotFoundException {

		return findCarChecked(carId);

	}

	private CarDO findCarChecked(Long carId) throws EntityNotFoundException {
		return carRepository.findById(carId)
				.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
	}

	@Override
	public CarDO create(CarDO carDO) throws ConstraintsViolationException {
		CarDO car;
		try {
			car = carRepository.save(carDO);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("ConstraintsViolationException while creating a driver: {}", carDO, e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return car;
	}

	@Override
	@Transactional
	public void delete(long carId) throws EntityNotFoundException {
		CarDO carDO = findCarChecked(carId);
		carDO.setDeleted(true);
	}

	@Override
	public void updateLicensePlate(long carId, String licensePlate) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		CarDO carDO = findCarChecked(carId);
		carDO.setLicensePlate(licensePlate);
		carDO = carRepository.save(carDO);
	}

	@Override
	public List<CarDO> find(EngineType engineType) {
		// TODO Auto-generated method stub
		return carRepository.findByEngineType(engineType);
	}

	@Override
	public CarDO findCarByLicensePlate(String licensePlate) throws EntityNotFoundException {
		// TODO Auto-generated method stub

		CarDO carDO = carRepository.findByLicensePlate(licensePlate);
		if (carDO != null) {
			return carDO;
		} else {
			throw new EntityNotFoundException("licensePlate is not associated with any car, please give valid input");
		}
	}

	@Override
	public List<CarDO> findByRatings(int rating) {
		// TODO Auto-generated method stub
		return carRepository.findByRating(rating);
	}

}
