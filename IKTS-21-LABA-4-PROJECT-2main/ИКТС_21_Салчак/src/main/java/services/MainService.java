package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainService {

    private final DBService dbService;

    public MainService() {
        dbService = new DBService();
    }

    public void addWarehouse(int ProductID, String ProductName, int Quantity, double Price, String Supplier, String DateReceived){
        String sql = "INSERT INTO warehouse(ProductID, ProductName, Quantity, Price, Supplier, DateReceived) VALUES ('"+ProductID+"', '"+ProductName+"', '"+Quantity+"', '"+Price+"', '"+Supplier+"', '"+DateReceived+"')";

        dbService.insert(sql);
    }
    public void editWarehouse(int ProductID, String ProductName, int Quantity, double Price, String Supplier, String DateReceived){
        String sql = "UPDATE warehouse SET ProductName ='" + ProductName + "', Quantity =" + Quantity + ", Price =" + Price + ", Supplier ='" + Supplier + "', DateReceived ='" + DateReceived + "' WHERE ProductID =" + ProductID;
        dbService.update(sql);
    }

    public void deleteWarehouse(int ProductID) {
        String sql = "DELETE FROM warehouse WHERE ProductID =" + ProductID + ";";
        dbService.delete(sql);
    }



    public List<Map<String,Object>> getNames() throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT * FROM warehouse";
        ResultSet rs = dbService.select(sql);
        while (rs.next()){
            Map<String, Object> row = new HashMap<>();
            row.put("ProductID", rs.getInt("ProductID"));
            row.put("ProductName", rs.getString("ProductName"));
            row.put("Quantity", rs.getInt("Quantity"));
            row.put("Price", rs.getInt("Price"));
            row.put("Supplier", rs.getString("Supplier"));
            row.put("DateReceived", rs.getString("DateReceived"));


            result.add(row);
        }
        return result;
    }

}
