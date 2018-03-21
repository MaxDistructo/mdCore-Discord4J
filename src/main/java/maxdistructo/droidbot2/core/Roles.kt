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
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.MANAGE_NICKNAMES)
                set.add(Permissions.MANAGE_ROLES)
                set.add(Permissions.ATTACH_FILES)
                set.add(Permissions.ADD_REACTIONS)
                set.add(Permissions.BAN)
                set.add(Permissions.CHANGE_NICKNAME)
                set.add(Permissions.CREATE_INVITE)
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "muteUsers" -> {
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.MANAGE_NICKNAMES)
                set.add(Permissions.MANAGE_ROLES)
                set.add(Permissions.ATTACH_FILES)
                set.add(Permissions.ADD_REACTIONS)
                set.add(Permissions.BAN)
                set.add(Permissions.CHANGE_NICKNAME)
                set.add(Permissions.CREATE_INVITE)
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "manageNicknames" -> {
                set.add(Permissions.MANAGE_NICKNAMES)
                set.add(Permissions.MANAGE_ROLES)
                set.add(Permissions.ATTACH_FILES)
                set.add(Permissions.ADD_REACTIONS)
                set.add(Permissions.BAN)
                set.add(Permissions.CHANGE_NICKNAME)
                set.add(Permissions.CREATE_INVITE)
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "manageRoles" -> {
                set.add(Permissions.MANAGE_ROLES)
                set.add(Permissions.ATTACH_FILES)
                set.add(Permissions.ADD_REACTIONS)
                set.add(Permissions.BAN)
                set.add(Permissions.CHANGE_NICKNAME)
                set.add(Permissions.CREATE_INVITE)
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "attachFiles" -> {
                set.add(Permissions.ATTACH_FILES)
                set.add(Permissions.ADD_REACTIONS)
                set.add(Permissions.BAN)
                set.add(Permissions.CHANGE_NICKNAME)
                set.add(Permissions.CREATE_INVITE)
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "addReactions" -> {
                set.add(Permissions.ADD_REACTIONS)
                set.add(Permissions.BAN)
                set.add(Permissions.CHANGE_NICKNAME)
                set.add(Permissions.CREATE_INVITE)
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "banMembers" -> {
                set.add(Permissions.BAN)
                set.add(Permissions.CHANGE_NICKNAME)
                set.add(Permissions.CREATE_INVITE)
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "changeOwnUsername" -> {
                set.add(Permissions.CHANGE_NICKNAME)
                set.add(Permissions.CREATE_INVITE)
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "createInvite" -> {
                set.add(Permissions.CREATE_INVITE)
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "embed" -> {
                set.add(Permissions.EMBED_LINKS)
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "kick" -> {
                set.add(Permissions.KICK)
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "manageThisChannel" -> {
                set.add(Permissions.MANAGE_CHANNEL)
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "manageChannels" -> {
                set.add(Permissions.MANAGE_CHANNELS)
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "manageEmojis" -> {
                set.add(Permissions.MANAGE_EMOJIS)
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "manageMessages" -> {
                set.add(Permissions.MANAGE_MESSAGES)
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "managePermissions" -> {
                set.add(Permissions.MANAGE_PERMISSIONS)
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "manageServer" -> {
                set.add(Permissions.MANAGE_SERVER)
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "manageWebhooks" -> {
                set.add(Permissions.MANAGE_WEBHOOKS)
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "mentionEveryone" -> {
                set.add(Permissions.MENTION_EVERYONE)
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "readMessageHistory" -> {
                set.add(Permissions.READ_MESSAGE_HISTORY)
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "readMessages" -> {
                set.add(Permissions.READ_MESSAGES)
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "sendMessages" -> {
                set.add(Permissions.SEND_MESSAGES)
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "sendTTSMessages" -> {
                set.add(Permissions.SEND_TTS_MESSAGES)
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "useExternalEmojis" -> {
                set.add(Permissions.USE_EXTERNAL_EMOJIS)
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "viewAuditLog" -> {
                set.add(Permissions.VIEW_AUDIT_LOG)
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "voiceConnect" -> {
                set.add(Permissions.VOICE_CONNECT)
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "voiceModerator" -> {
                set.add(Permissions.VOICE_DEAFEN_MEMBERS)
                set.add(Permissions.VOICE_MOVE_MEMBERS)
                set.add(Permissions.VOICE_MUTE_MEMBERS)
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
            }
            "voiceSpeak" -> {
                set.add(Permissions.VOICE_SPEAK)
                set.add(Permissions.VOICE_USE_VAD)
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

