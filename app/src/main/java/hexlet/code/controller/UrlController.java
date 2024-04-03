package hexlet.code.controller;

import hexlet.code.dto.UrlPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.dto.UrlsPage;
import hexlet.code.utils.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;


import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
public class UrlController {
    public static void show(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var checks = UrlCheckRepository.getAllLastChecks();
        var page = new UrlsPage(urls, checks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/show.jte", Collections.singletonMap("page", page));
    }

    public static void index(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var urlChecks = UrlCheckRepository.getEntitiesById(id);
        var page = new UrlPage(id, url.getName(), url.getCreatedAt(), urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/index.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        var input = ctx.formParamAsClass("url", String.class)
                .get()
                .toLowerCase()
                .trim();


        URL parsedUrl;
        try {
            parsedUrl = new URI(input).toURL();
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Incorrect URL");
            ctx.sessionAttribute("flash-type", "warning");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        if (UrlRepository.findByName(String.valueOf(parsedUrl)) != null) {
            ctx.sessionAttribute("flash", "This page already exist");
            ctx.sessionAttribute("flash-type", "info");
            ctx.redirect(NamedRoutes.urlsPath());
        } else {
            String strValidURL = parsedUrl.getProtocol() +
                    "://" + parsedUrl.getHost() +
                    (parsedUrl.getPort() == -1 ? "" : ":") +
                    (parsedUrl.getPort() == -1 ? "" : String.valueOf(parsedUrl.getPort()));
            var url = new Url(strValidURL, new Timestamp(System.currentTimeMillis()));
            UrlRepository.save(url);
            ctx.sessionAttribute("flash", "Page added successfully");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect(NamedRoutes.urlsPath());
        }
    }
}
