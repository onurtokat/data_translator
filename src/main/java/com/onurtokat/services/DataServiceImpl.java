package com.onurtokat.services;

import com.onurtokat.domain.DataDomain;
import com.onurtokat.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** DataServiceImpl implements DataService and
 * fills abstract methods for operations
 *
 * @author onurtokat
 */
@Service
public class DataServiceImpl implements DataService{

    @Autowired
    MongoTemplate mongoTemplate;

    private DataRepository dataRepository;

    @Autowired
    public DataServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    /** listAll method collects whole data from dataDomain
     * mongo db document
     *
     * @return whole data as DataDomain types List
     */
    @Override
    public List<DataDomain> listAll() {
        List<DataDomain> dataDomains = new ArrayList<>();
        dataRepository.findAll().forEach(dataDomains::add);
        return dataDomains;
    }
}
