package com.freenow.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;

public class CarMapper {

	public static CarDO makeCarDO(CarDTO carDTO) {
		return new CarDO(carDTO.getLicensePlate(), carDTO.getSeatCount(), carDTO.getConvertible(), carDTO.getRating(),
				carDTO.getEngineType());
	}

	public static CarDTO makeCarDTO(CarDO car) {

		return new CarDTO(car.getId(), car.getDateCreated(), car.getLicensePlate(), car.getSeatCount(),
				car.isConvertible(), car.getRating(), car.getEngineType());
	}

	public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars) {
		// TODO Auto-generated method stub
		return cars.stream().map(CarMapper::makeCarDTO).collect(Collectors.toList());
	}
}
