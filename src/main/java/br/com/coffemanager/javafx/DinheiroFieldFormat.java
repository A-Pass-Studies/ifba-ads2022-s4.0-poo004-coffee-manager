package br.com.coffemanager.javafx;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

final class DinheiroFieldFormat implements ChangeListener<String> {
	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		final var field = (StringProperty) observable;
		var numericTxt = newValue.replaceAll("[^\\d]", "");
		numericTxt = "000" + numericTxt;
		var sb = new StringBuilder(numericTxt).append(numericTxt);
		numericTxt = sb.insert(numericTxt.length() - 2, ".").toString();
		var numeric = NumberFormat.getCurrencyInstance().format(new BigDecimal(numericTxt));
		// if (!Objects.equals(newValue, numeric)) {
		field.setValue(numeric);
		// }
	}
}
