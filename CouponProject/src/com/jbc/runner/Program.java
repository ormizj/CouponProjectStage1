package com.jbc.runner;

/**
 * {@code class} that runs the method in the <code>Test</code> {@code class}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see runner#Test
 */
public final class Program {

	/**
	 * <code>main</code> that runs the <code>testAll</code> method in the
	 * <code>Test</code> {@code class}.
	 * 
	 * @param args
	 * @see Test
	 */
	public static void main(String[] args) {
		Test.testAll();
	}

}
