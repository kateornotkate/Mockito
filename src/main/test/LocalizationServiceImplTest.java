import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {

    LocalizationServiceImpl localization; // объект для проверки возвращаемого текста;

    @BeforeEach
    void setUp() {
        localization = new LocalizationServiceImpl();
    }

    @DisplayName("Проверка возвращаемого текста для русскоязычного сегмента.")
    @Test
    void localeRussia() {
        assertEquals("Добро пожаловать", localization.locale(Country.RUSSIA));
    }

    @DisplayName("Проверка возвращаемого текста для англоговорящего сегмента.")
    @Test
    void localeWorld() {
        assertEquals("Welcome", localization.locale(Country.USA));
        assertEquals("Welcome", localization.locale(Country.GERMANY));
        assertEquals("Welcome", localization.locale(Country.BRAZIL));
    }
}