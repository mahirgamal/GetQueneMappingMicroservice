package com.domain;

import com.config.DatabaseConfig;
import com.function.QueueMappingResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetQueueMapping {

    public List<QueueMappingResponse> getMappingsByPublisherId(long publisherId) {
        List<QueueMappingResponse> mappings = new ArrayList<>();
        String query = "SELECT * FROM queue_mapping WHERE publisher_id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, publisherId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QueueMappingResponse response = new QueueMappingResponse();
                response.setPublisherId(rs.getLong("publisher_id"));
                response.setConsumerQueueName(rs.getString("consumer_queuename"));
                response.setEventType(rs.getString("event_type"));
                mappings.add(response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mappings;
    }
    
    // Additional methods to fetch by consumer queue name, event type, or other criteria can be added here.
}
