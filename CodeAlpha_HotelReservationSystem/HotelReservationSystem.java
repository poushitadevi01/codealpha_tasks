import java.io.*;
import java.util.*;

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static int bookingCounter = 1;

    public static void main(String[] args) {
        loadRooms();
        loadBookings();
        initializeRooms();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Search Rooms\n2. Book Room\n3. Cancel Booking\n4. View Bookings\n5. Exit");
            System.out.print("Choose option: ");
            int opt = sc.nextInt();
            sc.nextLine();

            if (opt == 1) searchRooms();
            else if (opt == 2) {
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                System.out.print("Enter Room ID: ");
                int id = sc.nextInt();
                bookRoom(id, name);
            }
            else if (opt == 3) {
                System.out.print("Enter Booking ID: ");
                int bid = sc.nextInt();
                cancelBooking(bid);
            }
            else if (opt == 4) viewBookings();
            else if (opt == 5) break;
        }
    }

    static void initializeRooms() {
        if (rooms.isEmpty()) {
            rooms.add(new Room(101, "Standard", 1200));
            rooms.add(new Room(102, "Deluxe", 2000));
            rooms.add(new Room(103, "Suite", 3500));
            saveRooms();
        }
    }

    static void searchRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms)
            if (!r.isBooked)
                System.out.println("Room " + r.roomId + " | " + r.category + " | ₹" + r.price);
    }

    static void bookRoom(int roomId, String name) {
        for (Room r : rooms) {
            if (r.roomId == roomId && !r.isBooked) {
                r.isBooked = true;
                bookings.add(new Booking(bookingCounter++, roomId, name));
                saveRooms();
                saveBookings();
                System.out.println("Room booked! Booking ID: " + (bookingCounter - 1));
                return;
            }
        }
        System.out.println("Room not available!");
    }

    static void cancelBooking(int bookingId) {
        Iterator<Booking> it = bookings.iterator();
        while (it.hasNext()) {
            Booking b = it.next();
            if (b.bookingId == bookingId) {
                for (Room r : rooms)
                    if (r.roomId == b.roomId)
                        r.isBooked = false;
                it.remove();
                saveRooms();
                saveBookings();
                System.out.println("Booking cancelled!");
                return;
            }
        }
        System.out.println("Booking ID not found!");
    }

    static void viewBookings() {
        System.out.println("\nBookings:");
        for (Booking b : bookings)
            System.out.println("Booking " + b.bookingId + " | Room " + b.roomId + " | Name: " + b.customerName);
    }

    static void saveRooms() {
        try (PrintWriter pw = new PrintWriter(new File("rooms.csv"))) {
            for (Room r : rooms) pw.println(r.toCSV());
        } catch (Exception e) {}
    }

    static void saveBookings() {
        try (PrintWriter pw = new PrintWriter(new File("bookings.csv"))) {
            for (Booking b : bookings) pw.println(b.toCSV());
        } catch (Exception e) {}
    }

    static void loadRooms() {
        try (BufferedReader br = new BufferedReader(new FileReader("rooms.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                Room r = new Room(Integer.parseInt(d[0]), d[1], Double.parseDouble(d[3]));
                r.isBooked = Boolean.parseBoolean(d[2]);
                rooms.add(r);
            }
        } catch (Exception e) {}
    }

    static void loadBookings() {
        try (BufferedReader br = new BufferedReader(new FileReader("bookings.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                bookings.add(new Booking(Integer.parseInt(d[0]), Integer.parseInt(d[1]), d[2]));
            }
        } catch (Exception e) {}
    }

    static void loadBookings() {}
    static void initializeRooms() {}
}
