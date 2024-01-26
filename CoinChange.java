import java.util.Scanner;

public class CoinChange {

    // Function to find the minimum number of coins needed to reach the target amount
    public static int minCoins(int[] coins, int amount) {
        // Create an array to store the minimum number of coins needed for each amount
        int[] dp = new int[amount + 1];

        // Initialize the dp array with a value larger than any possible solution
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        // Initialize dp[0] to 0, as no coins are needed to make an amount of 0
        dp[0] = 0;
        // Iterate through each denomination of coins
        for (int i = 0; i < coins.length; i++) {
            // Get the value of the current coin denomination
            int coin = coins[i];

            // Iterate through each amount starting from the coin value
            for (int currentAmount = coin; currentAmount <= amount; currentAmount++) {
                // Check if using the current coin reduces the number of coins needed
                if (dp[currentAmount - coin] != Integer.MAX_VALUE) {
                    dp[currentAmount] = Math.min(dp[currentAmount], dp[currentAmount - coin] + 1);
                }
            }
        }

        // If dp[amount] is still value which was at the start, it's not possible to make the target amount
        if (dp[amount] == Integer.MAX_VALUE)
            return -1;
        else {
            return dp[amount];
        }
    }

    public static void main(String[] args) {
        // Create a scanner object to read input from the user
        Scanner sc = new Scanner(System.in);

        // Input the number of denominations
        System.out.print("Enter the number of denominations: ");
        int n = sc.nextInt();

        // Input the denominations
        System.out.print("Enter the denominations separated by space: ");
        int[] denominations = new int[n];
        for (int i = 0; i < n; i++) {
            denominations[i] = sc.nextInt();
        }

        // Input the target amount
        System.out.print("Enter the target amount: ");
        int targetAmount = sc.nextInt();

        // Called the function that found minimum number of coins and stored it in result
        int result = minCoins(denominations, targetAmount);
        // Printed the result
        if (result != -1) {
            System.out.println("The minimum number of coins needed: " + result);
        } else {
            System.out.println("It's not possible to make the target amount with the given denominations.");
        }
    }
}
