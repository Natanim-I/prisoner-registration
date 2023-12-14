package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Functions {

    public static void insertIntoAdmin(String UserName, String Password){
        String sqlAdmin = "INSERT INTO Admin (UserName,PasswordP)" +
                "Values ('"+UserName+"','"+Password+"')";
        try{
            DatabaseConnection.dbExecuteQuery(sqlAdmin);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteAdmin(int AID){
        String sql = "Delete From Admin WHERE AdminId = '"+AID+"'";
        try{
            DatabaseConnection.dbExecuteQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void insertData(String PFN, String PMN, String PLN, LocalDate Birth, String PMS, LocalDate Entry,
                                  LocalDate Release, String secLev, String cellSh, int cellNO, String photoFile ,
                                  String BloodT, String Health, String Crime, String Emergency){
        String sql = "INSERT INTO Prisoner (FirstName,MiddleName,LastName,DateOfBirth,MaritakStatus,EntryDate,ReleaseDate," +
                "SecurityLevel,CellSharing,CellNo,Photo,BloodType,HealthInfo,Crime,Emergency)" +
                "Values ('"+PFN+"', '"+PMN+"', '"+PLN+"', '"+Birth+"', '"+PMS+"', '"+Entry+"', '"+Release+"', " +
                "'"+secLev+"', '"+cellSh+"', '"+cellNO+"', '"+photoFile+"', '"+BloodT+"', '"+Health+"', '"+Crime+"', '"+Emergency+"')";
        String sqlLog = "INSERT INTO PrisonerLog (FirstName,MiddleName,LastName,DateOfBirth,MaritakStatus,EntryDate,ReleaseDate," +
                "SecurityLevel,CellSharing,CellNo,Photo,BloodType,HealthInfo,Crime,Emergency,StatusP)" +
                "Values ('"+PFN+"', '"+PMN+"', '"+PLN+"', '"+Birth+"', '"+PMS+"', '"+Entry+"', '"+Release+"', " +
                "'"+secLev+"', '"+cellSh+"', '"+cellNO+"', '"+photoFile+"', '"+BloodT+"', '"+Health+"', '"+Crime+"', " +
                "'"+Emergency+"', 'Present')";
        try{
            DatabaseConnection.dbExecuteQuery(sql);
            DatabaseConnection.dbExecuteQuery(sqlLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void Update(String PFN, String PMN, String PLN, LocalDate Birth, String PMS, LocalDate Entry,
                              LocalDate Release, String secLev, String cellSh, int cellNO, String photoFile ,
                              String BloodT, String Health, String Crime, String Emergency,int PID){
        String sqlUpdate = "UPDATE Prisoner SET FirstName = '"+PFN+"', MiddleName = '"+PMN+"', LastName = '"+PLN+"', " +
                "DateOfBirth = '"+Birth+"', MaritakStatus = '"+PMS+"', EntryDate = '"+Entry+"', ReleaseDate = '"+Release+"'," +
                "SecurityLevel = '"+secLev+"', CellSharing = '"+cellSh+"', CellNo = '"+cellNO+"', Photo = '"+photoFile+"', " +
                "BloodType = '"+BloodT+"', HealthInfo = '"+Health+"', Crime = '"+Crime+"', Emergency = '"+Emergency+"' WHERE " +
                "PrisonerId = '"+PID+"'";
        try{
              DatabaseConnection.dbExecuteQuery(sqlUpdate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void Release(){
        LocalDate Today = LocalDate.now();
        String TextToday = Today.toString();
        String sqlRel ="SELECT ReleaseDate FROM Prisoner";
        String sqlRelease = "DELETE FROM Prisoner WHERE ReleaseDate = '"+Today+"'";
        String sqlLog = "UPDATE PrisonerLog SET StatusP = 'Released' WHERE ReleaseDate = '"+Today+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlRel);
            while (rsSet.next()){
                if(rsSet.getString(1).equals(TextToday)){
                    DatabaseConnection.dbExecuteQuery(sqlRelease);
                    DatabaseConnection.dbExecuteQuery(sqlLog);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deletePrisoner(int PID){
        String sql = "Delete From Prisoner WHERE PrisonerId = '"+PID+"'";
        String sqlLog = "UPDATE PrisonerLog SET StatusP = 'Deleted' WHERE PrisonerId = '"+PID+"'";
        try{
            DatabaseConnection.dbExecuteQuery(sql);
            DatabaseConnection.dbExecuteQuery(sqlLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ObservableList<PrisonerTD> getAllPrsList(){
        String sqlAll = "SELECT * FROM Prisoner";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlAll);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerTD> PrsList = getPrisonerObj(rsSet);
        return PrsList;
    }
    private static ObservableList<PrisonerTD> getPrisonerObj(ResultSet rsSet){
        ObservableList<PrisonerTD> PrsList = FXCollections.observableArrayList();
        try {
            while(rsSet.next()){
                PrisonerTD PTD = new PrisonerTD();
                PTD.setID(rsSet.getInt("PrisonerId"));
                PTD.setFirstName(rsSet.getString("FirstName"));
                PTD.setLastName(rsSet.getString("LastName"));
                PTD.setCN(rsSet.getInt("CellNo"));
                PrsList.add(PTD);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return PrsList;
    }

    public static ObservableList<PrisonerTD> searchById(int PID){
        String sqlSID ="SELECT * FROM Prisoner WHERE PrisonerId ='"+PID+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlSID);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerTD> searchListID = getPrisonerObj(rsSet);
        return searchListID;
    }
    public static ObservableList<PrisonerTD> searchByFN(String PFN){
        String sqlSFN ="SELECT * FROM Prisoner WHERE FirstName ='"+PFN+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlSFN);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerTD> searchListFN = getPrisonerObj(rsSet);
        return searchListFN;
    }
    public static ObservableList<PrisonerTD> searchByLN(String PLN){
        String sqlSLN = "SELECT * FROM Prisoner WHERE LastName ='"+PLN+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlSLN);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerTD> searchListID = getPrisonerObj(rsSet);
        return searchListID;
    }
    public static ObservableList<PrisonerTD> searchByCN(int PCN){
        String sqlSCN = "SELECT * FROM Prisoner WHERE CellNo ='"+PCN+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlSCN);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerTD> searchListID = getPrisonerObj(rsSet);
        return searchListID;
    }

    public static ObservableList<PrisonerLogTD> getAllPrsListLog(){
        String sqlAll = "SELECT * FROM PrisonerLog";
        ResultSet rsSetLog = null;
        try{
            rsSetLog = DatabaseConnection.dbExecueSelect(sqlAll);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerLogTD> PrsListLog = getPrisonerLogObj(rsSetLog);
        return PrsListLog;
    }

    private static ObservableList<PrisonerLogTD> getPrisonerLogObj(ResultSet rsSetLog){
        ObservableList<PrisonerLogTD> PrsListLog = FXCollections.observableArrayList();
        try {
            while(rsSetLog.next()){
                PrisonerLogTD PLTD = new PrisonerLogTD();
                PLTD.setID(rsSetLog.getInt("PrisonerId"));
                PLTD.setFirstName(rsSetLog.getString("FirstName"));
                PLTD.setLastName(rsSetLog.getString("LastName"));
                PLTD.setCN(rsSetLog.getInt("CellNo"));
                PLTD.setPrsStatus(rsSetLog.getString("StatusP"));
                PrsListLog.add(PLTD);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return PrsListLog;
    }

    public static ObservableList<PrisonerLogTD> searchByIdLog(int PID){
        String sqlSID ="SELECT * FROM PrisonerLog WHERE PrisonerId ='"+PID+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlSID);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerLogTD> searchListID = getPrisonerLogObj(rsSet);
        return searchListID;
    }

    public static ObservableList<PrisonerLogTD> searchByFNLog(String PFN){
        String sqlSFN ="SELECT * FROM PrisonerLog WHERE FirstName ='"+PFN+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlSFN);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerLogTD> searchListFN = getPrisonerLogObj(rsSet);
        return searchListFN;
    }

    public static ObservableList<PrisonerLogTD> searchByLNLog(String PLN){
        String sqlSLN = "SELECT * FROM PrisonerLog WHERE LastName ='"+PLN+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlSLN);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerLogTD> searchListID = getPrisonerLogObj(rsSet);
        return searchListID;
    }
    public static ObservableList<PrisonerLogTD> searchByCNLog(int PCN){
        String sqlSCN = "SELECT * FROM PrisonerLog WHERE CellNo ='"+PCN+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlSCN);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerLogTD> searchListID = getPrisonerLogObj(rsSet);
        return searchListID;
    }

    public static ObservableList<PrisonerLogTD> searchBySLog(String PSL){
        String sqlSCN = "SELECT * FROM PrisonerLog WHERE StatusP ='"+PSL+"'";
        ResultSet rsSet = null;
        try{
            rsSet = DatabaseConnection.dbExecueSelect(sqlSCN);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<PrisonerLogTD> searchListID = getPrisonerLogObj(rsSet);
        return searchListID;
    }
}

