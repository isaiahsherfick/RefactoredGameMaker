package model;

public class MainModel {

    private final double accountBalance;
    
    public MainModel () {
    	this.accountBalance = 100;
    }

    //getters, setters, constructor

    public double getAccountBalance() {
		return accountBalance;
	}
    
    private double setAccountBalance(double balance) {
		return accountBalance;
	}

	public void deposit(double amount){
		setAccountBalance(getAccountBalance() + amount);
    }

    public void withdraw(double amount){
    	setAccountBalance(getAccountBalance() - amount);
    }
}