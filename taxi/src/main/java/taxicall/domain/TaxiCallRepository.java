package taxicall.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import taxicall.domain.*;

@RepositoryRestResource(collectionResourceRel = "taxiCalls", path = "taxiCalls")
public interface TaxiCallRepository
    extends PagingAndSortingRepository<TaxiCall, Long> {}
