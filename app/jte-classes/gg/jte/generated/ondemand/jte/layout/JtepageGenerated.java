package gg.jte.generated.ondemand.jte.layout;
import gg.jte.Content;
import hexlet.code.dto.BasePage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "jte/layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,33,33,33,34,34,34,34,35,35,35,37,37,41,41,41,45,45,45,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, BasePage page) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"utf-8\" />\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n    <title>Анализатор страниц</title>\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"\n          rel=\"stylesheet\"\n          integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"\n          crossorigin=\"anonymous\">\n</head>\n<body>\n<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n    <div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\">\n        <ul class=\"navbar-nav\">\n            <li class=\"nav-item active\">\n                <a class=\"nav-link\" href=\"/\">Анализатор страниц</a>\n            </li>\n            <li class=\"nav-item\">\n                <a class=\"nav-link\" href=\"/posts\">Главная</a>\n            </li>\n            <li class=\"nav-item\">\n                <a class=\"nav-link\" href=\"/posts\">Сайты</a>\n            </li>\n        </ul>\n    </div>\n</nav>\n\n");
		if (page != null && page.getFlash() != null) {
			jteOutput.writeContent("\n    <div class=\"alert alert-");
			jteOutput.setContext("div", "class");
			jteOutput.writeUserContent(page.getFlashType());
			jteOutput.setContext("div", null);
			jteOutput.writeContent("\" role=\"alert\">\n        ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(page.getFlash());
			jteOutput.writeContent("\n    </div>\n");
		}
		jteOutput.writeContent("\n\n\n<div class=\"mx-auto p-4 py-md-5\">\n    ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n</div>\n\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, content, page);
	}
}
