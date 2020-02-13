package au.com.ourveryown.ovoandroidhomeworktask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.ourveryown.ovoandroidhomeworktask.Model.JobListModel
import au.com.ourveryown.ovoandroidhomeworktask.ViewModel.SavedJobVM
import au.com.ourveryown.ovoandroidhomeworktask.ViewModel.SavedJobViewModelFactory
import au.com.ourveryown.ovoandroidhomeworktask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

     lateinit var jobListModel: SavedJobVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.setLifecycleOwner(this)
        binding.recyclerView!!.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        val factory = SavedJobViewModelFactory(applicationContext)

        jobListModel = ViewModelProviders.of(this,factory).get(SavedJobVM::class.java)

        jobListModel.getMutableSavedJobs()

        jobListModel.listOfSavedJobs.observe(this, Observer(function = fun(productList:  List<JobListModel>?) {
            productList?.let {

               Log.i("ReachedActivity","OMG")
                Log.i("ReachVAlue",productList.get(0).advertiser)

            }
        }))





    }
}
