// File: src/main/java/com/example/demo/entity/Event.java
// Add these methods to your existing Event class:

// Test expects setActive(boolean) not setIsActive(Boolean)
public void setActive(Boolean active) {
    this.isActive = active;
}

// Test expects isActive() not getIsActive()
public Boolean isActive() {
    return isActive;
}

// Test expects getLastUpdatedAt() - add if needed
private Timestamp lastUpdatedAt;

public Timestamp getLastUpdatedAt() {
    return lastUpdatedAt;
}

public void setLastUpdatedAt(Timestamp lastUpdatedAt) {
    this.lastUpdatedAt = lastUpdatedAt;
}

// Test expects onUpdate() method
@PreUpdate
protected void onUpdate() {
    lastUpdatedAt = Timestamp.valueOf(LocalDateTime.now());
}

// Change onCreate() from protected to public if tests call it
@PrePersist
public void onCreate() {
    createdAt = Timestamp.valueOf(LocalDateTime.now());
    if (lastUpdatedAt == null) {
        lastUpdatedAt = createdAt;
    }
}