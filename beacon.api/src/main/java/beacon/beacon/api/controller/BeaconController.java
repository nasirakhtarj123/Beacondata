package beacon.beacon.api.controller;

import beacon.beacon.api.dto.BeaconDTO;
import beacon.beacon.api.model.BeaconData;
import beacon.beacon.api.service.BeaconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beacon")
public class BeaconController {

    private final BeaconService beaconService;

    @Autowired
    public BeaconController(BeaconService beaconService) {
        this.beaconService = beaconService;
    }

    @GetMapping
    public ResponseEntity<List<BeaconData>> getAllBeaconData() {
        List<BeaconData> beaconDataList = beaconService.getAllBeaconData();
        return new ResponseEntity<>(beaconDataList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeaconData> getBeaconById(@PathVariable Long id) {
        BeaconData beaconData = beaconService.getBeaconById(id);
        if (beaconData != null) {
            return new ResponseEntity<>(beaconData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BeaconData> saveBeaconData(@RequestBody BeaconDTO beaconDTO) {
        BeaconData beaconData = mapDTOToEntity(beaconDTO);

        BeaconData savedBeaconData = beaconService.saveBeaconData(beaconData);

        return new ResponseEntity<>(savedBeaconData, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeaconData> updateBeaconData(@PathVariable Long id, @RequestBody BeaconDTO beaconDTO) {
        BeaconData existingBeaconData = beaconService.getBeaconById(id);
        if (existingBeaconData != null) {
            BeaconData updatedBeaconData = mapDTOToEntity(beaconDTO);
            updatedBeaconData.setId(existingBeaconData.getId());
            BeaconData savedBeaconData = beaconService.saveBeaconData(updatedBeaconData);
            return new ResponseEntity<>(savedBeaconData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeaconData(@PathVariable Long id) {
        boolean deleted = beaconService.deleteBeaconById(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private BeaconData mapDTOToEntity(BeaconDTO beaconDTO) {
        BeaconData beaconData = new BeaconData();
        // Map properties from DTO to entity
        beaconData.setMobile_uwb_support(beaconDTO.getMobile_uwb_support());
        beaconData.setBeacon_average_rssi(beaconDTO.getBeacon_average_rssi());
        beaconData.setPermission_battery_percent(beaconDTO.getPermission_battery_percent());
        beaconData.setDate(beaconDTO.getDate());
        beaconData.setBeacon_proximity(beaconDTO.getBeacon_proximity());
        beaconData.setBeacon_major(beaconDTO.getBeacon_major());
        beaconData.setPermission_bg_location_status(beaconDTO.getPermission_bg_location_status());
        beaconData.setMobile_os(beaconDTO.getMobile_os());
        beaconData.setBeacon_mac_address(beaconDTO.getBeacon_mac_address());
        beaconData.setBeacon_rssi(beaconDTO.getBeacon_rssi());
        beaconData.setNotes(beaconDTO.getNotes());
        beaconData.setMobile_device_temperature(beaconDTO.getMobile_device_temperature());
        beaconData.setMobile_manufacturer(beaconDTO.getMobile_manufacturer());
        beaconData.setBeacon_range(beaconDTO.getBeacon_range());
        beaconData.setPermission_location_status(beaconDTO.getPermission_location_status());
        beaconData.setMobile_internet_speed(beaconDTO.getMobile_internet_speed());
        beaconData.setMobile_connection_type(beaconDTO.getMobile_connection_type());
        beaconData.setBeacon_uuid(beaconDTO.getBeacon_uuid());
        beaconData.setPermission_gps_status(beaconDTO.getPermission_gps_status());
        beaconData.setMobile_brand(beaconDTO.getMobile_brand());
        beaconData.setMode(beaconDTO.getMode());
        beaconData.setBeacon_minor(beaconDTO.getBeacon_minor());
        beaconData.setUser_id(beaconDTO.getUser_id());
        beaconData.setMobile_model(beaconDTO.getMobile_model());
        beaconData.setBluetooth_version(beaconDTO.getBluetooth_version());
        beaconData.setMobile_device_type(beaconDTO.getMobile_device_type());
        beaconData.setMobile_nfc_support(beaconDTO.getMobile_nfc_support());
        beaconData.setApp_ver_no(beaconDTO.getApp_ver_no());
        beaconData.setPermission_bluetooth_status(beaconDTO.getPermission_bluetooth_status());
        beaconData.setLocation(beaconDTO.getLocation());
        return beaconData;
    }
}
