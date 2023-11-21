package com.ozcanalasalvar.datepicker.compose.timepicker

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.google.android.material.timepicker.TimeFormat
import com.ozcanalasalvar.datepicker.model.Time
import com.ozcanalasalvar.datepicker.view.timepicker.TimePicker

class TimePickerComposeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {


    private val offsetState = mutableStateOf(4)

    private val selectorEffectEnabledState = mutableStateOf(true)
    private val textSizeState = mutableStateOf(17)
    private val darkModeEnabledState = mutableStateOf(true)
    private val timeFormatState = mutableStateOf(TimeFormat.CLOCK_24H)
    private val startTimeState = mutableStateOf(Time(0, 0, "PM"))
    private val startHourState = mutableStateOf(0)
    private val endHourState = mutableStateOf(-1)
    private val minutesStepperState = mutableStateOf(0)

    var offset: Int
        get() = offsetState.value
        set(value) {
            offsetState.value = value
        }
    var selectorEffectEnabled: Boolean
        get() = selectorEffectEnabledState.value
        set(value) {
            selectorEffectEnabledState.value = value
        }

    var textSize: Int
        get() = textSizeState.value
        set(value) {
            textSizeState.value = value
        }

    var darkModeEnabled: Boolean
        get() = darkModeEnabledState.value
        set(value) {
            darkModeEnabledState.value = value
        }

    var timeFormat: Int
        get() = timeFormatState.value
        set(value) {
            timeFormatState.value = value
        }

    var startTime: Time
        get() = startTimeState.value
        set(value) {
            startTimeState.value = value
        }

    var startHour: Int
        get() = startHourState.value
        set(value) {
            startHourState.value = value
        }

    var endHour: Int
        get() = endHourState.value
        set(value) {
            endHourState.value = value
        }

    var minutesStepper: Int
        get() = minutesStepperState.value
        set(value) {
            minutesStepperState.value = value
        }

    private var timeChangeListener: TimePicker.TimeChangeListener? = null
    fun setTimeChangeListener(dataSelectListener: TimePicker.TimeChangeListener?) {
        timeChangeListener = dataSelectListener
    }

    @Suppress("RedundantVisibilityModifier")
    protected override var shouldCreateCompositionOnAttachedToWindow: Boolean = false
        private set

    @Composable
    override fun Content() {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        WheelTimePicker(
            offset = offsetState.value,
            selectorEffectEnabled = selectorEffectEnabledState.value,
            timeFormat = timeFormatState.value,
            startTime = startTimeState.value,
            textSize = textSizeState.value,
            startHour = startHourState.value,
            endHour = endHourState.value,
            minutesStepper = minutesStepperState.value,
            darkModeEnabled = darkModeEnabledState.value,
            onTimeChanged = { hour, minute, format ->
                timeChangeListener?.onTimeChanged(hour, minute, format)
            },
        )
    }


    override fun getAccessibilityClassName(): CharSequence {
        return javaClass.name
    }

}