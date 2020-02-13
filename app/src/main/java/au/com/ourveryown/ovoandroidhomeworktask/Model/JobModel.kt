package au.com.ourveryown.ovoandroidhomeworktask.Model

import java.io.Serializable

data class JobModel (

    var jobs:List<JobListModel>,
    var nextPage:Int

):Serializable

