package com.compose.sandbox.android

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import xrp_price_tracker.MockData
import xrp_price_tracker.TrendDisplay
import xrp_price_tracker.XrpPriceCard

@Preview(name = "Pumping 🚀🚀", showBackground = true)
@Composable
fun XrpPriceCardPumpingPreview() {
    MaterialTheme {
        XrpPriceCard(MockData.pumpingXrp)
    }
}

@Preview(name = "Dumping 💩", showBackground = true)
@Composable
fun XrpPriceCardDumpingPreview() {
    MaterialTheme {
        XrpPriceCard(MockData.dumpingXrp)
    }
}

@Preview(name = "Neutral", showBackground = true)
@Composable
fun XrpPriceCardNeutralPreview() {
    MaterialTheme {
        XrpPriceCard(MockData.neutralXrp)
    }
}

@Preview(name = "Trend - Pumping", showBackground = true)
@Composable
fun TrendDisplayPumpingPreview() {
    MaterialTheme {
        TrendDisplay(changePercent = 12.5)
    }
}

@Preview(name = "Trend - Dumping", showBackground = true)
@Composable
fun TrendDisplayDumpingPreview() {
    MaterialTheme {
        TrendDisplay(changePercent = -8.3)
    }
}