

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import targetCaseStudy2.FertileLandCalc;

public class FertileLandCalcTest {
	
	@Test
	public void testMain() throws IOException {
		String[] args = {"{\"0 292 \"399 307\"}"};
		FertileLandCalc.main(args);
	}
	
	@Test
	public void testMainMultipleOneInput() throws IOException {
		String[] args = {"{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}"};
		FertileLandCalc.main(args);
	}
	
	@Test
	public void testMainMultiple() throws IOException {
		String[] args = {"{\"0 292 \"399 307\"}, {\"1 2 3 4\"}", ""};
		FertileLandCalc.main(args);
	}

	@Test
	public void testMainEmpty() throws IOException {
		String[] args = {""};
		FertileLandCalc.main(args);
	}

}
