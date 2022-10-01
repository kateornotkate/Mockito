import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)

public class GeoServiceImplTest {

    GeoServiceImpl geoService; // объект определения локации по ip;

    @BeforeEach
    void setUp() {
        geoService = new GeoServiceImpl();
    }

    @DisplayName("Определение локации по точному ip адресу.")
    @Test
    void byFullIpAddress() {
        assertEquals(Country.RUSSIA, geoService.byIp("172.0.32.11").getCountry());
        assertEquals("Moscow", geoService.byIp("172.0.32.11").getCity());
        assertEquals("Lenina", geoService.byIp("172.0.32.11").getStreet());
        assertEquals(15, geoService.byIp("172.0.32.11").getBuiling());
    }

    @DisplayName("Определение локации по первым цифрам ip адреса.")
    @Test
    void byIpAddress() {
        assertEquals(Country.USA, geoService.byIp("96.").getCountry());
        assertEquals("New York", geoService.byIp("96.").getCity());
        assertNull(geoService.byIp("96.").getStreet());
        assertEquals(0, geoService.byIp("96.").getBuiling());
    }
}