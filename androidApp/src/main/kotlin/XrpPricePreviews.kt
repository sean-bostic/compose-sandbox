package com.compose.sandbox.android

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import xrp_price_tracker.MockData
import xrp_price_tracker.XrpDataCard

@Preview(name = "Pumping 🚀🚀", showBackground = true)
@Composable
fun XrpPriceCardPumpingPreview() {
    MaterialTheme {
        XrpDataCard(MockData.pumpingXrp)
    }
}

@Preview(name = "Dumping 💩", showBackground = true)
@Composable
fun XrpPriceCardDumpingPreview() {
    MaterialTheme {
        XrpDataCard(MockData.dumpingXrp)
    }
}

@Preview(name = "Neutral", showBackground = true)
@Composable
fun XrpPriceCardNeutralPreview() {
    MaterialTheme {
        XrpDataCard(MockData.neutralXrp)
    }
}
