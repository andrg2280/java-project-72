package gg.jte.generated.ondemand.urls;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.dto.UrlPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,7,7,9,9,9,14,14,14,18,18,18,22,22,22,29,29,29,29,29,29,29,29,29,45,45,47,47,47,48,48,48,49,49,49,50,50,50,51,51,51,52,52,52,54,54,58,58,58,58,58,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.jte.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container-lg mt-5\">\n        <h2>Page ");
				jteOutput.setContext("h2", null);
				jteOutput.writeUserContent(page.getName());
				jteOutput.writeContent("</h2>\n        <table class=\"table mt-3\">\n            <tbody>\n            <tr>\n                <th scope=\"row\">ID</th>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getId());
				jteOutput.writeContent("</td>\n            </tr>\n            <tr>\n                <th scope=\"row\">Name</th>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getName());
				jteOutput.writeContent("</td>\n            </tr>\n            <tr>\n                <th scope=\"row\">Created at</th>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getCreatedAt().toString());
				jteOutput.writeContent("</td>\n            </tr>\n            </tbody>\n        </table>\n\n        <h2 class=\"mt-5\">Checks</h2>\n\n        <form method=\"post\"");
				var __jte_html_attribute_0 = NamedRoutes.urlChecksPath(page.getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n            <button type=\"submit\" class=\"btn btn-primary\">Run check!</button>\n        </form>\n\n        <table class=\"table mt-3\">\n            <thead>\n            <tr>\n                <th class=\"col\">ID</th>\n                <th class=\"col\">Status code</th>\n                <th class=\"col\">Title</th>\n                <th class=\"col\">H1</th>\n                <th class=\"col\">Description</th>\n                <th class=\"col\">Check date</th>\n            </tr>\n            </thead>\n            <tbody>\n            ");
				for (var urlCheck: page.getUrlChecks()) {
					jteOutput.writeContent("\n                <tr>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getId());
					jteOutput.writeContent("</td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getStatusCode());
					jteOutput.writeContent("</td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getTitle());
					jteOutput.writeContent("</td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getH1());
					jteOutput.writeContent("</td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getDescription());
					jteOutput.writeContent("</td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getCreatedAt().toString());
					jteOutput.writeContent("</td>\n                </tr>\n            ");
				}
				jteOutput.writeContent("\n            </tbody>\n        </table>\n    </div>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
