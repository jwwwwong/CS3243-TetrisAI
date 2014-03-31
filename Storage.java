
import java.sql.*;

public class Storage {
    private final String DATABASE_NAME = "TetrisAi.db";
    private final String OPTIMIZER_LOG_TABLE = "OptimizerLog";
    
    public Storage() {
        createOptimizerLogTable();
    }
    
    private void createOptimizerLogTable() {        
        String sql = "CREATE TABLE IF NOT EXISTS " + OPTIMIZER_LOG_TABLE +
                   " (id            INTEGER PRIMARY KEY AUTOINCREMENT," +
                   " optimizer_name   TEXT, " + 
                   " performance      INT, " + 
                   " weights          TEXT, " + 
                   " parameters       TEXT, " + 
                   " time_stamp       DATETIME DEFAULT CURRENT_TIMESTAMP)"; 
                
        execUpdate(sql);
    }
    
    public void insertOptimizerLog(String optimizerName, int performance, double[] weights, String parameters) {
        String weightsStr = "";
        for(int i = 0; i < weights.length; i++) {
            weightsStr += String.valueOf(weights[i]);
            if(i != weights.length - 1) {
                weightsStr += ",";
            }
        }
        String sql = "INSERT INTO " + OPTIMIZER_LOG_TABLE + 
                    " (optimizer_name, performance, weights, parameters) " +
                    " VALUES ('"+optimizerName+"', "+performance+", '"+weightsStr+"', '"+parameters+"');"; 
        execUpdate(sql);
    }
    
    private void execUpdate(String sql) {
        try {
            Connection c = getConnection();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");      
        Connection c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
        return c;
    }
    
}
