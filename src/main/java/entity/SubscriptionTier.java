package entity;

public enum SubscriptionTier {
    TIER1(1.0f), TIER2(0.7f), TIER3(0.5f);
    float profitWeight;
    private SubscriptionTier(float profitWeight) {
        this.profitWeight = profitWeight;
    }
    public float getProfitWeight() {
        return this.profitWeight;
    }
}
