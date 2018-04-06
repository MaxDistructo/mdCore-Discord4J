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
}