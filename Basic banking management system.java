import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class User {
    private String name;
    private int age;
    private long mobileNo;
    private String address;
    private int accountNo;
    private int balance;

    public User(String name, int age, long mobileNo, String address, int accountNo, int balance) {
        this.name = name;
        this.age = age;
        this.mobileNo = mobileNo;
        this.address = address;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public long getMobileNo() { return mobileNo; }
    public String getAddress() { return address; }
    public int getAccountNo() { return accountNo; }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }
}

public class BasicBankingApp {
    private static ArrayList<User> bank = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("Welcome to Our Banking System");
        while (true) {
            System.out.println("1) Create Account\n2) Login\n3) Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: createAccount(); break;
                case 2: login(); break;
                case 3: System.out.println("Thank you for using the Banking System!"); return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void createAccount() {
        System.out.println("Enter Name, Age, Mobile No, Address:");
        String name = sc.next();
        int age = sc.nextInt();
        long mobileNo = sc.nextLong();
        String address = sc.next();
        if (age < 16) {
            System.out.println("You must be at least 16 to create an account.");
            return;
        }
        int accountNo = rand.nextInt(900000) + 100000;
        bank.add(new User(name, age, mobileNo, address, accountNo, 0));
        System.out.println("Account created! Your Account No: " + accountNo);
    }

    private static void login() {
        System.out.println("Enter Name and Account No:");
        String name = sc.next();
        int accountNo = sc.nextInt();
        User user = findUser(name, accountNo);
        if (user != null) {
            System.out.println("1) Deposit\n2) Withdraw\n3) Transfer");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: deposit(user); break;
                case 2: withdraw(user); break;
                case 3: transfer(user); break;
                default: System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Invalid login details");
        }
    }

    private static User findUser(String name, int accountNo) {
        for (User user : bank) {
            if (user.getName().equals(name) && user.getAccountNo() == accountNo) {
                return user;
            }
        }
        return null;
    }

    private static void deposit(User user) {
        System.out.println("Enter amount to deposit:");
        int amount = sc.nextInt();
        user.setBalance(user.getBalance() + amount);
        System.out.println("Deposited " + amount + ". New Balance: " + user.getBalance());
    }

    private static void withdraw(User user) {
        System.out.println("Enter amount to withdraw:");
        int amount = sc.nextInt();
        if (amount > user.getBalance()) {
            System.out.println("Insufficient balance");
        } else {
            user.setBalance(user.getBalance() - amount);
            System.out.println("Withdrew " + amount + ". New Balance: " + user.getBalance());
        }
    }

    private static void transfer(User sender) {
        System.out.println("Enter receiver's Account No and amount:");
        int receiverAccNo = sc.nextInt();
        int amount = sc.nextInt();
        User receiver = bank.stream().filter(u -> u.getAccountNo() == receiverAccNo).findFirst().orElse(null);
        if (receiver != null) {
            if (amount > sender.getBalance()) {
                System.out.println("Insufficient balance");
            } else {
                sender.setBalance(sender.getBalance() - amount);
                receiver.setBalance(receiver.getBalance() + amount);
                System.out.println("Transferred " + amount + " to Account No " + receiverAccNo);
            }
        } else {
            System.out.println("Receiver account not found");
        }
    }
}
