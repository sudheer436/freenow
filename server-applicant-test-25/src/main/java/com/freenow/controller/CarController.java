package com.freenow.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.EngineType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.driver.CarService;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
	private final CarService carService;

	@Autowired
	public CarController(final CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/{carId}")
	public CarDO getCar(@PathVariable long carId) throws EntityNotFoundException {
		// return CarMapper.makeCarDTO(carService.find(carId));
		LOGGER.info(" >>>>>> car controller: calling getCar by id");
		return carService.find(carId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarDO createCar(@Valid @RequestBody CarDO carDO) throws ConstraintsViolationException {
		LOGGER.info(" >>>>>> car controller: calling create car operation");
		return carService.create(carDO);
	}

	@DeleteMapping("/{carId}")
	public void deleteCar(@PathVariable long carId) throws EntityNotFoundException {
		LOGGER.info(" >>>>>> car controller: calling delete car operation");
		carService.delete(carId);
	}

	@PutMapping("/{carId}")
	public void updateLocation(@PathVariable long carId, @RequestParam String licensePlate)
			throws EntityNotFoundException {
		LOGGER.info(" >>>>>> car controller: calling update car operation");
		carService.updateLicensePlate(carId, licensePlate);
	}

	@GetMapping
	public List<CarDO> findCarss(@RequestParam EngineType engineType) {
		LOGGER.info(" >>>>>> car controller: calling get car operation");
		return carService.find(engineType);
	}

	@GetMapping("/findByLicensePlate")
	public CarDO findCarByLicensePlate(@RequestParam String licensePlate) throws EntityNotFoundException {
		LOGGER.info(" >>>>>> car controller: calling get car operation by license plate");
		return carService.findCarByLicensePlate(licensePlate);
	}

	@GetMapping("/rating/{rating}")
	public List<CarDO> findByRatings(@PathVariable int rating) {
		LOGGER.info(" >>>>>> car controller: calling get car operation by rating");
		return carService.findByRatings(rating);
	}

}
