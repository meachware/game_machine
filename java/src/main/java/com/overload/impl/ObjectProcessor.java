package com.overload.impl;

/**
 * Definition to process object(s).
 * @author Odell
 *
 * @param <I>
 * 		input type.
 * @param <O>
 * 		output type.
 */
public interface ObjectProcessor<I,O> {
	/**
	 * Process input to output.
	 * @param input
	 * 		the input to process.
	 * @return output from processed input.
	 */
	public O process(I input);
}