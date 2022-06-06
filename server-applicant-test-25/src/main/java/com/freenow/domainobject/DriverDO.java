package com.freenow.domainobject;

import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "driver", uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = { "username" }))
public class DriverDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	@NotNull(message = "Username can not be null!")
	private String username;

	@Column(nullable = false)
	@NotNull(message = "Password can not be null!")
	private String password;

	@Column(nullable = false)
	private Boolean deleted = false;

	@Embedded
	private GeoCoordinate coordinate;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OnlineStatus onlineStatus;

	@Column(nullable = true)
	private Long carId;

	/**
	 * @OneToOne(fetch = FetchType.LAZY)
	 * @JoinColumn(name = "carDO_id", nullable = true) private CarDO carDO;
	 * 
	 *                  public CarDO getCarDO() { return carDO; }
	 * 
	 * 
	 *                  public void setCarDO(CarDO carDO) { this.carDO = carDO; }
	 */

	public DriverDO() {
	}

	public DriverDO(String username, String password) {
		this.username = username;
		this.password = password;
		this.deleted = false;
		this.coordinate = null;
		this.dateCoordinateUpdated = null;
		this.onlineStatus = OnlineStatus.OFFLINE;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public ZonedDateTime getDateCoordinateUpdated() {
		return dateCoordinateUpdated;
	}

	public void setDateCoordinateUpdated(ZonedDateTime dateCoordinateUpdated) {
		this.dateCoordinateUpdated = dateCoordinateUpdated;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public OnlineStatus getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(OnlineStatus onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public GeoCoordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(GeoCoordinate coordinate) {
		this.coordinate = coordinate;
		this.dateCoordinateUpdated = ZonedDateTime.now();
	}

}
