package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.Test;

import java.util.Locale;

import static net.datafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/*
 * This file was used to test the issue #566
 * Wed Apr 25 16:58:22 GMT 2021
 * by SE_CHWJ
 */
public class EnZAIdNumberTest {

    //Test the method of judging ssn is right
    @Test
    public void testExistSsn() {
        EnZAIdNumber idNumber = new EnZAIdNumber();

        assertThat(idNumber.validSsn("9202204720085"), is(false));
        assertThat(idNumber.validSsn("foo2204720082"), is(false));
        assertThat(idNumber.validSsn("9232454720082"), is(false));

        assertThat(idNumber.validSsn("9202204720083"), is(true));
        assertThat(idNumber.validSsn("8801235111088"), is(true));
    }

    @Test
    public void testFakerSsn() {
        EnZAIdNumber idNumber = new EnZAIdNumber();
        final Faker f = new Faker(new Locale("en-ZA"));
        for (int i = 0; i < 100; i++) {
            assertThat(idNumber.validSsn(f.idNumber().valid()), is(true));
        }
        for (int i = 0; i < 100; i++) {
            assertThat(idNumber.validSsn(f.idNumber().invalid()), is(false));
        }
    }

    @Test
    public void testSsnFormat() {
        final Faker f = new Faker(new Locale("en-ZA"));
        for (int i = 0; i < 100; i++) {
            assertThat(f.idNumber().valid(), matchesRegularExpression("\\d{10}[01][8]\\d{1}"));
        }
        for (int i = 0; i < 100; i++) {
            assertThat(f.idNumber().invalid(), matchesRegularExpression("\\d{10}[01][8]\\d{1}"));
        }
    }

}