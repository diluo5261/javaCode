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
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest {

    @Autowired
    RestHighLevelClient client;


    @Test
    public void contextLoads() {
        System.out.println(client);
    }

    //创建一个索引
    @Test
    public void createIndex() throws IOException {
        //1.创建请求对象
        CreateIndexRequest request = new CreateIndexRequest("twitter");

        //2.执行同步请求创建索引
        //RequestOptions.DEFAULT 默认请求头信息
        CreateIndexResponse createIndexResponse =
                client.indices().create(request, RequestOptions.DEFAULT);

        //3.打印执行结果
        boolean acknowledged = createIndexResponse.isAcknowledged();
        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
        System.out.println("acknowledged = " + acknowledged);
        System.out.println("shardsAcknowledged = " + shardsAcknowledged);
    }

    /**
     * 添加索引并直接映射
     */
    @Test
    public void addIndexAndMapping() throws IOException {
        //1.使用client获取操作索引的对象
        IndicesClient indicesClient = client.indices();
        //2.具体操作，获取返回值
        CreateIndexRequest createRequest = new CreateIndexRequest("aaa");
        //2.1 设置mappings
        String mapping = "{\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"type\": \"long\",\n" +
                "      \"store\": true,\n" +
                "      \"index\": true\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"email\": {\n" +
                "      \"type\": \"text\",\n" +
                "      \"analyzer\": \"standard\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        createRequest.mapping(mapping, XContentType.JSON);
        CreateIndexResponse response = indicesClient.create(createRequest, RequestOptions.DEFAULT);
        //3.根据返回值判断结果
        System.out.println(response.isAcknowledged());
    }



    /**
     * 查询索引
     */
    @Test
    public void queryIndex() throws IOException {
        IndicesClient indices = client.indices();
        GetIndexRequest getReqeust = new GetIndexRequest("aaa");
        GetIndexResponse response = indices.get(getReqeust, RequestOptions.DEFAULT);
        //获取结果
        Map<String, MappingMetaData> mappings = response.getMappings();
        for (String key : mappings.keySet()) {
            System.out.println(key+":" + mappings.get(key).getSourceAsMap());
        }
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() throws IOException {
        IndicesClient indices = client.indices();
        DeleteIndexRequest deleteRequest = new DeleteIndexRequest("aaa");
        AcknowledgedResponse response = indices.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }



    /**
     * 判断索引是否存在
     */
    @Test
    public void existIndex() throws IOException {
        IndicesClient indices = client.indices();
        GetIndexRequest getRequest = new GetIndexRequest("twitter");
        boolean exists = indices.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }



    //========以下是文档操作====================================================

    /**
     * 添加文档,使用map作为数据
     */
    @Test
    public void addDoc() throws IOException {
        //数据对象，map
        Map data = new HashMap();
        data.put("address","深圳宝安");
        data.put("name","尚硅谷");
        data.put("age",20);

        //1.获取操作文档的对象
        IndexRequest request = new IndexRequest("aaa").id("1").source(data);
        //添加数据，获取结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        //打印响应结果
        System.out.println(response.getId());
    }



    /**
     * 添加文档,使用对象作为数据
     */
    @Test
    public void addDoc2() throws IOException {
        //数据对象，javaObject
        Person p = new Person();
        p.setId("2");
        p.setName("硅谷2222");
        p.setAge(30);
        p.setAddress("北京昌平区");

        //将对象转为json
        String data = JSON.toJSONString(p);

        //1.获取操作文档的对象
        IndexRequest request = new IndexRequest("aaa").id(p.getId()).source(data,XContentType.JSON);
        //添加数据，获取结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        //打印响应结果
        System.out.println(response.getId());
    }


    /**
     * 修改文档：添加文档时，如果id存在则修改，id不存在则添加
     */
    @Test
    public void updateDoc() throws IOException {
        //数据对象，javaObject
        Person p = new Person();
        p.setId("2");
        p.setName("硅谷");
        p.setAge(31);
        p.setAddress("北京昌平区1111");

        //将对象转为json
        String data = JSON.toJSONString(p);

        //1.获取操作文档的对象
        IndexRequest request = new IndexRequest("aaa").id(p.getId()).source(data,XContentType.JSON);
        //添加数据，获取结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        //打印响应结果
        System.out.println(response.getId());
    }


    /**
     * 根据id查询文档
     */
    @Test
    public void findDocById() throws IOException {
        GetRequest getReqeust = new GetRequest("aaa","1");
        //getReqeust.id("1");
        GetResponse response = client.get(getReqeust, RequestOptions.DEFAULT);
        //获取数据对应的json
        System.out.println(response.getSourceAsString());
    }


    /**
     * 根据id删除文档
     */
    @Test
    public void delDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("aaa","1");
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(response.getId());
        System.out.println(response.getResult());
    }



    /**
     * 1. 批量操作 bulk
     */
    @Test
    public void testBulk() throws IOException {
        //创建bulkrequest对象，整合所有操作
        BulkRequest bulkRequest = new BulkRequest();

        /*
        1. 删除1号记录
        2. 添加6号记录
        3. 修改3号记录 名称为 “三号”
         */
        //添加对应操作
        //1. 删除1号记录
        DeleteRequest deleteRequest = new DeleteRequest("person","1");
        bulkRequest.add(deleteRequest);

        //2. 添加6号记录
        Map map = new HashMap();
        map.put("name","六号");
        IndexRequest indexRequest = new IndexRequest("person").id("6").source(map);
        bulkRequest.add(indexRequest);

        Map map2 = new HashMap();
        map2.put("name","三号");
        //3. 修改3号记录 名称为 “三号”
        UpdateRequest updateReqeust = new UpdateRequest("person","3").doc(map2);
        bulkRequest.add(updateReqeust);

        //执行批量操作
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        RestStatus status = response.status();
        System.out.println(status);
    }


    //==========测试：将MySQL数据库 goods表的数据 批量  导入到Elasticsearch索引库中========================================
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void report() throws IOException {
        //1.查询MySQL数据库goods表所有数据
        List<Goods> all = goodsMapper.findAll();

        //2.创建批量请求对象
        BulkRequest bulkRequest = new BulkRequest();

        for (Goods goods : all) {
            //做特殊化处理
            String specStr = goods.getSpecStr(); //{"机身内存":"16G","网络":"移动3G"}
            Map spec = JSON.parseObject(specStr, Map.class);
            goods.setSpec(spec);
            goods.setSpecStr(null);

            //将实体对象转换成json串
            String jsonString = JSON.toJSONString(goods);

            IndexRequest indexRequest =
                    new IndexRequest("goods").id(goods.getId()+"").source(jsonString,XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        //执行批量操作
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        RestStatus status = response.status();
        System.out.println(status);
    }



}
