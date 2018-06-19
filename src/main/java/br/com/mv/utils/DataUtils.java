package br.com.mv.utils;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class DataUtils {

	public static int diferencaEntreDatasEmAnos(LocalDate dataInicio, LocalDate dataFim) {

		return Years.yearsBetween(dataInicio, dataFim).getYears();
	}

}
