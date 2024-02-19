package untitled.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "청약정보조회",
    path = "청약정보조회"
)
public interface 청약정보조회Repository
    extends PagingAndSortingRepository<청약정보조회, Long> {}
