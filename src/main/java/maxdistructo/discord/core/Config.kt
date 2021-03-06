package maxdistructo.discord.core

import maxdistructo.discord.core.message.Message
import org.json.JSONObject
import org.json.JSONTokener
import sx.blah.discord.handle.obj.IGuild

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @object Config
 * @description This class contains methods for getting and writing values to the local config files.
 * @author MaxDistructo
 */

object Config {

    @Deprecated("", ReplaceWith("Integer.valueOf(`in`.toString())")) //Use Utils.convertToInt instead. Method still exists for legacy support *WILL BE REMOVED IN v1.10 SO STOP USING THIS*
    fun converToInt(`in`: Any): Int {
        return Integer.valueOf(`in`.toString())
    }

    fun readToken(): String {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File("$s/config/config.txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
            println("Successfully read file config.txt")
        } catch (e: IOException) {
            //Message.sendDM(Client.client.getApplicationOwner(), e.toString());
            e.printStackTrace()
        }

        val root = JSONObject(tokener!!)
        println("Converted JSON file to JSONObject")
        return root.getString("Token")

    }

    fun readPrefix(): String {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File("$s/config/config.txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.sendDM(Client.client!!.applicationOwner, e.toString())
            e.printStackTrace()
        }

        val root = JSONObject(tokener!!)
        return root.getString("Prefix")

    }

    fun readServerModConfig(guild: IGuild): List<Long> {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + "/config/guild/" + guild.longID + ".txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.throwError(e)
        }

        val root = JSONObject(tokener!!)
        val array = root.getJSONArray("Moderators")
        val longArray = LongArray(array.length())
        println("Created Long Array")
        var i = 0
        while (i < longArray.size) {
            longArray[i] = array.getLong(i)
            i++
        }
        return longArray.toList()

    }

    fun readServerAdminConfig(guild: IGuild): List<Long> {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + "/config/guild/" + guild.longID + ".txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.throwError(e)
        }

        val root = JSONObject(tokener!!)
        val array = root.getJSONArray("Admins")
        val longArray = LongArray(array.length())
        var i = 0
        while (i < array.length()) {
            longArray[i] = array.getLong(i)
            i++
        }
        return longArray.toList()

    }

    fun readServerGamesConfig(guild: IGuild): Array<String?> {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + "/config/guild/" + guild.longID + ".txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
            println("Successfully read file " + guild.longID + ".txt")
        } catch (e: IOException) {
            Message.sendDM(Client.client!!.applicationOwner, e.toString())
            e.printStackTrace()
        }

        val root = JSONObject(tokener!!)
        println("Converted JSON file to JSONObject")
        val array = root.getJSONArray("GameChannels")
        val longArray = arrayOfNulls<String>(array.length())
        var i = 0
        while (i < array.length()) {
            longArray[i] = array.getString(i)
            i++
        }
        return longArray

    }

    fun readServerConfig(guild: IGuild): JSONObject {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + "/config/guild/" + guild.longID + ".txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.throwError(e)
        }

        return JSONObject(tokener!!)
    }

    @Deprecated("", ReplaceWith("Utils.convertToLong(o)", "maxdistructo.discord.core.Utils.convertToLong", "maxdistructo.discord.core.Utils")) //Use Utils.convertToLong instead. This is here for legacy support.
    fun convertToLong(o: Any): Long {
        return java.lang.Long.valueOf(o.toString())
    }

    fun readFileAsList(file: File): List<String>? {
        var lines: List<String>? = null
        try {
            lines = Files.readAllLines(Paths.get(file.toURI()))
        } catch (e: Exception) {
            Message.throwError(e)
        }

        return lines
    }
}


