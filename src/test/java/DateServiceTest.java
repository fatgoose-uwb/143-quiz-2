/*
 * DO NOT MAKE ANY CHANGES
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateServiceTest {
    @Test
    public void singleDigitUnit() {
        String[] inputs = {
                "1 hour",
                "1 day",
                "1 week",
                "1 month",
        };
        int[] expects = {
                1,
                24,
                24 * 7,
                24 * 30,
        };

        assertEquals(inputs.length, expects.length);

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, expects[i], actual);
        }
    }

    @Test
    public void multipleDigitUnit() {
        String[] inputs = {
                "1 day 12 hour",
                "2 month 1 week 5 day",
                "1 week 2 month 5 day",
        };
        int[] expects = {
                24 + 12,
                2 * 24 * 30 + 24 * 7 + 5 * 24,
                2 * 24 * 30 + 24 * 7 + 5 * 24,
        };

        assertEquals(inputs.length, expects.length);

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, expects[i], actual);
        }
    }

    @Test
    public void singularAndPlural() {
        String[] inputs = {
                "1 day 12 hours",
                "2 months 1 weeks 4 days",
                "8 weeks 7 months 6 days",
        };
        int[] expects = {
                24 + 12,
                2 * 24 * 30 + 24 * 7 + 4 * 24,
                7 * 24 * 30 + 8 * 24 * 7 + 6 * 24,
        };

        assertEquals(inputs.length, expects.length);

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, expects[i], actual);
        }
    }

    @Test
    public void caseInsensitive() {
        String[] inputs = {
                "1 day 12 HOUR",
                "3 MONTH 2 week 9 day",
                "8 week 6 MONTH 3 day",
        };
        int[] expects = {
                24 + 12,
                3 * 24 * 30 + 2 * 24 * 7 + 9 * 24,
                6 * 24 * 30 + 8 * 24 * 7 + 3 * 24,
        };

        assertEquals(inputs.length, expects.length);

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, expects[i], actual);
        }
    }

    @Test
    public void whiteSpaces() {
        String[] inputs = {
                " 2   day 3  HOUR",
                "    4 MONTH 5 week  6 day   ",
                "8   week 7  MONTH 9   day",
        };
        int[] expects = {
                2 * 24 + 3,
                4 * 24 * 30 + 5 * 24 * 7 + 6 * 24,
                7 * 24 * 30 + 8 * 24 * 7 + 9 * 24,
        };

        assertEquals(inputs.length, expects.length);

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, expects[i], actual);
        }
    }

    @Test
    public void connectorsLikeAndandComma() {
        String[] inputs = {
                "13 day and 14 hour",
                "8 month, 5 week and 3 day",
                "5 week, 4, month and 1 day",
        };
        int[] expects = {
                13 * 24 + 14,
                8 * 24 * 30 + 5 * 24 * 7 + 3 * 24,
                4 * 24 * 30 + 5 * 24 * 7 + 24,
        };

        assertEquals(inputs.length, expects.length);

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, expects[i], actual);
        }
    }

    @Test
    public void zeros() {
        String[] inputs = {
                "1 day and 0 hour",
                "0 month, 0 week and 0 day",
                "7 week, 0 day, 2 month",
        };
        int[] expects = {
                24,
                0,
                2 * 24 * 30 + 7 * 24 * 7,
        };

        assertEquals(inputs.length, expects.length);

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, expects[i], actual);
        }
    }

    @Test
    public void repeats() {
        String[] inputs = {
                "1 day, 3 day",
                "8 month, 5 week and 3 month",
                "2 week, 4 month, 1 week, and 1 day",
        };
        int[] expects = {
                4 * 24,
                8 * 24 * 30 + 5 * 24 * 7 + 3 * 24 * 30,
                4 * 24 * 30 + 3 * 24 * 7 + 24,
        };

        assertEquals(inputs.length, expects.length);

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, expects[i], actual);
        }
    }

    @Test
    public void invalidVInput() {
        String[] inputs = {
                "1 2 day",
                "day 1 month",
                "3 hello 1 day",
                "1 2 3",
                "1 day and 2",
                "4 month 1",
                "2 hello",
                "x days",
        };

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, -1, actual);
        }
    }

    @Test
    public void negativeNumber() {
        String[] inputs = {
                "-1 day",
                "5 hour, -0 month",
                "-3 week, -2 month, -1 day"
        };

        for (int i = 0; i < inputs.length; i++) {
            int actual = DateService.dateStringToNumberOfHour(inputs[i]);
            assertEquals("case " + i, -1, actual);
        }
    }
}