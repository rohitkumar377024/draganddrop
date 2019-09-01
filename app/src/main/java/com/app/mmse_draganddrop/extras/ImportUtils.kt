package com.app.mmse_draganddrop.extras

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.regex.Pattern

class ImportUtils(private val ctx: Context) {

    /* Getting All Main Blocks */
    fun regexMainBlock(inputCommand: String): ArrayList<String> {
        //Storing all Main Blocks
        val values = arrayListOf<String>()
        //Left and Right Patterns -> Between which regexMainBlock will get the data
        val patternLeft = "LabelCmd("
        val patternRight = ")"
        //Compiling the pattern -> Pattern.DOTALL Flag makes sure this pattern works on MULTI-LINE
        val p = Pattern.compile(Pattern.quote(patternLeft) + "(.*?)" + Pattern.quote(patternRight), Pattern.DOTALL)
        //Finding All Matches and Adding Them to ArrayList
        val m = p.matcher(inputCommand)
        while (m.find()) { values.add(m.group(1)) }
        //Returning the ArrayList of Matches
        return values
    }

    //TODO -> THIS WILL RETURN ALL THE IMPORTED LAYOUT FOR NOW -> returns list pair of view+params -- logically correct accidentally - lel
    @SuppressLint("DefaultLocale") fun processLabelsIndividually(labelIndividually: String): ArrayList<Pair<View, RelativeLayout.LayoutParams>> {
        val viewPlusParamsList: ArrayList<Pair<View, RelativeLayout.LayoutParams>> = arrayListOf()

        val propertyValueList = labelIndividually.split(",")

        var labelText = "" //Will store Text value for Label
        var labelTextSize = 0f //Will store Text Size value for Label
        var labelFontWeight = "" //Will store Font Weight value for Label

        var positionTop = 0 //Will store Top Position for Label
        var positionLeft = 0 //Will store Left Position for Label

        var dimensionWidth = 0 //Will store Width Dimension for Label
        var dimensionHeight = 0 //Will store Height Dimension for Label

        propertyValueList.forEach {
            val propertyValueSplitted = it.split("=")

            when (propertyValueSplitted[0].trim().toLowerCase()) {
                "text" -> labelText = propertyValueSplitted[1]
                "textsize" -> labelTextSize = propertyValueSplitted[1].toFloat()
                "fontweight" -> labelFontWeight = propertyValueSplitted[1].toLowerCase()
                "top" -> positionTop = propertyValueSplitted[1].toInt()
                "left" -> positionLeft = propertyValueSplitted[1].toInt()
                "width" -> dimensionWidth = propertyValueSplitted[1].toInt()
                "height" -> dimensionHeight = propertyValueSplitted[1].toInt()
            }
        }

        //Create New TextView
        val view = TextView(ctx).apply {
            text = labelText
            textSize = labelTextSize
            gravity = Gravity.CENTER
            typeface = when (labelFontWeight) {
                "thin" -> Utils(ctx).typefaces[Utils.TYPEFACE_THIN]
                "light" -> Utils(ctx).typefaces[Utils.TYPEFACE_LIGHT]
                "medium" -> Utils(ctx).typefaces[Utils.TYPEFACE_MEDIUM]
                "bold" -> Utils(ctx).typefaces[Utils.TYPEFACE_BOLD]
                else -> null
            }
        }

        //val a = listOf<Pair<View, RelativeLayout.LayoutParams>>()

        //Creating LayoutParams As Per Assigned Width, Height, Top and Left
        val params = RelativeLayout.LayoutParams(Utils(ctx).dpToPx(dimensionWidth), Utils(ctx).dpToPx(dimensionHeight))

        val statusBarSize = Utils(ctx).dpToPx(25) //todo -> hardcoding status bar value to 25dp currently
        val actionBarBarSize = Utils(ctx).dpToPx(56)
        val toolboxSize = Utils(ctx).dpToPx(60)

        params.topMargin = Utils(ctx).dpToPx(positionTop) - (statusBarSize + actionBarBarSize)
        params.leftMargin = Utils(ctx).dpToPx(positionLeft) - (toolboxSize)

        viewPlusParamsList.add(Pair(view, params))

        Log.d("13x21", "Text -> $labelText, Text Size -> $labelTextSize, Font Weight -> $labelFontWeight, " +
                "Top -> $positionTop, Left -> $positionLeft, Width -> $dimensionWidth, Height -> $dimensionHeight")

        return viewPlusParamsList
    }

}
