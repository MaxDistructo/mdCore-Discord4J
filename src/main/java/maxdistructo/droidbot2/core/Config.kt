package maxdistructo.droidbot2.core

import maxdistructo.droidbot2.core.message.Message
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import sx.blah.discord.handle.obj.IGuild

import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Scanner

import maxdistructo.droidbot2.core.Utils.s

object Config {

    @Deprecated("", ReplaceWith("Integer.valueOf(`in`.toString())")) //Use Utils.convertToInt instead. Method still exists for legacy support *WILL BE REMOVED IN v1.10 SO STOP USING THIS*
    fun converToInt(`in`: Any): Int {
        return Integer.valueOf(`in`.toString())
    }

    fun readToken(): String {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File("$s/droidbot/config.txt")
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
        val file = File("$s/droidbot/config.txt")
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

    fun readServerModConfig(guild: IGuild): LongArray {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + "/droidbot/config/" + guild.longID + ".txt")
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
        val array = root.getJSONArray("Moderators")
        val longArray = LongArray(array.length())
        println("Created Long Array")
        var i = 0
        while (i < longArray.size) {
            longArray[i] = array.getLong(i)
            i++
        }
        println("Converted JSON array to long Array")
        return longArray

    }

    fun readServerAdminConfig(guild: IGuild): LongArray {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + "/droidbot/config/" + guild.longID + ".txt")
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
        val array = root.getJSONArray("Admins")
        val longArray = LongArray(array.length())
        var i = 0
        while (i < array.length()) {
            longArray[i] = array.getLong(i)
            i++
        }
        return longArray

    }

    fun readServerGamesConfig(guild: IGuild): Array<String?> {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + "/droidbot/config/" + guild.longID + ".txt")
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
        val file = File(s + "/droidbot/config/" + guild.longID + ".txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.sendDM(Client.client!!.applicationOwner, e.toString())
            e.printStackTrace()
        }

        return JSONObject(tokener!!)
    }

    @Deprecated("", ReplaceWith("maxdistructo.droidbot2.core.Utils.convertToLong", "maxdistructo.droidbot2.core.Utils")) //Use Utils.convertToLong instead. This is here for legacy support.
    fun convertToLong(o: Any): Long {
        return java.lang.Long.valueOf(o.toString())
    }

    fun readFileAsList(file: File): List<String>? {
        var lines: List<String>? = null
        try {
            lines = Files.readAllLines(Paths.get(file.toURI()))
        } catch (e: Exception) {
            Message.sendDM(Client.client!!.applicationOwner, e.localizedMessage)
            e.printStackTrace()
        }

        return lines
    }

    fun readModHelp(): Array<String?> {

        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File("$s/droidbot/config.txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.sendDM(Client.client!!.applicationOwner, e.toString())
            e.printStackTrace()
        }

        val root = JSONObject(tokener!!)
        val jsonArray = root.getJSONArray("modHelp")
        val stringsArray = arrayOfNulls<String>(jsonArray.length())
        for (i in 0 until jsonArray.length()) {
            stringsArray[i] = jsonArray.getString(i)
        }
        return stringsArray
    }

    fun readAdminHelp(): Array<String?> {

        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File("$s/droidbot/config.txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.sendDM(Client.client!!.applicationOwner, e.toString())
            e.printStackTrace()
        }

        val root = JSONObject(tokener!!)
        val jsonArray = root.getJSONArray("adminHelp")
        val stringsArray = arrayOfNulls<String>(jsonArray.length())
        for (i in 0 until jsonArray.length()) {
            stringsArray[i] = jsonArray.getString(i)
        }
        return stringsArray
    }

    fun readHelp(): Array<String?> {

        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File("$s/droidbot/config.txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.sendDM(Client.client!!.applicationOwner, e.toString())
            e.printStackTrace()
        }

        val root = JSONObject(tokener!!)
        val jsonArray = root.getJSONArray("help")
        val stringsArray = arrayOfNulls<String>(jsonArray.length())
        for (i in 0 until jsonArray.length()) {
            stringsArray[i] = jsonArray.getString(i)
        }
        return stringsArray
    }

    fun writeHelp(`in`: List<String>) {

        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File("$s/droidbot/config.txt")
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Message.sendDM(Client.client!!.applicationOwner, e.toString())
            e.printStackTrace()
        }

        val root = JSONObject(tokener!!)
        val help = `in`.toTypedArray<String>()
        root.put("help", help)
    }


}


