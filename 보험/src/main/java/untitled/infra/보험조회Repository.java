package untitled.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

@RepositoryRestResource(collectionResourceRel = "보험조회", path = "보험조회")
public interface 보험조회Repository
    extends PagingAndSortingRepository<보험조회, Long> {}
