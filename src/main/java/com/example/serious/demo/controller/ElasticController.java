package com.example.serious.demo.controller;

import com.example.serious.demo.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.pipeline.BucketMetricsPipelineAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/elasticsearch")
@Slf4j
public class ElasticController {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    @RequestMapping("/add")
//    public String add() throws IOException {
//        /**
//         * 向ES中的索引christy下的type类型中添加一天文档
//         */
//        IndexRequest indexRequest = new IndexRequest("christy", "user", "13");
//        indexRequest.source("{\"name\":\"天蓬元帅猪八戒\",\"age\":985,\"bir\":\"1685-01-01\",\"introduce\":\"天蓬元帅猪八戒因调戏嫦娥被贬下凡\",\"address\":\"高老庄\"}", XContentType.JSON);
//        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//
//
//
//        return indexResponse.status().toString();
//    }
//
//    @RequestMapping("/updateDoc")
//    public void updateDoc() throws IOException {
//        UpdateRequest updateRequest = new UpdateRequest("christy","user","13");
//        updateRequest.doc("{\"name\":\"调皮捣蛋的hardy\"}",XContentType.JSON);
//        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
//        System.out.println(updateResponse.status());
//    }
//
//    @RequestMapping("/deleteDoc")
//    public void deleteDoc() throws IOException {
//        // 我们把特朗普删除了
//        DeleteRequest deleteRequest = new DeleteRequest("christy","user","rYBNG3kBRz-Sn-2f3ViU");
//        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
//        System.out.println(deleteResponse.status());
//    }
//
//    @RequestMapping("/bulkUpdate")
//    public void bulkUpdate() throws IOException {
//        BulkRequest bulkRequest = new BulkRequest();
//        // 添加
//        IndexRequest indexRequest = new IndexRequest("christy","user","13");
//        indexRequest.source("{\"name\":\"天蓬元帅猪八戒\",\"age\":985,\"bir\":\"1685-01-01\",\"introduce\":\"天蓬元帅猪八戒因调戏嫦娥被贬下凡\",\"address\":\"高老庄\"}", XContentType.JSON);
//        bulkRequest.add(indexRequest);
//
//        // 删除
//        DeleteRequest deleteRequest01 = new DeleteRequest("christy","user","pYAtG3kBRz-Sn-2fMFjj");
//        DeleteRequest deleteRequest02 = new DeleteRequest("christy","user","uhTyGHkBExaVQsl4F9Lj");
//        DeleteRequest deleteRequest03 = new DeleteRequest("christy","user","C8zCGHkB5KgTrUTeLyE_");
//        bulkRequest.add(deleteRequest01);
//        bulkRequest.add(deleteRequest02);
//        bulkRequest.add(deleteRequest03);
//
//        // 修改
//        UpdateRequest updateRequest = new UpdateRequest("christy","user","10");
//        updateRequest.doc("{\"name\":\"炼石补天的女娲\"}", XContentType.JSON);
//        bulkRequest.add(updateRequest);
//
//        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//        BulkItemResponse[] items = bulkResponse.getItems();
//        for (BulkItemResponse item : items) {
//            System.out.println(item.status());
//        }
//    }

    @GetMapping("/save")
    public String save(String name, String sex, Integer age, String grade) {
        UserEntity esEntity = new UserEntity(null, name, sex, age, grade);

//        不要拿到自增的返回值
//        UserEsEntity save = elasticsearchRestTemplate.save(esEntity);
//        log.info(save.toString());

//        拿到自增的返回值
        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId(esEntity.getId())
                .withObject(esEntity)
                .build();
        String id = elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of("user"));
        log.info("添加的id：{} ", id);

//        批量添加
//        List<UserEsEntity> list = new ArrayList<>();
//        list.add(esEntity);
//        list.add(esEntity);
//        elasticsearchRestTemplate.save(list);
        return "success";
    }


    @GetMapping("/indexExists")
    public String indexExists() {
        IndexOperations indexOps = elasticsearchRestTemplate.indexOps(UserEntity.class);
        boolean r1 = indexOps.exists();
//		创建索引
//      elasticsearchRestTemplate.createIndex()
        log.info("r1: {} ", r1);
        return "success";
    }

    @GetMapping("/search")
    public String search() throws ClassNotFoundException {
//        查询全部数据
//        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

//        精确查询 =
//        QueryBuilder queryBuilder = QueryBuilders.termQuery("name", "lisi");

//        精确查询 多个 in
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.fuzzyQuery("name", "小女友"));

//        match匹配，会把查询条件进行分词，然后进行查询，多个词条之间是 or 的关系,可以指定分词
//        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "张三");
//        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "张三").analyzer("ik_max_word");

//        match匹配 查询多个字段
//        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("男", "name", "sex");

//        fuzzy 模糊查询，返回包含与搜索字词相似的字词的文档。
//        QueryBuilder  queryBuilder = QueryBuilders.fuzzyQuery("name","lisx");

//        prefix 前缀检索
//        QueryBuilder  queryBuilder = QueryBuilders.prefixQuery("name","张");

//        wildcard 通配符检索
//        QueryBuilder  queryBuilder = QueryBuilders.wildcardQuery("name","张*");

