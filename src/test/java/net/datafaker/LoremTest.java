package net.datafaker;

import net.datafaker.repeating.Repeat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static net.datafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoremTest extends AbstractFakerTest {

    @Test
    public void shouldCreateFixedLengthString() {
        assertEquals(10, faker.lorem().fixedString(10).length());
        assertEquals(50, faker.lorem().fixedString(50).length());
        assertEquals(0, faker.lorem().fixedString(0).length());
        assertEquals(0, faker.lorem().fixedString(-1).length());
    }

    @Test
    public void wordShouldNotBeNullOrEmpty() {
        assertThat(faker.lorem().word(), not(is(emptyOrNullString())));
    }

    @Test
    public void testCharacter() {
        assertThat(String.valueOf(faker.lorem().character()), matchesRegularExpression("[a-z\\d]{1}"));
    }

    @Test
    public void testCharacterIncludeUpperCase() {
        assertThat(String.valueOf(faker.lorem().character(false)), matchesRegularExpression("[a-z\\d]{1}"));
        assertThat(String.valueOf(faker.lorem().character(true)), matchesRegularExpression("[a-zA-Z\\d]{1}"));
    }

    @Test
    public void testCharactersShouldIncludeMinAndMaxLenght() {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            results.add(faker.lorem().characters(1, 10));
        }

        final List<String> min = results.stream().filter(x -> x.length() == 1).collect(Collectors.toList());
        final List<String> max = results.stream().filter(x -> x.length() == 10).collect(Collectors.toList());

        assertTrue(min.size() > 0);
        assertTrue(max.size() > 0);
    }

    @Test
    public void testCharacters() {
        assertThat(faker.lorem().characters(), matchesRegularExpression("[a-z\\d]{255}"));
    }

    @Test
    public void testCharactersIncludeUpperCase() {
        assertThat(faker.lorem().characters(false), matchesRegularExpression("[a-z\\d]{255}"));
        assertThat(faker.lorem().characters(true), matchesRegularExpression("[a-zA-Z\\d]{255}"));
    }

    @Test
    public void testCharactersWithLength() {
        assertThat(faker.lorem().characters(2), matchesRegularExpression("[a-z\\d]{2}"));
        assertThat(faker.lorem().characters(500), matchesRegularExpression("[a-z\\d]{500}"));
        assertThat(faker.lorem().characters(0), is(emptyString()));
        assertThat(faker.lorem().characters(-1), is(emptyString()));
    }

    @Test
    public void testCharactersWithLengthIncludeUppercase() {
        assertThat(faker.lorem().characters(2, false), matchesRegularExpression("[a-z\\d]{2}"));
        assertThat(faker.lorem().characters(500, false), matchesRegularExpression("[a-z\\d]{500}"));
        assertThat(faker.lorem().characters(2, true), matchesRegularExpression("[a-zA-Z\\d]{2}"));
        assertThat(faker.lorem().characters(500, true), matchesRegularExpression("[a-zA-Z\\d]{500}"));
        assertThat(faker.lorem().characters(0, false), is(emptyString()));
        assertThat(faker.lorem().characters(-1, true), is(emptyString()));
    }

    @Test
    public void testCharactersMinimumMaximumLength() {
        assertThat(faker.lorem().characters(1, 10), matchesRegularExpression("[a-z\\d]{1,10}"));
    }

    @Test
    public void testCharactersMinimumMaximumLengthIncludeUppercase() {
        assertThat(faker.lorem().characters(1, 10, true), matchesRegularExpression("[a-zA-Z\\d]{1,10}"));
    }

    @Test
    public void testCharactersMinimumMaximumLengthIncludeUppercaseIncludeDigit() {
        assertThat(faker.lorem().characters(1, 10, false, false), matchesRegularExpression("[a-zA-Z]{1,10}"));
        assertThat(faker.lorem().characters(1, 10, true, true), matchesRegularExpression("[a-zA-Z\\d]{1,10}"));
    }

    @Test
    public void testSentence() {
        assertThat(faker.lorem().sentence(), matchesRegularExpression("(\\w+\\s?){4,10}\\."));
    }

    @Test
    public void testSentenceWithWordCount() {
        assertThat(faker.lorem().sentence(10), matchesRegularExpression("(\\w+\\s?){11,17}\\."));
    }

    @Test
    public void testSentenceWithWordCountAndRandomWordsToAdd() {
        assertThat(faker.lorem().sentence(10, 10), matchesRegularExpression("(\\w+\\s?){10,20}\\."));
    }

    @Test
    public void testSentenceFixedNumberOfWords() {
        assertThat(faker.lorem().sentence(10, 0), matchesRegularExpression("(\\w+\\s?){10}\\."));
    }

    @Test
    public void testWords() {
        assertThat(faker.lorem().words(), hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    @Repeat(times = 10)
    public void testMaxLengthSentence() {
        Random rand = new Random();
        // Test different lengths over 10 runs
        int length = Math.abs(rand.nextInt(10000));
        String s = faker.lorem().maxLengthSentence(length);
        assertEquals(s.length(), length);
    }

    @Test
    public void testMaxLengthWithEmptySentence() {
        String s = faker.lorem().maxLengthSentence(0);
        assertEquals(s.length(), 0);
    }

    @Test
    public void testMaxLengthWithNegativeLengthSentence() {
        String s = faker.lorem().maxLengthSentence(-1);
        assertEquals(s.length(), 0);
    }
}
