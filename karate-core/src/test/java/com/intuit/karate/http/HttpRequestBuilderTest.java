package com.intuit.karate.http;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.graalvm.polyglot.Value;



import com.intuit.karate.core.ScenarioEngine;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pthomas3
 */
class HttpRequestBuilderTest {

    static final Logger logger = LoggerFactory.getLogger(HttpRequestBuilderTest.class);

    HttpRequestBuilder http;

    @BeforeAll
    public static void setUp() {
        Coverage.visited = new boolean[16];
    }

    @AfterAll
    public static void showBranchCoverage() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("coverage_melissa.txt"));
        int totalBranchesCovered = 0;
        for (int i = 0; i < Coverage.visited.length; i++) {
            bufferedWriter.write("Branch ID: " + i + " Covered: " + Coverage.visited[i] + "\n");
            if (Coverage.visited[i]) {
                totalBranchesCovered++;
            }
        }
        bufferedWriter.write("branches covered %: " +  ((float) totalBranchesCovered/ (float) Coverage.visited.length) + "\n");
        bufferedWriter.close();
    }



    @BeforeEach
    void beforeEach() {
        ScenarioEngine se = ScenarioEngine.forTempUse(HttpClientFactory.DEFAULT);
        http = new HttpRequestBuilder(HttpClientFactory.DEFAULT.create(se));
    }
    @Test
    void testGetMemberURL() {
        HttpClient client = null;
        HttpRequestBuilder newFile = new HttpRequestBuilder(client);
        assertTrue(newFile.getMember("method") != null);
    }

    @Test
    void testGetMemberDefaultCase() {
        HttpClient client = null;
        HttpRequestBuilder newFile = new HttpRequestBuilder(client);
        Object hello = newFile.getMember("0");
        assertNull(hello);
    }

    @Test
    void testUrlAndPath() {
        http.url("http://host/foo");
        assertEquals("http://host/foo", http.getUri());
        http.path("/bar");
        assertEquals("http://host/foo/bar", http.getUri());
    }

    @Test
    void testUrlAndPathWithSlash() {
        http.url("http://host/foo/");
        assertEquals("http://host/foo/", http.getUri());
        http.path("/bar/");
        assertEquals("http://host/foo/bar", http.getUri());
    }
    
    @Test
    void testUrlAndPathWithTrailingSlash() {
        http.url("http://host/foo");
        assertEquals("http://host/foo", http.getUri());
        http.path("bar");
        http.path("/");
        assertEquals("http://host/foo/bar/", http.getUri());
    }    
    
    @Test
    void testUrlAndPathWithEncodedSlash() {
        http.url("http://host");
        assertEquals("http://host", http.getUri());
        http.path("foo\\/bar");
        assertEquals("http://host/foo%2Fbar", http.getUri());
    }     

}
