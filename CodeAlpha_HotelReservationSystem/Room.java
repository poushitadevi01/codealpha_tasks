public class Room {
    int roomId;
    String category;
    boolean isBooked;
    double price;

    public Room(int roomId, String category, double price) {
        this.roomId = roomId;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }

    public String toCSV() {
        return roomId + "," + category + "," + isBooked + "," + price;
    }
}
