package selenium.pages;

public enum StoryEstimate {
    ZERO("0"), HALF("0.5"), ONE("1");

    private String value;

    private StoryEstimate(String value) {
        this.value = value;
    }
}
