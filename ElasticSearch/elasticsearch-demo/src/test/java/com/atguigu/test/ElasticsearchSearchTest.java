package com.atguigu.test;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.Person;
import com.atguigu.dao.GoodsMapper;
import com.atguigu.domain.Goods;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchSearchTest {

    @Autowired
    RestHighLevelClient client;

    /**
     * ????????????
     * 1. matchAll
     * 2. ????????????????????????Goods??????????????????List???
     * 3. ?????????????????????10???
     */
    @Test
    public void testMatchAll() throws IOException {

        //2.????????????????????????
        SearchRequest searchRequest = new SearchRequest("goods");

        //3.????????????build??????
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //4.?????????????????????????????????
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        //5.??????????????????
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.query(matchAllQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        //1.????????????????????????????????????????????????????????????
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println("searchResponse = " + searchResponse);

        //6.?????????????????????
        SearchHits searchHits = searchResponse.getHits();
        long totalHits = searchHits.getTotalHits().value;
        System.out.println("???????????? = " + totalHits);

        SearchHit[] hits = searchHits.getHits(); //??????????????????

        List<Goods> list = new ArrayList<Goods>();

        //7.???es??????????????????????????????bean??????
        for (SearchHit hit : hits) {
            String sourceData = hit.getSourceAsString(); //json????????????

            Goods goods = JSON.parseObject(sourceData,Goods.class);

            list.add(goods);
        }

        //8.??????????????????
        for (Goods goods : list) {
            System.out.println("goods = " + goods);
        }
    }



    /**
     * termQuery:????????????
     */
    @Test
    public void testTermQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("goods");

        SearchSourceBuilder sourceBulider = new SearchSourceBuilder();

        QueryBuilder query = QueryBuilders.termQuery("title","??????");//term????????????
        sourceBulider.query(query);

        searchRequest.source(sourceBulider);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits searchHits = searchResponse.getHits();
        //???????????????
        long value = searchHits.getTotalHits().value;
        System.out.println("???????????????"+value);

        List<Goods> goodsList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            //??????java
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            goodsList.add(goods);
        }

        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }



    /**
     * matchQuery:????????????????????????????????????????????????
     */
    @Test
    public void testMatchQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder sourceBulider = new SearchSourceBuilder();

        MatchQueryBuilder query = QueryBuilders.matchQuery("title", "????????????");
        query.operator(Operator.AND);//?????????
        sourceBulider.query(query);

        searchRequest.source(sourceBulider);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits searchHits = searchResponse.getHits();
        //???????????????
        long value = searchHits.getTotalHits().value;
        System.out.println("???????????????"+value);

        List<Goods> goodsList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            //??????java
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            goodsList.add(goods);
        }

        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }


    /**
     * ????????????:WildcardQuery
     */
    @Test
    public void testWildcardQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder sourceBulider = new SearchSourceBuilder();

        WildcardQueryBuilder query = QueryBuilders.wildcardQuery("title", "???*");
        sourceBulider.query(query);
        searchRequest.source(sourceBulider);


        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = searchResponse.getHits();
        //???????????????
        long value = searchHits.getTotalHits().value;
        System.out.println("???????????????"+value);
        List<Goods> goodsList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            //??????java
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            goodsList.add(goods);
        }
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }


    /**
     * ????????????:perfixQuery
     */
    @Test
    public void testPrefixQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder sourceBulider = new SearchSourceBuilder();
        PrefixQueryBuilder query = QueryBuilders.prefixQuery("brandName", "???");
        sourceBulider.query(query);
        searchRequest.source(sourceBulider);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits searchHits = searchResponse.getHits();
        //???????????????
        long value = searchHits.getTotalHits().value;
        System.out.println("???????????????"+value);

        List<Goods> goodsList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            //??????java
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            goodsList.add(goods);
        }

        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }


    /**
     * 1. ???????????????rangeQuery
     * 2. ??????
     */
    @Test
    public void testRangeQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder sourceBulider = new SearchSourceBuilder();
        //????????????
        RangeQueryBuilder query = QueryBuilders.rangeQuery("price");

        //???????????? gte????????????
        query.gte(2700);
        //???????????? ????????????
        query.lte(3000);

        sourceBulider.query(query);

        //??????
        sourceBulider.sort("price", SortOrder.DESC);
        searchRequest.source(sourceBulider);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits searchHits = searchResponse.getHits();
        //???????????????
        long value = searchHits.getTotalHits().value;
        System.out.println("???????????????"+value);

        List<Goods> goodsList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            //??????java
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            goodsList.add(goods);
        }
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }


    /**
     * queryString
     */
    @Test
    public void testQueryStringQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder sourceBulider = new SearchSourceBuilder();

        //queryString
        QueryStringQueryBuilder query = QueryBuilders.queryStringQuery("????????????")
                .field("title")
                .field("categoryName")
                .field("brandName")
                .defaultOperator(Operator.AND);

        sourceBulider.query(query);
        searchRequest.source(sourceBulider);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits searchHits = searchResponse.getHits();
        //???????????????
        long value = searchHits.getTotalHits().value;
        System.out.println("???????????????"+value);

        List<Goods> goodsList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            //??????java
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            goodsList.add(goods);
        }

        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }



    /**
     * ???????????????boolQuery
     * 1. ?????????????????????:??????
     * 2. ???????????????????????????
     * 3. ??????????????????2000-3000
     */
    @Test
    public void testBoolQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder sourceBulider = new SearchSourceBuilder();

        //1.??????boolQuery
        BoolQueryBuilder query = QueryBuilders.boolQuery();

        //2.????????????????????????
        //2.1 ?????????????????????:??????
        QueryBuilder termQuery = QueryBuilders.termQuery("brandName","??????");
        query.must(termQuery);

        //2.2. ???????????????????????????
        QueryBuilder matchQuery = QueryBuilders.matchQuery("title","??????");
        query.filter(matchQuery);

        //2.3 ??????????????????2000-3000
        QueryBuilder rangeQuery = QueryBuilders.rangeQuery("price");
        ((RangeQueryBuilder) rangeQuery).gte(2000);
        ((RangeQueryBuilder) rangeQuery).lte(3000);
        query.filter(rangeQuery);

        //3.??????boolQuery??????
        sourceBulider.query(query);
        searchRequest.source(sourceBulider);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = searchResponse.getHits();
        //???????????????
        long value = searchHits.getTotalHits().value;
        System.out.println("???????????????"+value);

        List<Goods> goodsList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            //??????java
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            goodsList.add(goods);
        }

        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }



    /**
     * ???????????????????????????????????????
     * 1. ??????title?????????????????????
     * 2. ??????????????????
     */
    @Test
    public void testAggQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder sourceBulider = new SearchSourceBuilder();
        // 1. ??????title?????????????????????
        MatchQueryBuilder query = QueryBuilders.matchQuery("title", "??????");

        sourceBulider.query(query);
        // 2. ??????????????????
        /* ?????????
            1. ?????????????????????????????????????????????
            2. ???????????????
         */
        AggregationBuilder agg = AggregationBuilders.terms("goods_brands").field("brandName").size(10);
        sourceBulider.aggregation(agg);

        searchRequest.source(sourceBulider);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = searchResponse.getHits();
        //???????????????
        long value = searchHits.getTotalHits().value;
        System.out.println("???????????????"+value);

        List<Goods> goodsList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            //??????java
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            goodsList.add(goods);
        }

        for (Goods goods : goodsList) {
            System.out.println(goods);
        }

        // ??????????????????
        Aggregations aggregations = searchResponse.getAggregations();
        Map<String, Aggregation> aggregationMap = aggregations.asMap();

        //System.out.println(aggregationMap);
        Terms goods_brands = (Terms) aggregationMap.get("goods_brands");
        List<? extends Terms.Bucket> buckets = goods_brands.getBuckets();

        List brands = new ArrayList();
        for (Terms.Bucket bucket : buckets) {
            Object key = bucket.getKey();
            brands.add(key);
        }

        for (Object brand : brands) {
            System.out.println(brand);
        }
    }


    /**
     *
     * ???????????????
     *  1. ????????????
     *      * ????????????
     *      * ??????
     *      * ??????
     *  2. ????????????????????????????????????????????????
     */
    @Test
    public void testHighLightQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder sourceBulider = new SearchSourceBuilder();

        // 1. ??????title?????????????????????
        MatchQueryBuilder query = QueryBuilders.matchQuery("title", "??????");
        sourceBulider.query(query);

        //????????????
        HighlightBuilder highlighter = new HighlightBuilder();
        //???????????????
        highlighter.field("title");
        highlighter.preTags("<font color='red'>");
        highlighter.postTags("</font>");
        sourceBulider.highlighter(highlighter);

        // 2. ??????????????????
        /*
        ?????????
            1. ?????????????????????????????????????????????
            2. ???????????????
         */
        AggregationBuilder agg = AggregationBuilders.terms("goods_brands").field("brandName").size(100);
        sourceBulider.aggregation(agg);

        searchRequest.source(sourceBulider);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits searchHits = searchResponse.getHits();
        //???????????????
        long value = searchHits.getTotalHits().value;
        System.out.println("???????????????"+value);

        List<Goods> goodsList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            //??????java
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            // ???????????????????????????goods??????title
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField HighlightField = highlightFields.get("title");
            Text[] fragments = HighlightField.fragments();
            //??????
            goods.setTitle(fragments[0].toString());
            goodsList.add(goods);
        }

        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }



}