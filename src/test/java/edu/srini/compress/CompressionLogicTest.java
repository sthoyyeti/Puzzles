package edu.srini.compress;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompressionLogicTest {

	CompressionLogic cp;
	private static final String SEED_1_LINE_1 = "1";
	private static final String SEED_1_LINE_2 = "1 1";
	private static final String SEED_1_LINE_3 = "2 1";
	private static final String SEED_1_LINE_4 = "1 2 1 1";
	private static final String SEED_1_LINE_5 = "1 1 1 2 2 1";
	private static final String SEED_1_LINE_6 = "3 1 2 2 1 1";
	private static final String SEED_1_LINE_7 = "1 3 1 1 2 2 2 1";
	private static final String SEED_1_LINE_8 = "1 1 1 3 2 1 3 2 1 1";
	private static final String SEED_1_LINE_9 = "3 1 1 3 1 2 1 1 1 3 1 2 2 1";

	private static final String SEED_99_LINE_9 = "1 1 1 3 1 2 2 1 1 3 1 2 1 1 1 3 2 2 2 1 1 99";

	@Before
	public void prepare() {
		cp = new CompressionLogic();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithIllegalInput() {
		cp.getNthCompressedLine(-1, 2);
	}

	@Test
	public void testWithSeed1andLine6() {
		String nthCompressedLine = cp.getNthCompressedLine(1, 6).trim();
		Assert.assertEquals(SEED_1_LINE_6, nthCompressedLine);
	}

	@Test
	public void testWithSeed99andLine6() {
		String nthCompressedLine = cp.getNthCompressedLine(99, 9).trim();
		Assert.assertEquals(SEED_99_LINE_9, nthCompressedLine);
	}
}
