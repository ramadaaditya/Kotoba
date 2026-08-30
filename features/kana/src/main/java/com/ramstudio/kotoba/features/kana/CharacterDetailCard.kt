package com.ramstudio.kotoba.features.kana

import android.graphics.DashPathEffect
import android.graphics.PathMeasure
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.graphics.nativeCanvas
import java.util.Locale

@Composable
fun CharacterDetailCard(
    kana: String,
    romaji: String,
    categoryTitle: String,
    onClose: () -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // TextToSpeech for pronunciation (initialized safely)
    val ttsState = remember { androidx.compose.runtime.mutableStateOf<TextToSpeech?>(null) }
    LaunchedEffect(Unit) {
        var ttsLocal: TextToSpeech? = null
        ttsLocal = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                try {
                    val ja = Locale("ja", "JP")
                    val res = ttsLocal?.setLanguage(ja)
                    if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.w("CharacterDetailCard", "Japanese TTS not available: $res")
                    }
                } catch (t: Exception) {
                    Log.w("CharacterDetailCard", "TTS language not available", t)
                }
            }
        }
        ttsState.value = ttsLocal
    }

    DisposableEffect(Unit) {
        onDispose {
            try {
                ttsState.value?.stop()
                ttsState.value?.shutdown()
            } catch (_: Exception) {
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xBB000000)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.92f),
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 20.dp, vertical = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onClose) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                        }

                        Text(
                            text = categoryTitle,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Box(modifier = Modifier.size(40.dp))
                    }

                    // Big kana
                    Text(
                        text = kana,
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    // Romaji
                    Text(text = romaji, fontSize = 20.sp, color = Color.Black)

                    // Play pronunciation button
                    Button(
                        onClick = {
                            try {
                                ttsState.value?.language = Locale.JAPANESE
                                ttsState.value?.speak(kana, TextToSpeech.QUEUE_FLUSH, null, "kana")
                            } catch (t: Exception) {
                                Log.w("CharacterDetailCard", "TTS speak failed", t)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B))
                    ) {
                        Text(text = "Dengar Pengucapan", color = Color.White)
                    }

                    // Stroke order preview with animation
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Stroke Order", fontWeight = FontWeight.Bold)

                        val anim = remember { Animatable(0f) }
                        val density = LocalDensity.current

                        Canvas(modifier = Modifier
                            .padding(vertical = 12.dp)
                            .size(240.dp)) {
                            val canvasWidth = size.width
                            val canvasHeight = size.height

                            drawIntoCanvas { canvas ->
                                val androidPaint = android.graphics.Paint().apply {
                                    isAntiAlias = true
                                    style = android.graphics.Paint.Style.STROKE
                                    strokeCap = android.graphics.Paint.Cap.ROUND
                                }

                                val textSizePx = minOf(canvasWidth, canvasHeight) * 0.7f
                                androidPaint.strokeWidth = textSizePx * 0.08f
                                androidPaint.textSize = textSizePx

                                val path = android.graphics.Path()
                                // baseline y = textSizePx to approximate a centered baseline
                                androidPaint.getTextPath(kana, 0, kana.length, 0f, textSizePx, path)

                                // center path in canvas
                                val bounds = android.graphics.RectF()
                                path.computeBounds(bounds, true)

                                val dx = (canvasWidth - bounds.width()) / 2f - bounds.left
                                val dy = (canvasHeight + bounds.height()) / 2f - bounds.bottom
                                path.offset(dx, dy)

                                // Draw background (grey) full path
                                val bgPaint = android.graphics.Paint(androidPaint).apply {
                                    color = androidx.compose.ui.graphics.Color(0xFFE3E3E3).toArgb()
                                    strokeWidth = androidPaint.strokeWidth
                                    style = android.graphics.Paint.Style.STROKE
                                    strokeCap = android.graphics.Paint.Cap.ROUND
                                }
                                canvas.nativeCanvas.drawPath(path, bgPaint)

                                // measure length
                                val pm = PathMeasure(path, false)
                                val length = pm.length

                                // reveal paint with dash effect using anim value
                                val prog = anim.value.coerceIn(0f, 1f)
                                val phase = length * (1f - prog)
                                val revealPaint = android.graphics.Paint(androidPaint).apply {
                                    color = android.graphics.Color.BLACK
                                    strokeWidth = androidPaint.strokeWidth
                                    style = android.graphics.Paint.Style.STROKE
                                    strokeCap = android.graphics.Paint.Cap.ROUND
                                    pathEffect = DashPathEffect(floatArrayOf(length, length), phase)
                                }

                                canvas.nativeCanvas.drawPath(path, revealPaint)
                            }
                        }

                        Button(
                            onClick = {
                                scope.launch {
                                    anim.snapTo(0f)
                                    anim.animateTo(1f, animationSpec = tween(durationMillis = 1000))
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B)),
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .height(44.dp)
                        ) {
                            Text(text = "Animasi Ulang", color = Color.White)
                        }

                        // launch initial animation
                        LaunchedEffect(Unit) {
                            anim.animateTo(1f, animationSpec = tween(durationMillis = 1000))
                        }
                    }
                }
            }
        }
    }
}
