package maxdistructo.discord.core.command

import java.util.*

interface ICommandRegistry {
    var commandHolder : LinkedList<BaseCommand>
    fun init(listener: IBaseListener)
}