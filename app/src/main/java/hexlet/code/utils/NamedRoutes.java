package hexlet.code.utils;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String postsPath() {
        return "/urls";
    }

    public static String buildPostPath() {
        return "/urls/build";
    }

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String postPath(String id) {
        return "/urls/" + id;
    }
}
