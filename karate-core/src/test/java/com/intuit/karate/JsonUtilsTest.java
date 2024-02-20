package com.intuit.karate;

import com.intuit.karate.core.ComplexPojo;
import com.intuit.karate.core.SimplePojo;
import com.intuit.karate.core.Variable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

/**
 *
 * @author pthomas3
 */
class JsonUtilsTest {

    static final Logger logger = LoggerFactory.getLogger(JsonUtilsTest.class);

        @BeforeAll
    public static void setUp() {
        Coverage.branchVisited = new boolean[34];
    }

    @AfterAll
    public static void showBranchCoverage() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("branchCoverage_result"));
        int totalBranchesCovered = 0;
        for (int i = 0; i < Coverage.branchVisited.length; i++) {
            bufferedWriter.write("Branch ID: " + i + " Covered: " + Coverage.branchVisited[i] + "\n");
            if (Coverage.branchVisited[i]) {
                totalBranchesCovered++;
            }
        }

        bufferedWriter.write("branches covered %: " +  ((float) totalBranchesCovered/(float) Coverage.branchVisited.length) + "\n");

        bufferedWriter.close();
    }

    @Test
    void testParse() {
        String temp = JsonUtils.toStrictJson("{redirect:{url:'/index'}}");
        assertEquals(temp, "{\"redirect\":{\"url\":\"\\/index\"}}");
    }

    @Test
    void testDetect() {
        assertTrue(JsonUtils.isJson("{}"));
        assertTrue(JsonUtils.isJson("[]"));
        assertTrue(JsonUtils.isJson(" {}"));
        assertTrue(JsonUtils.isJson(" []"));
        assertFalse(JsonUtils.isJson(null));
        assertFalse(JsonUtils.isJson(""));
    }

    @Test
    void testBeanConversion() {
        SimplePojo pojo = new SimplePojo();
        String s = JsonUtils.toJson(pojo);
        Match.that(s).isEqualTo("{\"bar\":0,\"foo\":null}");
        Map<String, Object> map = Json.of(pojo).asMap();
        Match.that(map).isEqualTo("{ foo: null, bar: 0 }");
    }

    @Test
    public void testPojoConversion() {
        ComplexPojo pojo = new ComplexPojo();
        pojo.setFoo("testFoo");
        pojo.setBar(1);
        ComplexPojo p1 = new ComplexPojo();
        p1.setFoo("p1");
        ComplexPojo p2 = new ComplexPojo();
        p2.setFoo("p2");
        pojo.setBan(Arrays.asList(p1, p2));
        String s = JsonUtils.toJson(pojo);
        Match.that(s).isEqualTo("{\"bar\":1,\"foo\":\"testFoo\",\"baz\":null,\"ban\":[{\"bar\":0,\"foo\":\"p1\",\"baz\":null,\"ban\":null},{\"bar\":0,\"foo\":\"p2\",\"baz\":null,\"ban\":null}]}");
        ComplexPojo temp = (ComplexPojo) JsonUtils.fromJson(s, ComplexPojo.class.getName());
        assertEquals(temp.getFoo(), "testFoo");
        assertEquals(2, temp.getBan().size());
        temp = JsonUtils.fromJson(s, ComplexPojo.class);
        assertEquals(temp.getFoo(), "testFoo");
        assertEquals(2, temp.getBan().size());
        s = XmlUtils.toXml(pojo);
        Match.that(s).isEqualTo("<root><bar>1</bar><foo>testFoo</foo><baz/><ban><bar>0</bar><foo>p1</foo><baz/><ban/></ban><ban><bar>0</bar><foo>p2</foo><baz/><ban/></ban></root>");
    }

    @Test
    void testDeepCopy() {
        Map<String, Object> one = new HashMap();
        Map<String, Object> two = new HashMap();
        two.put("one", one);
        one.put("two", two);
        Object temp = JsonUtils.deepCopy(one);
        assertEquals(temp, one);
        assertFalse(temp == one);
        String json = JsonUtils.toJsonSafe(temp, false);
        assertEquals("{\"two\":{\"one\":{\"two\":{\"one\":\"#ref:java.util.HashMap\"}}}}", json);
    }

    @Test
    void testMalformed() {
        String text = FileUtils.toString(getClass().getResourceAsStream("malformed.txt"));
        try {
            Object o = JsonUtils.fromJsonStrict(text);
            fail("we should not have reached here");
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof net.minidev.json.parser.ParseException);
        }
    }

    @Test
    void fromJsonStrictRetainsKeyOrder() {
        String originalString = "{\"tango\":\"Alice\",\"foxtrot\":\"0.0.0.0\",\"sierra\":\"Bob\"}";
        Object obj = JsonUtils.fromJsonStrict(originalString);
                Variable var = new Variable(obj);
        String serialized = var.getAsString();
        assertEquals(originalString, serialized);
    }

}