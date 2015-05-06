package bda.model;

public class CurrencyPair {

    private final String baseCurrency;
    private final String quoteCurrency;
    private Double rate;

    private CurrencyPair(String baseCurrency, String quoteCurrency, Double rate){
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.rate = rate;
    }

    public static CurrencyPair withoutQuoteCurrency(String baseCurrency, Double rate) {
        return new CurrencyPair(baseCurrency, null, rate);
    }

    public static CurrencyPair withoutRate(String baseCurrency, String quoteCurrency) {
        return new CurrencyPair(baseCurrency, quoteCurrency, null);
    }

    public Double getRate() {
        return rate;
    }

    public String getBaseCurrency() {
        return this.baseCurrency;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CurrencyPair)) {
            return false;
        }
        final CurrencyPair other = (CurrencyPair) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$baseCurrency = this.baseCurrency;
        final Object other$baseCurrency = other.baseCurrency;
        if (this$baseCurrency == null ? other$baseCurrency != null : !this$baseCurrency.equals(other$baseCurrency))
            return false;
        final Object this$quoteCurrency = this.quoteCurrency;
        final Object other$quoteCurrency = other.quoteCurrency;
        return !(this$quoteCurrency == null ? other$quoteCurrency != null : !this$quoteCurrency.equals(other$quoteCurrency));
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $baseCurrency = this.baseCurrency;
        result = result * PRIME + ($baseCurrency == null ? 0 : $baseCurrency.hashCode());
        final Object $quoteCurrency = this.quoteCurrency;
        result = result * PRIME + ($quoteCurrency == null ? 0 : $quoteCurrency.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof CurrencyPair;
    }

    public String toString() {
        return "bda.model.CurrencyPair(baseCurrency=" + this.baseCurrency + ", quoteCurrency=" + this.quoteCurrency + ")";
    }
}
