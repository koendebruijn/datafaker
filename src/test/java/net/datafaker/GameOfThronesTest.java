package net.datafaker;

import org.junit.Test;

import static net.datafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

public class GameOfThronesTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.gameOfThrones().character(), matchesRegularExpression("[A-Za-z'\\-\\(\\) ]+"));
    }

    @Test
    public void house() {
        assertThat(faker.gameOfThrones().house(), matchesRegularExpression("[A-Za-z' ]+"));
    }

    @Test
    public void city() {
        assertThat(faker.gameOfThrones().city(), matchesRegularExpression("[A-Za-z' ]+"));
    }

    @Test
    public void dragon() {
        assertThat(faker.gameOfThrones().dragon(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void quote() {
        assertThat(faker.gameOfThrones().quote(), not(is(emptyOrNullString())));
    }
}
