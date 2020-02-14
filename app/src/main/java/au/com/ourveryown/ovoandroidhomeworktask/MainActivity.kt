package au.com.ourveryown.ovoandroidhomeworktask

import android.app.ActionBar
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
import com.google.gson.internal.LinkedTreeMap
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var jobListModel: SavedJobVM

    lateinit var layoutManager: LinearLayoutManager
    lateinit var dataAdapter: SavedJobsAdapter

    var page: Int = 1

    var isLoadedTwice: Boolean = false

    var saveJobPaginationList: ArrayList<JobListModel> = ArrayList<JobListModel>()

    var isAdapterCalled: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main
        )
        binding.setLifecycleOwner(this)

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false
        )

        binding.recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false
        )

        val factory = SavedJobViewModelFactory(applicationContext)


        val actionBar = supportActionBar

        actionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        actionBar!!.setCustomView(R.layout.custom_action_bar)



        jobListModel = ViewModelProviders.of(this, factory).get(SavedJobVM::class.java)

        jobListModel.getMutableSavedJobs(page)    // viewModel Call

        jobListModel.listOfSavedJobs.observe(this, Observer(function = fun(productList: ArrayList<JobListModel>?) {
                productList?.let {

                    Log.i("ReachedActivitySize", productList.size.toString())

                    val t = productList.get(0) as LinkedTreeMap<String, String>

                    var getTval = t.get("advertiser").toString()

                    Log.i("getTval", getTval)


                    if (!isAdapterCalled) {
                        isAdapterCalled = true
                        dataAdapter = SavedJobsAdapter(productList, applicationContext)
                        binding.recyclerView.adapter = dataAdapter
                    } else {
                        binding.progressbar.visibility = View.GONE
                        dataAdapter.addData(productList)
                    }


                }
            })
        )


        jobListModel.nextPageLivedata.observe(this, Observer(function = fun(nextPage: Double?) {
            nextPage?.let {

                Log.i("NextPage", nextPage.toString())

                page = nextPage.toInt()

            }
        }))

            //pagination
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!binding.recyclerView.canScrollVertically(1)) {
                    //Log.i("ScrollListener","end reached")
                    if (!isLoadedTwice) {
                        isLoadedTwice = true
                        binding.progressbar.visibility = View.VISIBLE
                        getMoreItems()
                    }
                }

            }


        })


    }

    private fun getMoreItems() {

        jobListModel.getMutableSavedJobs(page)

    }
}
