package subway.domain.feature;

public enum AppFeature {

    QUERY("1"),
    QUIT("Q");

    private static final String INVALID_APP_FEATURE_MSG = "유효하지 않은 기능입니다.\n반드시 1, Q 중 하나를 입력해주세요.";
    private final String value;

    AppFeature(final String value) {
        this.value = value;
    }

    public static AppFeature from(final String input) {
        if ("1".equals(input)) {
            return QUERY;
        }
        if ("Q".equals(input)) {
            return QUIT;
        }
        throw new IllegalArgumentException(INVALID_APP_FEATURE_MSG);
    }
}
