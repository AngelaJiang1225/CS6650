import java.util.Arrays;
import java.util.List;

public class ProcessStatistics {
    private int[] latencies;
    private double mean;
    private double median;
    private double maxLatency;
    private double p99Latency;

    public ProcessStatistics(List<CsvRecord> csvRecords) {
        latencies = new int[csvRecords.size()];
        for (int i = 0; i < csvRecords.size(); i++) {
            latencies[i] = csvRecords.get(i).getLatency();
        }
        Arrays.sort(latencies);
        mean = Arrays.stream(latencies).average().orElse(Double.NaN);
        if (latencies.length % 2 == 0) {
            median = ((double) latencies[latencies.length / 2] + (double) latencies[latencies.length / 2 - 1]) / 2;
        } else {
            median = latencies[latencies.length / 2];
        }
        maxLatency = latencies[latencies.length - 1];
    }

    public int[] getLatencies() {
        return latencies;
    }

    public double getMean() {
        return mean;
    }

    public double getMedian() {
        return median;
    }

    public double getMaxLatency() {
        return maxLatency;
    }

    public double getP99Latency() {
        return p99Latency;
    }
}
