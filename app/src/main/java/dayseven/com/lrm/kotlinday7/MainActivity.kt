package dayseven.com.lrm.kotlinday7

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import android.support.v4.graphics.drawable.DrawableCompat.clearColorFilter
import android.view.DragEvent
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.View.OnDragListener
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.view.MotionEvent
import android.annotation.SuppressLint
import android.view.View.OnTouchListener







class MainActivity : Activity() {

    private lateinit var mainLayout: ViewGroup
    private lateinit var image: ImageView

    private var xDelta: Int = 0
    private var yDelta: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image= findViewById(R.id.iv_ball) as ImageView
        mainLayout=findViewById(R.id.rl_main) as ViewGroup
        image.setOnTouchListener(onTouchListener())
    }

    private fun onTouchListener(): OnTouchListener {
        return OnTouchListener { view, event ->
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()

            when (event.action and MotionEvent.ACTION_MASK) {

                MotionEvent.ACTION_DOWN -> {
                    val lParams = image.layoutParams as RelativeLayout.LayoutParams

                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin
                }

                MotionEvent.ACTION_UP -> Toast.makeText(this@MainActivity,
                        "thanks for new location!", Toast.LENGTH_SHORT)
                        .show()

                MotionEvent.ACTION_MOVE -> {
                    val layoutParams = image
                            .layoutParams as RelativeLayout.LayoutParams
                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    image.layoutParams = layoutParams
                }
            }
            mainLayout?.invalidate()
            true
        }
    }
}
