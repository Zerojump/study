package com.cmy;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by Lankidd on 2017/3/8.
 */
public class ElasticsearchDemoTest {

    private static final String company = "company";
    private static final String employee = "employee";
    private static final String name = "name";
    private static final String age = "age";
    private static final String position = "position";
    private static final String country = "country";
    private static final String joinDate = "join_date";
    private static final String salary = "salary";


    private static final TransportClient client;

    static {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                //.put("client.transport.sniff", true)
                .build();
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.38.130"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }

    @Test
    public void prepareData() throws Exception {
        client.prepareIndex(company, employee, "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field(name, "jack")
                        .field(age, "27")
                        .field(position, "linux")
                        .field(country, "china")
                        .field(joinDate, "2017-01-01")
                        .field(salary, 10000)
                        .endObject())
                .get();

        client.prepareIndex(company, employee, "2")
                .setSource(jsonBuilder()
                        .startObject()
                        .field(name, "rose")
                        .field(age, "30")
                        .field(position, "ruby")
                        .field(country, "english")
                        .field(joinDate, "2017-01-01")
                        .field(salary, 12000)
                        .endObject())
                .get();

        client.prepareIndex(company, employee, "3")
                .setSource(jsonBuilder()
                        .startObject()
                        .field(name, "bob")
                        .field(age, "25")
                        .field(position, "mysql")
                        .field(country, "usa")
                        .field(joinDate, "2017-10-01")
                        .field(salary, 20000)
                        .endObject())
                .get();

        client.prepareIndex(company, employee, "4")
                .setSource(jsonBuilder()
                        .startObject()
                        .field(name, "ruby")
                        .field(age, "25")
                        .field(position, "ruby")
                        .field(country, "usa")
                        .field(joinDate, "2017-04-01")
                        .field(salary, 15000)
                        .endObject())
                .get();

        client.prepareIndex(company, employee, "5")
                .setSource(jsonBuilder()
                        .startObject()
                        .field(name, "scott")
                        .field(age, "38")
                        .field(position, "oracle")
                        .field(country, "usa")
                        .field(joinDate, "2016-04-01")
                        .field(salary, 30000)
                        .endObject())
                .get();
    }

    @Test
    public void fetch() throws Exception {
        SearchResponse response = client.prepareSearch(company)
                .setTypes("employee")
                .setQuery(QueryBuilders.matchQuery("position", "ruby"))
                .setPostFilter(QueryBuilders.rangeQuery("age").from(20).to(30))
                .setFrom(0)
                .setSize(2)
                .get();

        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    @Test
    public void modify() throws Exception {
        client.prepareUpdate(company, employee, "3")
                .setDoc(jsonBuilder()
                        .startObject()
                        .field(name, "kidd")
                        .field(position, "python")
                        .endObject())
                .get();
    }

    @Test
    public void delete() throws Exception {
        client.prepareDelete(company, employee, "3").get();
    }

    @Test
    public void aggregation() throws Exception {
        String groupByCountry = "group_by_country";
        String groupByJoinDate = "group_by_join_date";
        String avgSalary = "avg_salary";
        SearchResponse searchResponse = client.prepareSearch(company)
                .addAggregation(AggregationBuilders.terms(groupByCountry)
                        .field("country")
                        .subAggregation(AggregationBuilders.dateHistogram(groupByJoinDate)
                                .field("join_date")
                                .dateHistogramInterval(DateHistogramInterval.YEAR)
                                .subAggregation(AggregationBuilders.avg(avgSalary)
                                        .field("salary"))))
                .get();

        Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().asMap();
        StringTerms groupByCountryTerms = (StringTerms) aggregationMap.get(groupByCountry);

        for (Terms.Bucket groupByCountryBucket : groupByCountryTerms.getBuckets()) {
            System.out.println(groupByCountryBucket.getKey() + ":" + groupByCountryBucket.getDocCount());

            Histogram groupByJoinDateHistogram = (Histogram) groupByCountryBucket.getAggregations().asMap().get(groupByJoinDate);
            for (Histogram.Bucket groupByJoinDateBucket : groupByJoinDateHistogram.getBuckets()) {
                System.out.println(groupByJoinDateBucket.getKey() + ":" + groupByJoinDateBucket.getDocCount());

                Avg avg = (Avg) groupByJoinDateBucket.getAggregations().asMap().get(avgSalary);
                System.out.println(avg.getValue());
            }
        }
    }
}
