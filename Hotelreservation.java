import java.util.*;

class Room {
    int number;
    String type;
    double price;
    boolean isBooked;

    Room(int number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    Room room;
    int nights;

    Booking(String customerName, Room room, int nights) {
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
    }

    void display() {
        System.out.println("🔖 Booking Details:");
        System.out.println("👤 Name: " + customerName);
        System.out.println("🏨 Room No: " + room.number + " | Type: " + room.type);
        System.out.println("🛏 Nights: " + nights);
        System.out.println("💰 Total Cost: ₹" + (room.price * nights));
    }
}

public class Hotelreservation {
    static Scanner sc = new Scanner(System.in);
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        loadRooms();
        int choice;

        System.out.println("🏨 Welcome to Java Hotel Reservation System");

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. 🔍 View Available Rooms");
            System.out.println("2. 📝 Make a Reservation");
            System.out.println("3. 📋 View Bookings");
            System.out.println("4. 🚪 Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> makeReservation();
                case 3 -> viewBookings();
                case 4 -> System.out.println("👋 Thank you for using our system!");
                default -> System.out.println("❌ Invalid choice!");
            }
        } while (choice != 4);
    }

    static void loadRooms() {
        rooms.add(new Room(101, "Single", 1200));
        rooms.add(new Room(102, "Single", 1200));
        rooms.add(new Room(201, "Double", 2000));
        rooms.add(new Room(202, "Double", 2000));
        rooms.add(new Room(301, "Deluxe", 3000));
        rooms.add(new Room(302, "Deluxe", 3000));
    }

    static void viewAvailableRooms() {
        System.out.println("\n📄 Available Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room No: " + room.number + " | Type: " + room.type + " | Price: ₹" + room.price);
            }
        }
    }

    static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        viewAvailableRooms();

        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();
        System.out.print("Enter number of nights: ");
        int nights = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.number == roomNo && !room.isBooked) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("❌ Invalid or already booked room.");
            return;
        }

        double total = selectedRoom.price * nights;
        System.out.println("💵 Total to Pay: ₹" + total);
        System.out.print("💳 Enter 'yes' to confirm payment: ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            selectedRoom.isBooked = true;
            Booking booking = new Booking(name, selectedRoom, nights);
            bookings.add(booking);
            System.out.println("✅ Booking Confirmed!");
        } else {
            System.out.println("❌ Payment Cancelled. Booking not completed.");
        }
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("📭 No bookings yet.");
            return;
        }

        for (Booking booking : bookings) {
            System.out.println("-------------------------------");
            booking.display();
        }
    }
}
