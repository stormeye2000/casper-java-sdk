package com.casper.sdk.model.storedvalue;

import com.casper.sdk.model.AbstractJsonTests;
import com.casper.sdk.model.account.Account;
import com.casper.sdk.model.clvalue.*;
import com.casper.sdk.model.contract.Contract;
import com.casper.sdk.model.key.AlgorithmTag;
import com.casper.sdk.model.key.Key;
import com.casper.sdk.model.key.KeyTag;
import com.casper.sdk.model.key.PublicKey;
import com.casper.sdk.model.transfer.Transfer;
import com.casper.sdk.model.uref.URef;
import com.casper.sdk.model.uref.URefAccessRight;
import com.fasterxml.jackson.databind.JsonMappingException;
import dev.oak3.sbs4j.SerializerBuffer;
import dev.oak3.sbs4j.exception.ValueSerializationException;
import dev.oak3.sbs4j.util.ByteUtils;
import org.bouncycastle.util.encoders.Hex;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Unit;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.BufferUnderflowException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link StoredValueData}
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class StoredValueTests extends AbstractJsonTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoredValueTests.class);

    private static final String API_VERSION = "1.3.2";
    private static final String MERKLE_PROOF = "-- erased --";


    @Test
    void validate_CLValueAny_Mapping() throws IOException, JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-any.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueAny
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueAny);

        CLValueAny expectedClValue = new CLValueAny(Hex.decode("aced000574000f416e79204f626a6563742054657374"));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueU8_Mapping() throws IOException, JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-u8.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueU8
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueU8);

        CLValueU8 expectedClValue = new CLValueU8((byte) 1);

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueU32_Mapping() throws IOException, JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-u32.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueU32
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueU32);

        CLValueU32 expectedClValue = new CLValueU32(4294967295L);
        expectedClValue.setParsed(expectedClValue.getValue().toString());

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueU64_Mapping() throws IOException, JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-u64.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueU64
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueU64);
        CLValueU64 expectedClValue = new CLValueU64(new BigInteger("18446744073709551615", 10));
        expectedClValue.setParsed(expectedClValue.getValue().toString(10));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueU128_Mapping() throws IOException, JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-u128.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueU128
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueU128);
        CLValueU128 expectedClValue = new CLValueU128(new BigInteger("340282366920938463463374607431768211455", 10));
        expectedClValue.setParsed(expectedClValue.getValue().toString(10));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueU256_Mapping() throws IOException, JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-u256.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueU256
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueU256);
        CLValueU256 expectedClValue = new CLValueU256(
                new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935", 10));
        expectedClValue.setParsed(expectedClValue.getValue().toString(10));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueI64_Mapping() throws IOException, JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-i64.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueI64
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueI64);
        CLValueI64 expectedClValue = new CLValueI64(9223372036854775807L);
        expectedClValue.setParsed(expectedClValue.getValue().toString());

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueString_Mapping() throws IOException, ValueSerializationException, JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-string.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be string
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueString);
        CLValueString expectedClValue = new CLValueString("the string");

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueTuple1_Mapping_with_bool() throws IOException,
            ValueSerializationException, JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-tuple1-bool.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueTuple1
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueTuple1);
        CLValueTuple1 expectedClValue = new CLValueTuple1(new Unit<>(new CLValueBool(true)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueTuple2_Mapping_with_i32_string() throws IOException,
            ValueSerializationException, JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-tuple2-i32-string.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueTuple2
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueTuple2);
        CLValueTuple2 expectedClValue = new CLValueTuple2(
                new Pair<>(new CLValueI32(1), new CLValueString("Hello, World!")));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueTuple3_Mapping_with_u8_string_bool() throws IOException,
            ValueSerializationException, JSONException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-tuple3-u8-string-bool.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueTuple3
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueTuple3);
        CLValueTuple3 expectedClValue = new CLValueTuple3(
                new Triplet<>(new CLValueU8((byte) 1), new CLValueString("Hello, World!"), new CLValueBool(true)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueTuple3_Mapping_with_i32_string_bool() throws IOException,
            ValueSerializationException, JSONException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-tuple3-i32-string-bool.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueTuple3
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueTuple3);
        CLValueTuple3 expectedClValue = new CLValueTuple3(
                new Triplet<>(new CLValueI32(1), new CLValueString("Hello, World!"), new CLValueBool(true)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueTuple3_Mapping_with_tuple1_bool_string_bool() throws IOException, ValueSerializationException, JSONException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-tuple3-tuple1-bool-string-bool.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueTuple3
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueTuple3);
        CLValueTuple3 expectedClValue = new CLValueTuple3(new Triplet<>(
                new CLValueTuple1(new Unit<>(new CLValueBool(true))), new CLValueString("Hello, World!"),
                new CLValueBool(true)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueTuple3_Mapping_with_tuple2_tuple1_u512_u512_tuple1_string_tuple1_bool()
            throws IOException, ValueSerializationException,
            JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile(
                "stored-value-samples/stored-value-tuple3-tuple2-tuple1-u512-u512-tuple1-string-tuple1-bool.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueTuple3
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueTuple3);
        CLValueTuple3 expectedClValue = new CLValueTuple3(new Triplet<>(
                new CLValueTuple2(new Pair<>(
                        new CLValueTuple1(new Unit<>(new CLValueU512(BigInteger.valueOf(2)))),
                        new CLValueU512(BigInteger.valueOf(2)))),
                new CLValueTuple1(new Unit<>(new CLValueString("Hello, World!"))),
                new CLValueTuple1(new Unit<>(new CLValueBool(true)))));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueTuple3_Mapping_with_tuple1_u512_string_bool() throws IOException, ValueSerializationException, JSONException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-tuple3-tuple1-u512-string-bool.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueTuple3
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueTuple3);
        CLValueTuple3 expectedClValue = new CLValueTuple3(new Triplet<>(
                new CLValueTuple1(new Unit<>(new CLValueU512(new BigInteger("123456789101112131415", 10)))),
                new CLValueString("Hello, World!"), new CLValueBool(true)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);
        String serializedExpected = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);
        LOGGER.debug("Serialized Expected JSON: {}", serializedExpected);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueList_Mapping_with_i32() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-list-i32.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueList
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueList);
        CLValueList expectedClValue = new CLValueList(
                Arrays.asList(new CLValueI32(1), new CLValueI32(2), new CLValueI32(3)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueFixedList_Mapping_with_i32() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-fixedlist-i32.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueList
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueFixedList);
        CLValueFixedList expectedClValue = new CLValueFixedList(
                Arrays.asList(new CLValueI32(1), new CLValueI32(2), new CLValueI32(3)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueFixedList_Mapping_with_tuple1_i32() throws IOException, JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-fixedlist-tuple1-i32.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueList
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueFixedList);
        CLValueFixedList expectedClValue = new CLValueFixedList(Arrays.asList(
                new CLValueTuple1(new Unit<>(new CLValueI32(1))), new CLValueTuple1(new Unit<>(new CLValueI32(2))),
                new CLValueTuple1(new Unit<>(new CLValueI32(3)))));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueFixedList_Mapping_with_i32_odd_byte_length() throws IOException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-fixedlist-i32-odd-byte-length.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        Throwable exception = assertThrows(JsonMappingException.class,
                () -> OBJECT_MAPPER.readValue(inputJson, StoredValueData.class));
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
    }

    @Test
    void validate_CLValueFixedList_Mapping_with_i32_wrong_byte_length()
            throws IOException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-fixedlist-i32-wrong-byte-length.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        Throwable exception = assertThrows(JsonMappingException.class,
                () -> OBJECT_MAPPER.readValue(inputJson, StoredValueData.class));
        assertEquals(BufferUnderflowException.class, exception.getCause().getClass());
    }

    @Test
    void validate_CLValueList_Mapping_with_tuple2_i32_i32() throws IOException, JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-list-tuple2-i32-i32.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueList
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueList);
        CLValueList expectedClValue = new CLValueList(
                Arrays.asList(new CLValueTuple2(new Pair<>(new CLValueI32(1), new CLValueI32(1))),
                        new CLValueTuple2(new Pair<>(new CLValueI32(2), new CLValueI32(2))),
                        new CLValueTuple2(new Pair<>(new CLValueI32(3), new CLValueI32(3)))));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueMap_Mapping_with_string_i32() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-map-string-i32.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueMap
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueMap);
        Map<CLValueString, CLValueI32> map = new LinkedHashMap<>();
        map.put(new CLValueString("ABC"), new CLValueI32(10));
        CLValueMap expectedClValue = new CLValueMap(map);

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueMap_Mapping_with_string_tuple1_i32() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-map-string-tuple1-i32.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueMap
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueMap);
        Map<CLValueString, CLValueTuple1> map = new LinkedHashMap<>();
        map.put(new CLValueString("ABC"), new CLValueTuple1(new Unit<>(new CLValueI32(10))));
        CLValueMap expectedClValue = new CLValueMap(map);

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueResult_Mapping_with_i32_string() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-result-i32-string.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueResult
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueResult);
        CLValueResult expectedClValue = new CLValueResult(new CLValueI32(10), new CLValueString("Uh oh"));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueResult_Mapping_with_i32_tuple1_string() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-result-i32-tuple1-string.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueResult
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueResult);
        CLValueResult expectedClValue = new CLValueResult(new CLValueI32(10),
                new CLValueTuple1(new Unit<>(new CLValueString("Uh oh"))));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueOption_Mapping_with_empty() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-option-empty.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueOption
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueOption);
        CLValueOption expectedClValue = new CLValueOption(Optional.of(new CLValueBool(null)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueOption_Mapping_with_bool() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-option-bool.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueOption
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueOption);
        CLValueOption expectedClValue = new CLValueOption(Optional.of(new CLValueBool(true)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueOption_Mapping_with_i32() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-option-i32.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueOption
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueOption);
        CLValueOption expectedClValue = new CLValueOption(Optional.of(new CLValueI32(10)));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueOption_Mapping_with_tuple2_i32_string() throws IOException,
            JSONException, ValueSerializationException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-option-tuple2-i32-string.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueOption
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueOption);
        CLValueOption expectedClValue = new CLValueOption(
                Optional.of(new CLValueTuple2(new Pair<>(new CLValueI32(1), new CLValueString("Hello, World!")))));

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueUnit_Mapping() throws IOException,
            ValueSerializationException, JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-unit.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueUnit
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueUnit);
        CLValueUnit expectedClValue = new CLValueUnit();

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueURef_Mapping() throws IOException, ValueSerializationException,
            JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-uref.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValuURef
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueURef);
        CLValueURef expectedClValue = new CLValueURef(new URef(
                ByteUtils
                        .parseHexString("2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a"),
                URefAccessRight.READ_ADD_WRITE));
        expectedClValue.setParsed("the uref");

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueKey_Mapping_of_account() throws IOException, ValueSerializationException,
            JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-key-account.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueKey
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueKey);
        Key key = new Key();
        key.setTag(KeyTag.ACCOUNT);
        key.setKey(ByteUtils.parseHexString("2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a"));
        CLValueKey expectedClValue = new CLValueKey(key);

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueKey_Mapping_of_hash() throws IOException, ValueSerializationException,
            JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-key-hash.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueKey
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueKey);
        Key key = new Key();
        key.setTag(KeyTag.HASH);
        key.setKey(ByteUtils.parseHexString("2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a"));
        CLValueKey expectedClValue = new CLValueKey(key);

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValuePublicKey_Mapping() throws IOException, ValueSerializationException,
            JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-publickey.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValuePublicKey
        assertTrue(sv.getStoredValue().getValue() instanceof CLValuePublicKey);
        PublicKey pk = new PublicKey();
        pk.setTag(AlgorithmTag.ED25519);
        pk.setKey(ByteUtils.parseHexString("2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a2a"));
        CLValuePublicKey expectedClValue = new CLValuePublicKey(pk);

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_CLValueByteArray_Mapping() throws IOException,
            ValueSerializationException, JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-bytearray.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);
        // Should be CLValueByteArray
        assertTrue(sv.getStoredValue().getValue() instanceof CLValueByteArray);
        CLValueByteArray expectedClValue = new CLValueByteArray(
                new byte[]{122, -50, 107, 117, -83, -99, 95, 64, -35, 5, 34, 44, 108, -122, 69, -78, 28, -20, 71, 119,
                        98, 48, -34, 0, 111, -53, -39, 107, -38, 124, 73, -75});

        StoredValueData expected = createAndInitExpectedStoredValueData(expectedClValue);

        assertEquals(expected, sv);

        String expectedJson = getPrettyJson(expected);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_Account_Mapping() throws IOException, JSONException {
        /*
         * curl -X POST -H 'Content-Type: application/json' -d
         * '{"jsonrpc":"2.0","id":"1","method":"state_get_item",
         * "params":{"state_root_hash":
         * "09ac52260e370ed56bba5283a79b03d524b4f420bf964d7e629b0819dd1be09d", "key":
         * "account-hash-e1431ecb9f20f2a6e6571886b1e2f9dec49ebc6b2d3d640a53530abafba9bfa1"}
         * }' http://195.201.142.76:7777/rpc | json_pp
         */
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-account.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);

        assertTrue(sv.getStoredValue().getValue() instanceof Account);

        String expectedJson = getPrettyJson(sv);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_Contract_Mapping() throws IOException, JSONException {
        /*
         * curl -X POST -H 'Content-Type: application/json' -d
         * '{"jsonrpc":"2.0","id":"1","method":"state_get_item",
         * "params":{"state_root_hash":
         * "09ac52260e370ed56bba5283a79b03d524b4f420bf964d7e629b0819dd1be09d", "key":
         * "hash-d2469afeb99130f0be7c9ce230a84149e6d756e306ef8cf5b8a49d5182e41676"} }'
         * http://195.201.142.76:7777/rpc | json_pp
         */
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-contract.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);

        assertTrue(sv.getStoredValue().getValue() instanceof Contract);

        String expectedJson = getPrettyJson(sv);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_Contract_Mapping_with_access_as_groups()
            throws IOException, JSONException {
        String inputJson = getPrettyJson(
                loadJsonFromFile("stored-value-samples/stored-value-contract-access-groups.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);

        assertTrue(sv.getStoredValue().getValue() instanceof Contract);

        String expectedJson = getPrettyJson(sv);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void validate_Transfer_Mapping() throws IOException, JSONException {
        String inputJson = getPrettyJson(loadJsonFromFile("stored-value-samples/stored-value-transfer.json"));

        LOGGER.debug("Original JSON: {}", inputJson);

        StoredValueData sv = OBJECT_MAPPER.readValue(inputJson, StoredValueData.class);

        assertTrue(sv.getStoredValue().getValue() instanceof Transfer);

        String expectedJson = getPrettyJson(sv);

        LOGGER.debug("Serialized JSON: {}", expectedJson);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    private StoredValueData createAndInitExpectedStoredValueData(AbstractCLValue<?, ?> expectedClValue)
            throws ValueSerializationException {
        StoredValueData expected = new StoredValueData();
        expected.setApiVersion(API_VERSION);
        expected.setMerkleProof(MERKLE_PROOF);

        StoredValueCLValue svClValue = new StoredValueCLValue();
        svClValue.setValue(expectedClValue);
        expected.setStoredValue(svClValue);

        // This is done here to account for the missing encode call made by jackson
        // serializer
        SerializerBuffer clve = new SerializerBuffer();
        expectedClValue.serialize(clve);

        return expected;
    }
}