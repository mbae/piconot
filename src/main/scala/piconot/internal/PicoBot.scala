package piconot.internal
package object globalFunctions {
  def goChangeState(newState : Int, newDir : Direction) = 
  {
    
  }
}
case class OurRule (val state : OurState, val cond : Conditions){
  def -> (actions : String) = 
  {// parse string to goChangeState
    actions
  }
}

case class OurState (val s: Int)
{
  
}

case class Direction (val dir : Char)
{

}

case class Conditions (val blocked : List[Boolean], 
    val dir : List[Char])
{
  
}

object PicoBotExtender {
	def conditionConverterHelp(value : String, blockedBools : List[Boolean]
			, dirList : List[Char]) : Conditions =
			if (value.size == 0)
				Conditions(blockedBools, dirList)
			else if (value.charAt(0) == '~')
				conditionConverterHelp(value.tail.tail, true :: blockedBools, value.charAt(1) :: dirList)
			else
				conditionConverterHelp(value.tail, false :: blockedBools, value.charAt(0) :: dirList)

   implicit def StringToOurRule(value : String) : OurRule =
   {
		 // From a string like 0N~S will convert it to an appropriate OurRule object
		 // which represents the rule for state 0 when north is free and south is blocked
     OurRule(OurState(value.charAt(0).toInt), conditionConverterHelp(value.tail, List(), List()))
   }
}