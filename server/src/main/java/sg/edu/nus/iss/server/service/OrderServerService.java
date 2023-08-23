package sg.edu.nus.iss.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.server.models.Order;
import sg.edu.nus.iss.server.models.OrderDetails;
import sg.edu.nus.iss.server.repository.OrderServerRepository;

@Service
public class OrderServerService {

        @Autowired
        private OrderServerRepository repository;

        public JsonArray getAllOrdersJson() {

                List<Order> orders = repository.getOrders();
                JsonArrayBuilder array = Json.createArrayBuilder();

                for (Order order : orders) {
                        for (OrderDetails item : order.getOrderDetails()) {
                                JsonArrayBuilder detailsArray = Json.createArrayBuilder()
                                                .add(Json.createObjectBuilder()
                                                                .add("item_name", item.getItemName())
                                                                .add("quantity", item.getQuantity())
                                                                .add("unit_price", item.getQuantity())
                                                                .build());
                                array.add(detailsArray);
                        }

                        array.add(Json.createObjectBuilder()
                                        .add("id", order.getOrderID())
                                        .add("name", order.getName())
                                        .add("email", order.getEmail())
                                        .add("express", order.getExpress())
                                        .add("items", array)
                                        .build());
                }

                return array.build();
        }

        public List<Order> getOrders() {
                return repository.getOrders();
        }

        public Order getOrderByID(String orderID) {
                return repository.getOrderByID(orderID);
        }

        @Transactional(rollbackFor = DataAccessException.class)
        public String createNewOrder(JsonObject orderJson) {

                JsonArray detailsArray = orderJson.getJsonArray("items");

                List<OrderDetails> orderDetails = detailsArray.stream()
                                .map(i -> i.asJsonObject())
                                .map(i -> new OrderDetails(i.getString("name"), i.getInt("quantity"),
                                                i.getJsonNumber("unit_price").doubleValue()))
                                .toList();

                String orderID = UUID.randomUUID().toString().substring(0, 8);

                Order order = new Order(orderID, orderJson.getString("name"), orderJson.getString("email"),
                                orderJson.getBoolean("express"), orderDetails);

                try {
                        repository.insertOrder(order);
                } catch (DataAccessException e) {
                        System.out.println(e.getMessage());
                }

                try {
                        repository.insertOrderDetails(order);
                } catch (DataAccessException e) {
                        System.out.println(e.getMessage());
                }

                return orderID;
        }

}
