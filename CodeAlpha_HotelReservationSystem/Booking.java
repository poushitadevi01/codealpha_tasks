public class Booking {
    int bookingId;
    int roomId;
    String customerName;

    public Booking(int bookingId, int roomId, String customerName) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.customerName = customerName;
    }

    public String toCSV() {
        return bookingId + "," + roomId + "," + customerName;
    }
}
