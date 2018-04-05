package maxdistructo.droidbot2.core.impl

import maxdistructo.droidbot2.core.obj.IHelp

object Help() : IHelp{

private val basicHelpHolder : List<String>
private val modHelpHolder : List<String>
private val adminHelpHolder : List<String>

override val basicHelp : String{
  var sb = StringBuilder()
    sb.add("Command List: \n")
  for(line in basicHelpHolder){
    sb.add(line + "\n")
  }
  get() = sb.build()
}

override val modHelp : List<String>{
  get() = modHelpHolder
}

override val adminHelp : List<String>{
  get() = adminHelpHolder
}

fun addBasicHelp(help : String){
  basicHelpHolder.add(help)
}

fun addModHelp(help : String){
  modHelpHolder.add(help)
}

fun addAdminHelp(help : String){
  adminHelpHolder.add(help)
}
