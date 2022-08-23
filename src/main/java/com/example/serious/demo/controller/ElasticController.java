package com.example.serious.demo.controller;

import com.example.serious.demo.domain.UserEs;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ElasticController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @RequestMapping("/elasticsearch/add")
    public String add() throws IOException {
        /**
         * 向ES中的索引christy下的type类型中添加一天文档
         */
        IndexRequest indexRequest = new IndexRequest("christy","user","11");
        indexRequest.source("{\"name\":\"齐天大圣孙悟空\",\"age\":685,\"bir\":\"1685-01-01\",\"introduce\":\"花果山水帘洞美猴王齐天大圣孙悟空是也！\"," +
                "\"address\":\"花果山\"}", XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.status().toString();
    }

    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("christy","user","p4AtG3kBRz-Sn-2fMFjj");
        updateRequest.doc("{\"name\":\"调皮捣蛋的hardy\"}",XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }

    public void deleteDoc() throws IOException {
        // 我们把特朗普删除了
        DeleteRequest deleteRequest = new DeleteRequest("christy","user","rYBNG3kBRz-Sn-2f3ViU");
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }

    public void bulkUpdate() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        // 添加
        IndexRequest indexRequest = new IndexRequest("christy","user","13");
        indexRequest.source("{\"name\":\"天蓬元帅猪八戒\",\"age\":985,\"bir\":\"1685-01-01\",\"introduce\":\"天蓬元帅猪八戒因调戏嫦娥被贬下凡\",\"address\":\"高老庄\"}", XContentType.JSON);
        bulkRequest.add(indexRequest);

        // 删除
        DeleteRequest deleteRequest01 = new DeleteRequest("christy","user","pYAtG3kBRz-Sn-2fMFjj");
        DeleteRequest deleteRequest02 = new DeleteRequest("christy","user","uhTyGHkBExaVQsl4F9Lj");
        DeleteRequest deleteRequest03 = new DeleteRequest("christy","user","C8zCGHkB5KgTrUTeLyE_");
        bulkRequest.add(deleteRequest01);
        bulkRequest.add(deleteRequest02);
        bulkRequest.add(deleteRequest03);

        // 修改
        UpdateRequest updateRequest = new UpdateRequest("christy","user","10");
        updateRequest.doc("{\"name\":\"炼石补天的女娲\"}",XContentType.JSON);
        bulkRequest.add(updateRequest);

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        BulkItemResponse[] items = bulkResponse.getItems();
        for (BulkItemResponse item : items) {
            System.out.println(item.status());
        }
    }

    public void testSearch() throws IOException {
        //创建搜索对象
        SearchRequest searchRequest = new SearchRequest("christy");
        //搜索构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery())//执行查询条件
                .from(0)//起始条数
                .size(10)//每页展示记录
                .postFilter(QueryBuilders.matchAllQuery()) //过滤条件
                .sort("age", SortOrder.DESC);//排序

        //创建搜索请求
        searchRequest.types("user").source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println("符合条件的文档总数: "+searchResponse.getHits().getTotalHits());
        System.out.println("符合条件的文档最大得分: "+searchResponse.getHits().getMaxScore());
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    public void testHighLightQuery() throws IOException, ParseException {
        // 创建搜索请求
        SearchRequest searchRequest = new SearchRequest("christy");
        // 创建搜索对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("introduce", "唐僧"))    // 设置查询条件
                .from(0)    // 起始条数(当前页-1)*size的值
                .size(10)   // 每页展示条数
                .sort("age", SortOrder.DESC)    // 排序
                .highlighter(new HighlightBuilder().field("*").requireFieldMatch(false).preTags("<span style='color:red;'>").postTags("</span>"));  // 设置高亮
        searchRequest.types("user").source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHit[] hits = searchResponse.getHits().getHits();
        List<UserEs> userList = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            UserEs user = new UserEs();
            user.setId(hit.getId());
            user.setAge(Integer.parseInt(sourceAsMap.get("age").toString()));
            user.setBir(new SimpleDateFormat("yyyy-MM-dd").parse(sourceAsMap.get("bir").toString()));
            user.setIntroduce(sourceAsMap.get("introduce").toString());
            user.setName(sourceAsMap.get("name").toString());
            user.setAddress(sourceAsMap.get("address").toString());

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(highlightFields.containsKey("name")){
                user.setName(highlightFields.get("name").fragments()[0].toString());
            }

            if(highlightFields.containsKey("introduce")){
                user.setIntroduce(highlightFields.get("introduce").fragments()[0].toString());
            }

            if(highlightFields.containsKey("address")){
                user.setAddress(highlightFields.get("address").fragments()[0].toString());
            }

            userList.add(user);
        }

        userList.forEach(user -> System.out.println(user));
    }
}
