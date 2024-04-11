@import hexlet.code.utils.NamedRoutes
@import hexlet.code.dto.UrlPage
@param UrlPage page

@template.jte.layout.page(
page = page,
content = @`
    <div class="container-lg mt-5">
        <h2>Страница ${page.getName()}</h2>
        <table class="table mt-3">
            <tbody>
            <tr>
                <th scope="row">ID</th>
                <td>${page.getId()}</td>
            </tr>
            <tr>
                <th scope="row">Наименование</th>
                <td>${page.getName()}</td>
            </tr>
            <tr>
                <th scope="row">Дата/время добавления</th>
                <td>${page.getCreatedAt().toString()}</td>
            </tr>
            </tbody>
        </table>

        <h2 class="mt-5">Проверки</h2>

        <form method="post" action="${NamedRoutes.urlChecksPath(page.getId())}">
            <button type="submit" class="btn btn-primary">Запустить проверку</button>
        </form>

        <table class="table mt-3">
            <thead>
            <tr>
                <th class="col">ID</th>
                <th class="col">Код статуса</th>
                <th class="col">Заголовок</th>
                <th class="col">H1</th>
                <th class="col">Описание</th>
                <th class="col">Дата/время проверки</th>
            </tr>
            </thead>
            <tbody>
            @for(var urlCheck: page.getUrlChecks())
                <tr>
                    <td>${urlCheck.getId()}</td>
                    <td>${urlCheck.getStatusCode()}</td>
                    <td>${urlCheck.getTitle()}</td>
                    <td>${urlCheck.getH1()}</td>
                    <td>${urlCheck.getDescription()}</td>
                    <td>${urlCheck.getCreatedAt().toString()}</td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`
)