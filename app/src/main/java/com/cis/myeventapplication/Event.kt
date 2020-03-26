package com.cis.myeventapplication

class Event(val event_id:String,val nameEvent:String,val detailEvent: String,val typeEvent:String,val amount:String,val dateofstart:String,val dateofend:String) {}


class Students(val student_id:String,val name_student:String){}
class StudentEvent(val event_name: String,val student_name: ArrayList<String>)

class ToDo {
    companion object Factory {
        fun create(): ToDo = ToDo()
    }

    var name: String? = null
    var object_id : String? = null

}