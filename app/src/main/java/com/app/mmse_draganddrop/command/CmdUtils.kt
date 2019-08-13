package com.app.mmse_draganddrop.command

import java.util.regex.Pattern

class CmdUtils {

    //Gets all the Main Blocks used In Command
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
        while (m.find()) { values.add(m.group(1)) }

        //Returning the ArrayList of Matches
        return values
    }

}