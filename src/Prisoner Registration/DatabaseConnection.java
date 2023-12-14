package sample;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;

public class DatabaseConnection {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection Connect = null;
    private static final String connStr = "jdbc:mysql://localhost/prisonmanagementsystem";
    static Connection connL = null;
    public static Connection connectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connL = DriverManager.getConnection(connStr,"root","");
            return connL;
        }catch (Exception e){
            e.printStackTrace();
        }
        return connL;
    }

    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try{
            Class.forName(JDBC_DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }
        try{
            Connect = DriverManager.getConnection(connStr,"root","");
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException{
        try{
            if(Connect != null && !Connect.isClosed() )
                Connect.close();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbExecuteQuery(String sqlStm)throws SQLException, ClassNotFoundException{
        Statement Stmt = null;
        try{
            dbConnect();
            Stmt = Connect.createStatement();
            Stmt.executeUpdate(sqlStm);
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        finally {
            if(Stmt != null)
                Stmt.close();
            dbDisconnect();
        }
    }

    public static ResultSet dbExecueSelect(String sqlQuery) throws SQLException, ClassNotFoundException{
        Statement Stmt = null;
        ResultSet rsSet = null;
        CachedRowSetImpl cachedRowSI = null;
        try{
            dbConnect();
            Stmt = Connect.createStatement();
            rsSet = Stmt.executeQuery(sqlQuery);
            cachedRowSI = new CachedRowSetImpl();
            cachedRowSI.populate(rsSet);
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        finally {
            if(rsSet != null)
                rsSet.close();
            if(Stmt != null)
                Stmt.close();
            dbDisconnect();
        }
        return cachedRowSI;
    }
}