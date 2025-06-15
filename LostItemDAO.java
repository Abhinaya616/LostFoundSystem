import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LostItemDAO {
    private Connection conn;

    public LostItemDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    public void insertLostItem(LostItem item) throws SQLException {
        String sql = "INSERT INTO lost_items (person_name, item_name, description, location_lost, date_lost, status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, item.getPersonName());
        pst.setString(2, item.getItemName());
        pst.setString(3, item.getDescription());
        pst.setString(4, item.getLocationLost());
        pst.setDate(5, item.getDateLost());
        pst.setString(6, item.getStatus());
        pst.executeUpdate();
        pst.close();
    }

    public List<LostItem> getAllLostItems() throws SQLException {
        List<LostItem> list = new ArrayList<>();
        String sql = "SELECT * FROM lost_items";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            LostItem item = new LostItem(
                rs.getInt("id"),
                rs.getString("person_name"),
                rs.getString("item_name"),
                rs.getString("description"),
                rs.getString("location_lost"),
                rs.getDate("date_lost"),
                rs.getString("status")
            );
            list.add(item);
        }
        rs.close();
        stmt.close();
        return list;
    }

    public void updateStatus(int id, String newStatus) throws SQLException {
        String sql = "UPDATE lost_items SET status = ? WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, newStatus);
        pst.setInt(2, id);
        pst.executeUpdate();
        pst.close();
    }
}
