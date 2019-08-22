package com.lighthouse.resultautomation.repository;

import com.lighthouse.resultautomation.model.ElasticUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserElasticRepository extends ElasticsearchRepository<ElasticUser, String> {
}
