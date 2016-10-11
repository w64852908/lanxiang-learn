package com.lanxiang.test.orgjson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Created by lanxiang on 2016/10/10.
 */
public class OrgJson {

    @Test
    public void test1() {
        String jsonStr = "{\n" +
                "    \"limit\":10,\n" +
                "    \"total\":1,\n" +
                "    \"items\":[\n" +
                "        {\n" +
                "            \"reviews\":[\n" +
                "                {\n" +
                "                    \"startTime\":1476078150000,\n" +
                "                    \"result\":1,\n" +
                "                    \"owner\":\"884827\",\n" +
                "                    \"type\":1,\n" +
                "                    \"endTime\":1476078150177,\n" +
                "                    \"ownerInfo\":{\n" +
                "                        \"tenantId\":884825,\n" +
                "                        \"id\":884827,\n" +
                "                        \"status\":1,\n" +
                "                        \"type\":2,\n" +
                "                        \"access\":16381,\n" +
                "                        \"avatar\":\"https://static.weibangong.com/files/57de59b717f27096be57c9dc\",\n" +
                "                        \"fullname\":\"test333_10\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"startTime\":1476078150000,\n" +
                "                    \"result\":2,\n" +
                "                    \"cause\":\"4567890dsfgdhdWEARSDGFHGJ\",\n" +
                "                    \"owner\":\"884827\",\n" +
                "                    \"images\":[\n" +
                "                        {\n" +
                "                            \"id\":\"57edd2fe942416abaa471f01\",\n" +
                "                            \"imageUrl\":\"https://static.weibangong.com/files/57edd2fe942416abaa471f01\",\n" +
                "                            \"name\":\"32C7FC5188DAF355645CAC4188E8E4FC.jpg\",\n" +
                "                            \"length\":30783,\n" +
                "                            \"url\":\"https://static.weibangong.com/files/57edd2fe942416abaa471f01\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"attachments\":[\n" +
                "                        {\n" +
                "                            \"id\":\"57edd307942416abaa471f11\",\n" +
                "                            \"imageUrl\":\"https://static.weibangong.com/files/57edd307942416abaa471f11\",\n" +
                "                            \"name\":\"选择人员的下拉控件中增加“部门”和“职位”标识.pdf\",\n" +
                "                            \"length\":556683,\n" +
                "                            \"type\":\"application/pdf\",\n" +
                "                            \"key\":\"files/57edd307942416abaa471f11\",\n" +
                "                            \"url\":\"https://static.weibangong.com/files/57edd307942416abaa471f11\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"type\":1,\n" +
                "                    \"endTime\":1476078150493,\n" +
                "                    \"ownerInfo\":{\n" +
                "                        \"tenantId\":884825,\n" +
                "                        \"id\":884827,\n" +
                "                        \"status\":1,\n" +
                "                        \"type\":2,\n" +
                "                        \"access\":16381,\n" +
                "                        \"avatar\":\"https://static.weibangong.com/files/57de59b717f27096be57c9dc\",\n" +
                "                        \"fullname\":\"test333_10\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"scopeListInfo\":[\n" +
                "                {\n" +
                "                    \"tenantId\":884825,\n" +
                "                    \"id\":884827,\n" +
                "                    \"status\":1,\n" +
                "                    \"type\":2,\n" +
                "                    \"access\":16381,\n" +
                "                    \"avatar\":\"https://static.weibangong.com/files/57de59b717f27096be57c9dc\",\n" +
                "                    \"fullname\":\"test333_10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tenantId\":884825,\n" +
                "                    \"id\":891941,\n" +
                "                    \"status\":1,\n" +
                "                    \"type\":2,\n" +
                "                    \"access\":0,\n" +
                "                    \"avatar\":\"https://static.weibangong.com/files/57e0ec2c40744273ecdbbd5d\",\n" +
                "                    \"fullname\":\"位中再\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tenantId\":884825,\n" +
                "                    \"id\":891943,\n" +
                "                    \"status\":1,\n" +
                "                    \"type\":2,\n" +
                "                    \"access\":0,\n" +
                "                    \"avatar\":\"https://static.weibangong.com/files/57e0ec2d40744273ecdbbd5e\",\n" +
                "                    \"fullname\":\"它明给\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tenantId\":884825,\n" +
                "                    \"id\":891945,\n" +
                "                    \"status\":1,\n" +
                "                    \"type\":2,\n" +
                "                    \"access\":0,\n" +
                "                    \"avatar\":\"https://static.weibangong.com/files/57e0ec35a5c3974da806d599\",\n" +
                "                    \"fullname\":\"分位真\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tenantId\":884825,\n" +
                "                    \"id\":891944,\n" +
                "                    \"status\":1,\n" +
                "                    \"type\":2,\n" +
                "                    \"access\":0,\n" +
                "                    \"avatar\":\"https://static.weibangong.com/files/57e0ec36a5c3974da806d59a\",\n" +
                "                    \"fullname\":\"将话问\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"scopeList\":[\n" +
                "                \"884827\",\n" +
                "                \"891941\",\n" +
                "                \"891943\",\n" +
                "                \"891945\",\n" +
                "                \"891944\"\n" +
                "            ],\n" +
                "            \"processId\":\"57ea1f98e4b09440ef867499\",\n" +
                "            \"status\":4,\n" +
                "            \"lastUpdateTime\":1476078150684,\n" +
                "            \"department\":\"-\",\n" +
                "            \"advancedProcess\":[\n" +
                "                {\n" +
                "                    \"id\":884827,\n" +
                "                    \"idInfo\":{\n" +
                "                        \"tenantId\":884825,\n" +
                "                        \"id\":884827,\n" +
                "                        \"status\":1,\n" +
                "                        \"type\":2,\n" +
                "                        \"access\":16381,\n" +
                "                        \"avatar\":\"https://static.weibangong.com/files/57de59b717f27096be57c9dc\",\n" +
                "                        \"fullname\":\"test333_10\"\n" +
                "                    },\n" +
                "                    \"type\":1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"number\":\"BM2016101013421000\",\n" +
                "            \"properties\":{\n" +
                "                \"57e242ace4b0c18ec770ecec\":\"test333_10-位中再\",\n" +
                "                \"57e242ace4b0c18ec770eced\":\"test333_10-位中再-它明给\",\n" +
                "                \"57e242ade4b0c18ec770ecee\":\"test333_10,位中再,它明给,将话问,分位真\",\n" +
                "                \"57e242ace4b0c18ec770ece9\":\"[测试]test333_10\",\n" +
                "                \"57e242ace4b0c18ec770eceb\":\"test333_10\",\n" +
                "                \"57e242ace4b0c18ec770ecea\":\"平行审批部\"\n" +
                "            },\n" +
                "            \"type\":\"1-57e23c95e4b036ee7435c52c\",\n" +
                "            \"allPassedTime\":1476078150684,\n" +
                "            \"ownerInfo\":{\n" +
                "                \"tenantId\":884825,\n" +
                "                \"id\":884827,\n" +
                "                \"status\":1,\n" +
                "                \"type\":2,\n" +
                "                \"access\":16381,\n" +
                "                \"avatar\":\"https://static.weibangong.com/files/57de59b717f27096be57c9dc\",\n" +
                "                \"fullname\":\"test333_10\"\n" +
                "            },\n" +
                "            \"id\":\"57fb2a45e4b031b3194125da\",\n" +
                "            \"amount\":\"0\",\n" +
                "            \"startTime\":1476078148762,\n" +
                "            \"title\":\"部门选择\",\n" +
                "            \"owner\":\"884827\",\n" +
                "            \"comment\":\"4567890dsfgdhdWEARSDGFHGJ\",\n" +
                "            \"departmentInfo\":null,\n" +
                "            \"dealingMark\":true\n" +
                "        }\n" +
                "    ],\n" +
                "    \"offset\":0\n" +
                "}";

        JSONObject obj = new JSONObject(jsonStr);
        JSONArray items = obj.getJSONArray("items");

        String[] ids = new String[items.length()];
        for(int i = 0; i < items.length(); i++){
            ids[i] = ((JSONObject)items.get(i)).getString("id");
        }
        System.out.println(ids.length);
    }
}
