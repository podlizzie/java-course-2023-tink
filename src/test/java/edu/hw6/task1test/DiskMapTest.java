package edu.hw6.task1test;

import edu.hw6.task1.DiskMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiskMapTest {

    private static final String TEST_FILE_PATH = "src/test/java/edu/hw6/task1test/testFile.txt";
    DiskMap diskMap = new DiskMap(TEST_FILE_PATH);

    @Test
    public void testThatSizeReturnedCorrectSizeOfMap() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertThat(diskMap.size()).isEqualTo(2);
    }

    @Test
    public void testThatContainsKeyReturnedCorrect() {
        diskMap.put("key1", "value1");
        assertThat(diskMap.containsKey("key1")).isTrue();
        assertThat(diskMap.containsKey("key3")).isFalse();
    }

    @Test
    public void testThatContainsValueReturned() {
        diskMap.put("key1", "value1");
        assertThat(diskMap.containsValue("value1")).isTrue();
        assertThat(diskMap.containsValue("value3")).isFalse();
    }

    @Test
    public void testThatGetReturnedCorrectValue() {
        diskMap.put("key1", "value1");
        String key = "key1";
        String key2 = "key3";
        String expectedValue = "value1";

        assertThat(diskMap.get(key)).isEqualTo(expectedValue);
        assertThat(diskMap.get(key2)).isNull();
    }

    @Test
    public void testThatClearReturnedEmptyMap() {
        diskMap.clear();
        assertThat(diskMap.isEmpty()).isTrue();
    }

    @Test
    public void testPutMethodReturnsPreviousValue() {
        diskMap.put("key1", "value1");
        assertThat(diskMap.put("key1", "newValue")).isEqualTo("value1");
    }

    @Test
    public void testRemoveMethodReturnsCorrectValue() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        String removedValue = diskMap.remove("key1");
        diskMap.remove("abracadabra");

        assertThat(removedValue).isEqualTo("value1");
        assertThat(diskMap.size()).isEqualTo(1);
    }

    @Test
    public void testPutAllMethodAddsAllEntries() {
        Map<String, String> otherMap = Map.of(
            "key1", "value1",
            "key2", "value2"
        );

        diskMap.putAll(otherMap);

        assertThat(diskMap.size()).isEqualTo(2);
    }

    @Test
    public void testKeySetMethodReturnsCorrectSet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        Set<String> expected = Set.of("key1", "key2");

        assertThat(diskMap.keySet()).isEqualTo(expected);
    }

    @Test
    public void testEntrySetMethodReturnsCorrectSet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        Set<Map.Entry<String, String>> expected = Set.of(
            Map.entry("key1", "value1"),
            Map.entry("key2", "value2")
        );

        assertThat(diskMap.entrySet()).isEqualTo(expected);
    }
}
