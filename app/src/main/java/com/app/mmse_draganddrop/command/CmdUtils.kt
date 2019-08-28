package com.app.mmse_draganddrop.command

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.app.draganddrop.R
import com.app.mmse_draganddrop.Utils
import java.util.regex.Pattern

class CmdUtils(private val ctx: Context) {

    /* Getting All Main Blocks */
    fun regexMainBlock(inputCommand: String): ArrayList<String> {
        //Storing all Main Blocks
        val values = arrayListOf<String>()

        //Left and Right Patterns -> Between which regexMainBlock will get the data
        val patternLeft = "<"
        val patternRight = ">"

        //Compiling the pattern -> Pattern.DOTALL Flag makes sure this pattern works on MULTI-LINE
        val p = Pattern.compile(Pattern.quote(patternLeft)
                    + "(.*?)" + Pattern.quote(patternRight), Pattern.DOTALL)

        //Finding All Matches and Adding Them to ArrayList
        val m = p.matcher(inputCommand)
        while (m.find()) { values.add(m.group( 1)) }

        //Returning the ArrayList of Matches
        return values
    }

    /* Processing Each Main Block */
    @SuppressLint("DefaultLocale")
    fun processMainBlock(root: RelativeLayout, mainBlock: String) {
        //Splitting Each Main Block in Lines
        val propertyValueList = mainBlock.lines()

        //Getting the type -> It is always the first line in Main Block
        //Substring from start to ':' //Example -> 'Label:' returns 'Label'
        val type = propertyValueList[0].substring(0, propertyValueList[0].indexOf(":"))

        //Making it Lowercase so that one condition only needed //Eg. for Label, only 'label' check is needed then
        when (type.toLowerCase()) {
            "label" -> {
                var text = "" //Will store Text value for Label
                var textSize = 0f //Will store Text Size value for Label
                var fontWeight = "" //Will store Font Weight value for Label

                var positionTop = 0 //Will store Top Position for Label
                var positionLeft = 0 //Will store Left Position for Label

                var dimensionWidth = 0 //Will store Width Dimension for Label
                var dimensionHeight = 0 //Will store Height Dimension for Label

                //Looping through All Property-Value pairs
                for (x in 1 until propertyValueList.size) {
                    //Getting a Specific Property-Value Pair
                    val specificPropertyValue = propertyValueList[x].trim()
                    //Splitting it -> [0] is Property Name and [1] is Value
                    val propertyValueSplitted = specificPropertyValue.split("=")

                    //Checking Which Property It Is
                    when(propertyValueSplitted[0].toLowerCase()) { //Checking Property Name by [0] -> Value is stored at [1]
                        "text" -> text = propertyValueSplitted[1]
                        "textsize" -> textSize = propertyValueSplitted[1].toFloat()
                        "fontweight" -> fontWeight = propertyValueSplitted[1].toLowerCase()
                        "position" -> {
                            val positionValueSplitted = propertyValueSplitted[1].split(",")
                            positionValueSplitted.forEach {
                                val positionTopLeftSplitted = it.split(":")
                                when(positionTopLeftSplitted[0].trim()) {
                                    "top" ->  positionTop = positionTopLeftSplitted[1].toInt()
                                    "left" ->  positionLeft = positionTopLeftSplitted[1].toInt()
                                }
                            }
                        }
                        "dimension" -> {
                            val dimensionValueSplitted = propertyValueSplitted[1].split(",")
                            dimensionValueSplitted.forEach {
                                val dimensionWidthHeightSplitted = it.split(":")
                                when(dimensionWidthHeightSplitted[0].trim()) {
                                    "width" ->  dimensionWidth = dimensionWidthHeightSplitted[1].toInt()
                                    "height" ->  dimensionHeight = dimensionWidthHeightSplitted[1].toInt()
                                }
                            }
                        }
                    }
                }
                //Create Label with Assigned Values Above
                createLabel(root, text, textSize, fontWeight, positionTop, positionLeft, dimensionWidth, dimensionHeight)
            }
        }
    }

    /* Creates a Label */
    private fun createLabel(root: RelativeLayout /* root -> Existing Relative Layout */,
                    textInput: String, textSizeInput: Float, fontWeight: String,
                    topInput: Int, leftInput: Int, widthInput: Int, heightInput: Int) {

        //Create New TextView
        val tv = TextView(ctx).apply {
            text = textInput
            textSize = textSizeInput
            gravity = Gravity.CENTER
            typeface = when (fontWeight) {
                "thin" -> Utils(ctx).typefaces[Utils.TYPEFACE_THIN]
                "light" -> Utils(ctx).typefaces[Utils.TYPEFACE_LIGHT]
                "medium" -> Utils(ctx).typefaces[Utils.TYPEFACE_MEDIUM]
                "bold" -> Utils(ctx).typefaces[Utils.TYPEFACE_BOLD]
                else -> null
            }
            setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorRandomBlue)) //todo -> remove later
        }

        //Creating LayoutParams As Per Assigned Width, Height, Top and Left
        val params = RelativeLayout.LayoutParams(Utils(ctx).dpToPx(widthInput), Utils(ctx).dpToPx(heightInput))
        params.topMargin = Utils(ctx).dpToPx(topInput)
        params.leftMargin = Utils(ctx).dpToPx(leftInput)

        //Adding the Newly Created TextView
        root.addView(tv, params)
    }

}