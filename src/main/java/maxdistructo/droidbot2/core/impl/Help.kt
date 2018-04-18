package maxdistructo.droidbot2.core.impl

import maxdistructo.droidbot2.core.obj.IHelp

class Help : IHelp {

    private lateinit var basicHelpHolder: MutableList<String>
    private lateinit var modHelpHolder: MutableList<String>
    private lateinit var adminHelpHolder: MutableList<String>

    override var adminHelp: MutableList<String>
        get() = adminHelpHolder
        set(value) {
            adminHelpHolder = value
        }
    override var basicHelp: MutableList<String>
        get() = basicHelpHolder
        set(value) {
            basicHelpHolder = value
        }
    override var modHelp: MutableList<String>
        get() = modHelpHolder
        set(value) {
            modHelpHolder = value
        }
    fun addAdminHelp(input : String){
        adminHelpHolder.add(input)
    }
    fun addModHelp(input : String){
        modHelpHolder.add(input)
    }
    fun addHelp(input : String){
        basicHelpHolder.add(input)
    }
    fun adminHelpString(prefix : String) : String{
        val sb = StringBuilder()
        for (s in basicHelpHolder){
            sb.append(prefix + s + "\n")
        }
        for (s in modHelpHolder){
            sb.append(prefix + s + "\n")
        }
        for (s in adminHelpHolder){
            sb.append(prefix + s + "\n")
        }
        return sb.toString()
    }
    fun modHelpString(prefix : String) : String{
        val sb = StringBuilder()
        for (s in basicHelpHolder){
            sb.append(prefix + s + "\n")
        }
        for (s in modHelpHolder){
            sb.append(prefix + s + "\n")
        }
        return sb.toString()
    }
    fun helpString(prefix : String) : String{
        val sb = StringBuilder()
        for (s in basicHelpHolder){
            sb.append(prefix + s + "\n")
        }
        return sb.toString()
    }
}