package com.onurtokat.repositories;

import com.onurtokat.domain.DataDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** DataRepository extends CrudRepository for default spring data
 * methods
 *
 * @author onurtokat
 */
@Repository
public interface DataRepository extends CrudRepository<DataDomain, String> {
}
