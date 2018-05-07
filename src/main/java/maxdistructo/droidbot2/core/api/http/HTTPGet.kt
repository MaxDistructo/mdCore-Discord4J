package maxdistructo.droidbot2.core.api.http

import org.apache.commons.io.FileUtils
import org.json.*

import java.io.File
import java.io.IOException
import java.net.URI
import java.net.URL

import maxdistructo.droidbot2.core.Utils.s

object HTTPGet {

    fun from(url: URL): JSONObject { //Input get URL, output JSONObject for manipulation
        val file = File("$s/tmp/get.json")
        file.mkdirs() //Creates new file in the directory specified, Allows for any installation to use this.
        try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            FileUtils.copyURLToFile(url, file) //Get Data from Server
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val uri = file.toURI()//Read TMP file from Server
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
            println("Successfully read file config.txt")
        } catch (e: IOException) {
            //Message.sendDM(Client.client.getApplicationOwner(), e.toString());
            e.printStackTrace()
        }

        file.delete()
        return JSONObject(tokener!!)
    }

}
