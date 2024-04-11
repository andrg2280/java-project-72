package hexlet.code;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public final class AppTest {
    private static Javalin app;
    private static MockWebServer mockServer;
    private static String urlName;
    private static final String HTML_PATH = "src/test/resources/test.html";

    public static String getContentOfHtmlFile() throws IOException {
        var path = Paths.get(HTML_PATH);
        var lines = Files.readAllLines(path);
        return String.join("\n", lines);
    }

    @BeforeAll
    public static void beforeAll() throws IOException {
        mockServer = new MockWebServer();
        urlName = mockServer.url(HTML_PATH).toString();
        var mockResponse = new MockResponse().setBody(getContentOfHtmlFile());
        mockServer.enqueue(mockResponse);
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        app = App.getApp();

    }

    @AfterAll
    public static void afterAll() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void testRootPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.rootPath());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testUrlsPath() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testAddPage() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://www.google.com";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://www.google.com");
            assertThat(UrlRepository.getEntities()).hasSize(1);
        });
    }

    @Test
    public void testDoubleAddPage() throws SQLException {
        var url = new Url("https://www.google.com", new Timestamp(System.currentTimeMillis()));
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://www.google.com";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://www.google.com");
            assertThat(UrlRepository.getEntities()).hasSize(1);
        });
    }

    @Test
    public void testSavePage() throws SQLException {
        var url = new Url("https://www.google.com", new Timestamp(System.currentTimeMillis()));
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlPath(url.getId()));
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testBadRequest() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlPath("bad request"));
            assertThat(response.code()).isEqualTo(400);
        });
    }

    @Test
    public void testUrlNotExists() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("\\nonexistable");
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    public void testCheckUrl() throws SQLException {
        var url = new Url(urlName, new Timestamp(System.currentTimeMillis()));
        UrlRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var response = client.post(NamedRoutes.urlChecksPath(url.getId()));
            assertThat(response.code()).isEqualTo(200);

            var urlCheck = UrlCheckRepository.getLastCheck(url.getId()).get();
            var title = urlCheck.getTitle();
            var h1 = urlCheck.getH1();
            var description = urlCheck.getDescription();

            assertThat(title).isEqualTo("test HTML page");
            assertThat(h1).isEqualTo("H1 header");
            assertThat(description).isEqualTo("content description");
        });
    }
}
