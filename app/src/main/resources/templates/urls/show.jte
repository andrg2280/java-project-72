@import hexlet.code.utils.NamedRoutes
@import hexlet.code.dto.UrlsPage
@param UrlsPage page

@template.jte.layout.page(
page = page,
content = @`
    <div class="container-lg mt-5">
        <h2>Страницы</h2>
        <table class="table table-striped mt-3">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Наименовние</th>
                <th scope="col">Дата/время последней проверки</th>
                <th scope="col">Код ответа</th>
            </tr>
            </thead>
            <tbody>
            @for(var url: page.getUrls())
                <tr>
                    <th scope="row">${url.getId()}</th>
                    <td>
                        <a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
                    </td>
                    <td>${page.getChecks().isEmpty() || page.getChecks().get(url.getId()) == null ? "" : page.getChecks().get(url.getId()).getCreatedAt().toString()}</td>
                    <td>${page.getChecks().isEmpty() || page.getChecks().get(url.getId()) == null ? "" : page.getChecks().get(url.getId()).getStatusCode().toString()}</td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`
)