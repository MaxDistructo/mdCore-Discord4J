package maxdistructo.droidbot2.core

import maxdistructo.droidbot2.core.message.Message
import sx.blah.discord.handle.obj.*
import sx.blah.discord.util.RoleBuilder

import java.awt.*
import java.util.EnumSet

object Roles {
    fun checkForBotAbuse(message: IMessage): Boolean {
        val user = message.author
        val rolesList = user.getRolesForGuild(message.guild)
        val guildRoles = message.guild.getRolesByName("Bot Abuser")
        if (guildRoles.isEmpty()) {
            println("Could not find role Bot Abuser for server " + message.guild.name)
            return false
        }
        val botAbuser = guildRoles[0]
        return rolesList.contains(botAbuser)
    }

    fun checkForPayday(message: IMessage): Boolean {
        val user = message.author
        val rolesList = user.getRolesForGuild(message.guild)
        val guildRoles = message.guild.getRolesByName("Payday")
        if (guildRoles.isEmpty()) {
            println("Could not find role Payday for server " + message.guild.name)
            return false
        }
        val payday = guildRoles[0]
        return rolesList.contains(payday)
    }

    fun applyBotAbuser(message: IMessage, mentioned: IUser) {
        val guildRoles = message.guild.getRolesByName("Bot Abuser")
        if (guildRoles.isEmpty()) {
            println("Could not find role Bot Abuser for server " + message.guild.name)
        } else {
            val role = guildRoles[0]
            mentioned.addRole(role)
        }
    }

    fun applyPayday(message: IMessage, mentioned: IUser) {
        val guild = message.guild
        val channelList = guild.getRolesByName("Payday")
        val role = channelList[0]
        mentioned.addRole(role)
    }

    fun removeBotAbuser(message: IMessage, mentioned: IUser) {
        val guildRoles = message.guild.getRolesByName("Bot Abuser")
        if (guildRoles.isEmpty()) {
            println("Could not find role Bot Abuser for server " + message.guild.name)
        } else {
            val role = guildRoles[0]
            mentioned.removeRole(role)
        }
    }

    fun removePayday(message: IMessage, mentioned: IUser) {
        val guildRoles = message.guild.getRolesByName("Payday")
        val role = guildRoles[0]
        mentioned.removeRole(role)
    }

    fun getRole(message: IMessage, role: String): IRole? {
        val roles = message.guild.getRolesByName(role)
        return if (roles.size != 0) {
            roles[0]
        } else {
            null
        }
    }

    fun copyRolePerms(copy: IRole, paste: IRole) {
        val permissions = copy.permissions
        paste.permissions.addAll(permissions)
        paste.changePermissions(permissions)
    }

    fun applyRole(message: IMessage, mentioned: IUser, role: String) {
        val roleList = message.guild.getRolesByName(role)
        if (roleList.isEmpty()) {
            Message.sendMessage(message.channel, "The role $role was not found.")
            Thread.interrupted()
        }
        if (!roleList.isEmpty()) {
            mentioned.addRole(roleList[0])
        }
    }

    fun applyRole(message: IMessage, mentioned: IUser, roleL: Long) {
        val role = message.guild.getRoleByID(roleL)
        if (role == null) {
            Message.sendMessage(message.channel, "The role $roleL was not found.")
            Thread.interrupted()
        }
        mentioned.addRole(role)
    }

    fun changeRolePerm(message: IMessage, perm: String, role: String) {
        val roleList = message.guild.getRolesByName(role)
        if (roleList == null) {
            Message.sendMessage(message.channel, "The role $role was not found.")
            Thread.interrupted()
        }
        val roleNew = roleList!![0]
        val set = roleNew.permissions
        when (perm) {
            "administrator" -> {
                set.add(Permissions.ADMINISTRATOR)
            }
            "muteUsers" -> {
                set.add(Permissions.VOICE_MUTE_MEMBERS)
            }
            "manageNicknames" -> {
                set.add(Permissions.MANAGE_NICKNAMES)
            }
            "manageRoles" -> {
                set.add(Permissions.MANAGE_ROLES)
            }
            "attachFiles" -> {
                set.add(Permissions.ATTACH_FILES)
            }
            "addReactions" -> {
                set.add(Permissions.ADD_REACTIONS)
            }
            "banMembers" -> {
                set.add(Permissions.BAN)
            }
            "changeOwnUsername" -> {
                set.add(Permissions.CHANGE_NICKNAME)
            }
            "createInvite" -> {
                set.add(Permissions.CREATE_INVITE)
            }
            "embed" -> {
                set.add(Permissions.EMBED_LINKS)
            }
            "kick" -> {
                set.add(Permissions.KICK)
            }
            "manageThisChannel" -> {
                set.add(Permissions.MANAGE_CHANNEL)
            }
            "manageChannels" -> {
                set.add(Permissions.MANAGE_CHANNELS)
            }
            "manageEmojis" -> {
                set.add(Permissions.MANAGE_EMOJIS)
            }
            "manageMessages" -> {
                set.add(Permissions.MANAGE_MESSAGES)
            }
            "managePermissions" -> {
                set.add(Permissions.MANAGE_PERMISSIONS)
            }
            "manageServer" -> {
                set.add(Permissions.MANAGE_SERVER)
            }
            "manageWebhooks" -> {
                set.add(Permissions.MANAGE_WEBHOOKS)
            }
            "mentionEveryone" -> {
                set.add(Permissions.MENTION_EVERYONE)
            }
            "readMessageHistory" -> {
                set.add(Permissions.READ_MESSAGE_HISTORY)
            }
            "readMessages" -> {
                set.add(Permissions.READ_MESSAGES)
            }
            "sendMessages" -> {
                set.add(Permissions.SEND_MESSAGES)
            }
            "sendTTSMessages" -> {
                set.add(Permissions.SEND_TTS_MESSAGES)
            }
            "useExternalEmojis" -> {
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
            }
            "viewAuditLog" -> {
                set.add(Permissions.VIEW_AUDIT_LOG)
            }
            "voiceConnect" -> {
                set.add(Permissions.VOICE_CONNECT)
            }
            "voiceModerator" -> {
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
            }
            "voiceSpeak" -> {
                set.add(Permissions.VOICE_SPEAK)
            }
            "voiceUseVad" -> set.add(Permissions.VOICE_USE_VAD)
        }
        roleNew.changePermissions(set)


    }

    fun changeColor(role: IRole, color: String) {
        val hex: Color
        if (color.contains("#")) {
            hex = Color.decode(color)
        } else {
            hex = Color.decode("#$color")
        }
        role.changeColor(hex)
    }

    fun makeNewRole(guild: IGuild, roleName: String, hoist: Boolean, mentionable: Boolean) {
        val rb = RoleBuilder(guild)
        rb.withName(roleName)
        rb.setHoist(hoist)
        rb.setMentionable(mentionable)
        rb.build()
    }
}

