package xrp_price_tracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.*

fun Double.formatDecimal(decimals: Int): String {
    val multiplier = 10.0.pow(decimals)
    val rounded = round(this * multiplier) / multiplier

    return buildString {
        append(rounded.toLong())
        append('.')
        val decimalPart = ((rounded - rounded.toLong()) * multiplier).toLong()
        append(decimalPart.toString().padStart(decimals, '0'))
    }
}

fun formatLargeNumber(num: Long): String {
    return when {
        num >= 1_000_000_000 -> "$${(num / 1_000_000_000.0).formatDecimal(2)}B"
        num >= 1_000_000 -> "$${(num / 1_000_000.0).formatDecimal(2)}M"
        num >= 1_000 -> "$${(num / 1_000.0).formatDecimal(2)}K"
        else -> "$$num"
    }
}

@Composable
fun XrpPriceCard(data: XrpSummary) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "$${data.priceUsd.formatDecimal(4)}",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            TrendDisplay(changePercent = data.priceChangePercentage24h)

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(
                    label = "Market Cap",
                    value = formatLargeNumber(data.marketCapUsd),
                    modifier = Modifier.weight(1f)
                )

                StatItem(
                    label = "24h Volume",
                    value = formatLargeNumber(data.volume24hUsd),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun TrendDisplay(changePercent: Double) {
    val emojiCount = (abs(changePercent) / 5.0).toInt()
    val emoji = if (changePercent > 0) "🚀" else "💩"
    val emojiString = emoji.repeat(emojiCount)

    val pumpDumpMessage = if (emojiCount > 0) {
        if (changePercent > 0) "Pump Status" else "Dump Status"
    } else {
        "24h Trend"
    }

    val trendEmoji = if (changePercent > 0) "📈" else "📉"
    val trendText = if (changePercent > 0) "UP" else "DOWN"
    val trendColor = if (changePercent > 0) Color(0xFF10b981) else Color(0xFFef4444)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            pumpDumpMessage,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                trendEmoji,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                trendText,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = trendColor
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                "${if (changePercent > 0) "+" else ""}${changePercent.formatDecimal(2)}%",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = trendColor
            )
        }

        if (emojiString.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                emojiString,
                fontSize = 32.sp
            )
        }
    }
}

@Composable
fun StatItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}