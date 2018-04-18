package maxdistructo.droidbot2.core.impl

import maxdistructo.droidbot2.core.Config
import maxdistructo.droidbot2.core.impl.Bot
import maxdistructo.droidbot2.core.obj.IPerms
import sx.blah.discord.handle.obj.IGuild
import sx.blah.discord.handle.obj.IMessage
import sx.blah.discord.handle.obj.IUser

class Perms(guild: IGuild, bot: Bot) : IPerms {
    private var privAdmins : List<Long> = Config.readServerAdminConfig(guild)
    private var privMods : List<Long> = Config.readServerModConfig(guild)
    private var privBot : Bot = bot
    private var privGuild : IGuild = guild
    override var admins: List<Long>
        get() = privAdmins
        set(value) {
            privAdmins = value
        }
    override var mods: List<Long>
        get() = privMods
        set(value) {
            privMods = value
        }
    fun isMod(user : IUser) : Boolean{
        for(test in privMods){
            if(test == user.longID){
                return true
            }
        }
        if(isAdmin(user)){
            return true
        }
        return false
    }
    fun isMod(user : Long) : Boolean{
       for (test in privMods){
           if(test == user){
               return true
           }
       }
        if(isAdmin(user)){
            return true
        }
        return false
    }
    fun isMod(message : IMessage) : Boolean{
        for(test in privMods){
            if(test == message.author.longID){
                return true
            }
        }
        if(isAdmin(message)){
            return true
        }
        return false
    }
    fun isAdmin(user : IUser) : Boolean{
        for(test in privAdmins){
            if(test == user.longID){
                return true
            }
        }
        if(isOwnerGuild(user)){
            return true
        }
        return false
    }

    fun isAdmin(user : Long) : Boolean{
        for (test in privAdmins){
            if(test == user){
                return true
            }
        }
        if(isOwnerGuild(user)){
            return true
        }
        return false
    }
    fun isAdmin(message : IMessage) : Boolean{
        for(test in privMods){
            if(test == message.author.longID){
                return true
            }
        }
        if(isOwnerGuild(message)){
            return true
        }
        return false
    }
    fun isOwnerGuild(user: IUser): Boolean {
        if(privBot.client.applicationOwner == user){
            return true
        }
        if(privGuild.owner == user){
            return true
        }
        return false
    }
    fun isOwnerGuild(user: Long): Boolean {
        if(privBot.client.applicationOwner.longID == user){
            return true
        }
        if(privGuild.owner.longID == user){
            return true
        }
        return false
    }
    fun isOwnerGuild(message: IMessage): Boolean {
        if(privBot.client.applicationOwner == message.author){
            return true
        }
        if(privGuild.owner == message.author){
            return true
        }
        return false
    }
    fun isOwner(user : IUser) : Boolean{
        if(privBot.client.applicationOwner == user){
            return true
        }
        return false
    }
    fun isOwner(user : Long) : Boolean{
        if(privBot.client.applicationOwner.longID == user){
            return true
        }
        return false
    }
    fun isOwner(message:IMessage) : Boolean{
        if(privBot.client.applicationOwner == message.author){
            return true
        }
        return false
    }
}