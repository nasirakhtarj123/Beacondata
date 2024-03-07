package beacon.beacon.api.service;

import beacon.beacon.api.model.BeaconData;
import beacon.beacon.api.repository.BeaconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BeaconService {

    private final BeaconRepository beaconRepository;

    @Autowired
    public BeaconService(BeaconRepository beaconRepository) {
        this.beaconRepository = beaconRepository;
    }

    public List<BeaconData> getAllBeaconData() {
        return beaconRepository.findAll();
    }

    public BeaconData saveBeaconData(BeaconData beaconData) {
        return beaconRepository.save(beaconData);
    }

    public BeaconData getBeaconById(Long id) {
        Optional<BeaconData> optionalBeaconData = beaconRepository.findById(id);
        return optionalBeaconData.orElse(null);
    }

    public BeaconData updateBeaconData(BeaconData beaconData) {
        // Ensure the beacon data exists in the database before updating
        if (beaconData.getId() != null && beaconRepository.existsById(beaconData.getId())) {
            return beaconRepository.save(beaconData);
        }
        return null; // If beacon data does not exist, return null or throw an exception as needed
    }

    public boolean deleteBeaconById(Long id) {
        // Check if the beacon with given ID exists
        if (beaconRepository.existsById(id)) {
            beaconRepository.deleteById(id);
            return true;
        }
        return false; // If beacon does not exist, return false
    }
}
