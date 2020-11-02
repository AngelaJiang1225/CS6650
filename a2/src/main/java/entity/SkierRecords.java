package entity;

public class SkierRecords {
    private final Integer id;
    private final Integer resortId;
    private final Integer skierId;
    private final Integer liftId;
    private final int seasonId;
    private final int dayId;
    private final int time;
    private final int vertical;

    public SkierRecords(Integer id, Integer resortId, Integer skierId, Integer liftId, int seasonId, int dayId, int time, int vertical) {
        this.id = id;
        this.resortId = resortId;
        this.skierId = skierId;
        this.liftId = liftId;
        this.seasonId = seasonId;
        this.dayId = dayId;
        this.time = time;
        this.vertical = vertical;
    }

    public Integer getId() {
        return id;
    }

    public Integer getResortId() {
        return resortId;
    }

    public int getDayId() {
        return dayId;
    }

    public int getSkierId() {
        return skierId;
    }

    public int getLiftId() {
        return liftId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public int getTime() {
        return time;
    }

    public void setVertical() {
        this.vertical = this.liftId * 10;
    }
}
