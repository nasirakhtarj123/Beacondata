package beacon.beacon.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import beacon.beacon.api.model.BeaconData;

public interface BeaconRepository extends JpaRepository<BeaconData, Long> {
}