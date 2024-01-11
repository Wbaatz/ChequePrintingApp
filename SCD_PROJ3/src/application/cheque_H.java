package application;

public class cheque_H {
	String H_payee, H_date, H_bank, H_amount, H_wordings, H_types;

	public cheque_H(String h_payee, String h_date, String h_bank, String h_amount, String h_wordings, String h_types) {

		H_payee = h_payee;
		H_date = h_date;
		H_bank = h_bank;
		H_amount = h_amount;
		H_wordings = h_wordings;
		H_types = h_types;
	}

	

	public String getH_payee() {
		return H_payee;
	}

	public void setH_payee(String h_payee) {
		H_payee = h_payee;
	}

	public String getH_date() {
		return H_date;
	}

	public void setH_date(String h_date) {
		H_date = h_date;
	}

	public String getH_bank() {
		return H_bank;
	}

	public void setH_bank(String h_bank) {
		H_bank = h_bank;
	}

	public String getH_amount() {
		return H_amount;
	}

	public void setH_amount(String h_amount) {
		H_amount = h_amount;
	}

	public String getH_wordings() {
		return H_wordings;
	}

	public void setH_wordings(String h_wordings) {
		H_wordings = h_wordings;
	}

	public String getH_types() {
		return H_types;
	}

	public void setH_types(String h_types) {
		H_types = h_types;
	}
}
