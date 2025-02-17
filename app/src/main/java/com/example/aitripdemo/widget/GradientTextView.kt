package com.example.aitripdemo.widget

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.aitripdemo.R

class GradientTextView : AppCompatTextView {

  private var startColor: Int = ContextCompat.getColor(context, R.color.black)
  private var endColor: Int = ContextCompat.getColor(context, R.color.white)
  private var baseTextColor: Int = ContextCompat.getColor(context, android.R.color.black)
  private var angle: Float = 0f

  constructor(context: Context) : super(context) {
    init()
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    init(attrs)
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    init(attrs)
  }

  private fun init(attrs: AttributeSet? = null) {
    if (attrs != null) {
      val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView)
      startColor = typedArray.getColor(R.styleable.GradientTextView_startColor, startColor)
      endColor = typedArray.getColor(R.styleable.GradientTextView_endColor, endColor)
      baseTextColor = typedArray.getColor(R.styleable.GradientTextView_baseTextColor, baseTextColor)
      angle = typedArray.getFloat(R.styleable.GradientTextView_angle, angle)
      typedArray.recycle()
    }

    updateGradient()
  }

  private fun updateGradient() {
    setTextColor(baseTextColor)

    val x0: Float
    val y0: Float
    val x1: Float
    val y1: Float

    when (angle) {
      0f -> {   // 0 degrees: The gradient transitions from left to right.
        x0 = 0f
        y0 = 0f
        x1 = width.toFloat()
        y1 = 0f
      }
      90f -> {  // 90 degrees: The gradient transitions from bottom to top.
        x0 = 0f
        y0 = 0f
        x1 = 0f
        y1 = height.toFloat()
      }
      180f -> {  // 180 degrees: The gradient transitions from right to left.
        x0 = width.toFloat()
        y0 = 0f
        x1 = 0f
        y1 = 0f
      }
      270f -> {  // 270 degrees: The gradient transitions from top to bottom.
        x0 = 0f
        y0 = height.toFloat()
        x1 = 0f
        y1 = 0f
      }
      else -> {
        x0 = 0f
        y0 = 0f
        x1 = width.toFloat()
        y1 = 0f
      }
    }

    val textShader = LinearGradient(x0, y0, x1, y1, startColor, endColor, Shader.TileMode.CLAMP)
    paint.shader = textShader
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    updateGradient()
  }
}