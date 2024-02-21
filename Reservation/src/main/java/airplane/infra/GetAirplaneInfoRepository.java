package airplane.infra;

import airplane.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "getAirplaneInfos",
    path = "getAirplaneInfos"
)
public interface GetAirplaneInfoRepository
    extends PagingAndSortingRepository<GetAirplaneInfo, Long> {}
