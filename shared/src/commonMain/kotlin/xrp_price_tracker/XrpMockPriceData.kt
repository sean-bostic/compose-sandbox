package xrp_price_tracker

data class XrpSummary(
    val priceUsd: Double,
    val priceChangePercentage24h: Double,
    val marketCapUsd: Long,
    val volume24hUsd: Long
)

object MockData {
    val pumpingXrp = XrpSummary(
        priceUsd = 2.5432,
        priceChangePercentage24h = 12.5,
        marketCapUsd = 140_000_000_000,
        volume24hUsd = 8_000_000_000
    )

    val dumpingXrp = XrpSummary(
        priceUsd = 1.8234,
        priceChangePercentage24h = -8.3,
        marketCapUsd = 98_000_000_000,
        volume24hUsd = 6_000_000_000
    )

    val neutralXrp = XrpSummary(
        priceUsd = 2.1234,
        priceChangePercentage24h = 1.2,
        marketCapUsd = 120_000_000_000,
        volume24hUsd = 5_000_000_000
    )
}