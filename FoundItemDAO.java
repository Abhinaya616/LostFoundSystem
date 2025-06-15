import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoundItemDAO {
    private Connection conn;

    public FoundItemDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    public void insertFoundItem(FoundItem item) throws SQLException {
        String sql = "INSERT INTO found_items (finder_name, item_name, description, location_found, date_found, status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, item.getFinderName());
        pst.setString(2, item.getItemName());
        pst.setString(3, item.getDescription());
        pst.setString(4, item.getLocationFound());
        pst.setDate(5, item.getDateFound());
        pst.setString(6, item.getStatus());
        pst.executeUpdate();
        pst.close();
    }

    public List<FoundItem> getAllFoundItems() throws SQLException {
        List<FoundItem> list = new ArrayList<>();
        String sql = "SELECT * FROM found_items";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Integer matchedId = rs.getObject("matched_to_lost_item_id") != null ? rs.getInt("matched_to_lost_item_id") : null;

            FoundItem item = new FoundItem(
                rs.getInt("id"),
                rs.getString("finder_name"),
                rs.getString("item_name"),
                rs.getString("description"),
                rs.getString("location_found"),
                rs.getDate("date_found"),
                rs.getString("status"),
                matchedId
            );
            list.add(item);
        }
        rs.close();
        stmt.close();
        return list;
    }

    public void updateStatusAndMatch(int id, String newStatus, int matchedLostItemId) throws SQLException {
        String sql = "UPDATE found_items SET status = ?, matched_to_lost_item_id = ? WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, newStatus);
        pst.setInt(2, matchedLostItemId);
        pst.setInt(3, id);
        pst.executeUpdate();
        pst.close();
    }
}
