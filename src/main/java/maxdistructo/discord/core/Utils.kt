package maxdistructo.discord.core

import maxdistructo.discord.core.Client.client
import java.io.File
import java.io.IOException
import java.nio.file.Paths

import maxdistructo.discord.core.message.Message
import org.json.*
import sx.blah.discord.handle.obj.*
import java.io.FileWriter

object Utils {
    private val currentRelativePath = Paths.get("")
    val s = currentRelativePath.toAbsolutePath().toString()
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

    fun convertToLong(o: Any): Long? {
        return try {
            java.lang.Long.valueOf(o.toString())
        }
        catch(e : Exception){
            null
        }
    }

    fun convertToInt(`in`: Any): Int {
        return Integer.valueOf(`in`.toString())
    }

    fun toStringArray(array: JSONArray?): Array<String?>? {
        if (array == null)
            return null

        val arr = arrayOfNulls<String>(array.length())
        for (i in arr.indices) {
            arr[i] = array.optString(i)
        }
        return arr
    }

    fun getMentionedChannel(message: IMessage): IChannel? {

        val mentionedChannelList = message.channelMentions
        val mentionedChannelArray = mentionedChannelList.toTypedArray()
        return if (mentionedChannelArray.isNotEmpty()) {
            mentionedChannelArray[0]
        } else {
            null
        }
    }

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

    fun getAttachement(message: IMessage): IMessage.Attachment? {
        var attachments: List<IMessage.Attachment>? = null
        attachments = message.attachments
        return attachments!![0]
    }

    fun getAttachementUrl(message: IMessage): String {
        var attachments: List<IMessage.Attachment>? = null
        attachments = message.attachments
        return attachments!![0].url
    }

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

    fun readJSONFromFile(fileName: String): JSONObject {
        val file = File(s + fileName)
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
           Message.throwError(e)
        }
        return if (tokener != null) {
            JSONObject(tokener)
        } else {
            throw NullPointerException()
        }
    }
    fun writeJSONToFile(fileName: String, json : JSONObject){
        val file = File(s + fileName)
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                Message.throwError(e)
            }
        }
        try {
            FileWriter(s + fileName).use { fileWriter ->
                fileWriter.write(json.toString())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
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
    fun getUserFromInput(input : Any) : IUser?{
        when {
            convertToLong(input) != null -> return client!!.getUserByID(convertToLong(input)!!)
            client!!.getUsersByName(input.toString()).isNotEmpty() -> return client!!.getUsersByName(input.toString(), true)[0]
        }
        return null
    }

}
