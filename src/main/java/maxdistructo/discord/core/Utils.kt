package maxdistructo.discord.core

import maxdistructo.discord.core.Client.client
import java.io.File
import java.io.IOException
import java.nio.file.Paths

import maxdistructo.discord.core.message.Message
import org.json.*
import sx.blah.discord.handle.obj.*

object Utils {
    private val currentRelativePath = Paths.get("")
    val s = currentRelativePath.toAbsolutePath().toString()
    
    /**
    *   Creates a new String using the provided Array and a position to start at. This start position is based on the number in the array.
    *   Ex. To cut out the first value in an array and make it into a string, input 1 for startAt
    *   @param input The array to draw from
    *   @param startAt The value to start from
    *   @return A string built off of all the values the array after the specified value.
    */
    fun makeNewString(input: Array<Any>, startAt: Int): String {
        val stringBuilder = StringBuilder()
        var i = startAt
        while (i < input.size) {
            if (i - 1 == input.size) {
                stringBuilder.append(input[i])
            } else {
                stringBuilder.append(input[i])
                stringBuilder.append(" ")
            }
            i++
        }
        return stringBuilder.toString()
    }
    
    /**
    *   Converts the input to a Long value. Returns null if the convert fails.
    *   @param o The object to try and convert to Long
    *   @return The long value of the input or null if it is unable to be converted
    */

    fun convertToLong(o: Any): Long? {
        return try {
            java.lang.Long.valueOf(o.toString())
        }
        catch(e : Exception){
            null
        }
    }

    /**
    *   Converts the input to an Int value. Returns null if the convert fails or is impossible.
    *   @param in The object to convert
    *   @return The converted object or null
    */
    
    fun convertToInt(`in`: Any): Int {
        return Integer.valueOf(`in`.toString())
    }
    
    /**
    *   Converts the inputed @org.json.JSONArray to an Array of Strings 
    *   @param array The JSON array to convert to String
    *   @return The string array or null if the convert is impossible or fails
    */
    
    fun toStringArray(array: JSONArray?): Array<String?>? {
        if (array == null)
            return null

        val arr = arrayOfNulls<String>(array.length())
        for (i in arr.indices) {
            arr[i] = array.optString(i)
        }
        return arr
    }
    
    /**
    *   Gets the mentioned channel from the IMessage
    *   @param message The message 
    *   @return The mentioned channel or null if impossible
    */
    
    fun getMentionedChannel(message: IMessage): IChannel? {

        val mentionedChannelList = message.channelMentions
        val mentionedChannelArray = mentionedChannelList.toTypedArray()
        return if (mentionedChannelArray.isNotEmpty()) {
            mentionedChannelArray[0]
        } else {
            null
        }
    }
    
    /**
    *   Gets the mentioned user from the IMessage
    *   @param message The message 
    *   @return The mentioned user or null if impossible
    */

    fun getMentionedUser(message: IMessage): IUser? {
        val mentionedList = message.mentions
        val mentionedArray = mentionedList.toTypedArray()
        val mentioned: IUser?
        if (mentionedArray.isNotEmpty()) {
            mentioned = mentionedList[0]
        } else {
            mentioned = null
        }
        return mentioned
    }
    
    /**
    *   Gets the attachment from the IMessage
    *   @param message The message 
    *   @return The attachment or null if impossible
    */

    fun getAttachement(message: IMessage): IMessage.Attachment? {
        var attachments: List<IMessage.Attachment>? = null
        attachments = message.attachments
        return attachments!![0]
    }
    
    /**
    *   Gets the url for the attachment
    *   @param message The message 
    *   @return The url for the attachement or null if impossible
    */

    fun getAttachementUrl(message: IMessage): String {
        var attachments: List<IMessage.Attachment>? = null
        attachments = message.attachments
        return attachments!![0].url
    }
    
    /**
    *   Similar to #makeNewString but puts new lines between the values instead of spaces
    *   @param input The array to convert
    *   @param startAt The spot in the array to start reading at
    *   @return The String with new lines
    */

    fun makeNewLineString(input: Array<String?>, startAt: Int): String {
        val stringBuilder = StringBuilder()
        var i = startAt
        while (i < input.size) {
            stringBuilder.append(input[i])
            stringBuilder.append("\n")
            i++
        }
        return stringBuilder.toString()
    }
    
    /**
    *   Reads the JSON from the specified file
    *   @param fileName The path to the file in relation to the location of the running directory
    *   @return The JSON object from the file.
    */

    fun readJSONFromFile(fileName: String): JSONObject {

        val file = File(s + fileName)

        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.sendDM(Client.client!!.applicationOwner, e.toString())
            e.printStackTrace()
        }

        return if (tokener != null) {
            JSONObject(tokener)
        } else {
            throw NullPointerException()
        }
    }
    
    /**
    *   Attempts to get a user from the input if a mentioned user is not found. This is accomplished by comparing the input to the Debug ID of a user, the actual Account name of the User, and finally checking if it is a fragment of the name of the user.
    *   @param message The message 
    *   @param input The name/longID/namefragement for the user
    *   @return The user that was mentioned or matches one of the characteristics
    */
    
    fun getUserFromInput(message : IMessage, input : Any) : IUser?{
        when {
            getMentionedUser(message) != null -> return getMentionedUser(message)
            convertToLong(input) != null -> return message.guild.getUserByID(convertToLong(input)!!)
            message.guild.getUsersByName(input.toString()).isNotEmpty() -> return message.guild.getUsersByName(input.toString(), true)[0]
            else ->
                    for(user in message.guild.users) {
                        if (user.getDisplayName(message.guild).contains(input.toString())) {
                            return user
                        }
                        else if(user.name.contains(input.toString())){
                            return user
                        }
                    }
        }
        return null
    }
    
    /**
    *   Similar to the above method but does not have the message requirement and as such can not check as much
    *   @param input The name/longID of a user
    *   @return The user
    */
    
    fun getUserFromInput(input : Any) : IUser?{
        when {
            convertToLong(input) != null -> return client!!.getUserByID(convertToLong(input)!!)
            client!!.getUsersByName(input.toString()).isNotEmpty() -> return client!!.getUsersByName(input.toString(), true)[0]
        }
        return null
    }

}
