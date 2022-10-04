import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

public class MessageSenderImplTest {

    public static final String IP_ADDRESS_HEADER = "x-real-ip";

    private MessageSenderImpl messageSender; // объект проверки языка отправляемого сообщения;

    @Mock
    private GeoService geoService; // мок объекта определения локации по ip;

    @Mock
    private LocalizationService localizationService; // мок объекта проверки возвращаемого текста;

    @BeforeEach
    void setUp() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @DisplayName("Отправка сообщений российскому сегменту номеров в соответствие с ip.")
    @Test
    void sendMessageToRussia() {
        Mockito.when(geoService.byIp("172.")).thenReturn(new Location("MOSCOW", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Map<String, String> headers = Map.of(IP_ADDRESS_HEADER, "172.");
        assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @DisplayName("Отправка сообщений заграничному сегменту номеров в соответствие с ip.")
    @Test
    void sendMessageToTheWorld() {
        Mockito.when(geoService.byIp("96.")).thenReturn((new Location("New York", Country.USA, null, 0)));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Map<String, String> headers = Map.of(IP_ADDRESS_HEADER, "96.");
        assertEquals("Welcome", messageSender.send(headers));
    }
}