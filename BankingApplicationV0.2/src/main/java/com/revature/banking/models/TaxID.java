package com.revature.banking.models;

public class TaxID {
	private String taxId;

	public TaxID() {
		super();
	}

	public TaxID(String taxId) {
		super();
		this.taxId = taxId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaxID other = (TaxID) obj;
		if (taxId == null) {
			if (other.taxId != null)
				return false;
		} else if (!taxId.equals(other.taxId))
			return false;
		return true;
	}

	public String getTaxId() {
		return taxId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taxId == null) ? 0 : taxId.hashCode());
		return result;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	@Override
	public String toString() {
		return "TaxID [taxId=" + taxId + "]";
	}
}