//        regexp 正则查询
        //QueryBuilder queryBuilder = QueryBuilders.regexpQuery("name", "(李四)|(lisi)");

//        boost 评分权重,令满足某个条件的文档的得分更高，从而使得其排名更靠前。
        //queryBuilder.boost(2);
//        多条件构建
//        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//        并且 and
//        queryBuilder.must(QueryBuilders.termQuery("name", "张三"));
//        queryBuilder.must(QueryBuilders.termQuery("sex", "女"));

//        或者 or
//        queryBuilder.should(QueryBuilders.termQuery("name", "张三"));
//        queryBuilder.should(QueryBuilders.termQuery("name", "lisi"));

//        不等于,去除
//        queryBuilder.mustNot(QueryBuilders.termQuery("name", "lisi"));

//        过滤数据
//        queryBuilder.filter(QueryBuilders.matchQuery("name", "张三"));

//        范围查询
        /*
            gt 大于 >
            gte 大于等于 >=
            lt 小于 <
            lte 小于等于 <=
        */
//        queryBuilder.filter(new RangeQueryBuilder("age").gt(10).lte(50));

//        构建分页，page 从0开始
//        Pageable pageable = PageRequest.of(0, 3);
//
//        Query query = new NativeSearchQueryBuilder()
//                .withQuery(queryBuilder)
//                .withPageable(pageable)
//                //排序
//                .withSort(SortBuilders.fieldSort("_score").order(SortOrder.DESC))
//                //投影
//                .withFields("name")
//                .build();
//        SearchHits<UserEntity> search = elasticsearchRestTemplate.search(query, UserEntity.class);
//        log.info("total: {}", search.getTotalHits());
//        Stream<SearchHit<UserEntity>> searchHitStream = search.get();
//        List<UserEntity> list = searchHitStream.map(SearchHit::getContent).collect(Collectors.toList());
//        log.info("结果数量：{}", list.size());
//        list.forEach(entity -> {
//            log.info(entity.toString());
//        });
        //高亮查询
        //构建分页，page 从0开始
        Pageable pageable = PageRequest.of(0, 100);

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(pageable)
                //排序
                .withSort(SortBuilders.fieldSort("_score").order(SortOrder.DESC))
                //投影
                .withFields("age", "name", "grade", "sex")
                .withHighlightBuilder(new HighlightBuilder()
                        .postTags("</font>")
                        .preTags("<font color=\"red\">")
                        .field("name").requireFieldMatch(false).fragmentSize(20)
                )
                .addAggregation(AggregationBuilders.terms("group_by_age").field("age"))
                .build();
        printDSL(query);
        SearchHits<UserEntity> search = elasticsearchRestTemplate.search(query, UserEntity.class);
        //得到聚合
        Aggregations aggregations = search.getAggregations();
        //得到上面设置的分组
        Terms brandName = aggregations.get("group_by_age");
        //创建一个集合用来存放手机品牌
        ArrayList<String> collect = new ArrayList<>();
        //得到这个聚合的List对象
        List<? extends Terms.Bucket> buckets = brandName.getBuckets();
        //遍历这个List对象
        for (Terms.Bucket bucket : buckets) {
            //得到分组后的每一个值
            String keyAsString = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            //存入到集合中
            collect.add(keyAsString);
            log.info("key: {}  count: {}", keyAsString, docCount);
        }
        log.info("total: {}", search.getTotalHits());
        Stream<SearchHit<UserEntity>> searchHitStream = search.get();
        List<UserEntity> list = searchHitStream.map(SearchHit::getContent).collect(Collectors.toList());
        log.info("结果数量：{}", list.size());
        list.forEach(entity -> {
            log.info(entity.toString());
        });
        return "success";
    }


    @GetMapping("/delete")
    public String delete() {
//        根据id删除
//        String r = elasticsearchRestTemplate.delete("XNMVD34BVYNyxUnr8cdE", IndexCoordinates.of("user"));
//        log.info("r : {} ", r);

//        根据条件删除
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//        并且 and
        //queryBuilder.must(QueryBuilders.termQuery("name", "zhangsan"));
        queryBuilder.must(QueryBuilders.termQuery("sex", "女"));
        Query query = new NativeSearchQuery(queryBuilder);
        elasticsearchRestTemplate.delete(query, UserEntity.class, IndexCoordinates.of("user"));

        return "success";
    }

    private void printDSL(Query query) throws ClassNotFoundException {
        Method searchRequest = ReflectionUtils.findMethod(Class.forName("org.springframework.data.elasticsearch.core.RequestFactory"), "searchRequest", Query.class, Class.class, IndexCoordinates.class);
        searchRequest.setAccessible(true);
        Object o = ReflectionUtils.invokeMethod(searchRequest, elasticsearchRestTemplate.getRequestFactory(), query, UserEntity.class, elasticsearchRestTemplate.getIndexCoordinatesFor(UserEntity.class));

        Field source = ReflectionUtils.findField(Class.forName("org.elasticsearch.action.search.SearchRequest"), "source");
        source.setAccessible(true);
        Object s = ReflectionUtils.getField(source, o);
        log.info("dsl:{}", s);
    }
}
