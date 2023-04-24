package taxicall.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import taxicall.domain.*;

@RepositoryRestResource(collectionResourceRel = "calls", path = "calls")
public interface CallRepository
    extends PagingAndSortingRepository<Call, Long> {}
