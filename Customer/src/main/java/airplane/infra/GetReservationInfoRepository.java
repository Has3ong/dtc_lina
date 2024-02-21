package airplane.infra;

import airplane.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "getReservationInfos",
    path = "getReservationInfos"
)
public interface GetReservationInfoRepository
    extends PagingAndSortingRepository<GetReservationInfo, Long> {}
