package com.mytaxi.controller;

import com.mytaxi.controller.driverfilter.AndCriteria;
import com.mytaxi.controller.driverfilter.ElectricCar;
import com.mytaxi.controller.driverfilter.FiveSeatCar;
import com.mytaxi.controller.driverfilter.GasCar;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.controller.mapper.DriversHavingCar;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverAlreadySelectException;
import com.mytaxi.exception.DriverHasNoCar;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.OffLineDriverNotAllowed;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import java.util.List;
import javax.validation.Valid;
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

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController {

    private final DriverService driverService;
    private final CarService carService;

    @Autowired
    public DriverController(final DriverService driverService, CarService carService) {
        this.driverService = driverService;
        this.carService = carService;
    }

    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }

    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        driverService.delete(driverId);
    }

    @PutMapping("/{driverId}")
    public void updateLocation(
            @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
            throws ConstraintsViolationException, EntityNotFoundException {
        driverService.updateLocation(driverId, longitude, latitude);
    }

    @PutMapping("/{driverId}/select")
    public DriverDTO selectCar(@PathVariable long driverId, @RequestParam long carId) throws EntityNotFoundException, CarAlreadyInUseException, OffLineDriverNotAllowed, DriverAlreadySelectException {
        DriverDO driverDO = driverService.find(driverId);
        if (driverDO.getCar() != null) {
            throw new DriverAlreadySelectException("Driver " + driverId + " is driving a car");
        }
        if (driverDO.getOnlineStatus() == OnlineStatus.OFFLINE) {
            throw new OffLineDriverNotAllowed("Offline driver " + driverId + " not allowed to select a car");
        }

        CarDO carDO = carService.find(carId);
        if (carDO.isOccupied()) {
            throw new CarAlreadyInUseException("Car " + carId + " already in use! ");
        }

        carDO.setDriver(driverDO);

        carService.update(carId, carDO);
        DriverDO updated = driverService.find(driverId);
        return DriverMapper.makeDriverDTO(updated);
    }

    @PutMapping("/{driverId}/deselect")
    public DriverDTO deSelectCar(@PathVariable long driverId) throws EntityNotFoundException, DriverHasNoCar {
        DriverDO driverDO = driverService.find(driverId);
        if (driverDO.getCar() == null) {
            throw new DriverHasNoCar("Driver has not a car ... ");
        }

        Long carId = driverDO.getCar().getId();
        CarDO carDO = carService.find(carId);
        carDO.setDriver(null);
        carService.update(carId, carDO);

        DriverDO updated = driverService.find(driverId);
        return DriverMapper.makeDriverDTO(updated);
    }

    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
            throws ConstraintsViolationException, EntityNotFoundException {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }

    @GetMapping(value = "/gas_car")
    public List<DriverDTO> findDriversWithGasCar(@RequestParam OnlineStatus onlineStatus)
            throws ConstraintsViolationException, EntityNotFoundException {

        List<DriverDTO> drivers = DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
        
        AndCriteria andCriteria = new AndCriteria(new DriversHavingCar(), new GasCar());
        return andCriteria.meetCriteria(drivers);
    }
    
    @GetMapping(value = "/electric_car")
    public List<DriverDTO> findDriversWithElectricCar(@RequestParam OnlineStatus onlineStatus)
            throws ConstraintsViolationException, EntityNotFoundException {

        List<DriverDTO> drivers = DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
        
        AndCriteria andCriteria = new AndCriteria(new DriversHavingCar(), new ElectricCar());
        return andCriteria.meetCriteria(drivers);
    }
    
    @GetMapping(value = "/five_seat_car")
    public List<DriverDTO> findDriversWithFiveSeatCar(@RequestParam OnlineStatus onlineStatus)
            throws ConstraintsViolationException, EntityNotFoundException {

        List<DriverDTO> drivers = DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
        
        AndCriteria andCriteria = new AndCriteria(new DriversHavingCar(), new FiveSeatCar());
        return andCriteria.meetCriteria(drivers);
    }

}
