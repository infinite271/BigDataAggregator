package bda.model;

public class Transaction {

    private final String partner;
    private final String currency;
    private final Double amount;

    public Transaction(String partner, String currency, Double amount) {
        this.partner = partner;
        this.currency = currency;
        this.amount = amount;
    }

    public String getPartner() {
        return this.partner;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Double getAmount() {
        return this.amount;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        final Transaction other = (Transaction) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$partner = this.partner;
        final Object other$partner = other.partner;
        if (this$partner == null ? other$partner != null : !this$partner.equals(other$partner)) return false;
        final Object this$currency = this.currency;
        final Object other$currency = other.currency;
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final Object this$amount = this.amount;
        final Object other$amount = other.amount;
        return !(this$amount == null ? other$amount != null : !this$amount.equals(other$amount));
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $partner = this.partner;
        result = result * PRIME + ($partner == null ? 0 : $partner.hashCode());
        final Object $currency = this.currency;
        result = result * PRIME + ($currency == null ? 0 : $currency.hashCode());
        final Object $amount = this.amount;
        result = result * PRIME + ($amount == null ? 0 : $amount.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Transaction;
    }

    public String toString() {
        return "bda.model.Transaction(partner=" + this.partner + ", currency=" + this.currency + ", amount=" + this.amount + ")";
    }
}
