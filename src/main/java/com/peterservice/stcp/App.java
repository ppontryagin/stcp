package com.peterservice.stcp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	new StudentNetworkSimulator(10, 0, 0.1, 10, 1, 5).runSimulator();
    }
}
