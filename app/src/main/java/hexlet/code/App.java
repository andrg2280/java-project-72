package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.repository.BaseRepository;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
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
    public static Javalin getApp() {

        // BEGIN
        var hikariConfig = new HikariConfig();
        String databaseUrl = getDatabaseUrl();
        if (databaseUrl.contains("postgresql")) {
            hikariConfig.setDriverClassName("org.postgresql.Driver");
        }
        hikariConfig.setJdbcUrl(databaseUrl);
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        // Описываем, что загрузится по адресу /
        app.get("/welcome", ctx -> ctx.result("Welcome to Hexlet!"));
        app.get("/", ctx -> ctx.result("Hello World"));
        return app;
        // END

    }
    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();

        app.start(getPort());
    }
}
