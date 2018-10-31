package com.onurtokat.services;

import com.onurtokat.domain.DataDomain;

import java.util.List;

/** DataService interface for customized abstract methods
 *
 * @author onurtokat
 */
public interface DataService {

    // Select all data from mongodb dataDomain document
    List<DataDomain> listAll();
}
