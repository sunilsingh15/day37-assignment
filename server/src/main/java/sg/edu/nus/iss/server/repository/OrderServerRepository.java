package sg.edu.nus.iss.server.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.server.models.Order;
import sg.edu.nus.iss.server.models.OrderDetails;

@Repository
public class OrderServerRepository {

    @Autowired
    JdbcTemplate template;

    public final String GET_ORDERS_SQL = "select * from orders";
    public final String GET_ORDER_BY_ID_SQL = "select * from orders where id = ?";
    public final String GET_ORDER_DETAILS_SQL = "select * from order_details where order_id = ?";
    public final String INSERT_ORDER_SQL = "insert into orders values (?, ?, ?, ?)";
    public final String INSERT_ORDER_DETAILS_SQL = "insert into order_details (order_id, item_name, quantity, unit_price) values (?, ?, ?, ?)";

    public List<Order> getOrders() {

        List<Order> allOrders = new ArrayList<>();
        SqlRowSet rs = template.queryForRowSet(GET_ORDERS_SQL);

        while (rs.next()) {
            String orderID = rs.getString("id");
            List<OrderDetails> orderDetails = new ArrayList<>();

            SqlRowSet details = template.queryForRowSet(GET_ORDER_DETAILS_SQL, orderID);

            while (details.next()) {
                orderDetails.add(new OrderDetails(
                        details.getString("item_name"),
                        details.getInt("quantity"),
                        details.getDouble("unit_price")));
            }

            allOrders.add(new Order(
                    orderID,
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getBoolean("express_order"),
                    orderDetails));
        }

        return allOrders;
    }

    public Order getOrderByID(String orderID) {
        Order order = new Order();
        SqlRowSet rs = template.queryForRowSet(GET_ORDER_BY_ID_SQL, orderID);

        while (rs.next()) {
            String id = rs.getString("id");
            List<OrderDetails> orderDetails = new ArrayList<>();

            SqlRowSet details = template.queryForRowSet(GET_ORDER_DETAILS_SQL, orderID);

            while (details.next()) {
                orderDetails.add(new OrderDetails(
                        details.getString("item_name"),
                        details.getInt("quantity"),
                        details.getDouble("unit_price")));
            }

            order.setOrderID(id);
            order.setName(rs.getString("name"));
            order.setEmail(rs.getString("email"));
            order.setExpress(rs.getBoolean("express_order"));
            order.setOrderDetails(orderDetails);
        }

        return order;
    }

    public int insertOrder(Order order) {
        return template.update(INSERT_ORDER_SQL, order.getOrderID(), order.getName(), order.getEmail(),
                order.getExpress());
    }

    public void insertOrderDetails(Order order) {
        order.getOrderDetails().stream()
                .forEach(d -> template.update(INSERT_ORDER_DETAILS_SQL, order.getOrderID(), d.getItemName(),
                        d.getQuantity(), d.getUnitPrice()));
    }

}
