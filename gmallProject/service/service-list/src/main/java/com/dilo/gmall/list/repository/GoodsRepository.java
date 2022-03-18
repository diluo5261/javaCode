package com.dilo.gmall.list.repository;

import com.dilo.gmall.model.list.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface GoodsRepository extends ElasticsearchCrudRepository<Goods,Long> {
}
