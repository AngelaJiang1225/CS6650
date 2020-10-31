package entity;

public class SkierDayVertical {
    private final Integer resortId;
    private final Integer dayId;
    private final Integer skierId;
    private final int vertical;

    public SkierDayVertical(Integer resortId, Integer dayId, Integer skierId, int vertical) {
        this.resortId = resortId;
        this.dayId = dayId;
        this.skierId = skierId;
        this.vertical = vertical;
    }
    public Integer getResortId() {return resortId;}

    public Integer getDayId() {return dayId;}

    public Integer getSkierId() {
        return skierId;
    }

    public int getVertical() {
        return vertical;
    }
    }


