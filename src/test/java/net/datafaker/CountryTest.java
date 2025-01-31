package net.datafaker;

import net.datafaker.repeating.Repeat;
import org.junit.Test;

import static net.datafaker.matchers.IsStringWithContents.isStringWithContents;
import static net.datafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class CountryTest extends AbstractFakerTest {

    @Test
    @Repeat(times = 10)
    public void testFlag() {
        String flag = faker.country().flag();
        assertThat(flag, matchesRegularExpression("^https:\\/\\/flags.fmcdn\\.net\\/data\\/flags\\/w580\\/[a-zA-Z0-9_]+\\.png$"));
    }


    @Test
    public void testCode2() {
        assertThat(faker.country().countryCode2(), matchesRegularExpression("([a-z]{2})"));
    }

    @Test
    public void testCode3() {
        assertThat(faker.country().countryCode3(), matchesRegularExpression("([a-z]{3})"));
    }

    @Test
    public void testCapital() {
        assertThat(faker.country().capital(), matchesRegularExpression("([\\p{L}0-9+,. '-])+"));
    }

    @Test
    public void testCurrency() {
        assertThat(faker.country().currency(), matchesRegularExpression("([A-Za-zÀ-ÿ'’()-]+ ?)+"));
    }

    @Test
    public void testCurrencyCode() {
        assertThat(faker.country().currencyCode(), matchesRegularExpression("([\\w-’í]+ ?)+"));
    }

    @Test
    public void testName() {
        assertThat(faker.country().name(), isStringWithContents());
    }
}
