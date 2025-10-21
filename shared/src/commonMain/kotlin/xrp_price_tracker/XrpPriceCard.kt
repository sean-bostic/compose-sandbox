package xrp_price_tracker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.*

fun Double.formatDecimal(decimals: Int): String {
    val multiplier = 10.0.pow(decimals)
    val rounded = round(this * multiplier) / multiplier

    return buildString {
        append(rounded.toLong())
        append('.')
        val decimalPart = abs((rounded - rounded.toLong()) * multiplier).toLong()
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
fun XrpDataCard(data: XrpSummary) {
    val changePercent = data.priceChangePercentage24h
    val trendColor = if (changePercent > 0) Color(0xFF10b981) else Color.Red // TODO: The Color.Green looks like alien piss so figure out

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ){
            Text(
                text="XRP",
                fontWeight = FontWeight.ExtraBold
            )
            Box(
                modifier = Modifier
                    .background(
                        color = trendColor.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = trendColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    "${if (changePercent > 0) "+" else ""}${changePercent.formatDecimal(2)}%",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    ),
                    color = trendColor
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                BoxWithConstraints(
                    modifier = Modifier.weight(1f)
                ) {
                    val priceText = "$${data.priceUsd.formatDecimal(2)}"

                    val fontSize = when {
                        priceText.length <= 5 -> 90.sp  // $1.23 (5 chars)
                        priceText.length <= 6 -> 75.sp  // $12.34 (6 chars)
                        priceText.length <= 7 -> 65.sp  // $123.45 (7 chars)
                        priceText.length <= 8 -> 55.sp  // $1234.56 (8 chars)
                        else -> 45.sp                   // $12345.67+ (9+ chars)
                    }

                    Text(
                        priceText,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = fontSize
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        softWrap = false,
                        overflow = TextOverflow.Visible
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top
                ) {

                    Spacer(modifier = Modifier.height(20.dp))

                    Row {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "High",
                            tint = Color.Green,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            "$${data.high24hUsd.formatDecimal(3)}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }

                    Row {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Low",
                            tint = Color.Red,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            "$${data.low24hUsd.formatDecimal(3)}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

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

                VerticalDivider(modifier = Modifier.height(100.dp))

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

    val trendColor = if (changePercent > 0) Color(0xFF10b981) else Color(0xFFef4444)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Spacer(modifier = Modifier.width(8.dp))


            Spacer(modifier = Modifier.width(8.dp))

            Text(
                "${if (changePercent > 0) "+" else ""}${changePercent.formatDecimal(2)}%",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = trendColor
            )
        }

        if (emojiString.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    emojiString,
                    fontSize = 32.sp
                )
            }

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