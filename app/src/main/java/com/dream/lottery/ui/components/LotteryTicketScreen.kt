package com.dream.lottery.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dream.lottery.data.LotteryTicket


@Composable
fun LotteryTicketsScreen(tickets: List<LotteryTicket>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lottery Tickets",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
        )
        LazyColumn {
            items(tickets){ lotteryTicket ->
                TicketView(lotteryTicket)
            }
        }
    }
}

@Composable
fun TicketView(ticket: LotteryTicket) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp,
        backgroundColor = if (ticket.isWinner) Color.Green.copy(alpha = 0.2f) else Color.Red.copy(alpha = 0.2f)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Ticket Numbers: ${ticket.numbers.joinToString(", ")}",
                fontSize = 18.sp
            )
            Text(
                text = if (ticket.isWinner) "Status: Winner" else "Status: Not a Winner",
                fontSize = 18.sp,
                color = if (ticket.isWinner) Color(0xFF006400) else Color.Red
            )
        }
    }
}
