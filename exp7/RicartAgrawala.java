import java.util.*;


public class RicartAgrawala {
    static int clock = 0;
    static void requestCS(int id) {
        clock++;
        System.out.println("Process " + id + " requesting CS at clock " + clock);
    }
    static void receiveRequest(int id, int senderClock) {
        clock = Math.max(clock, senderClock) + 1;
        System.out.println("Process " + id + " received request at clock " + clock);
    }
    static void releaseCS(int id) {
        clock++;
        System.out.println("Process " + id + " released CS at clock " + clock);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter process ID: ");
        int id = sc.nextInt();
        requestCS(id);
        System.out.print("Enter sender's clock: ");
        int senderClock = sc.nextInt();
        receiveRequest(id, senderClock);
        releaseCS(id);
        }
    }