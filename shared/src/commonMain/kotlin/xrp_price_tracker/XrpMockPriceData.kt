package xrp_price_tracker

data class XrpSummary(
    val priceUsd: Double,
    val priceChangePercentage24h: Double,
    val marketCapUsd: Long,
    val volume24hUsd: Long,
    val high24hUsd: Double,
    val low24hUsd: Double
)

object MockData {
    val pumpingXrp = XrpSummary(
        priceUsd = 22.23,
        priceChangePercentage24h = 15.0,
        marketCapUsd = 500_000_000_000,
        volume24hUsd = 38_000_000_000,
        high24hUsd = 22.59,
        low24hUsd = 21.40
    )

    val dumpingXrp = XrpSummary(
        priceUsd = 1.82,
        priceChangePercentage24h = -8.3,
        marketCapUsd = 98_000_000_000,
        volume24hUsd = 6_000_000_000,
        high24hUsd = 1.90,
        low24hUsd = 1.74
    )

    val neutralXrp = XrpSummary(
        priceUsd = 2.12,
        priceChangePercentage24h = 1.2,
        marketCapUsd = 120_000_000_000,
        volume24hUsd = 5_000_000_000,
        high24hUsd = 2.15,
        low24hUsd = 2.10
    )
}