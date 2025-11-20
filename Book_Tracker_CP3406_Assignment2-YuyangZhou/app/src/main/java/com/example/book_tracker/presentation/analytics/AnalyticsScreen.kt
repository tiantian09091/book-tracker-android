package com.example.book_tracker.presentation.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AnalyticsScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Analytics",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // Stats Overview
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = "24h",
                    label = "Study Time This Week",
                    color = Color(0xFF3B82F6)
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = "87%",
                    label = "Tasks Completed",
                    color = Color(0xFF10B981)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = "3.4",
                    label = "Average GPA",
                    color = Color(0xFF8B5CF6)
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = "5",
                    label = "Courses This Semester",
                    color = Color(0xFFF59E0B)
                )
            }
        }

        // Weekly Study Pattern
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Weekly Study Pattern",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    listOf(
                        "Mon" to 4.2f,
                        "Tue" to 3.1f,
                        "Wed" to 4.8f,
                        "Thu" to 3.7f,
                        "Fri" to 2.1f,
                        "Sat" to 1.5f,
                        "Sun" to 1.0f
                    ).forEach { (day, hours) ->
                        StudyPatternBar(day = day, hours = hours, maxHours = 5f)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }

        // Course Performance
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Course Performance",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    CoursePerformanceItem(
                        courseName = "CP3406 Database Systems",
                        tasksCompleted = 15,
                        grade = "A-",
                        gradeColor = Color(0xFF10B981)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    CoursePerformanceItem(
                        courseName = "CP5307 Mobile Development",
                        tasksCompleted = 12,
                        grade = "B+",
                        gradeColor = Color(0xFF3B82F6)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    CoursePerformanceItem(
                        courseName = "CP3307 Data Structures",
                        tasksCompleted = 18,
                        grade = "A",
                        gradeColor = Color(0xFF10B981)
                    )
                }
            }
        }

        // Bottom padding
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    color: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun StudyPatternBar(day: String, hours: Float, maxHours: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(40.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(12.dp)
                .background(Color.LightGray.copy(alpha = 0.3f), shape = RoundedCornerShape(6.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(hours / maxHours)
                    .background(Color(0xFF3B82F6), shape = RoundedCornerShape(6.dp))
            )
        }

        Text(
            text = "${hours}h",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(40.dp)
        )
    }
}

@Composable
fun CoursePerformanceItem(
    courseName: String,
    tasksCompleted: Int,
    grade: String,
    gradeColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradeColor.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = courseName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$tasksCompleted tasks completed",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = grade,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = gradeColor
        )
    }
}

