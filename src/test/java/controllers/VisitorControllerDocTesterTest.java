/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;


import lombok.SneakyThrows;
import models.Visitor;
import ninja.NinjaDocTester;
import org.apache.http.HttpStatus;
import org.doctester.testbrowser.Request;
import org.doctester.testbrowser.Response;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class VisitorControllerDocTesterTest extends NinjaDocTester {

    private final static Logger LOG = LoggerFactory.getLogger(VisitorControllerDocTesterTest.class);

    String SETUP_URL = "/setup";

    String VISITOR_URL = "/visitor";

    String newVisitorId = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));


    @Before
    public void setupDatabase(){
        Response response = makeRequest(Request.GET().url(testServerUrl().path(SETUP_URL)));
        assertThat(response.httpStatus, equalTo(HttpStatus.SC_OK));
    }


    @Test
    @SneakyThrows
    public void testGetVisitorByMissingId() {
        String missingId = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
        Response response = makeRequest(Request.GET().url(testServerUrl().path(VISITOR_URL).addQueryParameter("id", missingId)));

        assertThat(response.payloadJsonAs(Visitor.class), equalTo(new Visitor()));
    }
    
    @Test
    @SneakyThrows
    public void testSaveAndFindNewVisitor() {
        Visitor visitor = Visitor.builder().visitorId(newVisitorId).siteId(1).visitTs(2L).build();
        saveNewVisitor(visitor);
        findNewVisitor(visitor);
    }

    private void saveNewVisitor(Visitor visitor){
        Response response = makeRequest(Request.POST().url(testServerUrl().path(VISITOR_URL))
                .payload(visitor)
                .contentTypeApplicationJson());

        assertThat(response.payloadJsonAs(Map.class).get("result"), equalTo(String.valueOf(true)));
    }

    private void findNewVisitor(Visitor visitor){
        Response response = makeRequest(Request.GET().url(testServerUrl().path(VISITOR_URL).addQueryParameter("id", visitor.getVisitorId())));
        assertThat(response.payloadJsonAs(Visitor.class), equalTo(visitor));
    }
}
