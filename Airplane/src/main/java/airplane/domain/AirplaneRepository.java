package airplane.domain;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel="airplanes", path="airplanes")
public interface AirplaneRepository extends PagingAndSortingRepository<Airplane, Long>{
    Optional<Airplane> findByAirPlaneId(Long airPlaneId);
}