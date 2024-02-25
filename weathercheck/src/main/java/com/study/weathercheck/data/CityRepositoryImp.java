package com.study.weathercheck.data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.study.weathercheck.domain.City;
import com.study.weathercheck.domain.Status;

@Repository
public class CityRepositoryImp implements CityRepository {
    private final MongoTemplate mongoTemplate;

    public CityRepositoryImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(City city) {
        mongoTemplate.save(city);
    }

    @Override
    public List<City> findAll() {
        return mongoTemplate.findAll(City.class);
    }

    @Override
    public City findNextToProcess() {
        LocalDateTime dateLimit = LocalDateTime.now().minusMinutes(5);

        Criteria criteria = new Criteria();

        criteria.orOperator(Criteria.where("status").is(Status.Pending),
                Criteria.where("status").is(Status.Checked).and("lastCheck").lt(dateLimit));

        Query query = new Query(criteria);
        Update update = new Update().set("status", Status.Checking);

        return mongoTemplate.findAndModify(query, update, City.class);
    }
}
