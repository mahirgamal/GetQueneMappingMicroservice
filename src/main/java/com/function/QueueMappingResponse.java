package com.function;

public class QueueMappingResponse {
    private long publisherId;
    private String consumerQueueName;
    private String eventType;

    // Getters and setters
    public long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    public String getConsumerQueueName() {
        return consumerQueueName;
    }

    public void setConsumerQueueName(String consumerQueueName) {
        this.consumerQueueName = consumerQueueName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
