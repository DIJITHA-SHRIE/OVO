package au.com.ourveryown.ovoandroidhomeworktask.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.ourveryown.ovoandroidhomeworktask.Model.JobListModel
import au.com.ourveryown.ovoandroidhomeworktask.Model.JobModel
import au.com.ourveryown.ovoandroidhomeworktask.SavedJobsClient

class SavedJobVM(context: Context): ViewModel() {
    var listOfSavedJobs = MutableLiveData< List<JobListModel>>()
    lateinit var context: Context
    lateinit var repository:SavedJobsClient


    init{
        this.context = context
        listOfSavedJobs.value = listOf()
        repository = SavedJobsClient(context)

    }

    init {
        listOfSavedJobs.value = listOf()
    }



    fun getMutableSavedJobs(){

        repository.getSavedJobs(1,::completion)

    }

    fun completion (savedJobs:Map<String, Any>?,error:Exception?){
        Log.i("Reached",savedJobs.toString())
        var jobList = ArrayList<JobListModel>()

        jobList = savedJobs!!.get("jobs") as ArrayList<JobListModel>

        Log.i("joblistSize",jobList.size.toString())

        /*var mapSavedJob: MutableMap<String,List<JobListModel>> = mutableMapOf()
        mapSavedJob.put("jobs",savedJobs!!.get("jobs") as List<JobListModel> )
*/

        //listOfSavedJobs.value = mapSavedJob!!.get("jobs") as List<JobListModel>

        listOfSavedJobs.value = jobList


    }

}