package maxdistructo.droidbot2.core

import java.io.File
import java.io.IOException
import java.net.URISyntaxException
import java.nio.file.Paths
import java.util.ArrayList

import maxdistructo.droidbot2.core.message.Message
import org.json.*
import sx.blah.discord.handle.obj.*

object Utils {

    private val currentRelativePath = Paths.get("")
    val s = currentRelativePath.toAbsolutePath().toString()

    fun restartApplication() {
        val javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java"
        var currentJar: File? = null
        try {
            currentJar = File(Utils::class.java!!.getProtectionDomain().getCodeSource().getLocation().toURI())
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }

        /* is it a jar file? */
        if (!currentJar!!.name.endsWith(".jar"))
            return

        /* Build command: java -jar application.jar */
        val command = ArrayList<String>()
        command.add(javaBin)
        command.add("-jar")
        command.add(currentJar.path)

        val builder = ProcessBuilder(command)
        try {
            builder.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        System.exit(0)
    }

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

    fun convertToLong(o: Any): Long {
        return java.lang.Long.valueOf(o.toString())
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
        val channelMention: IChannel?
        if (mentionedChannelArray.size > 0) {
            channelMention = mentionedChannelArray[0]
        } else {
            channelMention = null
        }
        return channelMention
    }

    fun getMentionedUser(message: IMessage): IUser {

        val mentionedList = message.mentions
        val mentionedArray = mentionedList.toTypedArray()
        val mentioned: IUser?
        if (mentionedArray.size > 0) {
            mentioned = mentionedArray[0]
        } else {
            mentioned = null
        }
        return mentioned!!
    }

    fun getAttachement(message: IMessage): IMessage.Attachment {

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
            Message.sendDM(Client.client!!.applicationOwner, e.toString())
            e.printStackTrace()
        }

        return if (tokener != null) {
            JSONObject(tokener)
        } else {
            throw NullPointerException()
        }
    }

}
