package au.com.ourveryown.ovoandroidhomeworktask.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.ourveryown.ovoandroidhomeworktask.Model.JobListModel
import au.com.ourveryown.ovoandroidhomeworktask.Model.JobModel
import au.com.ourveryown.ovoandroidhomeworktask.SavedJobsClient

class SavedJobVM(context: Context) : ViewModel() {
    var listOfSavedJobs = MutableLiveData<ArrayList<JobListModel>>()    // for Jobs
    var nextPageLivedata = MutableLiveData<Double>()                    //for nextpage
    lateinit var context: Context
    lateinit var repository: SavedJobsClient


    init {
        this.context = context
        listOfSavedJobs.value = arrayListOf()
        repository = SavedJobsClient(context)

    }


    fun getMutableSavedJobs(page: Int?) {

        repository.getSavedJobs(page, ::completion)         //assetCall

    }

    fun completion(savedJobs: Map<String, Any>?, error: Exception?) {


        var jobList = ArrayList<JobListModel>()

        jobList = savedJobs!!.get("jobs") as ArrayList<JobListModel>

        if (savedJobs!!.containsKey("nextPage")) {
            nextPageLivedata.value = savedJobs!!.get("nextPage") as Double
        } else {
            nextPageLivedata.value = 3.0           //assigned a dummy val
        }


        listOfSavedJobs.value = jobList


    }

}