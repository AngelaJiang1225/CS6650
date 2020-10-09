import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.SkiersApi;
import io.swagger.client.model.LiftRide;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Client1 {

    private static final int PHASE1_NUM_GET = 5;
    private static final int PHASE1_NUM_POST = 100;
    private static final int PHASE2_NUM_GET = 5;
    private static final int PHASE2_NUM_POST = 100;
    private static final int PHASE3_NUM_GET = 10;
    private static final int PHASE3_NUM_POST = 100;

    private static final int PHASE1_START = 1;
    private static final int PHASE2_START = 90;
    private static final int PHASE1_END = 91;
    private static final int PHASE2_END = 360;
    private static final int PHASE3_START = 361;
    private static final int PHASE3_END = 420;
    public static AtomicInteger numSuccessAtomic = new AtomicInteger(0);
    public static AtomicInteger numFailureAtomic = new AtomicInteger(0);

    public static class TaskForClientPart1 implements Runnable {

        private final int skierIdStart;
        private final int skierIdEnd;
        private final int liftIdRange;
        private final int timeStart;
        private final int timeEnd;
        private final String resortId;
        private final String skiDayNumber;
        private final int numPost;
        private final int numGet;
        private final CountDownLatch tenPercentLatch;
        private final CountDownLatch totalLatch;
        private final String address;

        public TaskForClientPart1(int skierIdStart, int skierIdEnd, int liftIdRange, int timeStart, int timeEnd,
                                  String resortId, String skiDayNumber, int numPost, int numGet,
                                  CountDownLatch tenPercentLatch, CountDownLatch totalLatch, String address) {
            this.skierIdStart = skierIdStart;
            this.skierIdEnd = skierIdEnd;
            this.liftIdRange = liftIdRange;
            this.timeStart = timeStart;
            this.timeEnd = timeEnd;
            this.resortId = resortId;
            this.skiDayNumber = skiDayNumber;
            this.numPost = numPost;
            this.numGet = numGet;
            this.tenPercentLatch = tenPercentLatch;
            this.totalLatch = totalLatch;
            this.address = address;
        }

        private void sendRequests() {
            SkiersApi skiersApi = new SkiersApi();
            skiersApi.getApiClient().setBasePath(address);
            int successCnt = 0;
            int failureCnt = 0;
            boolean isSuccessful;
            for (int i = 0; i < numPost; i++) {
                isSuccessful = sendPost(skiersApi);
                if (isSuccessful) {
                    successCnt++;
                } else {
                    failureCnt++;
                }
            }

            for (int i = 0; i < numGet; i++) {
                isSuccessful = sendGet(skiersApi);
                if (isSuccessful) {
                    successCnt += 1;
                } else {
                    failureCnt += 1;
                }
            }

            Client1.numSuccessAtomic.addAndGet(successCnt);
            Client1.numFailureAtomic.addAndGet(failureCnt);
        }

        private boolean sendPost(SkiersApi skiersApi) {
//    1. a skierID from the range of ids passed to the thread
//    2. a lift number (liftID)
//    3. a time from the range of minutes passed to each thread (start and end time -
//        same for each thread)
            String skierId = getRandomSkierId(skierIdStart, skierIdEnd);
            String liftId = getRandomLiftId(liftIdRange);
            String time = getRandomTime(timeStart, timeEnd);
            LiftRide liftRide = new LiftRide();
            liftRide.setResortID(resortId);
            liftRide.setLiftID(liftId);
            liftRide.setSkierID(skierId);
            liftRide.setTime(time);
            liftRide.setDayID(skiDayNumber);

            ApiResponse apiResponse = null;
            try {
                apiResponse = skiersApi.writeNewLiftRideWithHttpInfo(liftRide);
            } catch (ApiException e) {
                int errorCode = e.getCode();
                if (errorCode / 100 == 4 || errorCode / 100 == 5) {
                    System.out.println("Meet error!");
                }
                return false;
            }
            if (apiResponse == null) {
                return false;
            }
            int code = apiResponse.getStatusCode();
            return code == 201 || code == 200;
        }

        private boolean sendGet(SkiersApi skiersApi) {
            // Each GET randomly selects a skierID and calls /skiers/{resortID}/days/{dayID}/skiers/{skierID}
            String skierId = getRandomSkierId(skierIdStart, skierIdEnd);
            ApiResponse apiResponse = null;
            try {
                apiResponse = skiersApi.getSkierDayVerticalWithHttpInfo(resortId, skiDayNumber, skierId);
            } catch (ApiException e) {
                int errorCode = e.getCode();
                if (errorCode / 100 == 4 || errorCode / 100 == 5) {
                    System.out.println("Meet error!");
                }
                return false;
            }

            if (apiResponse == null) {
                return false;
            }

            int code = apiResponse.getStatusCode();
            return code == 200 || code == 201;
        }

        public void run() {
            sendRequests();
            if (tenPercentLatch != null) {
                tenPercentLatch.countDown();
            }
            totalLatch.countDown();
        }

        private String getRandomLiftId(int range) {
            return String.valueOf(getRandomNumber(0, range));
        }

        private String getRandomSkierId(int start, int end) {
            return String.valueOf(getRandomNumber(start, end));
        }

        private String getRandomTime(int start, int end) {
            return String.valueOf(getRandomNumber(start, end));
        }

        private int getRandomNumber(int min, int max) {
            return (int) ((Math.random() * (max - min)) + min);
        }
    }

    private static void runPhase(int numThreads, int numSkiers, int skierIdStart, int skierIdEnd, int numLifts, String resortID, String skiDayId, CountDownLatch phase1LatchTenPct,
                                 CountDownLatch phase1LatchAll, String serverAddr, int phaseStart, int phaseEnd, int phaseNumPost, int phaseNumGet) {
        int numOfSkierIdsPerThread = numSkiers / numThreads;
        for (int i = 0; i < numThreads; i++) {
            skierIdStart = skierIdEnd + 1;
            if (i == numThreads - 1) {
                skierIdEnd = numSkiers;
            } else {
                skierIdEnd = skierIdStart + numOfSkierIdsPerThread - 1;
            }
            Runnable th = new TaskForClientPart1(skierIdStart, skierIdEnd, numLifts, phaseStart,
                    phaseEnd,
                    resortID, skiDayId, phaseNumPost, phaseNumGet, phase1LatchTenPct,
                    phase1LatchAll, serverAddr);
            new Thread(th).start();
        }
    }

    public static void main(String[] args) throws Exception {
        CommandInput commandInput;
        try {
            commandInput = new CommandInput(args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Invalid inputs. Program exits.");
        }

        // Read them from inputArguments
        int maxThreads = commandInput.getMaxThreads();
        int numSkiers = commandInput.getNumSkiers();
        int numLifts = commandInput.getNumLifts();
        String skiDayId = commandInput.getSkiDayNumber();
        String resortID = commandInput.getResortId();
        String serverAddr = commandInput.getServerAddress();

        int phase1Threads = maxThreads / 4;
        CountDownLatch phase1LatchTenPct = new CountDownLatch(phase1Threads * 10 / 100);
        CountDownLatch phase1LatchAll = new CountDownLatch(phase1Threads);
        int skierIdEnd = 0;

        long startTimeInMillSec = System.currentTimeMillis();

        runPhase(phase1Threads, numSkiers, 0, skierIdEnd, numLifts, resortID, skiDayId, phase1LatchTenPct,
                phase1LatchAll, serverAddr, PHASE1_START, PHASE1_END, PHASE1_NUM_POST, PHASE1_NUM_GET);

        phase1LatchTenPct.await();

        // Phase 2
        int phase2Threads = maxThreads;
        CountDownLatch phase2LatchTenPct = new CountDownLatch(phase2Threads * 10 / 100);
        CountDownLatch phase2LatchAll = new CountDownLatch(phase2Threads);
        skierIdEnd = 0;

        runPhase(phase2Threads, numSkiers, 0, skierIdEnd, numLifts, resortID, skiDayId, phase2LatchTenPct,
                phase2LatchAll, serverAddr, PHASE2_START, PHASE2_END, PHASE2_NUM_POST, PHASE2_NUM_GET);
        phase2LatchTenPct.await();

        // Phase 3
        int phase3Threads = maxThreads / 4;
        CountDownLatch phase3LatchAll = new CountDownLatch(phase3Threads);
        skierIdEnd = 0;
        runPhase(phase3Threads, numSkiers, 0, skierIdEnd, numLifts, resortID, skiDayId, null,
                phase3LatchAll, serverAddr, PHASE3_START, PHASE3_END, PHASE3_NUM_POST, PHASE3_NUM_GET);

        phase1LatchAll.await();
        phase2LatchAll.await();
        phase3LatchAll.await();

        long endTimeInMillSec = System.currentTimeMillis();
        int numOfSuccess = numSuccessAtomic.get();
        int numOfFailure = numFailureAtomic.get();
        double wallTimeInSec = ((endTimeInMillSec - startTimeInMillSec) / 1000.0) * 1.0;
        double throughPut =
                numOfSuccess / (wallTimeInSec * 1.0);
        System.out.println(String.format("Number of successful requests is: %s \n"
                        + "Number of failed requests sent: %s \n"
                        + "Total number of requests is: %s \n"
                        + "Total run time (seconds) is: %s \n"
                        + "Throughput is: %s.", numOfSuccess, numOfFailure, numOfFailure + numOfSuccess, wallTimeInSec,
                throughPut));
    }
}
