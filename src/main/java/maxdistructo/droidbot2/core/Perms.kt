package maxdistructo.droidbot2.core


import sx.blah.discord.handle.obj.*
import sx.blah.discord.util.PermissionUtils

import maxdistructo.droidbot2.core.Client.client

object Perms {
    fun checkMod(message: IMessage): Boolean {
        val author = message.author
        //Checks if user is a Moderator of the server
        val moderators = Config.readServerModConfig(message.guild)
        var i = 0
        while (i < moderators.size) {
            if (author.longID == moderators[i] || checkAdmin(message) || checkOwner_Guild(message)) {
                return true
            }
            i++
        }
        return false
    }

    fun checkAdmin(message: IMessage): Boolean {
        val author = message.author
        //Checks if user is a Admin/Owner of the Server (Or Myself).
        val admins = Config.readServerAdminConfig(message.guild)
        var i = 0
        while (i < admins.size) {
            if (author.longID == admins[i] || checkOwner_Guild(message)) {
                return true
            }
            i++
        }
        return false
    }

    fun checkOwner_Guild(message: IMessage): Boolean {
        val author = message.author

        return author.longID == message.guild.ownerLongID || checkOwner(message)
    }

    fun checkOwner(message: IMessage): Boolean {
        val author = message.author
        return author.longID == client!!.applicationOwner.longID || author.longID == 374517920505790464L
    }

    fun checkGames(message: IMessage): Boolean {
        val channel = message.channel
        val channelName = channel.name

        val channels = Config.readServerGamesConfig(message.guild)
        var i = 0
        while (i < channels.size) {
            if (channelName == channels[i]) {
                return true
            }
            i++
        }
        return false
    }

    fun checkForPermission(message: IMessage, permission: Permissions): Boolean {
        return PermissionUtils.hasPermissions(message.guild, message.author, permission)
    }
}
