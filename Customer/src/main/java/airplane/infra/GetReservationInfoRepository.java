package airplane.infra;

import airplane.domain.*;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "customers",
    path = "customers"
)
public interface GetReservationInfoRepository extends PagingAndSortingRepository<GetReservationInfo, Long> {
    Optional<GetReservationInfo> findByReservId(Long reservId);
}
