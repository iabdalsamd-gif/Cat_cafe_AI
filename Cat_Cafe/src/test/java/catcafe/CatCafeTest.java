package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CatCafeTest {

    @ParameterizedTest
    @CsvSource({"'Huli',   4, 1", "'Sterni', 3, 1", "'Ahmo',   7, 1"})
    void addCat(String a, int b, int anzahl) {
        // given
        var SUT = new CatCafe();
        var cat = new FelineOverLord(a, b);

        // when
        SUT.addCat(cat);
        var result = SUT.getCatCount();

        // then
        assertEquals(
                anzahl,
                result,
                "Nach dem Hinzufügen einer Katze sollte das Café eine Katze enthalten");
    }

    @Test
    void catcafe_isempty() {
        // given
        var SUT = new CatCafe();

        // when
        var result = SUT.getCatCount();

        // then
        assertEquals(0, result, "Ein neues CatCafe sollte keine Katzen enthalten");
    }

    @Test
    void getcatcount_testifstimmt() {
        // given
        var SUT = new CatCafe();
        var cat = new FelineOverLord("Huli", 3);
        var cat2 = new FelineOverLord("Sterni", 4);
        var cat3 = new FelineOverLord("Ahmo", 5);

        // when
        SUT.addCat(cat);
        SUT.addCat(cat2);
        SUT.addCat(cat3);
        var result = SUT.getCatCount();

        // then
        assertEquals(
                3,
                result,
                "Nach dem Hinzufügen von mehreren Katzen sollte man ein genamt Anzahl bekommen");
    }

    @Test
    void getcatbyname_testifvorhanden() {
        // given
        var SUT = new CatCafe();
        var cat = new FelineOverLord("Huli", 4);
        SUT.addCat(cat);

        // when
        var result = SUT.getCatByName("Huli");

        // then
        assertEquals(
                "Huli",
                result.name(),
                "Nach dem Hinzufügen einer Katze sollte diese durch ihre Name gefunden werden");
    }

    @Test
    void getcatbyname_testifnichtvorhanden() {
        // given
        var SUT = new CatCafe();
        var cat = new FelineOverLord("Huli", 4);
        SUT.addCat(cat);

        // when
        var result = SUT.getCatByName("Sterni");

        // then
        assertNull(
                result, "Wenn ein Katze nicht hingefügt sollte diese auch nicht gefunden werden");
    }

    @Test
    void getcatbyweight_testifvorhanden() {
        // given
        var SUT = new CatCafe();
        var cat = new FelineOverLord("Huli", 6);
        SUT.addCat(cat);
        var cat2 = new FelineOverLord("Sterni", 5);
        SUT.addCat(cat2);

        // when
        var result = SUT.getCatByWeight(3, 7);

        // then
        assertEquals(
                5,
                result.weight(),
                "Nach dem Hinzufügen von Katzen Sollte man die Katze mit den kleinsten gewicht"
                        + " finden");
    }

    @Test
    void getcatbyweight_testifichtvorhanden() {
        // given
        var SUT = new CatCafe();
        var cat = new FelineOverLord("Huli", 6);
        SUT.addCat(cat);
        var cat2 = new FelineOverLord("Sterni", 5);
        SUT.addCat(cat2);

        // when
        var result = SUT.getCatByWeight(3, 5);

        // then
        assertNull(
                result,
                "Wenn keine Katze in der Gewichtsspanne liegt, sollte keine Katze gefunden werden");
    }

    @Test
    void getcatbyweight_maxgewichtnichtfinden() {
        // given
        var SUT = new CatCafe();
        var cat = new FelineOverLord("Huli", 4);
        SUT.addCat(cat);

        // when
        var result = SUT.getCatByWeight(6, 3);

        // then
        assertNull(
                result,
                "Wenn maxWeight kleiner als minWeight ist, sollte keine Katze gefunden werden");
    }

    @Test
    void getcatbyweight_negativgewichtgibtnicht() {
        // given
        var SUT = new CatCafe();
        var cat = new FelineOverLord("Huli", 4);
        SUT.addCat(cat);

        // when
        var result = SUT.getCatByWeight(-1, 5);

        // then
        assertNull(result, "Bei negativem Mindestgewicht sollte keine Katze gefunden werden");
    }

    @Test
    void givenCatCafe_whenAddingNullCat_thenNullPointerExceptionIsThrown() {
        // given
        var SUT = new CatCafe();

        // when / then
        assertThrows(
                NullPointerException.class,
                () -> SUT.addCat(null),
                "Beim Hinzufügen von null sollte eine NullPointerException geworfen werden");
    }
}
