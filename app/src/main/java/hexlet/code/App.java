package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.controller.RootController;
import hexlet.code.repository.BaseRepository;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.controller.UrlController;
import hexlet.code.controller.UrlCheckController;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.stream.Collectors;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import io.javalin.rendering.template.JavalinJte;
import gg.jte.resolve.ResourceCodeResolver;

@Slf4j
public class App {
    private static String getDatabaseUrl() {
        return System.getenv()
                .getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:webapp;DB_CLOSE_DELAY=-1;");
    }
    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }
    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }
    public static Javalin getApp() throws SQLException {

        // BEGIN
        var hikariConfig = new HikariConfig();
        String databaseUrl = getDatabaseUrl();
        if (databaseUrl.contains("postgresql")) {
            hikariConfig.setDriverClassName("org.postgresql.Driver");
        }
        hikariConfig.setJdbcUrl(databaseUrl);
        var dataSource = new HikariDataSource(hikariConfig);
        var inputStream = App.class.getClassLoader().getResourceAsStream("dbStruct.sql");
        var reader = new BufferedReader(new InputStreamReader(inputStream));
        var sql = reader.lines().collect(Collectors.joining("\n"));
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });
        // Описываем, что загрузится по адресу /
        app.get("/welcome", ctx -> ctx.result("Welcome to Hexlet!"));
        app.get(NamedRoutes.rootPath(), RootController::index);
        app.get(NamedRoutes.urlsPath(), UrlController::show);
        app.get(NamedRoutes.urlPath("{id}"), UrlController::index);
        app.post(NamedRoutes.urlsPath(), UrlController::create);
        app.post(NamedRoutes.urlChecksPath("{id}"), UrlCheckController::create);
        return app;
        // END

    }
    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();

        app.start(getPort());
    }
}
