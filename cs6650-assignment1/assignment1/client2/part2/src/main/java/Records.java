public class Records {
    private String type;
    private int latency;
    private long start;
    private int response;

    public Records(String records) {
        String[] values = records.split(",");
        this.type = values[1];
        this.latency = Integer.valueOf(values[2]);
        this.start = Long.valueOf(values[0]);
        this.response = Integer.parseInt(values[3]);
    }

    public String getType() {
        return type;
    }

    public int getLatency() {
        return latency;
    }
}
