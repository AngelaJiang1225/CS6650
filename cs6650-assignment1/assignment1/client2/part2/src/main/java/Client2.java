import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Client2 {

    private static final int PHASE1_NUM_GET = 5;
    private static final int PHASE2_NUM_GET = 5;
    private static final int PHASE3_NUM_GET = 10;
    private static final int PHASE1_NUM_POST = 100;
    private static final int PHASE2_NUM_POST = 100;
    private static final int PHASE3_NUM_POST = 100;
    private static final int PHASE1_START = 1;
    private static final int PHASE2_START = 91;
    private static final int PHASE3_START = 361;
    private static final int PHASE1_END = 90;
    private static final int PHASE2_END = 360;
    private static final int PHASE3_END = 420;
    private static AtomicInteger numSuccessAtomic = new AtomicInteger(0);
    private static AtomicInteger numFailureAtomic = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        CommandInput1 commandInput1;
        try {
            commandInput1 = new CommandInput1(args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Invalid inputs. Program exits.");
        }

        // Read them from inputArguments
        int maxThreads = commandInput1.getMaxThreads();
        int numSkiers = commandInput1.getNumSkiers();
        int numLifts = commandInput1.getNumLifts();
        String skiDayId = commandInput1.getSkiDayNumber();
        String resortID = commandInput1.getResortId();
        String serverAddr = commandInput1.getServerAddress();

        int numThreadForPhase1 = maxThreads / 4;
        CountDownLatch phase1LatchTenPct = new CountDownLatch(numThreadForPhase1 * 10 / 100);
        CountDownLatch phase1LatchAll = new CountDownLatch(numThreadForPhase1);
        int numOfSkierIdsPerThread = numSkiers / numThreadForPhase1;
        int skierIdStart;
        int skierIdEnd = 0;

        List<String> resultList = Collections.synchronizedList(new ArrayList<String>());

        long startTimeInMillSec = System.currentTimeMillis();

        // Phase 1
        for (int i = 0; i < numThreadForPhase1; i++) {
            skierIdStart = skierIdEnd + 1;
            if (i == numThreadForPhase1 - 1) {
                skierIdEnd = numSkiers;
            } else {
                skierIdEnd = skierIdStart + numOfSkierIdsPerThread - 1;
            }
            Runnable th = new TaskForClientPart2(resultList, skierIdStart, skierIdEnd, numLifts,
                    PHASE1_START,
                    PHASE1_END,
                    resortID, skiDayId, PHASE1_NUM_POST, PHASE1_NUM_GET, phase1LatchTenPct,
                    phase1LatchAll, serverAddr);
            new Thread(th).start();
        }

        phase1LatchTenPct.await();

        // Phase 2
        int numThreadForPhase2 = maxThreads;
        CountDownLatch phase2LatchTenPct = new CountDownLatch(numThreadForPhase2 * 10 / 100);
        CountDownLatch phase2LatchAll = new CountDownLatch(numThreadForPhase2);
        numOfSkierIdsPerThread = numSkiers / numThreadForPhase2;
        skierIdEnd = 0;

        for (int i = 0; i < numThreadForPhase2; i++) {
            skierIdStart = skierIdEnd + 1;
            if (i == numThreadForPhase2 - 1) {
                skierIdEnd = numSkiers;
            } else {
                skierIdEnd = skierIdStart + numOfSkierIdsPerThread - 1;
            }

            Runnable th = new TaskForClientPart2(resultList, skierIdStart, skierIdEnd, numLifts,
                    PHASE2_START,
                    PHASE2_END,
                    resortID, skiDayId, PHASE2_NUM_POST, PHASE2_NUM_GET, phase2LatchTenPct,
                    phase2LatchAll, serverAddr);
            new Thread(th).start();
        }

        phase2LatchTenPct.await();

        // Phase 3
        int numThreadForPhase3 = maxThreads / 4;
        CountDownLatch phase3LatchAll = new CountDownLatch(numThreadForPhase3);
        numOfSkierIdsPerThread = numSkiers / numThreadForPhase3;
        skierIdEnd = 0;

        for (int i = 0; i < numThreadForPhase3; i++) {
            skierIdStart = skierIdEnd + 1;
            if (i == numThreadForPhase3 - 1) {
                skierIdEnd = numSkiers;
            } else {
                skierIdEnd = skierIdStart + numOfSkierIdsPerThread - 1;
            }
            Runnable th = new TaskForClientPart2(resultList, skierIdStart, skierIdEnd, numLifts,
                    PHASE3_START,
                    PHASE3_END,
                    resortID, skiDayId, PHASE3_NUM_POST, PHASE3_NUM_GET, null,
                    phase3LatchAll, serverAddr);
            new Thread(th).start();
        }

        phase1LatchAll.await();
        phase2LatchAll.await();
        phase3LatchAll.await();

        long endTimeInMillSec = System.currentTimeMillis();
        double wallTimeInSec = ((endTimeInMillSec - startTimeInMillSec) / 1000.0) * 1.0;
        double throughPut =
                numSuccessAtomic.get() / (wallTimeInSec * 1.0);
        System.out.println(String.format("Number of successful requests= %s \n"
                        + "number of failed requests= %s \n"
                        + "Total requests= %s \n"
                        + "Total run time in seconds (wall time)= %s \n"
                        + "Throughput= %s.", numSuccessAtomic.get(), numFailureAtomic.get(), numSuccessAtomic.get() + numFailureAtomic.get(),
                wallTimeInSec, throughPut));

        FileWriter csvWriter = new FileWriter(String.format("%s_threads.csv", maxThreads));
        csvWriter.append("StartTime,RequestType,Latency,ResponseCode\n");

        List<CsvRecord> csvRecordsForGet = new LinkedList<CsvRecord>();
        List<CsvRecord> csvRecordsForPost = new LinkedList<CsvRecord>();
        List<CsvRecord> csvRecordsTotal = new LinkedList<>();
        for (String res : resultList) {
            csvWriter.append(res);
            csvWriter.append("\n");
            if (res.contains("GET")) {
                csvRecordsForGet.add(new CsvRecord(res));
            } else {
                csvRecordsForPost.add(new CsvRecord(res));
            }
            csvRecordsTotal.add(new CsvRecord(res));
        }
        csvWriter.flush();
        csvWriter.close();

        ProcessStatistics processStatisticsPost = new ProcessStatistics(csvRecordsForPost);
        ProcessStatistics processStatisticsGet = new ProcessStatistics(csvRecordsForGet);
        ProcessStatistics processStatisticsTotal = new ProcessStatistics(csvRecordsTotal);
        System.out.println("\n");
        System.out.println(String.format(
                "Totally wall time is: %s,\n"
                        + "Throughput is: %s,\n"
                        + "Mean response time of get is: %s,\n"
                        + "Median response time of get is: %s,\n"
                        + "P99 response time of get is: %s,\n"
                        + "Max response time of get is: %s.\n"
                        + "Mean response time of post is: %s,\n"
                        + "Median response time of post is: %s,\n"
                        + "P99 response time of post is: %s,\n"
                        + "Max response time of post is: %s.\n"
                        + "Mean response time totally is: %s,\n"
                        + "Median response time totally is: %s,\n"
                        + "P99 response time totally is: %s,\n"
                        + "Max response time totally is: %s.\n",
                wallTimeInSec, throughPut, processStatisticsGet.getMean(), processStatisticsGet.getMedian(), processStatisticsGet.getP99Latency(), processStatisticsGet.getMaxLatency(),
                processStatisticsPost.getMean(), processStatisticsPost.getMedian(), processStatisticsPost.getP99Latency(),
                processStatisticsPost.getMaxLatency(),
                processStatisticsPost.getMean(), processStatisticsPost.getMedian(), processStatisticsPost.getP99Latency(),
                processStatisticsPost.getMaxLatency()));
    }
}
