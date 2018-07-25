package com.steveh1uk.breadshop.core.reserve;

import java.math.BigDecimal;

public final class MoneyUtil {
	
	private MoneyUtil() {}
	
	public static BigDecimal convertToMain(Integer money) {
		BigDecimal pricePence = new BigDecimal(money);
		return pricePence.divide(new BigDecimal(100));
	}

}
