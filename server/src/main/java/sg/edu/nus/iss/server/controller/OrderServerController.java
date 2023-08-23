package sg.edu.nus.iss.server.controller;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.server.models.Order;
import sg.edu.nus.iss.server.service.OrderServerService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin()
public class OrderServerController {

    @Autowired
    private OrderServerService service;

    @GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(service.getOrders());
    }

    @GetMapping(path = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderByID(@PathVariable String id) {
        return ResponseEntity.ok(service.getOrderByID(id));
    }

    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewOrder(@RequestBody String payload) {

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject orderJson = reader.readObject();

        JsonObject success = Json.createObjectBuilder()
                .add("success", service.createNewOrder(orderJson))
                .build();

        return ResponseEntity.ok(success.toString());
    }

}
