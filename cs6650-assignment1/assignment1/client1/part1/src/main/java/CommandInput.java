import java.lang.String;

public class CommandInput {

    private static final int MAX_THREADS = 256;
    private String serverAddress;
    private int maxThreads;
    private int numSkiers;
    private int numLifts;
    private String skiDayNumber;
    private String resortId;

    public CommandInput(String[] args) throws Exception {
        this.numSkiers = 50000;
        this.numLifts = 40;
        this.skiDayNumber = "1";
        this.resortId = "SilverMt";

        for (int i = 0; i < args.length; i++) {
            String cur = args[i];
            if ("-maxThreads".equals(cur) && i < args.length - 1) {
                int inputMaxThreads = Integer.parseInt(args[i + 1]);
                if (inputMaxThreads < 0 || inputMaxThreads > MAX_THREADS) {
                    throw new Exception(
                            String.format("Input maxThreads invalid!"));
                }
                this.maxThreads = inputMaxThreads;
            } else if ("-numSkiers".equals(cur) && i < args.length - 1) {
                this.numSkiers = Integer.parseInt(args[i + 1]);
            } else if ("-numLifts".equals(cur) && i < args.length - 1) {
                this.numLifts = Integer.parseInt(args[i + 1]);
            } else if ("-skiDay".equals(cur)) {
                this.skiDayNumber = args[i + 1];
            } else if ("-resortID".equals(cur)) {
                this.resortId = args[i + 1];
            } else if ("-address".equals(cur)) {
                this.serverAddress = args[i + 1];
            }
        }
    }
    public String getServerAddress() {
        return serverAddress;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public int getNumSkiers() {
        return numSkiers;
    }

    public int getNumLifts() {
        return numLifts;
    }

    public String getSkiDayNumber() {
        return skiDayNumber;
    }

    public String getResortId() {
        return resortId;
    }

    @Override
    public String toString() {
        return "CommandInput: {" +
                "maxThreads is: " + maxThreads +
                ", numSkiers is: " + numSkiers +
                ", numLifts is: " + numLifts +
                ", skiDayNumber is: " + skiDayNumber + '\'' +
                ", resortId is: " + resortId + '\'' +
                ", serverAddress is: " + serverAddress + '\'' +
                '}';
    }
}
