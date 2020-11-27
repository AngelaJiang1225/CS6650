package dao;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBDataSource {
    private static final String INSTANCE = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cs6650";
    private static final String PORT_NUM = "3306";
    private static final String DB_NAME = "skier_lift";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "00788mmH!";

    public static final String URL = "jdbc:mysql://"
            + INSTANCE + ":" + PORT_NUM + "/" + DB_NAME
            + "?user=" + USERNAME + "&password=" + PASSWORD
            + "&autoReconnect=true&serverTimezone=UTC";
    private static BasicDataSource dataSource;

    static {
        // https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-jdbc-url-format.html
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource = new BasicDataSource();
        String url = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", INSTANCE, PORT_NUM, DB_NAME);
        dataSource.setUrl(url);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialSize(128);
        dataSource.setMaxTotal(512);
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }
}
