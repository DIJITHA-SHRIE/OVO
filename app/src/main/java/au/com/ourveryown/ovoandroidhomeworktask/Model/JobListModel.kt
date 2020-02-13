package au.com.ourveryown.ovoandroidhomeworktask.Model

import java.io.Serializable

data class JobListModel (
    var jobId:Int,
    var title:String,
    var advertiser:String,
    var location:String,
    var status:String,
    var salary:String,
    var isExternal:String,
    var listedDateUtc:String,
    var savedDateUtc:String,
    var _links:JobLinkModel
):Serializable
