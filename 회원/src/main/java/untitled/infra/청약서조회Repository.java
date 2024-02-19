package untitled.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "청약서조회",
    path = "청약서조회"
)
public interface 청약서조회Repository
    extends PagingAndSortingRepository<청약서조회, Long> {}
