import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class User{
    private String name;
    private int Age;
    private long Mobile_no;
    private String Address;
    private int Account_no;
    private int Balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public long getMobile_no() {
        return Mobile_no;
    }

    public void setMobile_no(long mobile_no) {
        Mobile_no = mobile_no;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAccount_no() {
        return Account_no;
    }

    public void setAccount_no(int account_no) {
        Account_no = account_no;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }
    public User(){

    }

    public User(String name, int age, long mobile_no, String address, int account_no, int balance) {
        this.name = name;
        Age = age;
        Mobile_no = mobile_no;
        Address = address;
        Account_no = account_no;
        Balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", Age='" + Age + '\'' +
                ", Mobile_no='" + Mobile_no + '\'' +
                ", Address='" + Address + '\'' +
                ", Account_no='" + Account_no + '\'' +
                ", Balance='" + Balance + '\'' +
                '}';
    }
}
public class baisic_banking_app {
    static boolean e = true;
    public static ArrayList<User> bank = new ArrayList<>();
    public static void createAccount(){
        User u = new User();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Name: ");
        String name =sc.next();
        u.setName(name);

        System.out.println("Enter your age: ");
        if(sc.hasNextInt()){
            int age =sc.nextInt();
            u.setAge(age);
            if(age<16) {
//                System.out.println("You are under 16 and can't allow to create account");
//                return;
                throw new RuntimeException("You are under 16 and can't allow to create account");
            }
        }else{
            System.out.println("Enter number only");
            return;
        }

        System.out.println("Enter your mobile no.: ");
        if(sc.hasNextLong()){
            long mob =sc.nextLong();
            u.setMobile_no(mob);
        }else{
//            System.out.println("Enter a valid mobile number");
//            return;
            throw new RuntimeException("Enter a valid mobile number");
        }

        System.out.println("Enter your Address: ");
        String Address =sc.next();
        u.setAddress(Address);

        Random r  =new Random();
        int no = r.nextInt(100000,999999);
        u.setAccount_no(no);
        System.out.println("Here's your Account no.: "+u.getAccount_no());

        bank.add(u);
        System.out.println("Your Account Created Successfully");
    }
    public static void login(Scanner sc){
        System.out.println("Enter your name: ");
        String n=sc.next();
        System.out.println("Enter your Bank Account no.: ");
        if(sc.hasNextInt()){
            int k=sc.nextInt();
            for (int i = 0; i < bank.size(); i++) {
                if(bank.get(i).getName().equals(n) && bank.get(i).getAccount_no()==(k)){
                    System.out.println("Login Successfully ");
                    System.out.println("1) Deposit ");
                    System.out.println("2) Withdraw ");
                    System.out.println("3) Transfer Money ");
                    System.out.println("Enter your choice: ");
                    if(sc.hasNextInt()){
                        int ch = sc.nextInt();
                        switch (ch){
                            case 1 :
                                deposit(sc);
                                return;
                            case 2 :
                                withdraw(sc);
                                return;
                            case 3 :
                                transfer(sc);
                                return;
                            default:
                                System.out.println("Invalid Choice ");
                                break;

                        }
                    }else{
                        System.out.println("Enter a valid choice");
                        return;
                    }
                }
            }
        }else{
            System.out.println("Enter a valid Account number ");
            return;
        }
    }
    public static void deposit(Scanner sc){
        System.out.println("Enter your Bank Account no.: ");
        if(sc.hasNextInt()){
            int k=sc.nextInt();
            for (int i = 0; i < bank.size(); i++) {
                if (bank.get(i).getAccount_no()==(k)) {
                    System.out.println("How much amount you want to deposit? ");
                    if(sc.hasNextInt()){
                        int cash= sc.nextInt();
                        System.out.println(cash+"Rs deposit in your Account");
                        int dcash= bank.get(i).getBalance()+cash;
                        bank.get(i).setBalance(dcash);
                        System.out.println("Your Bank Balance is :"+dcash);
                    }else{
                        System.out.println("Enter amount only ");
                        return;
                    }
                }
            }
        }else{
            System.out.println("Enter a valid Account number ");
            return;
        }
    }
    public static void withdraw(Scanner sc){
        System.out.println("Enter your Bank Account no.: ");
        if(sc.hasNextInt()) {
            int k = sc.nextInt();
            for (int i = 0; i < bank.size(); i++) {
                if (bank.get(i).getAccount_no() == (k)) {
                    if (bank.get(i).getBalance() == 0) {
                        System.out.println("Your Bank Balance is 0 \nyou cannot withdraw money ");
                        return;
                    }
                    System.out.println("How much amount you want to withdraw?");
                    if(sc.hasNextInt()){
                        int money = sc.nextInt();
                        if (money < bank.get(i).getBalance()) {
                            System.out.println(money + "Rs withdraw from your Account");
                            int wcash = bank.get(i).getBalance() - money;
                            bank.get(i).setBalance(wcash);
                            System.out.println("Your Bank Balance is :" + wcash);
                        }
                    }else{
                        System.out.println("Enter amount only ");
                        return;
                    }
                }
            }
        }else{
            System.out.println("Enter a valid Account number ");
            return;
        }
    }
    public static void transfer(Scanner sc){
        System.out.println("Enter your Bank Account no.: ");
        if(sc.hasNextInt()){
            int k=sc.nextInt();
            for (int i = 0; i < bank.size(); i++) {
                if (bank.get(i).getAccount_no()==(k)) {
                    System.out.println("Balance in your Account: "+bank.get(i).getBalance());
                    if(bank.get(i).getBalance()==0) {
                        System.out.println("Your Bank Balance is 0 \n you cannot transfer money ");
                        return;
                    }

                    System.out.println("Enter Receiver's Account no.: ");
                    if(sc.hasNextInt()){
                        int rno = sc.nextInt();
                        for (int j = 0; j < bank.size(); j++) {
                            if (bank.get(j).getAccount_no() == (rno)) {
                                System.out.println("How much money you want to transfer? ");
                                if(sc.hasNextInt()){
                                    int newmoney = sc.nextInt();
                                    if (bank.get(i).getBalance() > newmoney) {
                                        int newbalance2 = bank.get(i).getBalance() - newmoney;
                                        bank.get(i).setBalance(newbalance2);
                                        System.out.println(bank.get(i).getName()+" your current bank balance is Rs " + bank.get(i).getBalance());
                                    }else{
                                        System.out.println("Insufficient balance");
                                        return;
                                    }
                                    int receiversbalance = bank.get(j).getBalance() + newmoney;
                                    bank.get(j).setBalance(receiversbalance);
                                    System.out.println(bank.get(j).getName()+" your current bank balance is Rs " + bank.get(j).getBalance());
                                    System.out.println("Money Transferred Successfully ");
                                }else{
                                    System.out.println("Enter amount only ");
                                    return;
                                }
                            }
                        }
                    }else{
                        System.out.println("Enter a valid Account number ");
                        return;
                    }
                }
            }
        }else{
            System.out.println("Enter a valid Account number ");
            return;
        }
    }
    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        System.out.println();
        System.out.println("ThankYou For using Banking System!!!");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("\t\t\tWelcome to Our Banking System ");
        while(e){
            System.out.println("1) Create Bank Account ");
            System.out.println("2) Login ");
            System.out.println("3) Exit ");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your choice: ");
            if(sc.hasNextInt()){
                int userchoice =sc.nextInt();
                switch (userchoice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        login(sc);
                        break;
                    case 3:
                        exit();
                        return;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }else{
                System.out.println("Enter a valid choice");
                return;
            }

        }
    }
}