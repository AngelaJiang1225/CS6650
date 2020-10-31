package entity;

public class SkierVertical {
    private final Integer skierId;
    private final int vertical;

    public SkierVertical(Integer skierId, int vertical) {
        this.skierId = skierId;
        this.vertical = vertical;
    }

    public Integer getSkierId() {
        return skierId;
    }

    public int getVertical() {
        return vertical;
    }
}
