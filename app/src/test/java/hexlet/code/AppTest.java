package hexlet.code;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class AppTest {

    private Javalin app;

    private static String website;
    private static String name;

    @BeforeAll
    public static void setServer() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        app = App.getApp();
    }

    @AfterEach
    public void close() {
        app.stop();
    }

    @AfterAll
    public static void closeServer() throws IOException {
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        });
    }
}
