/**
* Computes the periodical payment necessary to re-pay a given loan.
*/
public class LoanCalc {
	
	static double epsilon = 0.001;  // The computation tolerance (estimation error)
	static int iterationCounter;    // Monitors the efficiency of the calculation
	
    /** 
     * Gets the loan data and computes the periodical payment.
     * Expects to get three command-line arguments: sum of the loan (double),
     * interest rate (double, as a percentage), and number of payments (int).  
     */
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		
		// Computes the periodical payment using brute force search
		System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
	}
	
	/**
	* Uses a sequential search method  ("brute force") to compute an approximation
	* of the periodical payment that will bring the ending balance of a loan close to 0.
	* Given: the sum of the loan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
    	double payment = 0;
		double balance;
		int iterationCounter = 0;
		do {
			balance = endBalance(loan, rate, n, payment);
			payment += epsilon; 
			iterationCounter++; 
		} while (Math.abs(balance) > epsilon);
	    return payment;
    }
    
    /**
	* Uses bisection search to compute an approximation of the periodical payment 
	* that will bring the ending balance of a loan close to 0.
	* Given: the sum of theloan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
    	double low = 0; // Initial lower bound for bisection
		double high = loan * (1 + rate / 12); // Initial upper bound for bisection
		double guess = (low + high) / 2; // Initial guess

		int iterationCounter = 0;

		while (Math.abs(endBalance(loan, rate, n, guess)) > epsilon) {
			if (endBalance(loan, rate, n, guess) < 0) {
				high = guess;
			} else {
				low = guess;
			}

			guess = (low + high) / 2;

			iterationCounter++; 
		}
	    return guess;
    }
	
	/**
	* Computes the ending balance of a loan, given the sum of the loan, the periodical
	* interest rate (as a percentage), the number of periods (n), and the periodical payment.
	*/
	private static double endBalance(double loan, double rate, int n, double payment) {
	    double monthlyRate = rate / 100 / 12;
		double temp = Math.pow(1 + monthlyRate, n);
		double endingBalance = loan * (temp - (Math.pow(1 + monthlyRate, n) - 1) / monthlyRate) / temp + payment * ((temp - 1) / monthlyRate);
		return endingBalance;
	}
}